package model;

import exception.InvalidProductException;


public class PackagedProduct extends Product {
    private String manufacturer;
    private double weight; // in grams


    public PackagedProduct(int productId, String name, double price, int stockQuantity,
                           String manufacturer, double weight) throws InvalidProductException {
        super(productId, name, price, stockQuantity);
        setManufacturer(manufacturer);
        setWeight(weight);
    }


    public PackagedProduct() {
        super();
        this.manufacturer = "Unknown";
        this.weight = 0.0;
    }


    public String getManufacturer() {
        return manufacturer;
    }

    public double getWeight() {
        return weight;
    }


    public void setManufacturer(String manufacturer) throws InvalidProductException {
        if (manufacturer == null || manufacturer.trim().isEmpty()) {
            throw new InvalidProductException("Manufacturer name cannot be empty");
        }
        this.manufacturer = manufacturer;
    }


    public void setWeight(double weight) throws InvalidProductException {
        if (weight <= 0) {
            throw new InvalidProductException("Weight must be positive, got: " + weight);
        }
        this.weight = weight;
    }


    @Override
    public String getProductType() {
        return "Packaged Product";
    }


    @Override
    public void displayProductDetails() {
        System.out.println("📦 Packaged Product: " + getName());
        System.out.println("   Manufacturer: " + manufacturer);
        System.out.println("   Weight: " + weight + "g");
        System.out.println("   Price per kg: " + String.format("%.2f KZT", getPricePerKg()));
        System.out.println("   Stock: " + getStockQuantity() + " units");
        System.out.println("   Price: " + getFormattedPrice());

        if (isLightweight()) {
            System.out.println("   🪶 Lightweight package");
        } else if (isBulk()) {
            System.out.println("   📦 Bulk package");
        }
    }


    @Override
    public String getFormattedPrice() {
        double pricePerKg = (getPrice() / weight) * 1000;
        return String.format("%.2f KZT (%.2f KZT/kg)", getPrice(), pricePerKg);
    }



    public boolean isLightweight() {
        return weight < 500;
    }


    public boolean isBulk() {
        return weight > 2000;
    }


    public double getPricePerKg() {
        if (weight > 0) {
            return (getPrice() / weight) * 1000;
        }
        return 0.0;
    }


    public void displayPackageInfo() {
        displayProductDetails();
    }


    public void applyBulkDiscount() throws InvalidProductException {
        if (isBulk()) {
            double discount = 10.0;
            applyDiscount(discount);
            System.out.println("✅ Applied 10% bulk discount!");
        } else {
            throw new InvalidProductException("Bulk discount only applies to packages over 2kg!");
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Manufacturer: " + manufacturer +
                " | Weight: " + weight + "g";
    }
}