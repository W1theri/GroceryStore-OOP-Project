package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5433/grocery_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1212";



    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database successfully");

        } catch (SQLException e) {
            System.out.println(" Connection Failed");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        return connection;
    }


    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println(" Connection closed.");
            } catch (SQLException e) {
                System.out.println(" Error closing connection!");
                e.printStackTrace();
            }
        }
    }


    public static boolean testConnection() {
        Connection connection = getConnection();

        if (connection != null) {
            System.out.println(" Database connection test Passed");
            closeConnection(connection);
            return true;
        } else {
            System.out.println(" Database connection test Failed");
            return false;
        }
    }


    public static void printConnectionInfo() {
        System.out.println("========================================");
        System.out.println("DATABASE CONNECTION INFO");
        System.out.println("========================================");
        System.out.println("URL: " + URL);
        System.out.println("User: " + USER);
        System.out.println("Password: " + "***" + PASSWORD.substring(Math.max(0, PASSWORD.length() - 3)));
        System.out.println("========================================");
    }
}