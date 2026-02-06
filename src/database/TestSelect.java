package database;


public class TestSelect {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      TEST SELECT OPERATIONS          ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();

        ProductDAO productDAO = new ProductDAO();
        CustomerDAO customerDAO = new CustomerDAO();

        System.out.println(" Test 1: Get All Products ");
        productDAO.displayAllProducts();

        System.out.println("\n\n Test 2: Get Fresh Products Only ");
        productDAO.getAllFreshProducts();

        System.out.println("\n\n Test 3: Get All Customers ");
        customerDAO.getAllCustomers();

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║      ALL SELECTS COMPLETED!          ║");
        System.out.println("╚══════════════════════════════════════╝");
    }
}