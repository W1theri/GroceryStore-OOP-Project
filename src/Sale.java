public class Sale {
    // PRIVATE FIELDS
    private int saleId;
    private String customerName;
    private double totalAmount;
    private String date;

    // CONSTRUCTOR WITH PARAMETERS (with validation)
    public Sale(int saleId, String customerName, double totalAmount, String date) {
        this.saleId = saleId;
        setCustomerName(customerName);
        setTotalAmount(totalAmount);
        setDate(date);
    }

    public Sale() {
        this.saleId = 0;
        this.customerName = "Unknown";
        this.totalAmount = 0.0;
        this.date = "Not set";
    }

    public int getSaleId() {
        return saleId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getDate() {
        return date;
    }

    // SETTERS WITH VALIDATION
    public void setSaleId(int saleId) {
        if (saleId > 0) {
            this.saleId = saleId;
        } else {
            System.out.println("⚠️ Warning: Sale ID must be positive! Setting to 0.");
            this.saleId = 0;
        }
    }

    public void setCustomerName(String customerName) {
        if (customerName != null && !customerName.trim().isEmpty()) {
            this.customerName = customerName;
        } else {
            System.out.println("⚠️ Warning: Customer name cannot be empty! Setting to 'Unknown'.");
            this.customerName = "Unknown";
        }
    }

    public void setTotalAmount(double totalAmount) {
        if (totalAmount >= 0) {
            this.totalAmount = totalAmount;
        } else {
            System.out.println("⚠️ Warning: Total amount cannot be negative! Setting to 0.");
            this.totalAmount = 0.0;
        }
    }

    public void setDate(String date) {
        if (date != null && !date.trim().isEmpty()) {
            this.date = date;
        } else {
            System.out.println("⚠️ Warning: Date cannot be empty! Setting to 'Not set'.");
            this.date = "Not set";
        }
    }

    // ADDITIONAL METHODS (minimum 2)

    // Add item to sale with validation
    public void addItem(double itemPrice) {
        if (itemPrice > 0) {
            this.totalAmount += itemPrice;
            System.out.println("✅ Added item worth " + String.format("%.2f", itemPrice) + " KZT");
        } else {
            System.out.println("❌ Item price must be positive! ");
        }
    }

    // Apply discount to total with validation
    public void applyDiscount(double percentage) {
        if (percentage > 0 && percentage <= 100) {
            double discountAmount = this.totalAmount * (percentage / 100);
            this.totalAmount = this.totalAmount * (1 - percentage / 100);
            System.out.println("✅ Applied " + percentage + "% discount. Saved: " +
                    String.format("%.2f", discountAmount) + " KZT");
        } else {
            System.out.println("❌ Discount must be between 0 and 100%!");
        }
    }

    public double calculateTax() {
        return this.totalAmount * 0.12;
    }

    public double getTotalWithTax() {
        return this.totalAmount + calculateTax();
    }

    public boolean isLargeSale() {
        return totalAmount > 20000;
    }

    public String getFormattedTotal() {
        return String.format("%.2f KZT", totalAmount);
    }

    public String getFormattedTotalWithTax() {
        return String.format("%.2f KZT", getTotalWithTax());
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", customerName='" + customerName + '\'' +
                ", totalAmount=" + String.format("%.2f", totalAmount) +
                " KZT, date='" + date + '\'' +
                '}';
    }
}