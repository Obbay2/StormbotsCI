package com.sb.ci.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sb.ci.model.Round;
import com.sb.ci.model.Score;
import com.sb.ci.model.Team;
import com.sb.ci.utility.POJOHelper;
import com.sb.database.ConnectionManager;
import com.sb.database.Find;
import com.sb.database.Insert;
import com.sb.database.Query;

@Path("/score")
public class ScoreService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getScore() {
		Collection output = null;
		Query query = null;

		try {
			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);
			Find find = new Find(query);

			output = find.findEverything("score");

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
	@Path("/sum_auto/{teamNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAutoSum(@PathParam("teamNumber") String teamNumber) {

		Query query = null;
		Collection<Score> list = null;

		try {

			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);
			Find find = new Find(query);
			POJOHelper helper = new POJOHelper();

			Score score = new Score();
			String searchQuery = helper.sumScore(score);

			Collection<Team> data = find.findSomething("team", "teamNumber", teamNumber);
			if (data.isEmpty()) {
				return null;
			}
			Team team = data.iterator().next();
			String teamId = team.getTeamId();

			String current = helper.getCurrentCompetitionId(query);

			String lowRound = helper.getLowRound(query, current);

			String highRound = helper.getHighRound(query, current);

			HashMap data2 = find.findSomething("SELECT COUNT(STACK_YELLOW > '0') AS STACK_YELLOW, " + searchQuery
					+ " FROM SCORE WHERE TEAM_ID = '" + teamId + "' AND STAGE = '1' AND (ROUND_ID <= '" + highRound
					+ "' AND ROUND_ID >= '" + lowRound + "')");

			query.close();

			Score score2 = new Score(data2);
			score2.setTeamNumber(team.getTeamNumber());

			list = new ArrayList<Score>();
			list.add(score2);

		} catch (Throwable e) {
			e.printStackTrace();

			if (query != null) {
				query.close();
			}

			return Response.serverError().entity(e.getCause()).build();
		}

		return Response.ok(list).build();

	}

	@GET
	@Path("/average_auto/{teamNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAutoAverage(@PathParam("teamNumber") String teamNumber) {

		Query query = null;
		Collection<Score> list = null;
		try {

			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);
			Find find = new Find(query);
			POJOHelper helper = new POJOHelper();

			Score score = new Score();
			String searchQuery = helper.averageScore(score);

			Collection<Team> data = find.findSomething("team", "teamNumber", teamNumber);
			if (data.isEmpty()) {
				return null;
			}
			Team team = data.iterator().next();
			String teamId = team.getTeamId();

			String current = helper.getCurrentCompetitionId(query);

			String lowRound = helper.getLowRound(query, current);

			String highRound = helper.getHighRound(query, current);

			HashMap data2 = find.findSomething("SELECT " + searchQuery + " FROM SCORE WHERE TEAM_ID = '" + teamId
					+ "' AND STAGE = '1' AND (ROUND_ID <= '" + highRound + "' AND ROUND_ID >= '" + lowRound + "')");

			query.close();

			Score score2 = new Score(data2);
			score2.setTeamNumber(team.getTeamNumber());

			list = new ArrayList<Score>();
			list.add(score2);
		} catch (Throwable e) {
			e.printStackTrace();

			if (query != null) {
				query.close();
			}

			return Response.serverError().entity(e.getCause()).build();
		}

		return Response.ok(list).build();

	}

	@GET
	@Path("/sum_tele/{teamNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeleSum(@PathParam("teamNumber") String teamNumber) {

		Query query = null;
		Collection<Score> list = null;

		try {

			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);
			Find find = new Find(query);
			POJOHelper helper = new POJOHelper();

			Score score = new Score();
			String searchQuery = helper.sumScore(score);

			Collection<Team> data = find.findSomething("team", "teamNumber", teamNumber);
			if (data.isEmpty()) {
				return null;
			}
			Team team = data.iterator().next();
			String teamId = team.getTeamId();

			String current = helper.getCurrentCompetitionId(query);

			String lowRound = helper.getLowRound(query, current);

			String highRound = helper.getHighRound(query, current);

			HashMap data2 = find
					.findSomething("SELECT COUNT(STACK_YELLOW > '0') AS STACK_YELLOW, COUNT(STACK_GRAY > '0') AS STACK_GRAY, "
							+ searchQuery
							+ " FROM SCORE WHERE TEAM_ID = '"
							+ teamId
							+ "' AND STAGE = '2' AND (ROUND_ID <= '"
							+ highRound
							+ "' AND ROUND_ID >= '"
							+ lowRound
							+ "')");

			query.close();

			Score score2 = new Score(data2);
			score2.setTeamNumber(team.getTeamNumber());

			list = new ArrayList<Score>();
			list.add(score2);
		} catch (Throwable e) {
			e.printStackTrace();

			if (query != null) {
				query.close();
			}

			return Response.serverError().entity(e.getCause()).build();
		}

		return Response.ok(list).build();

	}

	@GET
	@Path("/average_tele/{teamNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeleAverage(@PathParam("teamNumber") String teamNumber) {

		Query query = null;
		Collection<Score> list = null;

		try {

			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);
			Find find = new Find(query);
			POJOHelper helper = new POJOHelper();

			Score score = new Score();
			String searchQuery = helper.averageScore(score);

			Collection<Team> data = find.findSomething("team", "teamNumber", teamNumber);
			if (data.isEmpty()) {
				return null;
			}
			Team team = data.iterator().next();
			String teamId = team.getTeamId();

			String current = helper.getCurrentCompetitionId(query);

			String lowRound = helper.getLowRound(query, current);

			String highRound = helper.getHighRound(query, current);

			HashMap data2 = find.findSomething("SELECT " + searchQuery + " FROM SCORE WHERE TEAM_ID = '" + teamId
					+ "' AND STAGE = '2' AND (ROUND_ID <= '" + highRound + "' AND ROUND_ID >= '" + lowRound + "')");

			query.close();

			Score score2 = new Score(data2);
			score2.setTeamNumber(team.getTeamNumber());

			list = new ArrayList<Score>();

			list.add(score2);
		} catch (Throwable e) {
			e.printStackTrace();

			if (query != null) {
				query.close();
			}

			return Response.serverError().entity(e.getCause()).build();
		}

		return Response.ok(list).build();

	}

	@GET
	@Path("sum_auto/sort/{columnName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAutoSumAll(@PathParam("columnName") String columnName) {

		Query query = null;
		Collection<Score> data = null;

		try {

			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);
			Find find = new Find(query);
			POJOHelper helper = new POJOHelper();

			Score score = new Score();
			String searchQuery = helper.sumScore(score);

			String current = helper.getCurrentCompetitionId(query);

			String lowRound = helper.getLowRound(query, current);

			String highRound = helper.getHighRound(query, current);

			data = find.findSomething("score", "SELECT TEAM_NUMBER, COUNT(STACK_YELLOW > '0') AS STACK_YELLOW, "
					+ searchQuery
					+ " FROM SCORE s right outer join team t on s.TEAM_ID = t.TEAM_ID WHERE (ROUND_ID <= '" + highRound
					+ "' AND ROUND_ID >= '" + lowRound + "') AND STAGE = '1' group by TEAM_NUMBER ORDER BY "
					+ columnName + " DESC");

			query.close();
		} catch (Throwable e) {
			e.printStackTrace();

			if (query != null) {
				query.close();
			}

			return Response.serverError().entity(e.getCause()).build();
		}
		return Response.ok(data).build();
	}

	@GET
	@Path("sum_tele/sort/{columnName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeleSumAll(@PathParam("columnName") String columnName) {

		Query query = null;
		Collection<Score> data = null;

		try {

			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);
			Find find = new Find(query);
			POJOHelper helper = new POJOHelper();

			Score score = new Score();
			String searchQuery = helper.sumScore(score);

			String current = helper.getCurrentCompetitionId(query);

			String lowRound = helper.getLowRound(query, current);

			String highRound = helper.getHighRound(query, current);

			data = find.findSomething("score",
					"SELECT TEAM_NUMBER, COUNT(STACK_YELLOW > '0') AS STACK_YELLOW, COUNT(STACK_GRAY > '0') AS STACK_GRAY, "
							+ searchQuery
							+ " FROM SCORE s right outer join team t on s.TEAM_ID = t.TEAM_ID WHERE (ROUND_ID <= '"
							+ highRound + "' AND ROUND_ID >= '" + lowRound
							+ "') AND STAGE = '2' group by TEAM_NUMBER ORDER BY " + columnName + " DESC");

			query.close();
		} catch (Throwable e) {
			e.printStackTrace();

			if (query != null) {
				query.close();
			}

			return Response.serverError().entity(e.getCause()).build();
		}
		return Response.ok(data).build();
	}

	@GET
	@Path("average_auto/sort/{columnName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAutoAverageAll(@PathParam("columnName") String columnName) {

		Query query = null;
		Collection<Score> data = null;

		try {

			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);
			Find find = new Find(query);
			POJOHelper helper = new POJOHelper();

			Score score = new Score();
			String searchQuery = helper.averageScore(score);

			String current = helper.getCurrentCompetitionId(query);

			String lowRound = helper.getLowRound(query, current);

			String highRound = helper.getHighRound(query, current);

			data = find.findSomething("score", "SELECT TEAM_NUMBER, " + searchQuery
					+ " FROM SCORE s right outer join team t on s.TEAM_ID = t.TEAM_ID WHERE (ROUND_ID <= '" + highRound
					+ "' AND ROUND_ID >= '" + lowRound + "') AND STAGE = '1' group by TEAM_NUMBER ORDER BY "
					+ columnName + " DESC");

			query.close();
		} catch (Throwable e) {
			e.printStackTrace();

			if (query != null) {
				query.close();
			}

			return Response.serverError().entity(e.getCause()).build();
		}
		return Response.ok(data).build();
	}

	@GET
	@Path("average_tele/sort/{columnName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeleAverageAll(@PathParam("columnName") String columnName) {

		Query query = null;
		Collection<Score> data = null;

		try {

			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);
			Find find = new Find(query);
			POJOHelper helper = new POJOHelper();

			Score score = new Score();
			String searchQuery = helper.averageScore(score);

			String current = helper.getCurrentCompetitionId(query);

			String lowRound = helper.getLowRound(query, current);

			String highRound = helper.getHighRound(query, current);

			data = find.findSomething("score", "SELECT TEAM_NUMBER, " + searchQuery
					+ " FROM SCORE s right outer join team t on s.TEAM_ID = t.TEAM_ID WHERE (ROUND_ID <= '" + highRound
					+ "' AND ROUND_ID >= '" + lowRound + "') AND STAGE = '2' group by TEAM_NUMBER ORDER BY "
					+ columnName + " DESC");

			query.close();
		} catch (Throwable e) {
			e.printStackTrace();

			if (query != null) {
				query.close();
			}

			return Response.serverError().entity(e.getCause()).build();
		}
		return Response.ok(data).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertScore(Score score) {

		Response result = null;

		Query query = null;
		try {
			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);

			if ((score.getRoundId() != null || !score.getRoundId().equals(""))
					&& (score.getTeamId() != null || !score.getTeamId().equals(""))
					&& (score.getStage() != null || !score.getStage().equals(""))) {
				Insert insert = new Insert(query);
				String id = insert.insertSomething(score);
				result = Response.ok(id).build();
			} else {
				result = Response.ok("Did not specify roundId, teamId, or stage").build();
			}
			query.close();
		} catch (Throwable e) {
			e.printStackTrace();

			if (query != null) {
				query.close();
			}

			result = Response.serverError().entity(e.getCause()).build();
		}

		return result;
	}

	@DELETE
	@Path("{number}")
	public Response deleteScore(@PathParam("number") String roundNumber) {

		Response result = null;
		Query query = null;

		try {

			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);
			Find find = new Find(query);
			POJOHelper helper = new POJOHelper();

			String current = helper.getCurrentCompetitionId(query);

			Collection<Round> data2 = find.findSomething("round", new String[] { "competitionId", "number" },
					new String[] { current, roundNumber }, false, 0);

			String roundId = "";

			if (data2.isEmpty()) {
				return Response.ok("No Round Found").build();
			} else {
				roundId = data2.iterator().next().getRoundId();
			}

			query.executeUpdate("DELETE FROM SCORE WHERE ROUND_ID = '" + roundId + "'");
			result = Response.ok("Data Deleted").build();
			query.close();
		} catch (Throwable e) {
			e.printStackTrace();

			if (query != null) {
				query.close();
			}

			result = Response.serverError().entity(e.getCause()).build();
		}

		return result;
	}

}
