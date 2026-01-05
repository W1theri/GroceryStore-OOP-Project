public class PackagedProduct extends Product {
    private String manufacturer;
    private double weight; // в граммах

    public PackagedProduct(int productId, String name, double price, int stockQuantity,
                           String manufacturer, double weight) {
        super(productId, name, price, stockQuantity);
        this.manufacturer = manufacturer;
        this.weight = weight;
    }

    public PackagedProduct() {
        super();
        this.manufacturer = "Unknown";
        this.weight = 0.0;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        if (manufacturer != null && !manufacturer.trim().isEmpty()) {
            this.manufacturer = manufacturer;
        } else {
            System.out.println("⚠️ Warning: Manufacturer cannot be empty!");
            this.manufacturer = "Unknown";
        }
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight > 0) {
            this.weight = weight;
        } else {
            System.out.println("⚠️ Warning: Weight must be positive!");
            this.weight = 0.0;
        }
    }

    @Override
    public String getFormattedPrice() {
        double pricePerKg = (getPrice() / weight) * 1000;
        return String.format("%.2f KZT (%.2f KZT/kg)", getPrice(), pricePerKg);
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Manufacturer: " + manufacturer +
                " | Weight: " + weight + "g";
    }

    // Уникальные методы для PackagedProduct
    public boolean isLightweight() {
        return weight < 500; // Меньше 500 грамм
    }

    public boolean isBulk() {
        return weight > 2000; // Больше 2 кг
    }

    public double getPricePerKg() {
        if (weight > 0) {
            return (getPrice() / weight) * 1000;
        }
        return 0.0;
    }

    public void displayPackageInfo() {
        System.out.println("📦 Packaged Product: " + getName());
        System.out.println("   Manufacturer: " + manufacturer);
        System.out.println("   Weight: " + weight + "g");
        System.out.println("   Price per kg: " + String.format("%.2f KZT", getPricePerKg()));

        if (isLightweight()) {
            System.out.println("   🪶 Lightweight package");
        } else if (isBulk()) {
            System.out.println("   📦 Bulk package");
        }
    }

    public void applyBulkDiscount() {
        if (isBulk()) {
            double discount = 10.0; // 10% скидка на крупные упаковки
            applyDiscount(discount);
            System.out.println("✅ Applied 10% bulk discount!");
        } else {
            System.out.println("❌ Bulk discount only applies to packages over 2kg!");
        }
    }
}