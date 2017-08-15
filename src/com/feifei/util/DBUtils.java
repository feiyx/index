package com.feifei.util;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtils {
	private static String driver = "";
	private static String url = "";
	private static String user = "";
	private static String pwd = "";
	private static BasicDataSource bds = null;
	static {
		bds = new BasicDataSource();
		Properties prop = new Properties();
		try {
			prop.load(DBUtils.class.getClassLoader().getResourceAsStream(
					"db.properties"));
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			pwd = prop.getProperty("pwd");
			driver=prop.getProperty("driver");
			Class.forName(driver); 
			//initialSize = Integer.parseInt(prop.getProperty("initialSize"));
			// 1.6
			//maxActive = Integer.parseInt(prop.getProperty("maxActive"));
			// 1.7
			// maxTotal=Integer.parseInt(prop.getProperty("maxTotal"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		bds.setUrl(url);
		bds.setUsername(user);
		bds.setPassword(pwd);
		//bds.setInitialSize(initialSize);
		//bds.setMaxActive(maxActive);
	}

	public static Connection getConnection() throws Exception {
		if (bds != null) {
			return bds.getConnection();
		}
		return null;
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
