void main() {
    System.out.println("=== Grocery Store Management System ===");
    System.out.println();

    // CREATE OBJECTS
    // Create Product objects
    Product product1 = new Product(101, "Milk", 650.0, 50);
    Product product2 = new Product(102, "Bread", 200.0, 100);
    Product product3 = new Product(103, "Eggs", 800.0, 30);
    Product product4 = new Product(); // Default constructor

    // Create Customer objects
    Customer customer1 = new Customer(1001, "Aidar Nurbek", "Standard", 15000.0);
    Customer customer2 = new Customer(1002, "Aliya Kairat", "Gold", 65000.0);
    Customer customer3 = new Customer(); // Default constructor

    // Create Sale objects
    Sale sale1 = new Sale(5001, "Aidar Nurbek", 3500.0, "2025-01-15");
    Sale sale2 = new Sale(5002, "Aliya Kairat", 0.0, "2025-01-15");

    // DISPLAY INITIAL STATE
    System.out.println("--- PRODUCTS ---");
    System.out.println(product1);
    System.out.println(product2);
    System.out.println(product3);
    System.out.println(product4);
    System.out.println();

    System.out.println("--- CUSTOMERS ---");
    System.out.println(customer1);
    System.out.println(customer2);
    System.out.println(customer3);
    System.out.println();

    System.out.println("--- SALES ---");
    System.out.println(sale1);
    System.out.println(sale2);
    System.out.println();

    System.out.println("--- TESTING GETTERS ---");
    System.out.println("Product 1 name: " + product1.getName());
    System.out.println("Product 1 price: " + product1.getPrice() + " KZT");
    System.out.println("Product 1 stock: " + product1.getStockQuantity() + " units");
    System.out.println("Customer 1 name: " + customer1.getName());
    System.out.println("Customer 1 membership: " + customer1.getMembershipLevel());
    System.out.println("Sale 1 total: " + sale1.getTotalAmount() + " KZT");
    System.out.println();

    System.out.println("--- TESTING SETTERS ---");
    System.out.println("Updating product4 information...");
    product4.setProductId(104);
    product4.setName("Cheese");
    product4.setPrice(1500.0);
    product4.setStockQuantity(25);
    System.out.println("Updated: " + product4);
    System.out.println();

    System.out.println("Updating customer3 information...");
    customer3.setCustomerId(1003);
    customer3.setName("Arman Bekzat");
    customer3.setMembershipLevel("Silver");
    customer3.setTotalPurchases(25000.0);
    System.out.println("Updated: " + customer3);
    System.out.println();

    System.out.println("--- TESTING PRODUCT METHODS ---");
    System.out.println(product1.getName() + " in stock: " + product1.isInStock());

    System.out.println("\nSelling 5 units of " + product1.getName());
    boolean sold = product1.sell(5);
    System.out.println("Sale successful: " + sold);
    System.out.println("New stock: " + product1.getStockQuantity() + " units");

    System.out.println("\nRestocking " + product2.getName() + " by 50 units");
    product2.restock(50);
    System.out.println("New stock: " + product2.getStockQuantity() + " units");

    System.out.println("\nApplying 15% discount to " + product3.getName());
    System.out.println("Old price: " + product3.getPrice() + " KZT");
    product3.applyDiscount(15);
    System.out.println("New price: " + product3.getPrice() + " KZT");
    System.out.println();

    System.out.println("--- TESTING CUSTOMER METHODS ---");
    System.out.println(customer1.getName() + " is VIP: " + customer1.isVIP());
    System.out.println(customer2.getName() + " is VIP: " + customer2.isVIP());

    System.out.println("\n" + customer1.getName() + " discount: " + customer1.getDiscountPercentage() + "%");
    System.out.println(customer2.getName() + " discount: " + customer2.getDiscountPercentage() + "%");

    System.out.println("\nAdding 40000 KZT purchase to " + customer1.getName());
    customer1.addPurchase(40000.0);
    System.out.println("Updated: " + customer1);
    System.out.println(customer1.getName() + " is now VIP: " + customer1.isVIP());
    System.out.println(customer1.getName() + " new discount: " + customer1.getDiscountPercentage() + "%");
    System.out.println();

    System.out.println("--- TESTING SALE METHODS ---");
    System.out.println("Sale " + sale2.getSaleId() + " initial total: " + sale2.getTotalAmount() + " KZT");

    System.out.println("\nAdding items to sale " + sale2.getSaleId());
    sale2.addItem(650.0);  // Milk
    sale2.addItem(200.0);  // Bread
    sale2.addItem(680.0);  // Eggs (after discount)
    System.out.println("New total: " + sale2.getTotalAmount() + " KZT");

    System.out.println("\nApplying 10% customer discount to sale " + sale2.getSaleId());
    sale2.applyDiscount(10);
    System.out.println("Total after discount: " + sale2.getTotalAmount() + " KZT");

    System.out.println("\nCalculating tax for sale " + sale2.getSaleId());
    System.out.println("Tax amount (12%): " + sale2.calculateTax() + " KZT");
    System.out.println("Total with tax: " + sale2.getTotalWithTax() + " KZT");

    System.out.println("\nSale " + sale1.getSaleId() + " is large sale: " + sale1.isLargeSale());
    System.out.println("Sale " + sale2.getSaleId() + " is large sale: " + sale2.isLargeSale());
    System.out.println();

    System.out.println("--- FINAL STATE ---");
    System.out.println("\nProducts:");
    System.out.println(product1);
    System.out.println(product2);
    System.out.println(product3);
    System.out.println(product4);

    System.out.println("\nCustomers:");
    System.out.println(customer1);
    System.out.println(customer2);
    System.out.println(customer3);

    System.out.println("\nSales:");
    System.out.println(sale1);
    System.out.println(sale2);

    System.out.println("\nProgram Complete ");
}