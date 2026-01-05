public class FreshProduct extends Product {
    private String expiryDate;
    private boolean isOrganic;

    public FreshProduct(int productId, String name, double price, int stockQuantity,
                        String expiryDate, boolean isOrganic) {
        super(productId, name, price, stockQuantity);
        this.expiryDate = expiryDate;
        this.isOrganic = isOrganic;
    }

    public FreshProduct() {
        super();
        this.expiryDate = "Not set";
        this.isOrganic = false;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        if (expiryDate != null && !expiryDate.trim().isEmpty()) {
            this.expiryDate = expiryDate;
        } else {
            System.out.println("⚠️ Warning: Expiry date cannot be empty!");
            this.expiryDate = "Not set";
        }
    }

    public boolean isOrganic() {
        return isOrganic;
    }

    public void setOrganic(boolean organic) {
        isOrganic = organic;
    }

    @Override
    public boolean isInStock() {
        return getStockQuantity() > 0 && !isExpired();
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Expiry: " + expiryDate +
                (isOrganic ? " | 🌿 ORGANIC" : "");
    }

    public boolean isExpired() {
        return expiryDate.equals("Expired");
    }

    public void markAsExpired() {
        this.expiryDate = "Expired";
        setStockQuantity(0); // Убираем из продажи
        System.out.println("⚠️ Product " + getName() + " has been marked as expired!");
    }

    public int getDaysUntilExpiry() {
        if (isExpired()) {
            return 0;
        }
        return 7;
    }

    public void displayFreshnessInfo() {
        System.out.println("🍎 Fresh Product: " + getName());
        System.out.println("   Expiry Date: " + expiryDate);
        System.out.println("   Days Until Expiry: " + getDaysUntilExpiry());
        System.out.println("   Organic: " + (isOrganic ? "Yes 🌿" : "No"));
        System.out.println("   Status: " + (isExpired() ? "❌ EXPIRED" : "✅ FRESH"));
    }
}