package model;

public class Sale {
    private int saleId;
    private String customerName;
    private double totalAmount;
    private String date;


    public Sale(int saleId, String customerName, double totalAmount, String date) {
        setSaleId(saleId);
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


    public void setSaleId(int saleId) {
        if (saleId <= 0) {
            throw new IllegalArgumentException("Sale ID must be positive, got: " + saleId);
        }
        this.saleId = saleId;
    }


    public void setCustomerName(String customerName) {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        this.customerName = customerName;
    }


    public void setTotalAmount(double totalAmount) {
        if (totalAmount < 0) {
            throw new IllegalArgumentException("Total amount cannot be negative, got: " + totalAmount);
        }
        this.totalAmount = totalAmount;
    }


    public void setDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty");
        }
        this.date = date;
    }



    public void addItem(double itemPrice) {
        if (itemPrice <= 0) {
            throw new IllegalArgumentException("Item price must be positive, got: " + itemPrice);
        }
        this.totalAmount += itemPrice;
        System.out.println("✅ Added item worth " + String.format("%.2f", itemPrice) + " KZT");
    }


    public void applyDiscount(double percentage) {
        if (percentage <= 0 || percentage > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100%, got: " + percentage);
        }
        double discountAmount = this.totalAmount * (percentage / 100);
        this.totalAmount = this.totalAmount * (1 - percentage / 100);
        System.out.println("✅ Applied " + percentage + "% discount. Saved: " +
                String.format("%.2f", discountAmount) + " KZT");
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