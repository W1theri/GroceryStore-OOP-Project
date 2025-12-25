public class Sale {
    // PRIVATE FIELDS
    private int saleId;
    private String customerName;
    private double totalAmount;
    private String date;

    public Sale(int saleId, String customerName, double totalAmount, String date) {
        this.saleId = saleId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.date = date;
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
        this.saleId = saleId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // ADDITIONAL METHODS (minimum 2)

    public void addItem(double itemPrice) {
        if (itemPrice > 0) {
            this.totalAmount += itemPrice;
        }
    }

    public void applyDiscount(double percentage) {
        if (percentage > 0 && percentage <= 100) {
            this.totalAmount = this.totalAmount * (1 - percentage / 100);
        }
    }

    // Calculate tax (12% in Kazakhstan)
    public double calculateTax() {
        return this.totalAmount * 0.12;
    }

    public double getTotalWithTax() {
        return this.totalAmount + calculateTax();
    }

    // Check if large sale (> 20000 KZT)
    public boolean isLargeSale() {
        return totalAmount > 20000;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", customerName='" + customerName + '\'' +
                ", totalAmount=" + totalAmount +
                " KZT, date='" + date + '\'' +
                '}';
    }
}
