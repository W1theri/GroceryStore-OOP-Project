package model;

import exception.InvalidProductException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class FreshProduct extends Product implements Perishable {
    private String expiryDate;
    private boolean isOrganic;


    public FreshProduct(int productId, String name, double price, int stockQuantity,
                        String expiryDate, boolean isOrganic) throws InvalidProductException {
        super(productId, name, price, stockQuantity);
        setExpiryDate(expiryDate);
        this.isOrganic = isOrganic;
    }


    public FreshProduct() {
        super();
        this.expiryDate = "Not set";
        this.isOrganic = false;
    }


    @Override
    public String getExpiryDate() {
        return expiryDate;
    }

    public boolean isOrganic() {
        return isOrganic;
    }


    public void setExpiryDate(String expiryDate) throws InvalidProductException {
        if (expiryDate == null || expiryDate.trim().isEmpty()) {
            throw new InvalidProductException("Expiry date cannot be empty");
        }
        this.expiryDate = expiryDate;
    }

    public void setOrganic(boolean organic) {
        isOrganic = organic;
    }


    @Override
    public boolean isInStock() {
        return getStockQuantity() > 0 && !isExpired();
    }


    @Override
    public String getProductType() {
        return "Fresh Product";
    }


    @Override
    public void displayProductDetails() {
        System.out.println("🍎 Fresh Product: " + getName());
        System.out.println("   Expiry Date: " + expiryDate);
        System.out.println("   Days Until Expiry: " + getDaysUntilExpiry());
        System.out.println("   Organic: " + (isOrganic ? "Yes 🌿" : "No"));
        System.out.println("   Status: " + (isExpired() ? "❌ EXPIRED" : "✅ FRESH"));
        System.out.println("   Stock: " + getStockQuantity() + " units");
        System.out.println("   Price: " + getFormattedPrice());
    }



    @Override
    public boolean isExpired() {
        if (expiryDate.equals("Expired")) {
            return true;
        }
        try {
            LocalDate today = LocalDate.now();
            LocalDate expiry = LocalDate.parse(expiryDate);
            return today.isAfter(expiry);
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public void markAsExpired() {
        this.expiryDate = "Expired";
        try {
            setStockQuantity(0);
        } catch (InvalidProductException e) {
        }
        System.out.println("⚠️ Product " + getName() + " has been marked as expired!");
    }


    @Override
    public int getDaysUntilExpiry() {
        if (isExpired()) {
            return 0;
        }
        try {
            LocalDate today = LocalDate.now();
            LocalDate expiry = LocalDate.parse(expiryDate);
            long days = ChronoUnit.DAYS.between(today, expiry);
            return (int) days;
        } catch (Exception e) {
            return 0;
        }
    }



    public void displayFreshnessInfo() {
        displayProductDetails();
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Expiry: " + expiryDate +
                (isOrganic ? " | 🌿 ORGANIC" : "");
    }
}