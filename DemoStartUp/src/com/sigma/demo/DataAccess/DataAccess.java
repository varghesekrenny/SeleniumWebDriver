package com.sigma.demo.DataAccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sigma.demo.Constants.CommonUtils;
import com.sigma.demo.Constants.DataConstants;

public class DataAccess {

	static Logger log = Logger.getLogger(DataAccess.class);

	public static Connection connect() throws FileNotFoundException,
			IOException, ClassNotFoundException, SQLException {
		Properties properties = new Properties();
		Connection connection;
		properties.load(new FileInputStream("config" + File.separator
				+ "dbconnection.properties"));
		String driver = properties.getProperty("database.driver");
		String url = properties.getProperty("database.url");
		String userName = properties.getProperty("database.user");
		String passWord = properties.getProperty("database.password");

		System.out.println("Loading driver" + driver);
		Class.forName(driver);
		System.out.println("Driver loaded");

		if (userName.equals("") || userName == null) {
			connection = DriverManager.getConnection(url);
		} else {
			connection = DriverManager.getConnection(url, userName, passWord);
		}
		return connection;

	}

	public static String executeSelectQuery(String query) throws SQLException {
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		String result = null;
		try {
			connection = connect();
			stmt = connection.createStatement();
			resultSet = stmt.executeQuery(query);
			if(resultSet.next())
			{
				result = resultSet.getString(1);
			}
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		} catch (SQLException e) {
			log.error(e.getMessage());
		}	
			finally
			{
				resultSet.close();
				stmt.close();
				connection.close();
			}
			
		
		return CommonUtils.isNULL(result); 

	}

}
