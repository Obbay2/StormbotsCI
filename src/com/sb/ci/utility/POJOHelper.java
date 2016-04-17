package com.sb.ci.utility;

import java.util.Collection;
import java.util.HashMap;

import com.sb.ci.model.Competition;
import com.sb.ci.model.Game;
import com.sb.ci.model.Robot;
import com.sb.ci.model.Round;
import com.sb.ci.model.Score;
import com.sb.ci.model.Team;
import com.sb.database.Find;
import com.sb.database.Query;
import com.sb.database.Update;

public class POJOHelper {

	public String getCurrentCompetitionId(Query query) {
		Find find = new Find(query);

		Collection<Competition> data2 = find.findSomething("competition", "current", "1");
		Competition comp = data2.iterator().next();

		return comp.getCompetitionId();
	}

	public Competition getCurrentCompetition(Query query) {
		Find find = new Find(query);

		Collection<Competition> data2 = find.findSomething("competition", "current", "1");
		Competition comp = data2.iterator().next();

		return comp;
	}

	public String getLowRound(Query query, String currentCompetitionId) {
		Find find = new Find(query);

		HashMap low = find.findSomething("SELECT MIN(ROUND_ID) AS minimum FROM ROUND WHERE COMPETITION_ID = '"
				+ currentCompetitionId + "'");
		return (String) low.get("minimum");
	}

	public String getHighRound(Query query, String currentCompetitionId) {
		Find find = new Find(query);

		HashMap high = find.findSomething("SELECT MAX(ROUND_ID) AS maximum FROM ROUND WHERE COMPETITION_ID = '"
				+ currentCompetitionId + "'");
		return (String) high.get("maximum");
	}

	public void setCompetitionYear(Competition competition, Query query) {
		Find find = new Find(query);

		Collection<Game> stuff = find.findSomething("game", "gameId", competition.getGameId());
		Game game = stuff.iterator().next();
		competition.setYear(game.getYear());
	}

	public void setMisc(Robot robot, Query query) {
		Find find = new Find(query);

		Collection<Team> data = find.findSomething("team", "teamId", robot.getTeamId());
		Team team = data.iterator().next();
		robot.setTeamNumber(team.getTeamNumber());

		// Collection<Game> data2 = find.findSomething("game", "year",
		// robot.getYear());
		// Game game = data2.iterator().next();
		// robot.setYear(game.getYear());
	}

	public void setCurrentYear(Robot robot, Query query) {
		Find find = new Find(query);

		Collection<Competition> data = find.findSomething("competition", "current", "1");
		Competition comp = data.iterator().next();
		robot.setGameId(comp.getGameId());

		Collection<Game> data2 = find.findSomething("game", "gameId", comp.getGameId());
		Game game = data2.iterator().next();
		robot.setYear(game.getYear());
	}

	public void setTeamNumbers(Round round, Query query) {
		Find find = new Find(query);

		Collection<Team> data = find.findSomething("team", "teamId", round.getRed1Id());
		Team team = data.iterator().next();
		round.setRed1Number(team.getTeamNumber());

		Collection<Team> data2 = find.findSomething("team", "teamId", round.getRed2Id());
		Team team2 = data2.iterator().next();
		round.setRed2Number(team2.getTeamNumber());

		Collection<Team> data3 = find.findSomething("team", "teamId", round.getRed3Id());
		Team team3 = data3.iterator().next();
		round.setRed2Number(team3.getTeamNumber());

		Collection<Team> data4 = find.findSomething("team", "teamId", round.getBlue1Id());
		Team team4 = data4.iterator().next();
		round.setRed2Number(team4.getTeamNumber());

		Collection<Team> data5 = find.findSomething("team", "teamId", round.getBlue2Id());
		Team team5 = data5.iterator().next();
		round.setRed2Number(team5.getTeamNumber());

		Collection<Team> data6 = find.findSomething("team", "teamId", round.getBlue3Id());
		Team team6 = data6.iterator().next();
		round.setRed2Number(team6.getTeamNumber());

	}

	public void setTeamIds(Round round, Query query) {
		Find find = new Find(query);

		Collection<Team> data = find.findSomething(
				"team",
				"SELECT * FROM TEAM WHERE TEAM_NUMBER IN('" + round.getBlue1Number() + "', '" + round.getBlue2Number()
						+ "', '" + round.getBlue3Number() + "', '" + round.getRed1Number() + "', '"
						+ round.getRed2Number() + "', '" + round.getRed3Number() + "')");

		Object[] test = data.toArray();
		Team team = new Team();

		for (int i = 0; i < data.size(); i++) {
			team = (Team) test[i];

			if (round.getBlue1Number().equals(team.getTeamNumber())) {
				round.setBlue1Id(team.getTeamId());
			} else if (round.getBlue2Number().equals(team.getTeamNumber())) {
				round.setBlue2Id(team.getTeamId());
			} else if (round.getBlue3Number().equals(team.getTeamNumber())) {
				round.setBlue3Id(team.getTeamId());
			} else if (round.getRed1Number().equals(team.getTeamNumber())) {
				round.setRed1Id(team.getTeamId());
			} else if (round.getRed2Number().equals(team.getTeamNumber())) {
				round.setRed2Id(team.getTeamId());
			} else if (round.getRed3Number().equals(team.getTeamNumber())) {
				round.setRed3Id(team.getTeamId());
			}
		}
	}

	public void setCurrent(Round round, Query query) {
		Find find = new Find(query);

		Collection<Competition> data = find.findSomething("competition", "current", "1");
		Competition comp = data.iterator().next();
		round.setCompetitionId(comp.getCompetitionId());
	}

	public void setCurrentRound(int current, Query query) {
		Find find = new Find(query);
		Update update = new Update(query);

		Collection<Competition> data = find.findSomething("competition", "current", "1");
		Competition comp = data.iterator().next();
		comp.setCurrentRound(Integer.toString(current));
		update.updateSomething(comp);
	}

	// Will build a queryString using the hashMap of score
	public String sumScore(Score score) {

		String queryString = "";

		HashMap columns = (HashMap) score.getSummingMap();
		Object[] data = columns.keySet().toArray();

		for (int i = 0; i < data.length; i++) {
			String columnName = (String) data[i];

			if (i == data.length - 1) {
				queryString += "SUM(" + columnName + ") AS " + columnName;
			} else {
				queryString += "SUM(" + columnName + ") AS " + columnName + ", ";
			}
		}

		return queryString;
	}

	public String averageScore(Score score) {

		String queryString = "";

		HashMap columns = (HashMap) score.getAverageMap();
		Object[] data = columns.keySet().toArray();

		for (int i = 0; i < data.length; i++) {
			String columnName = (String) data[i];
			if (i == data.length - 1) {
				queryString += "AVG(" + columnName + ") AS " + columnName;
			} else {
				queryString += "AVG(" + columnName + ") AS " + columnName + ", ";
			}
		}

		return queryString;
	}
}
