package com.sb.database;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sb.ci.exception.CIException;

public class ConnectionManager {

	private static DataSource dataSource;

	public static Connection getConnection() {
		try {
			if (dataSource == null) {
				Context initialContext;
				initialContext = new InitialContext();
				Context newContext = (Context) initialContext.lookup("java:/comp/env");
				if (newContext != null) {
					dataSource = (DataSource) newContext.lookup("jdbc/stormBots");
				} else {
					throw new CIException("Unable to resolve Initial Context");
				}
			}

			if (dataSource == null) {
				throw new CIException("Unable to resolve DataSource");
			}

			return dataSource.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
			throw new CIException(e);
		}
	}

}
