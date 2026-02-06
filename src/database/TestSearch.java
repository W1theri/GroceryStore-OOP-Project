package database;

import model.Product;
import java.util.List;


public class TestSearch {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      TEST SEARCH OPERATIONS          ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();

        ProductDAO productDAO = new ProductDAO();

        System.out.println(" Test 1: Search by Name (ILIKE) ");
        System.out.println("Searching for 'app'...");
        List<Product> results1 = productDAO.searchByName("app");
        displayResults(results1);

        System.out.println("\n\n Test 2: Search by Name (case-insensitive) ");
        System.out.println("Searching for 'RICE'...");
        List<Product> results2 = productDAO.searchByName("RICE");
        displayResults(results2);

        System.out.println("\n\n Test 3: Search by Price Range ");
        System.out.println("Searching for products between 400 and 1000 KZT...");
        List<Product> results3 = productDAO.searchByPriceRange(400, 1000);
        displayResults(results3);

        System.out.println("\n\n Test 4: Search by Minimum Price ");
        System.out.println("Searching for products >= 1000 KZT...");
        List<Product> results4 = productDAO.searchByMinPrice(1000);
        displayResults(results4);

        System.out.println("\n\n╔══════════════════════════════════════╗");
        System.out.println("║      ALL SEARCHES COMPLETED          ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    private static void displayResults(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println(" No products found");
            return;
        }

        System.out.println("\n📋 Results:");
        int count = 0;
        for (Product p : products) {
            count++;
            System.out.println(count + ". " + p.getName() +
                    " | Price: " + p.getFormattedPrice() +
                    " | Stock: " + p.getStockQuantity() +
                    " | Type: " + p.getProductType());
        }
        System.out.println("\n Total: " + count + " product(s)");
    }
}