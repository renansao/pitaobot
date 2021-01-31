package com.disc.bot.pitao.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlUtils {
	
	Connection c = null;
	Statement stmt = null;
	String databaseConnectionURI = System.getenv("DATABASECONNECTIONURI");
	String databaseUsername = System.getenv("DATABASEUSERNAME");
	String databasePassword = System.getenv("DATABASEPASSWORD");
	
	public SqlUtils() throws Exception {
		Class.forName("org.postgresql.Driver");
		c = DriverManager.getConnection(databaseConnectionURI, databaseUsername, databasePassword);
		c.setAutoCommit(false);
		stmt = c.createStatement();
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		return stmt.executeQuery(query);
	}
	
}
