package database;

import model.Customer;
import java.sql.*;


public class CustomerDAO {


    public int insertCustomer(Customer customer) {
        String sql = "INSERT INTO customer (name, membership_level, total_purchases) " +
                "VALUES (?, ?, ?) RETURNING customer_id";

        Connection connection = DatabaseConnection.getConnection();
        int customerId = -1;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getMembershipLevel());
            statement.setDouble(3, customer.getTotalPurchases());

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                customerId = rs.getInt("customer_id");
                System.out.println(" Customer inserted successfully ID: " + customerId);
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(" Insert customer failed");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return customerId;
    }


    public void getAllCustomers() {
        String sql = "SELECT * FROM customer ORDER BY customer_id";

        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║   👥 ALL CUSTOMERS FROM DATABASE 👥  ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println();

            int count = 0;
            while (resultSet.next()) {
                count++;
                int id = resultSet.getInt("customer_id");
                String name = resultSet.getString("name");
                String membership = resultSet.getString("membership_level");
                double purchases = resultSet.getDouble("total_purchases");

                System.out.println(count + ". Customer ID: " + id);
                System.out.println("   Name: " + name);
                System.out.println("   Membership: " + membership);
                System.out.println("   Total Purchases: " + String.format("%.2f KZT", purchases));
                System.out.println("   ─────────────────────────────────");
            }

            if (count == 0) {
                System.out.println(" No customers found in database");
            } else {
                System.out.println("\n Total customers: " + count);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(" Select customers failed");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }
}