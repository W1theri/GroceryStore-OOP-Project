import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Sale> sales = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        products.add(new Product(101, "Milk", 650.0, 50));
        products.add(new Product(102, "Bread", 200.0, 100));
        products.add(new Product(103, "Eggs", 800.0, 30));

        customers.add(new Customer(1001, "Aidar Nurbek", "Standard", 15000.0));
        customers.add(new Customer(1002, "Aliya Kairat", "Gold", 65000.0));
        customers.add(new Customer(1003, "Arman Bekzat", "Silver", 25000.0));

        sales.add(new Sale(5001, "Aidar Nurbek", 3500.0, "2025-01-15"));
        sales.add(new Sale(5002, "Aliya Kairat", 12000.0, "2025-01-15"));

        // Menu loop
        boolean running = true;

        System.out.println("\n🎉 Welcome to Grocery Store Management System! 🎉");

        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewAllProducts();
                    break;
                case 3:
                    addCustomer();
                    break;
                case 4:
                    viewAllCustomers();
                    break;
                case 5:
                    addSale();
                    break;
                case 6:
                    viewAllSales();
                    break;
                case 0:
                    System.out.println("\n👋 Thank you for using Grocery Store System! Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("\n❌ Invalid choice! Please enter a number from the menu. ");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("    🛒 GROCERY STORE SYSTEM 🛒");
        System.out.println("========================================");
        System.out.println("1. Add Product");
        System.out.println("2. View All Products");
        System.out.println("3. Add Customer");
        System.out.println("4. View All Customers");
        System.out.println("5. Add Sale");
        System.out.println("6. View All Sales");
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }

    private static void addProduct() {
        System.out.println("\n--- ADD PRODUCT ---");

        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter price (KZT): ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter stock quantity: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Product product = new Product(id, name, price, stock);
        products.add(product);

        System.out.println("\n✅ Product added successfully!");
        System.out.println(product);
    }

    private static void viewAllProducts() {
        System.out.println("\n========================================");
        System.out.println("          ALL PRODUCTS");
        System.out.println("========================================");

        if (products.isEmpty()) {
            System.out.println("❌ No products found.");
            return;
        }

        System.out.println("Total products: " + products.size());
        System.out.println();

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i + 1) + ". " + product.getName() +
                    " - " + product.getFormattedPrice());
            System.out.println("   ID: " + product.getProductId());
            System.out.println("   Stock: " + product.getStockQuantity() + " units");
            System.out.println("   In Stock: " + (product.isInStock() ? "✅ Yes" : "❌ No"));

            if (product.isExpensive()) {
                System.out.println("   💎 Premium Product");
            }
            System.out.println();
        }
    }

    private static void addCustomer() {
        System.out.println("\n--- ADD CUSTOMER ---");

        System.out.print("Enter customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter membership level (Standard/Silver/Gold/Platinum): ");
        String membership = scanner.nextLine();

        System.out.print("Enter total purchases (KZT): ");
        double purchases = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Customer customer = new Customer(id, name, membership, purchases);
        customers.add(customer);

        System.out.println("\n✅ Customer added successfully!");
        System.out.println(customer);
    }

    private static void viewAllCustomers() {
        System.out.println("\n========================================");
        System.out.println("          ALL CUSTOMEРS");
        System.out.println("========================================");

        if (customers.isEmpty()) {
            System.out.println("❌ No customers found.");
            return;
        }

        System.out.println("Total customers: " + customers.size());
        System.out.println();

        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            System.out.println((i + 1) + ". " + customer.getName());
            System.out.println("   ID: " + customer.getCustomerId());
            System.out.println("   Membership: " + customer.getMembershipLevel());
            System.out.println("   Total Purchases: " + customer.getFormattedPurchases());
            System.out.println("   Discount: " + customer.getDiscountPercentage() + "%");
            System.out.println("   VIP Status: " + (customer.isVIP() ? "⭐ Yes" : "No"));
            System.out.println();
        }
    }

    private static void addSale() {
        System.out.println("\n--- ADD SALE ---");

        System.out.print("Enter sale ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter total amount (KZT): ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Sale sale = new Sale(id, customerName, amount, date);
        sales.add(sale);

        System.out.println("\n✅ Sale added successfully!");
        System.out.println(sale);
    }

    private static void viewAllSales() {
        System.out.println("\n========================================");
        System.out.println("           ALL SALES");
        System.out.println("========================================");

        if (sales.isEmpty()) {
            System.out.println("❌ No sales found.");
            return;
        }

        System.out.println("Total sales: " + sales.size());
        System.out.println();

        double totalRevenue = 0;

        for (int i = 0; i < sales.size(); i++) {
            Sale sale = sales.get(i);
            System.out.println((i + 1) + ". Sale #" + sale.getSaleId());
            System.out.println("   Customer: " + sale.getCustomerName());
            System.out.println("   Amount: " + sale.getFormattedTotal());
            System.out.println("   Tax (12%): " + String.format("%.2f KZT", sale.calculateTax()));
            System.out.println("   Total with Tax: " + sale.getFormattedTotalWithTax());
            System.out.println("   Date: " + sale.getDate());
            System.out.println("   " + (sale.isLargeSale() ? "💰 Large Sale" : "Regular Sale"));
            System.out.println();

            totalRevenue += sale.getTotalAmount();
        }

        System.out.println("📊 Total Revenue: " + String.format("%.2f KZT", totalRevenue));
    }
}