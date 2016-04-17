package com.sb.database;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;

import com.sb.ci.model.BasePOJO;
import com.sb.ci.utility.POJOFactory;
import com.sb.ci.utility.Text;

public class Find {

	// Class that gets things from the database

	private Query query;

	// Needs an opened query to be passed to the constructor
	public Find(Query query) {
		this.query = query;
	}

	/**
	 * This method will generate a collection based on parameters passed.
	 * <p>
	 * This method will take:
	 * <p>
	 * tableName = name of queried table
	 * <p>
	 * criteria = column names written in javaCase
	 * <p>
	 * values = the passed in values to query in the criteria
	 * <p>
	 * orderBy = column name to order by in SQL_CASE
	 * <p>
	 * orderDir = order direction given ^ ascending or !^ descending
	 * <p>
	 * or = Do you need an OR in your query? (1 = yes or 0 = no)
	 * <p>
	 * startIndex = which criteria you want to start ORing at
	 * <p>
	 * <b/> Will throw standard SQL errors on failure to query
	 */
	public Collection findSomething(String tableName, String[] criteria, String[] values, boolean or, int startIndex) {

		// By default searchQuery selects everything in the table
		String searchQuery = "SELECT * FROM " + tableName;

		// If there is both a criteria and values array are not null
		if (criteria != null && values != null) {
			// Start search definition
			searchQuery += " WHERE ";

			// Converts criteria array and values array to SQL syntax
			searchQuery = setupSQL(or, startIndex, criteria, values, searchQuery);
		}

		query.execute(searchQuery);

		return initializeAndCollect(tableName);
	}

	public Collection findSomething(String tableName, String criteria, String value) {
		String searchQuery = "SELECT * FROM " + Text.toSQLCase(tableName);

		searchQuery += " WHERE " + Text.toSQLCase(criteria) + "='" + Text.toSQLCase(value) + "'";
		query.execute(searchQuery);

		return initializeAndCollect(tableName);
	}

	public Collection findEverything(String tableName) {

		query.execute("SELECT * FROM " + Text.toSQLCase(tableName));

		return initializeAndCollect(tableName);
	}

	public HashMap findSomething(String searchQuery) {

		query.execute(searchQuery);
		HashMap data = query.getNextRowData();

		return data;
	}

	public Collection findSomething(String tableName, String searchQuery) {

		query.execute(searchQuery);

		return initializeAndCollect(tableName);
	}

	private String setupSQL(boolean or, int startIndex, String[] criteria, String[] values, String searchQuery) {

		// If we don't need OR's
		if (or == false) {
			for (int i = 0; i < criteria.length; i++) {
				if (i == criteria.length - 1) {
					searchQuery += Text.toSQLCase(criteria[i]) + "='" + values[i] + "'";
				} else {
					searchQuery += Text.toSQLCase(criteria[i]) + "='" + values[i] + "'";
					searchQuery += " AND ";
				}
			}
		}
		// If we need OR's
		else if (or == true) {
			if (criteria.length == 1) {
				for (int i = 0; i < criteria.length; i++) {
					if (i == criteria.length - 1) {
						searchQuery += Text.toSQLCase(criteria[i]) + "='" + values[i] + "'";
					}
				}
			} else if (criteria.length > 1) {
				for (int i = 0; i < criteria.length; i++) {
					if (i == criteria.length - 1) {
						searchQuery += Text.toSQLCase(criteria[i]) + "='" + values[i] + "')";
					} else if (i < startIndex) {
						searchQuery += Text.toSQLCase(criteria[i]) + "='" + values[i] + "'";
						searchQuery += " AND ";
					} else if (i > startIndex) {
						searchQuery += Text.toSQLCase(criteria[i]) + "='" + values[i] + "'";
						searchQuery += " OR ";
					} else if (i == startIndex) {
						searchQuery += "(" + Text.toSQLCase(criteria[i]) + "='" + values[i] + "'";
						searchQuery += " OR ";
					}
				}
			}
		}

		return searchQuery;
	}

	private Collection initializeAndCollect(String tableName) {

		Collection result = new LinkedHashSet();

		while (true) {
			HashMap data = query.getNextRowData();

			if (data == null) {
				break;
			}

			BasePOJO object = POJOFactory.getInstance(Text.toSQLCase(tableName));
			object.setDataMap(data);
			result.add(object);

		}

		return result;
	}

}
