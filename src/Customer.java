public class Customer {
    // PRIVATE FIELDS
    private int customerId;
    private String name;
    private String membershipLevel;
    private double totalPurchases;

    public Customer(int customerId, String name, String membershipLevel, double totalPurchases) {
        this.customerId = customerId;
        this.name = name;
        this.membershipLevel = membershipLevel;
        this.totalPurchases = totalPurchases;
    }

    public Customer() {
        this.customerId = 0;
        this.name = "Unknown Customer";
        this.membershipLevel = "Standard";
        this.totalPurchases = 0.0;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public double getTotalPurchases() {
        return totalPurchases;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public void setTotalPurchases(double totalPurchases) {
        this.totalPurchases = totalPurchases;
    }

    // ADDITIONAL METHODS (minimum 2)

    public boolean isVIP() {
        return totalPurchases > 50000;
    }

    public void addPurchase(double amount) {
        if (amount > 0) {
            this.totalPurchases += amount;
            updateMembershipLevel();
        }
    }

    private void updateMembershipLevel() {
        if (totalPurchases >= 100000) {
            this.membershipLevel = "Platinum";
        } else if (totalPurchases >= 50000) {
            this.membershipLevel = "Gold";
        } else if (totalPurchases >= 20000) {
            this.membershipLevel = "Silver";
        } else {
            this.membershipLevel = "Standard";
        }
    }

    // Get discount percentage based on membership
    public double getDiscountPercentage() {
        switch (membershipLevel) {
            case "Platinum":
                return 15.0;
            case "Gold":
                return 10.0;
            case "Silver":
                return 5.0;
            default:
                return 0.0;
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", membershipLevel='" + membershipLevel + '\'' +
                ", totalPurchases=" + totalPurchases +
                " KZT}";
    }
}