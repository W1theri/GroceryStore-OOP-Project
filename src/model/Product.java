package model;

import exception.InvalidProductException;

public abstract class Product {
    protected int productId;
    protected String name;
    protected double price;
    protected int stockQuantity;


    public Product(int productId, String name, double price, int stockQuantity) throws InvalidProductException {
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

    // ==================== SETTERS (with CUSTOM EXCEPTION) ====================


    public void setProductId(int productId) throws InvalidProductException {
        if (productId <= 0) {
            throw new InvalidProductException("Product ID must be positive, got: " + productId);
        }
        this.productId = productId;
    }


    public void setName(String name) throws InvalidProductException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidProductException("Product name cannot be empty");
        }
        this.name = name;
    }


    public void setPrice(double price) throws InvalidProductException {
        if (price < 0) {
            throw new InvalidProductException("Price cannot be negative, got: " + price);
        }
        this.price = price;
    }


    public void setStockQuantity(int stockQuantity) throws InvalidProductException {
        if (stockQuantity < 0) {
            throw new InvalidProductException("Stock quantity cannot be negative, got: " + stockQuantity);
        }
        this.stockQuantity = stockQuantity;
    }

    // ==================== CONCRETE METHODS (shared by all products) ====================


    public boolean isInStock() {
        return stockQuantity > 0;
    }

    public void restock(int quantity) throws InvalidProductException {
        if (quantity <= 0) {
            throw new InvalidProductException("Restock quantity must be positive");
        }
        this.stockQuantity += quantity;
        System.out.println("✅ Restocked " + quantity + " units of " + name);
    }


    public boolean sell(int quantity) throws InvalidProductException {
        if (quantity <= 0) {
            throw new InvalidProductException("Sell quantity must be positive");
        }
        if (stockQuantity >= quantity) {
            this.stockQuantity -= quantity;
            return true;
        } else {
            throw new InvalidProductException("Insufficient stock! Available: " + stockQuantity + ", requested: " + quantity);
        }
    }


    public void applyDiscount(double percentage) throws InvalidProductException {
        if (percentage <= 0 || percentage > 100) {
            throw new InvalidProductException("Discount percentage must be between 0 and 100, got: " + percentage);
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