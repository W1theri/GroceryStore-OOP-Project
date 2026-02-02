package database;


public class TestDelete {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      TEST DELETE OPERATIONS          ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();

        ProductDAO productDAO = new ProductDAO();

        System.out.println("--- BEFORE DELETE ---");
        productDAO.displayAllProducts();

        System.out.println("\n\n--- Test: Delete Product ---");
        System.out.println("⚠️  WARNING: This will delete a product from database!");
        System.out.println("Attempting to delete product with ID 1 (Rice)...");

        boolean deleted = productDAO.deleteProduct(1);

        if (deleted) {
            System.out.println("✅ Delete test passed");
        } else {
            System.out.println("❌ Delete test failed");
        }

        System.out.println("\n\n--- AFTER DELETE ---");
        productDAO.displayAllProducts();

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║   ✅ DELETE TEST COMPLETED           ║");
        System.out.println("╚══════════════════════════════════════╝");

        System.out.println("\n⚠️  Note: Product ID 1 should no longer appear");
        System.out.println("Verify in pgAdmin: SELECT * FROM product;");
    }
}