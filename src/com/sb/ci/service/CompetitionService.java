package com.sb.ci.service;

import java.sql.Connection;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sb.ci.model.Competition;
import com.sb.ci.model.Game;
import com.sb.database.ConnectionManager;
import com.sb.database.Find;
import com.sb.database.Insert;
import com.sb.database.Query;
import com.sb.database.Update;

@Path("/competition")
public class CompetitionService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompetitions() {

		Collection output = null;
		Query query = null;

		try {
			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);
			Find find = new Find(query);

			output = find.findEverything("competition");

			query.close();
		} catch (Throwable e) {
			e.printStackTrace();
			if (query != null) {
				query.close();
			}

			return Response.serverError().entity(e.getCause()).build();
		}

		return Response.ok(output).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertCompetition(Competition competition) {

		Response result = null;
		Query query = null;

		try {

			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);
			Find find = new Find(query);

			Collection<Game> data = find.findSomething("game", "year", competition.getYear());

			if (data != null && data.size() > 0) {
				Game game = data.iterator().next();
				competition.setGameId(game.getGameId());

				if (competition.getCompetitionOrder() != null && !competition.getCompetitionOrder().trim().equals("")) {

					Insert insert = new Insert(query);
					String id = insert.insertSomething(competition);
					result = Response.ok(id).build();
				} else {
					result = Response.ok("Competition Order must be provided").build();
				}
			} else {
				result = Response.ok("Game not found, either Game Year is incorrect or not set").build();
			}

			query.close();
		} catch (Throwable e) {
			e.printStackTrace();
			if (query != null) {
				query.close();
			}

			return Response.serverError().entity(e.getCause()).build();
		}

		return result;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCompetition(Competition competition) {

		Response result = null;
		Query query = null;
		
		try{

		Connection connection = ConnectionManager.getConnection();
		query = new Query(connection);

		Update update = new Update(query);
		String id = update.updateSomething(competition);
		result = Response.ok(id).build();

		query.close();
		} catch (Throwable e) {
			e.printStackTrace();
			if(query != null){
				query.close();
			}
			
			return Response.serverError().entity(e.getCause()).build();
		}
		return result;
	}

}
