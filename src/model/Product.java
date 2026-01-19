package model;

public abstract class Product {
    protected int productId;
    protected String name;
    protected double price;
    protected int stockQuantity;

    public Product(int productId, String name, double price, int stockQuantity) {
        setProductId(productId);
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

    // GETTERS
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

    // SETTERS with EXCEPTIONS (Week 6 requirement)
    public void setProductId(int productId) {
        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID must be positive, got: " + productId);
        }
        this.productId = productId;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        this.name = name;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative, got: " + price);
        }
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative, got: " + stockQuantity);
        }
        this.stockQuantity = stockQuantity;
    }

    // CONCRETE METHODS (shared by all products)
    public boolean isInStock() {
        return stockQuantity > 0;
    }

    public void restock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Restock quantity must be positive");
        }
        this.stockQuantity += quantity;
        System.out.println("✅ Restocked " + quantity + " units of " + name);
    }

    public boolean sell(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Sell quantity must be positive");
        }
        if (stockQuantity >= quantity) {
            this.stockQuantity -= quantity;
            return true;
        } else {
            throw new IllegalArgumentException("Insufficient stock! Available: " + stockQuantity + ", requested: " + quantity);
        }
    }

    public void applyDiscount(double percentage) {
        if (percentage <= 0 || percentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100, got: " + percentage);
        }
        this.price = this.price * (1 - percentage / 100);
        System.out.println("✅ Discount applied successfully!");
    }

    public String getFormattedPrice() {
        return String.format("%.2f KZT", price);
    }

    public boolean isExpensive() {
        return price > 5000;
    }

    // ABSTRACT METHODS - must be implemented by child classes

    public abstract String getProductType();


    public abstract void displayProductDetails();

    @Override
    public String toString() {
        return "[" + getProductType() + "] " +
                "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + String.format("%.2f", price) +
                " KZT, stockQuantity=" + stockQuantity +
                '}';
    }
}