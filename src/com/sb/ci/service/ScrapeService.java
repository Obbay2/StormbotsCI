package com.sb.ci.service;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sb.ci.model.Competition;
import com.sb.ci.model.Round;
import com.sb.ci.utility.POJOHelper;
import com.sb.database.ConnectionManager;
import com.sb.database.Find;
import com.sb.database.Insert;
import com.sb.database.Query;
import com.sb.database.Update;

@Path("/scrape")
public class ScrapeService {

	public int numColumns = 10;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/rounds")
	public Response getRounds() {
		Response result = null;

		Query query = null;

		try {

			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);

			ArrayList<Round> rounds = getRounds(query);
			result = Response.ok(rounds).build();
		} catch (Throwable e) {
			e.printStackTrace();

			if (query != null) {
				query.close();
			}

			return Response.serverError().entity(e.getCause()).build();
		}

		return result;
	}

	private ArrayList<Round> getRounds(Query query) {

		Document doc = getPageData(query);

		Elements all = doc.select("TD"); // get the data table
											// elements

		ArrayList<String> data = new ArrayList<String>();

		for (int i = 0; i < all.size(); i++) {
			data.add(all.get(i).text()); // transfer the raw data to a string
											// list
		}

		ArrayList<Round> rounds = new ArrayList<Round>();
		int size = data.size();
		for (int i = 0; i < size; i = i + numColumns) {
			Round round = new Round();
			if (!data.isEmpty()) {

				round.setNumber(data.get(0));
				round.setRed1Number(data.get(2));
				round.setRed2Number(data.get(3));
				round.setRed3Number(data.get(4));
				round.setBlue1Number(data.get(5));
				round.setBlue2Number(data.get(6));
				round.setBlue3Number(data.get(7));

				for (int j = 0; j < numColumns; j++) {
					data.remove(0);
				}
			}
			round.setRoundId("999999");
			rounds.add(round);
		}

		return rounds;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/rounds")
	public Response insertRounds() {

		Query query = null;

		try {

			Connection connection = ConnectionManager.getConnection();
			query = new Query(connection);
			Insert insert = new Insert(query);
			POJOHelper helper = new POJOHelper();

			ScrapeService test = new ScrapeService();
			ArrayList<Round> rounds = test.getRounds(query);

			for (int i = 0; i < rounds.size(); i++) {
				rounds.get(i).setRoundId("");
				helper.setCurrent(rounds.get(i), query);
				insert.insertRoundsAndTeams(rounds.get(i));
			}

			query.close();

		} catch (Throwable e) {
			e.printStackTrace();

			if (query != null) {
				query.close();
			}

			return Response.serverError().entity(e.getCause()).build();
		}

		return Response.ok("Inserted All Rounds").build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/score_round")
	public Response synchronizeScoreRoundUpdate() {

		Query query = null;

		try {

			Connection connection = ConnectionManager.getConnection();
			POJOHelper helper = new POJOHelper();
			query = new Query(connection);

			ArrayList<Round> rounds = getRounds(query);
			int currentRound = 0;
			for (int i = 0; i < rounds.size(); i++) {
				if ((rounds.get(i).getScoreRed() != null || rounds.get(i).getScoreRed() != "")
						&& (rounds.get(i).getScoreBlue() != null || rounds.get(i).getScoreBlue() != "")) {
					currentRound = i + 1;
				}
			}
			System.out.println(currentRound);

			helper.setCurrentRound(currentRound, query);

			if (currentRound > 1) {
				updateFinalScores(query, currentRound);
			}

			query.close();
		} catch (Throwable e) {
			e.printStackTrace();

			if (query != null) {
				query.close();
			}

			return Response.serverError().entity(e.getCause()).build();
		}

		return Response.ok("Updated final scores and current round successfully.").build();
	}

	// public Response updateCurrentRound(Document doc, Query query) {
	//
	// Update update = new Update(query);
	// POJOHelper helper = new POJOHelper();
	//
	// Competition comp = helper.getCurrentCompetition(query);
	//
	// Elements all = doc.select("big");
	//
	// String text = all.get(0).text();
	// int number = 9999999;
	// for (int i = 0; i < text.length(); i++) {
	// if (text.charAt(i) == '0' || text.charAt(i) == '1' || text.charAt(i) ==
	// '2' || text.charAt(i) == '3'
	// || text.charAt(i) == '4' || text.charAt(i) == '5' || text.charAt(i) ==
	// '6' || text.charAt(i) == '7'
	// || text.charAt(i) == '8' || text.charAt(i) == '9') {
	// number = i;
	// break;
	// }
	// }
	//
	// if (number == 9999999) {
	// return Response.ok("Failed to find current round").build();
	// }
	//
	// Integer i = Integer.parseInt(text.substring(number)) + 1;
	// String s = i.toString();
	//
	// comp.setCurrentRound(s);
	// update.updateSomething(comp);
	//
	// query.close();
	//
	// return Response.ok("Successfully set final round scores").build();
	// }
	//
	public Response updateFinalScores(Query query, int currentRound) {

		Document doc = getPageData(query);
		Update update = new Update(query);
		Find find = new Find(query);
		POJOHelper helper = new POJOHelper();

		String current = helper.getCurrentCompetitionId(query);

		Elements all = doc.select("TD");

		ArrayList<String> data = new ArrayList<String>();

		for (int i = 0; i < all.size(); i++) {
			data.add(all.get(i).text()); // transfer the raw data to a string
											// list
		}

		String scoreRed = "", scoreBlue = "";

		int size = data.size();
		if (currentRound <= (size / numColumns)) {
			System.out.println(currentRound);
			for (int i = 0; i < size; i = i + numColumns) {
				if (!data.isEmpty() && (i / numColumns) == currentRound - 2) {
					scoreRed = data.get(8);
					scoreBlue = data.get(9);
				}
				for (int j = 0; j < numColumns; j++) {
					data.remove(0);
				}
			}
		} else { // for the last round
			for (int i = 0; i < size; i = i + numColumns) {
				if (!data.isEmpty() && i == size - numColumns) {
					scoreRed = data.get(8);
					scoreBlue = data.get(9);
				}
				for (int j = 0; j < numColumns; j++) {
					data.remove(0);
				}
			}
		}

		Collection<Round> data2 = find.findSomething("round", "number", current);
		Round round = data2.iterator().next();
		round.setScoreRed(scoreRed);
		round.setScoreBlue(scoreBlue);
		update.updateSomething(round);

		System.out.println(round.getScoreRed() + " | " + round.getScoreBlue());

		return Response.ok("Updated final scores successfully.").build();
	}

	public Document getPageData(Query query) {

		POJOHelper helper = new POJOHelper();

		Competition comp = helper.getCurrentCompetition(query);

		Document doc = null;
		try {
			doc = Jsoup.connect(comp.getUrl()).userAgent("Mozilla").get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return doc;
	}
}