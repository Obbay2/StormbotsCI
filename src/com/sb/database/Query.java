package com.sb.database;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.HashMap;

import com.sb.ci.exception.CIException;

public class Query {
	//
	// String url = "jdbc:mysql://localhost:3306/"; // Hosting MYSQL on port
	// 3306
	// String dbName = "bunnybots_2014"; // Database name
	// String driver = "com.mysql.jdbc.Driver"; // File for connecting with
	// MYSQL
	// String userName = "root"; // Database connection username
	// String password = "sasa"; // Database connection password

	private Connection connection = null; // Connection to the database
	private Statement statement = null; // Statement object used to execute
										// queries
	private ResultSet resultSet = null; // What is returned by the query
	private int numberOfColumns = 0; // Will have number of columns after
										// executed query

	// Default constructor
	public Query(Connection connection) {
		this.connection = connection;
		try {
			statement = connection.createStatement();
		} catch (Exception e) {
			throw new CIException(e);
		}
	}

	// public boolean openConnection() {
	// try {
	// Class.forName(driver).newInstance();
	// connection = DriverManager.getConnection(url + dbName, userName,
	// password);
	// statement = connection.createStatement();
	// return true;
	// } catch (java.lang.Exception ex) {
	// // Print out the error
	// ex.printStackTrace();
	// return false;
	// }
	// }
	//
	public void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resultSet = null;
			statement = null;
			connection = null;
		}
	}

	public boolean execute(String query) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			resultSet = statement.executeQuery(query);
			numberOfColumns = (resultSet.getMetaData()).getColumnCount();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean executeUpdate(String query) {
		try {
			int result = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			return result > 0;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public String getId() {
		String id = null;
		ResultSet rs;
		try {
			rs = statement.getGeneratedKeys();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return id;
		}
		try {
			if (rs.next()) {
				id = rs.getString(".GENERATED_KEY");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}

	public HashMap getNextRowData() {
		HashMap result = null;
		int i;

		try {
			if (resultSet != null) {
				if (resultSet.next()) {
					result = new HashMap();
					String[] columns = getColumnNames();
					for (i = 1; i <= numberOfColumns; i++) {
						result.put(columns[i - 1], resultSet.getString(i));
					}
				} else {
					result = null;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return result;
	}

	public String[] getNextRow() {
		String[] row = null;
		int i;

		try {
			if (resultSet != null) {
				if (resultSet.next()) {
					row = new String[numberOfColumns];
					for (i = 1; i <= numberOfColumns; i++) {
						row[i - 1] = resultSet.getString(i);
					}
				} else {
					row = null;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return row;
	}

	public String[] getColumnNames() {
		String[] labels = new String[numberOfColumns];
		int i;

		try {
			for (i = 1; i <= numberOfColumns; i++) {
				labels[i - 1] = ((resultSet.getMetaData()).getColumnLabel(i));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return labels;
	}

	private String loadTemplate(String templateFileName) throws IOException {

		if (templateFileName != null && templateFileName.length() > 0) {
			File templateFile = new File(templateFileName);

			long length = templateFile.length();

			char[] buffer = new char[(int) length];

			FileReader reader = null;

			reader = new FileReader(templateFile);
			reader.read(buffer);
			reader.close();

			return new String(buffer);
		}

		return "";
	}

	/**
	 * @return Formatted text for HTML
	 * @throws IOException
	 */
	public String getContent(String templateFileName, Object[] data) throws IOException {
		String template = loadTemplate(templateFileName);

		MessageFormat formatter = new MessageFormat(template);
		return formatter.format(data);

	}

	public String setQueryParameter(String query, String parameterName, Object data) {

		parameterName = ":" + parameterName;

		if (data == null || (data != null && data.toString().equals(""))) {
			parameterName = "'" + parameterName + "'";
			data = "null";
		}

		query = query.replace(parameterName, data.toString());

		return query;
	}

	public Connection getConnection() {
		return connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}
}
