package menu;

import model.*;
import database.ProductDAO;
import database.CustomerDAO;
import exception.InvalidProductException;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;


public class MenuManager implements Menu {
    // NO ArrayLists! Fully database-driven ✅
    private Scanner scanner;
    private ProductDAO productDAO;
    private CustomerDAO customerDAO;

    public MenuManager() {
        this.scanner = new Scanner(System.in);
        this.productDAO = new ProductDAO();
        this.customerDAO = new CustomerDAO();
    }

    @Override
    public void displayMenu() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║     GROCERY STORE SYSTEM - WEEK 8     ║");
        System.out.println("║     Complete CRUD + Search            ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("┌────────────────────────────────────────┐");
        System.out.println("│  PRODUCT MANAGEMENT                    │");
        System.out.println("│  1. 🍎 Add Fresh Product               │");
        System.out.println("│  2. 📦 Add Packaged Product            │");
        System.out.println("│  3. 📋 View All Products               │");
        System.out.println("│  4. 🍎 View Fresh Products Only        │");
        System.out.println("│  5. 📦 View Packaged Products Only     │");
        System.out.println("│  6. ✏️  Update Product                 │");
        System.out.println("│  7. 🗑️  Delete Product                 │");
        System.out.println("├────────────────────────────────────────┤");
        System.out.println("│  SEARCH & FILTER                       │");
        System.out.println("│  8. 🔍 Search by Name                  │");
        System.out.println("│  9. 💰 Search by Price Range           │");
        System.out.println("│ 10. 💎 High-Priced Products (>= X)     │");
        System.out.println("├────────────────────────────────────────┤");
        System.out.println("│  CUSTOMER MANAGEMENT                   │");
        System.out.println("│ 11. 👤 Add Customer                    │");
        System.out.println("│ 12. 👥 View All Customers              │");
        System.out.println("├────────────────────────────────────────┤");
        System.out.println("│  0. 🚪 Exit                            │");
        System.out.println("└────────────────────────────────────────┘");
        System.out.print("Enter your choice: ");
    }

    @Override
    public void run() {
        boolean running = true;
        System.out.println("\n🎉 Welcome to Grocery Store Management System!");
        System.out.println("✨ Week 8: Complete CRUD + Advanced Search!");

        while (running) {
            displayMenu();
            try {
                int choice = getIntInput();
                scanner.nextLine();

                switch (choice) {
                    case 1: addFreshProduct(); break;
                    case 2: addPackagedProduct(); break;
                    case 3: viewAllProducts(); break;
                    case 4: viewFreshProductsOnly(); break;
                    case 5: viewPackagedProductsOnly(); break;
                    case 6: updateProduct(); break;
                    case 7: deleteProduct(); break;
                    case 8: searchByName(); break;
                    case 9: searchByPriceRange(); break;
                    case 10: searchByMinPrice(); break;
                    case 11: addCustomer(); break;
                    case 12: viewAllCustomers(); break;
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

    // ========================================
    // CREATE OPERATIONS
    // ========================================

    private void addFreshProduct() {
        System.out.println("\n--- ADD FRESH PRODUCT 🍎 ---");
        try {
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

            FreshProduct product = new FreshProduct(0, name, price, stock, expiryDate, isOrganic);
            int id = productDAO.insertFreshProduct(product);

            if (id > 0) {
                System.out.println("\n✅ Fresh product added successfully! ID: " + id);
            }
        } catch (InvalidProductException e) {
            System.out.println("\n❌ Failed to add product: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\n❌ Invalid input format!");
            scanner.nextLine();
        }
    }

    private void addPackagedProduct() {
        System.out.println("\n--- ADD PACKAGED PRODUCT 📦 ---");
        try {
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

            PackagedProduct product = new PackagedProduct(0, name, price, stock, manufacturer, weight);
            int id = productDAO.insertPackagedProduct(product);

            if (id > 0) {
                System.out.println("\n✅ Packaged product added successfully! ID: " + id);
            }
        } catch (InvalidProductException e) {
            System.out.println("\n❌ Failed to add product: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\n❌ Invalid input format!");
            scanner.nextLine();
        }
    }

    // ========================================
    // READ OPERATIONS
    // ========================================

    private void viewAllProducts() {
        // Load from database on demand ✅
        productDAO.displayAllProducts();
    }

    private void viewFreshProductsOnly() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║       🍎 FRESH PRODUCTS ONLY 🍎       ║");
        System.out.println("╚══════════════════════════════════════╝");

        List<FreshProduct> products = productDAO.getAllFreshProducts();

        if (products.isEmpty()) {
            System.out.println("❌ No fresh products found.");
            return;
        }

        int count = 0;
        for (FreshProduct p : products) {
            count++;
            System.out.println("\n" + count + ". " + p.getName());
            p.displayProductDetails();
        }

        System.out.println("\n📊 Total fresh products: " + count);
    }

    private void viewPackagedProductsOnly() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║     📦 PACKAGED PRODUCTS ONLY 📦      ║");
        System.out.println("╚══════════════════════════════════════╝");

        List<PackagedProduct> products = productDAO.getAllPackagedProducts();

        if (products.isEmpty()) {
            System.out.println("❌ No packaged products found.");
            return;
        }

        int count = 0;
        for (PackagedProduct p : products) {
            count++;
            System.out.println("\n" + count + ". " + p.getName());
            p.displayProductDetails();
        }

        System.out.println("\n📊 Total packaged products: " + count);
    }

    // ========================================
    // UPDATE OPERATION - Week 8
    // ========================================

    private void updateProduct() {
        System.out.println("\n--- UPDATE PRODUCT ✏️ ---");
        System.out.print("Enter Product ID to update: ");
        int productId = getIntInput();
        scanner.nextLine();

        Product existingProduct = productDAO.getProductById(productId);

        if (existingProduct == null) {
            System.out.println("❌ No product found with ID: " + productId);
            return;
        }

        System.out.println("\n📋 Current Info:");
        existingProduct.displayProductDetails();

        try {
            System.out.print("\nNew Name [" + existingProduct.getName() + "]: ");
            String newName = scanner.nextLine();
            if (newName.trim().isEmpty()) {
                newName = existingProduct.getName();
            }

            System.out.print("New Price [" + existingProduct.getPrice() + "]: ");
            String priceInput = scanner.nextLine();
            double newPrice = priceInput.trim().isEmpty() ?
                    existingProduct.getPrice() :
                    Double.parseDouble(priceInput);

            System.out.print("New Stock [" + existingProduct.getStockQuantity() + "]: ");
            String stockInput = scanner.nextLine();
            int newStock = stockInput.trim().isEmpty() ?
                    existingProduct.getStockQuantity() :
                    Integer.parseInt(stockInput);

            if (existingProduct instanceof FreshProduct) {
                FreshProduct fresh = (FreshProduct) existingProduct;

                System.out.print("New Expiry Date [" + fresh.getExpiryDate() + "]: ");
                String newExpiry = scanner.nextLine();
                if (newExpiry.trim().isEmpty()) {
                    newExpiry = fresh.getExpiryDate();
                }

                System.out.print("Is Organic? [" + fresh.isOrganic() + "] (true/false): ");
                String organicInput = scanner.nextLine();
                boolean newOrganic = organicInput.trim().isEmpty() ?
                        fresh.isOrganic() :
                        Boolean.parseBoolean(organicInput);

                FreshProduct updatedProduct = new FreshProduct(
                        productId, newName, newPrice, newStock, newExpiry, newOrganic
                );

                if (productDAO.updateFreshProduct(updatedProduct)) {
                    System.out.println("\n✅ Product updated successfully!");
                } else {
                    System.out.println("\n❌ Update failed!");
                }

            } else if (existingProduct instanceof PackagedProduct) {
                PackagedProduct packaged = (PackagedProduct) existingProduct;

                System.out.print("New Manufacturer [" + packaged.getManufacturer() + "]: ");
                String newManufacturer = scanner.nextLine();
                if (newManufacturer.trim().isEmpty()) {
                    newManufacturer = packaged.getManufacturer();
                }

                System.out.print("New Weight [" + packaged.getWeight() + "]: ");
                String weightInput = scanner.nextLine();
                double newWeight = weightInput.trim().isEmpty() ?
                        packaged.getWeight() :
                        Double.parseDouble(weightInput);

                PackagedProduct updatedProduct = new PackagedProduct(
                        productId, newName, newPrice, newStock, newManufacturer, newWeight
                );

                if (productDAO.updatePackagedProduct(updatedProduct)) {
                    System.out.println("\n✅ Product updated successfully!");
                } else {
                    System.out.println("\n❌ Update failed!");
                }
            }

        } catch (InvalidProductException e) {
            System.out.println("\n❌ Failed to update: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\n❌ Invalid number format!");
        }
    }

    // ========================================
    // DELETE OPERATION - Week 8
    // ========================================

    private void deleteProduct() {
        System.out.println("\n--- DELETE PRODUCT 🗑️ ---");
        System.out.print("Enter Product ID to delete: ");
        int productId = getIntInput();
        scanner.nextLine();

        Product product = productDAO.getProductById(productId);

        if (product == null) {
            System.out.println("❌ No product found with ID: " + productId);
            return;
        }

        System.out.println("\n⚠️  Product to delete:");
        product.displayProductDetails();

        System.out.print("\n❗ Are you sure you want to delete this product? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            if (productDAO.deleteProduct(productId)) {
                System.out.println("\n✅ Product deleted successfully!");
            } else {
                System.out.println("\n❌ Deletion failed!");
            }
        } else {
            System.out.println("\n🚫 Deletion cancelled.");
        }
    }

    // ========================================
    // SEARCH OPERATIONS - Week 8
    // ========================================

    private void searchByName() {
        System.out.println("\n--- SEARCH BY NAME 🔍 ---");
        System.out.print("Enter product name (partial match): ");
        String name = scanner.nextLine();

        List<Product> products = productDAO.searchByName(name);

        if (products.isEmpty()) {
            System.out.println("\n❌ No products found matching: " + name);
            return;
        }

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║       SEARCH RESULTS                  ║");
        System.out.println("╚══════════════════════════════════════╝");

        int count = 0;
        for (Product p : products) {
            count++;
            System.out.println("\n" + count + ". " + p.getName());
            p.displayProductDetails();
        }

        System.out.println("\n📊 Total found: " + count);
    }

    private void searchByPriceRange() {
        System.out.println("\n--- SEARCH BY PRICE RANGE 💰 ---");

        System.out.print("Enter minimum price (KZT): ");
        double minPrice = getDoubleInput();
        scanner.nextLine();

        System.out.print("Enter maximum price (KZT): ");
        double maxPrice = getDoubleInput();
        scanner.nextLine();

        List<Product> products = productDAO.searchByPriceRange(minPrice, maxPrice);

        if (products.isEmpty()) {
            System.out.println("\n❌ No products found in range: " +
                    String.format("%.2f - %.2f KZT", minPrice, maxPrice));
            return;
        }

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║       PRODUCTS IN PRICE RANGE         ║");
        System.out.println("╚══════════════════════════════════════╝");

        int count = 0;
        for (Product p : products) {
            count++;
            System.out.println("\n" + count + ". " + p.getName());
            p.displayProductDetails();
        }

        System.out.println("\n📊 Total found: " + count);
    }

    private void searchByMinPrice() {
        System.out.println("\n--- HIGH-PRICED PRODUCTS 💎 ---");

        System.out.print("Enter minimum price (KZT): ");
        double minPrice = getDoubleInput();
        scanner.nextLine();

        List<Product> products = productDAO.searchByMinPrice(minPrice);

        if (products.isEmpty()) {
            System.out.println("\n❌ No products found with price >= " +
                    String.format("%.2f KZT", minPrice));
            return;
        }

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║     PRODUCTS >= " + String.format("%.2f KZT", minPrice) + "        ║");
        System.out.println("╚══════════════════════════════════════╝");

        int count = 0;
        for (Product p : products) {
            count++;
            System.out.println("\n" + count + ". " + p.getName() + " - " + p.getFormattedPrice());
            p.displayProductDetails();
        }

        System.out.println("\n📊 Total found: " + count);
    }



    private void addCustomer() {
        System.out.println("\n--- ADD CUSTOMER 👤 ---");
        try {
            System.out.print("Enter customer name: ");
            String name = scanner.nextLine();

            System.out.print("Enter membership level: ");
            String membership = scanner.nextLine();

            System.out.print("Enter total purchases (KZT): ");
            double purchases = getDoubleInput();
            scanner.nextLine();

            Customer customer = new Customer(0, name, membership, purchases);
            int id = customerDAO.insertCustomer(customer);

            if (id > 0) {
                System.out.println("\n✅ Customer added successfully! ID: " + id);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ Failed to add customer: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\n❌ Invalid input format!");
            scanner.nextLine();
        }
    }

    private void viewAllCustomers() {
        customerDAO.getAllCustomers();
    }



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