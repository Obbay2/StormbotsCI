package com.sb.ci.service;

import java.sql.Connection;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sb.ci.model.Team;
import com.sb.database.ConnectionManager;
import com.sb.database.Find;
import com.sb.database.Insert;
import com.sb.database.Query;
import com.sb.database.Update;

@Path("/team")
public class TeamService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeams() {

		Collection output = null;
		Query query = null;

		try {

			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);
			Find find = new Find(query);

			output = find.findEverything("team");

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

	@GET
	@Path("/{number}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeam(@PathParam("number") String teamNumber) {

		Query query = null;
		Collection<Team> output = null;

		try {

			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);
			Find find = new Find(query);

			output = find.findSomething("team", "teamNumber", teamNumber);

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
	public Response insertTeam(Team team) {

		Response result = null;
		Query query = null;

		try {
			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);
			Find find = new Find(query);

			Collection test = find.findSomething("team", "teamNumber", team.getTeamNumber());

			if (test == null || test.size() == 0) {
				if (team.getTeamNumber() == null || team.getTeamNumber().equals("")) {
					result = Response.ok("Did not specify a teamNumber").build();
				} else {
					Insert insert = new Insert(query);
					String id = insert.insertSomething(team);
					result = Response.ok(id).build();
				}
			} else {
				result = Response.ok("Team already exists").build();
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
	public Response updateTeam(Team team) {

		Response result = null;
		Query query = null;
		try {
			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);
			Update update = new Update(query);

			String id = update.updateSomething(team);
			result = Response.ok(id).build();

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

}
