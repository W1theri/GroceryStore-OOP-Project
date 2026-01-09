import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Sale> sales = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        products.add(new Product(101, "Rice", 1200.0, 200));
        products.add(new FreshProduct(102, "Apple", 500.0, 150, "2025-01-20", true));
        products.add(new FreshProduct(103, "Tomato", 800.0, 100, "2025-01-18", false));
        products.add(new PackagedProduct(104, "Chocolate", 450.0, 80, "Rakhat", 100));
        products.add(new PackagedProduct(105, "Flour", 2500.0, 50, "Kazakhstan", 2000));

        customers.add(new Customer(1001, "Aidar Nurbek", "Standard", 15000.0));
        customers.add(new Customer(1002, "Aliya Kairat", "Gold", 65000.0));

        sales.add(new Sale(5001, "Aidar Nurbek", 3500.0, "2025-01-15"));

        boolean running = true;

        System.out.println("\nWelcome to Grocery Store Management System! ");
        System.out.println("Added New INHERITANCE & POLYMORPHISM! ");

        while (running) {
            displayMenu();
            int choice = getIntInput();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addGeneralProduct();
                    break;
                case 2:
                    addFreshProduct();
                    break;
                case 3:
                    addPackagedProduct();
                    break;
                case 4:
                    viewAllProducts();
                    break;
                case 5:
                    demonstratePolymorphism();
                    break;
                case 6:
                    viewFreshProductsOnly();
                    break;
                case 7:
                    viewPackagedProductsOnly();
                    break;
                case 8:
                    addCustomer();
                    break;
                case 9:
                    viewAllCustomers();
                    break;
                case 10:
                    addSale();
                    break;
                case 11:
                    viewAllSales();
                    break;
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
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     GROCERY STORE SYSTEM               ║");
        System.out.println("║     With Inheritance                   ║");
        System.out.println("╚════════════════════════════════════════╝");
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
        System.out.println("│  10. 🛍️  Add Sale                      │");
        System.out.println("│  11. 📊 View All Sales                 │");
        System.out.println("├────────────────────────────────────────┤");
        System.out.println("│  0. 🚪 Exit                            │");
        System.out.println("└────────────────────────────────────────┘");
        System.out.print("Enter your choice: ");
    }


    private static void addGeneralProduct() {
        System.out.println("\n--- ADD GENERAL PRODUCT ---");

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

        Product product = new Product(id, name, price, stock);
        products.add(product);

        System.out.println("\n✅ General product added successfully!");
        System.out.println(product);
    }

    private static void addFreshProduct() {
        System.out.println("\n--- ADD FRESH PRODUCT 🍎 ---");

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

        Product product = new FreshProduct(id, name, price, stock, expiryDate, isOrganic);
        products.add(product);

        System.out.println("\n✅ Fresh product added successfully!");
        System.out.println(product);
    }

    private static void addPackagedProduct() {
        System.out.println("\n--- ADD PACKAGED PRODUCT 📦 ---");

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

        Product product = new PackagedProduct(id, name, price, stock, manufacturer, weight);
        products.add(product);

        System.out.println("\n✅ Packaged product added successfully!");
        System.out.println(product);
    }

    private static void viewAllProducts() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     📋 ALL PRODUCTS (POLYMORPHIC)      ║");
        System.out.println("╚════════════════════════════════════════╝");

        if (products.isEmpty()) {
            System.out.println("❌ No products found.");
            return;
        }

        System.out.println("Total products: " + products.size());
        System.out.println("─────────────────────────────────────────");

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println("\n" + (i + 1) + ". " + p);

            if (p instanceof FreshProduct) {
                FreshProduct fresh = (FreshProduct) p;
                System.out.println("   🍎 Fresh Product Details:");
                System.out.println("   Expiry: " + fresh.getExpiryDate());
                System.out.println("   Days Until Expiry: " + fresh.getDaysUntilExpiry());
                if (fresh.isOrganic()) {
                    System.out.println("   🌿 ORGANIC CERTIFIED");
                }
            } else if (p instanceof PackagedProduct) {
                PackagedProduct packaged = (PackagedProduct) p;
                System.out.println("   📦 Packaged Product Details:");
                System.out.println("   Manufacturer: " + packaged.getManufacturer());
                System.out.println("   Weight: " + packaged.getWeight() + "g");
                System.out.println("   Price per kg: " + String.format("%.2f KZT", packaged.getPricePerKg()));

                if (packaged.isBulk()) {
                    System.out.println("   📦 BULK PACKAGE");
                }
            }

            if (p.isExpensive()) {
                System.out.println("   💎 Premium Product");
            }
        }
    }

    private static void demonstratePolymorphism() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   ✨ POLYMORPHISM DEMONSTRATION ✨     ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("\nCalling isInStock() on all products:");
        System.out.println("(Same method name, different behavior!)\n");
        System.out.println("─────────────────────────────────────────");

        for (Product p : products) {
            System.out.print(p.getName() + " - ");
            boolean inStock = p.isInStock();
            System.out.println(inStock ? "✅ In Stock" : "❌ Out of Stock");
            System.out.println("   Type: " + p.getProductType());
        }

        System.out.println("\n─────────────────────────────────────────");
        System.out.println("✨ Notice: Same method call (isInStock())");
        System.out.println("   but different logic for FreshProduct!");
        System.out.println("   This is POLYMORPHISM in action! 🎭");
    }

    private static void viewFreshProductsOnly() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       🍎 FRESH PRODUCTS ONLY 🍎        ║");
        System.out.println("╚════════════════════════════════════════╝");

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

    private static void viewPackagedProductsOnly() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     📦 PACKAGED PRODUCTS ONLY 📦       ║");
        System.out.println("╚════════════════════════════════════════╝");

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



    private static void addCustomer() {
        System.out.println("\n--- ADD CUSTOMER ---");

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
    }

    private static void viewAllCustomers() {
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


    private static void addSale() {
        System.out.println("\n--- ADD SALE ---");

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


    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("❌ Invalid input! Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("❌ Invalid input! Please enter a number: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    private static boolean getBooleanInput() {
        while (!scanner.hasNextBoolean()) {
            System.out.print("❌ Invalid input! Please enter true or false: ");
            scanner.next();
        }
        return scanner.nextBoolean();
    }
}