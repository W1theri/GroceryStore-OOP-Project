public class Product {
    // PRIVATE FIELDS
    private int productId;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(int productId, String name, double price, int stockQuantity) {
        this.productId = productId;
        setName(name);
        setPrice(price);
        setStockQuantity(stockQuantity);
    }

    public Product() {
        this.productId = 0;
        this.name = "Unknown Product";
        this.price = 0.0;
        this.stockQuantity = 0;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    // SETTERS WITH VALIDATION
    public void setProductId(int productId) {
        if (productId > 0) {
            this.productId = productId;
        } else {
            System.out.println("⚠️ Warning: Product ID must be positive! Setting to 0.");
            this.productId = 0;
        }
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("⚠️ Warning: Name cannot be empty! Setting to 'Unknown Product'.");
            this.name = "Unknown Product";
        }
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("⚠️ Warning: Price cannot be negative! Setting to 0.");
            this.price = 0.0;
        }
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity >= 0) {
            this.stockQuantity = stockQuantity;
        } else {
            System.out.println("⚠️ Warning: Stock quantity cannot be negative! Setting to 0.");
            this.stockQuantity = 0;
        }
    }

    // ADDITIONAL METHODS (minimum 2)

    // Checking if product is in stock
    public boolean isInStock() {
        return stockQuantity > 0;
    }

    // Restock product with validation
    public void restock(int quantity) {
        if (quantity > 0) {
            this.stockQuantity += quantity;
            System.out.println("✅ Restocked " + quantity + " units of " + name);
        } else {
            System.out.println("❌ Restock quantity must be positive!");
        }
    }

    // Sell product (reduce stock) with validation
    public boolean sell(int quantity) {
        if (quantity <= 0) {
            System.out.println("❌ Sell quantity must be positive!");
            return false;
        }
        if (stockQuantity >= quantity) {
            this.stockQuantity -= quantity;
            return true;
        } else {
            System.out.println("❌ Insufficient stock! Available: " + stockQuantity);
            return false;
        }
    }

    public void applyDiscount(double percentage) {
        if (percentage > 0 && percentage <= 100) {
            this.price = this.price * (1 - percentage / 100);
        } else {
            System.out.println("❌ Invalid discount percentage! Must be between 0 and 100.");
        }
    }

    public String getFormattedPrice() {
        return String.format("%.2f KZT", price);
    }

    public boolean isExpensive() {
        return price > 5000;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + String.format("%.2f", price) +
                " KZT, stockQuantity=" + stockQuantity +
                '}';
    }
}