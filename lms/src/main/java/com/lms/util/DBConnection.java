package com.lms.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Simple DB connection helper that reads src/main/resources/application.properties
 */
public class DBConnection {

    private static final String PROPS_FILE = "/application.properties";
    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            // 🔥 VERY IMPORTANT: Explicitly load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            InputStream in = DBConnection.class.getResourceAsStream(PROPS_FILE);
            if (in == null) {
                throw new RuntimeException("application.properties not found in resources");
            }

            Properties props = new Properties();
            props.load(in);

            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");

            System.out.println("DB properties loaded successfully");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load DB properties", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
