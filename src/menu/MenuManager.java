package menu;

import model.*;
import exception.InvalidProductException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;


public class MenuManager implements Menu {
    private ArrayList<Product> products;
    private ArrayList<Customer> customers;
    private ArrayList<Sale> sales;
    private Scanner scanner;

    public MenuManager() {
        this.products = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.sales = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        loadTestData();
    }

    private void loadTestData() {
        try {
            products.add(new Product(101, "Rice", 1200.0, 200) {
                @Override
                public String getProductType() {
                    return "General Product";
                }

                @Override
                public void displayProductDetails() {
                    System.out.println("📦 General Product: " + getName());
                    System.out.println("   Stock: " + getStockQuantity());
                    System.out.println("   Price: " + getFormattedPrice());
                }
            });
            products.add(new FreshProduct(102, "Apple", 500.0, 150, "2025-01-20", true));
            products.add(new FreshProduct(103, "Tomato", 800.0, 100, "2025-01-18", false));
            products.add(new PackagedProduct(104, "Chocolate", 450.0, 80, "Rakhat", 100));
            products.add(new PackagedProduct(105, "Flour", 2500.0, 50, "Kazakhstan", 2000));

            customers.add(new Customer(1001, "Aidar Nurbek", "Standard", 15000.0));
            customers.add(new Customer(1002, "Aliya Kairat", "Gold", 65000.0));

            sales.add(new Sale(5001, "Aidar Nurbek", 3500.0, "2025-01-15"));
        } catch (Exception e) {
            System.out.println("⚠️ Error loading test data: " + e.getMessage());
        }
    }

    @Override
    public void displayMenu() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║     GROCERY STORE SYSTEM              ║");
        System.out.println("║     With Interfaces & Exceptions      ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println("┌────────────────────────────────────────┐");
        System.out.println("│  PRODUCT MANAGEMENT                    │");
        System.out.println("│  1. 📦 Add General Product             │");
        System.out.println("│  2. 🍎 Add Fresh Product               │");
        System.out.println("│  3. 📦 Add Packaged Product            │");
        System.out.println("│  4. 📋 View All Products (Polymorphic) │");
        System.out.println("│  5. ✨ Demonstrate Polymorphism        │");
        System.out.println("│  6. 🍎 View Fresh Products Only        │");
        System.out.println("│  7. 📦 View Packaged Products Only     │");
        System.out.println("├────────────────────────────────────────┤");
        System.out.println("│  CUSTOMER & SALES                      │");
        System.out.println("│  8. 👤 Add Customer                    │");
        System.out.println("│  9. 👥 View All Customers              │");
        System.out.println("│  10. 🛒 Add Sale                       │");
        System.out.println("│  11. 📊 View All Sales                 │");
        System.out.println("├────────────────────────────────────────┤");
        System.out.println("│  0. 🚪 Exit                            │");
        System.out.println("└────────────────────────────────────────┘");
        System.out.print("Enter your choice: ");
    }

    @Override
    public void run() {
        boolean running = true;
        System.out.println("\n🎉 Welcome to Grocery Store Management System!");
        System.out.println("✨ Now with Abstract Classes, Interfaces & Exception Handling!");

        while (running) {
            displayMenu();
            try {
                int choice = getIntInput();
                scanner.nextLine();

                switch (choice) {
                    case 1: addGeneralProduct(); break;
                    case 2: addFreshProduct(); break;
                    case 3: addPackagedProduct(); break;
                    case 4: viewAllProducts(); break;
                    case 5: demonstratePolymorphism(); break;
                    case 6: viewFreshProductsOnly(); break;
                    case 7: viewPackagedProductsOnly(); break;
                    case 8: addCustomer(); break;
                    case 9: viewAllCustomers(); break;
                    case 10: addSale(); break;
                    case 11: viewAllSales(); break;
                    case 0:
                        System.out.println("\n👋 Thank you for using Grocery Store System! Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("\n❌ Invalid choice! Please enter a valid number.");
                }

                if (running) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("\n❌ Error: Please enter a valid number!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("\n❌ Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private void addGeneralProduct() {
        System.out.println("\n--- ADD GENERAL PRODUCT ---");
        try {
            System.out.print("Enter product ID: ");
            int id = getIntInput();
            scanner.nextLine();

            System.out.print("Enter product name: ");
            String name = scanner.nextLine();

            System.out.print("Enter price (KZT): ");
            double price = getDoubleInput();
            scanner.nextLine();

            System.out.print("Enter stock quantity: ");
            int stock = getIntInput();
            scanner.nextLine();

            Product product = new Product(id, name, price, stock) {
                @Override
                public String getProductType() {
                    return "General Product";
                }

                @Override
                public void displayProductDetails() {
                    System.out.println("📦 General Product: " + getName());
                    System.out.println("   Stock: " + getStockQuantity());
                    System.out.println("   Price: " + getFormattedPrice());
                }
            };
            products.add(product);

            System.out.println("\n✅ General product added successfully!");
            System.out.println(product);
        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ Failed to add product: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\n❌ Invalid input format!");
            scanner.nextLine();
        }
    }

    private void addFreshProduct() {
        System.out.println("\n--- ADD FRESH PRODUCT 🍎 ---");
        try {
            System.out.print("Enter product ID: ");
            int id = getIntInput();
            scanner.nextLine();

            System.out.print("Enter product name: ");
            String name = scanner.nextLine();

            System.out.print("Enter price (KZT): ");
            double price = getDoubleInput();
            scanner.nextLine();

            System.out.print("Enter stock quantity: ");
            int stock = getIntInput();
            scanner.nextLine();

            System.out.print("Enter expiry date (YYYY-MM-DD): ");
            String expiryDate = scanner.nextLine();

            System.out.print("Is organic? (true/false): ");
            boolean isOrganic = getBooleanInput();
            scanner.nextLine();

            FreshProduct product = new FreshProduct(id, name, price, stock, expiryDate, isOrganic);
            products.add(product);

            System.out.println("\n✅ Fresh product added successfully!");
            System.out.println(product);
        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ Failed to add product: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\n❌ Invalid input format!");
            scanner.nextLine();
        }
    }

    private void addPackagedProduct() {
        System.out.println("\n--- ADD PACKAGED PRODUCT 📦 ---");
        try {
            System.out.print("Enter product ID: ");
            int id = getIntInput();
            scanner.nextLine();

            System.out.print("Enter product name: ");
            String name = scanner.nextLine();

            System.out.print("Enter price (KZT): ");
            double price = getDoubleInput();
            scanner.nextLine();

            System.out.print("Enter stock quantity: ");
            int stock = getIntInput();
            scanner.nextLine();

            System.out.print("Enter manufacturer: ");
            String manufacturer = scanner.nextLine();

            System.out.print("Enter weight (grams): ");
            double weight = getDoubleInput();
            scanner.nextLine();

            PackagedProduct product = new PackagedProduct(id, name, price, stock, manufacturer, weight);
            products.add(product);

            System.out.println("\n✅ Packaged product added successfully!");
            System.out.println(product);
        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ Failed to add product: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\n❌ Invalid input format!");
            scanner.nextLine();
        }
    }

    private void viewAllProducts() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║     📋 ALL PRODUCTS (POLYMORPHIC)     ║");
        System.out.println("╚═══════════════════════════════════════╝");

        if (products.isEmpty()) {
            System.out.println("❌ No products found.");
            return;
        }

        System.out.println("Total products: " + products.size());
        System.out.println("─────────────────────────────────────────");

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println("\n" + (i + 1) + ". " + p);
            p.displayProductDetails(); // Polymorphic call!

            if (p instanceof FreshProduct) {
                FreshProduct fresh = (FreshProduct) p;
                if (fresh.isOrganic()) {
                    System.out.println("   🌿 ORGANIC CERTIFIED");
                }
            } else if (p instanceof PackagedProduct) {
                PackagedProduct packaged = (PackagedProduct) p;
                if (packaged.isBulk()) {
                    System.out.println("   📦 BULK PACKAGE");
                }
            }

            if (p.isExpensive()) {
                System.out.println("   💎 Premium Product");
            }
        }
    }

    private void demonstratePolymorphism() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║   ✨ POLYMORPHISM DEMONSTRATION ✨    ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println("\nCalling displayProductDetails() on all products:");
        System.out.println("(Same method name, different behavior!)\n");
        System.out.println("─────────────────────────────────────────");

        for (Product p : products) {
            p.displayProductDetails(); // Polymorphic behavior!
            System.out.println();
        }

        System.out.println("─────────────────────────────────────────");
        System.out.println("✨ Notice: Same method call (displayProductDetails())");
        System.out.println("   but different implementation for each type!");
        System.out.println("   This is POLYMORPHISM in action! 🎭");
    }

    private void viewFreshProductsOnly() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║       🍎 FRESH PRODUCTS ONLY 🍎       ║");
        System.out.println("╚═══════════════════════════════════════╝");

        int count = 0;
        for (Product p : products) {
            if (p instanceof FreshProduct) {
                count++;
                FreshProduct fresh = (FreshProduct) p;
                System.out.println("\n" + count + ". " + fresh.getName());
                fresh.displayFreshnessInfo();
            }
        }

        if (count == 0) {
            System.out.println("❌ No fresh products found.");
        } else {
            System.out.println("\n📊 Total fresh products: " + count);
        }
    }

    private void viewPackagedProductsOnly() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║     📦 PACKAGED PRODUCTS ONLY 📦      ║");
        System.out.println("╚═══════════════════════════════════════╝");

        int count = 0;
        for (Product p : products) {
            if (p instanceof PackagedProduct) {
                count++;
                PackagedProduct packaged = (PackagedProduct) p;
                System.out.println("\n" + count + ". " + packaged.getName());
                packaged.displayPackageInfo();
            }
        }

        if (count == 0) {
            System.out.println("❌ No packaged products found.");
        } else {
            System.out.println("\n📊 Total packaged products: " + count);
        }
    }

    private void addCustomer() {
        System.out.println("\n--- ADD CUSTOMER ---");
        try {
            System.out.print("Enter customer ID: ");
            int id = getIntInput();
            scanner.nextLine();

            System.out.print("Enter customer name: ");
            String name = scanner.nextLine();

            System.out.print("Enter membership level (Standard/Silver/Gold/Platinum): ");
            String membership = scanner.nextLine();

            System.out.print("Enter total purchases (KZT): ");
            double purchases = getDoubleInput();
            scanner.nextLine();

            Customer customer = new Customer(id, name, membership, purchases);
            customers.add(customer);

            System.out.println("\n✅ Customer added successfully!");
            System.out.println(customer);
        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ Failed to add customer: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\n❌ Invalid input format!");
            scanner.nextLine();
        }
    }

    private void viewAllCustomers() {
        System.out.println("\n========================================");
        System.out.println("          ALL CUSTOMERS");
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

    private void addSale() {
        System.out.println("\n--- ADD SALE ---");
        try {
            System.out.print("Enter sale ID: ");
            int id = getIntInput();
            scanner.nextLine();

            System.out.print("Enter customer name: ");
            String customerName = scanner.nextLine();

            System.out.print("Enter total amount (KZT): ");
            double amount = getDoubleInput();
            scanner.nextLine();

            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            Sale sale = new Sale(id, customerName, amount, date);
            sales.add(sale);

            System.out.println("\n✅ Sale added successfully!");
            System.out.println(sale);
        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ Failed to add sale: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\n❌ Invalid input format!");
            scanner.nextLine();
        }
    }

    private void viewAllSales() {
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

    // INPUT HELPER METHODS with exception handling
    private int getIntInput() throws InputMismatchException {
        if (!scanner.hasNextInt()) {
            scanner.next();
            throw new InputMismatchException("Expected a number");
        }
        return scanner.nextInt();
    }

    private double getDoubleInput() throws InputMismatchException {
        if (!scanner.hasNextDouble()) {
            scanner.next();
            throw new InputMismatchException("Expected a decimal number");
        }
        return scanner.nextDouble();
    }

    private boolean getBooleanInput() throws InputMismatchException {
        if (!scanner.hasNextBoolean()) {
            scanner.next();
            throw new InputMismatchException("Expected true or false");
        }
        return scanner.nextBoolean();
    }
}