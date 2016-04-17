package com.sb.ci.service;

import java.sql.Connection;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sb.ci.model.Competition;
import com.sb.ci.model.Robot;
import com.sb.ci.model.Round;
import com.sb.ci.model.Team;
import com.sb.database.ConnectionManager;
import com.sb.database.Find;
import com.sb.database.Insert;
import com.sb.database.Query;
import com.sb.database.Update;

@Path("/robot")
public class RobotService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRobots() throws Exception {
		
		Collection output = null;
		Query query = null;
		
		try{
		
		Connection connection = ConnectionManager.getConnection();
		query = new Query(connection);
		Find find = new Find(query);

		output = find.findEverything("robot");

		query.close();
		} catch (Throwable e) {
			e.printStackTrace();
			if(query != null){
				query.close();
			}
			
			return Response.serverError().entity(e.getCause()).build();
		}
		return Response.ok(output).build();

	}
	
	@GET
	@Path("{teamNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRobot(@PathParam("teamNumber") String teamNumber) throws Exception {
		
		Collection<Robot> output = null;
		Query query = null;
		
		try{
		
		Connection connection = ConnectionManager.getConnection();
		query = new Query(connection);
		Find find = new Find(query);
		
		Collection<Team> data = find.findSomething("team", "teamNumber", teamNumber);
		
		Team team = new Team();
		if(data.size() > 0){
			 team = data.iterator().next();
		}
		
		String teamId = team.getTeamId();
		
		Collection<Competition> data2 = find.findSomething("competition", "current", "1");
		
		Competition competition = new Competition();
		if(data.size() >0){
			competition = data2.iterator().next();
		}
		
		String gameId = competition.getGameId();
		
		output = find.findSomething("robot", new String[] {"teamId", "gameId"}, new String[] {teamId, gameId}, false, 0);
		
		query.close();
		} catch (Throwable e) {
			e.printStackTrace();
			if(query != null){
				query.close();
			}
			
			return Response.serverError().entity(e.getCause()).build();
		}
		return Response.ok(output).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertRobot(Robot robot) {
	
		Response result = null;
		Query query = null;

		try{
		if (!((robot.getTeamId() != null || !robot.getTeamId().equals(""))
				&& (robot.getGameId() != null || !robot.getGameId()
						.equals("")))) {
			result = Response.ok("Did not specify teamId or gameId").build();
		}


		Connection connection = ConnectionManager.getConnection();
		query = new Query(connection);
		Find find = new Find(query);
		Insert insert = new Insert(query);
		
		insert.checkTeamExists(robot.getTeamNumber());
		
		Collection<Team> data = find.findSomething("team", "teamNumber", robot.getTeamNumber());
		Team team  = data.iterator().next();
		robot.setTeamId(team.getTeamId());
		
		Collection<Competition> data2 = find.findSomething("competition", "current", "1");
		Competition comp = data2.iterator().next();
		robot.setGameId(comp.getGameId());
			
		String id = insert.insertSomething(robot);
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
	

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRobot(Robot robot) {

		Response result = null;
		Query query = null;
		
		try{
		Connection connection = ConnectionManager.getConnection();
		query = new Query(connection);

		Update update = new Update(query);
		String id = update.updateSomething(robot);
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
