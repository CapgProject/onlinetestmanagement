package com.cg.TestManagement.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.TestManagement.exception.UserException;

public class DbUtil {
	private static Logger myLogger;
	private static Connection connection;
	static {

		Properties props = System.getProperties();
		String userDir = props.getProperty("user.dir") + "/src/main/resources/";
		PropertyConfigurator.configure(userDir + "log4j.properties");
		myLogger = Logger.getLogger("DbUtil.class");
	}

	private DbUtil() {
		super();
	}
	public static Connection getConnection() throws UserException {

		String url, username, password;
		try {
			Properties prop = DbUtil.loadProp();
			url = prop.getProperty("url");
			username = prop.getProperty("user");
			password = prop.getProperty("password");

			connection = DriverManager.getConnection(url, username, password);
			myLogger.info("connection Obtained! : " + connection);

		} catch (Exception e) {
			myLogger.error("sorry!!! Something went wrong" + " with the connection" + e);
		}
		return connection;
	}

	private static Properties loadProp() throws UserException {
		Properties props = System.getProperties();
		String userDir = props.getProperty("user.dir") + "/src/main/resources/";
		Properties myProp = new Properties();
		try (FileInputStream fis = new FileInputStream(userDir + "jdbc.properties")) {
			myProp.load(fis);
			myLogger.info("Property File loaded : ");
		} catch (Exception e) {
			myLogger.error("Property File Not loaded" + e);
			throw new UserException(e.getMessage());
		}
		return myProp;
	}

	// method for closing the connection
	public static void closeConnection() throws UserException {
		try {
			if (connection != null) {
				connection.close();
				myLogger.error("Closing Connection");
			} else
				myLogger.error("Connection already closed");
		} catch (Exception e) {
			myLogger.error("Connection already closed" + e);
			throw new UserException(e.getMessage());
		}
	}
}
