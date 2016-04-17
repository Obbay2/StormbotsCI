package com.sb.database;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.sb.ci.model.BasePOJO;
import com.sb.ci.model.Round;
import com.sb.ci.model.Team;
import com.sb.ci.utility.POJOHelper;

public class Insert {

	private Query query;

	public Insert(Query query) {
		this.query = query;
	}

	public void checkTeamExists(String teamNumber) {
		Find find = new Find(query);

		Collection<Team> test = find.findSomething("team", "teamNumber",
				teamNumber);

		if (test == null || test.size() == 0) {
			Team team = new Team();
			team.setTeamNumber(teamNumber);
			insertSomething(team);
		}
	}

	public String buildParameters(String[] fields, BasePOJO pojo,
			String queryString) {

		for (int i = 0; i < fields.length; i++) {
			if (fields[i].equals(pojo.getIDName())) {
				continue;
			}

			if (i < fields.length - 1) {
				queryString += fields[i];
				queryString += ", ";
			} else {
				queryString += fields[i];
			}
		}

		return queryString;
	}

	public String buildValues(String[] fields, BasePOJO pojo,
			String queryString, Map data) {

		for (int i = 0; i < fields.length; i++) {
			String value = (String) data.get(fields[i]);

			if (fields[i].equals(pojo.getIDName())) {
				continue;
			}

			if (value != null && !value.equals("")) {
				if (i < fields.length - 1) {
					queryString += "'" + value + "'";
					queryString += ", ";
				} else {
					queryString += "'" + value + "'";
				}
			} else {
				if (value != null && value.equals("")) {
					value = null;
				}
				if (i < fields.length - 1) {
					queryString += value;
					queryString += ", ";
				} else {
					queryString += value;
				}
			}
		}

		return queryString;
	}

	public String insertSomething(BasePOJO pojo) {

		Map data = pojo.getDataMap();
		String tableName = pojo.getTableName();

		query.execute("SELECT * FROM " + tableName + " WHERE 1=1");
		String[] fields = query.getColumnNames();

		String queryString = "INSERT INTO " + tableName + " (";

		queryString = buildParameters(fields, pojo, queryString);

		queryString += ") VALUES (";

		queryString = buildValues(fields, pojo, queryString, data);

		queryString += ")";
		query.executeUpdate(queryString);

		String id = query.getId();
		pojo.setId(id);

		return pojo.getId();
	}

	public String insertRoundsAndTeams(Round round) {

		String[] teamNumbers = new String[6];
		teamNumbers[0] = round.getBlue1Number();
		teamNumbers[1] = round.getBlue2Number();
		teamNumbers[2] = round.getBlue3Number();
		teamNumbers[3] = round.getRed1Number();
		teamNumbers[4] = round.getRed2Number();
		teamNumbers[5] = round.getRed3Number();

		for (int i = 0; i < teamNumbers.length; i++) {
			checkTeamExists(teamNumbers[i]);
		}

		POJOHelper helper = new POJOHelper();
		helper.setTeamIds(round, query);

		Map data = round.getDataMap();
		String tableName = round.getTableName();

		query.execute("SELECT * FROM " + tableName + " WHERE 1=1");
		String[] fields = query.getColumnNames();

		String queryString = "INSERT INTO " + tableName + " (";

		queryString = buildParameters(fields, round, queryString);

		queryString += ") VALUES (";

		queryString = buildValues(fields, round, queryString, data);

		queryString += ")";
		query.executeUpdate(queryString);

		String id = query.getId();
		round.setId(id);

		return round.getId();
	}
}