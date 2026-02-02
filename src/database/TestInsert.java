package database;

import model.*;
import exception.InvalidProductException;


public class TestInsert {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      TEST INSERT OPERATIONS          ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();

        ProductDAO productDAO = new ProductDAO();
        CustomerDAO customerDAO = new CustomerDAO();

        try {
            System.out.println("--- Test 1: Insert Fresh Product ---");
            FreshProduct apple = new FreshProduct(
                    0,
                    "Green Apple",
                    600.00,
                    100,
                    "2025-03-01",
                    true
            );

            int appleId = productDAO.insertFreshProduct(apple);
            System.out.println("Apple inserted with ID: " + appleId);
            System.out.println();

            System.out.println("--- Test 2: Insert Packaged Product ---");
            PackagedProduct pasta = new PackagedProduct(
                    0,
                    "Pasta",
                    350.00,
                    200,
                    "Barilla",
                    500.0
            );

            int pastaId = productDAO.insertPackagedProduct(pasta);
            System.out.println("Pasta inserted with ID: " + pastaId);
            System.out.println();

            System.out.println("--- Test 3: Insert Customer ---");
            Customer customer = new Customer(
                    0,
                    "Nurlan Bekov",
                    "Standard",
                    5000.00
            );

            int customerId = customerDAO.insertCustomer(customer);
            System.out.println("Customer inserted with ID: " + customerId);
            System.out.println();

            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║   ✅ ALL INSERTS COMPLETED           ║");
            System.out.println("╚══════════════════════════════════════╝");

        } catch (InvalidProductException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}