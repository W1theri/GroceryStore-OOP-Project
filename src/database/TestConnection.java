package database;

import java.sql.Connection;


public class TestConnection {

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("    DATABASE CONNECTION TEST");
        System.out.println("========================================");
        System.out.println();

        DatabaseConnection.printConnectionInfo();
        System.out.println();

        System.out.println("Test 1: Attempting to connect to database");
        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            System.out.println(" Connection test Passed");
            System.out.println();

            DatabaseConnection.closeConnection(connection);
            System.out.println();

            System.out.println("========================================");
            System.out.println("    ALL TESTS PASSED");
            System.out.println("   You can now proceed to insert data");
            System.out.println("========================================");

        } else {
            System.out.println(" Connection test Failed");
            System.out.println();
            System.out.println("========================================");
            System.out.println("   ⚠️  TROUBLESHOOTING:");
            System.out.println("   1. Check if PostgreSQL is running");
            System.out.println("   2. Verify database name is correct");
            System.out.println("   3. Check username and password");
            System.out.println("   4. Ensure JDBC driver is added");
            System.out.println("========================================");
        }
    }
}