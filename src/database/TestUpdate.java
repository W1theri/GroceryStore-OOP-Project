package database;

import model.*;
import exception.InvalidProductException;


public class TestUpdate {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      TEST UPDATE OPERATIONS          ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();

        ProductDAO productDAO = new ProductDAO();

        try {
            System.out.println("--- Test 1: Update Fresh Product ---");
            FreshProduct updatedApple = new FreshProduct(
                    2,
                    "Green Apple (Updated)",
                    550.00,
                    175,
                    "2025-03-15",
                    true
            );

            if (productDAO.updateFreshProduct(updatedApple)) {
                System.out.println("✅ Test 1 Passed");
            } else {
                System.out.println("❌ Test 1 Failed");
            }
            System.out.println();

            System.out.println("--- Test 2: Update Packaged Product ---");
            PackagedProduct updatedChocolate = new PackagedProduct(
                    4,
                    "Dark Chocolate (Updated)",
                    500.00,
                    100,
                    "Rakhat Premium",
                    150.0
            );

            if (productDAO.updatePackagedProduct(updatedChocolate)) {
                System.out.println("✅ Test 2 Passed");
            } else {
                System.out.println("❌ Test 2 Failed");
            }
            System.out.println();

            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║   ✅ ALL UPDATES COMPLETED           ║");
            System.out.println("╚══════════════════════════════════════╝");

            System.out.println("\nVerify in database:");
            System.out.println("SELECT * FROM product WHERE product_id IN (2, 4);");

        } catch (InvalidProductException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}