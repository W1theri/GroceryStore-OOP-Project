public class Customer {
    private int customerId;
    private String name;
    private String membershipLevel;
    private double totalPurchases;

    public Customer(int customerId, String name, String membershipLevel, double totalPurchases) {
        this.customerId = customerId;
        setName(name);
        this.membershipLevel = membershipLevel;
        setTotalPurchases(totalPurchases);
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
        if (customerId > 0) {
            this.customerId = customerId;
        } else {
            System.out.println("⚠️ Warning: Customer ID must be positive! Setting to 0. ");
            this.customerId = 0;
        }
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("⚠️ Warning: Name cannot be empty! Setting to 'Unknown Customer'.");
            this.name = "Unknown Customer";
        }
    }

    public void setMembershipLevel(String membershipLevel) {
        if (membershipLevel != null && !membershipLevel.trim().isEmpty()) {
            this.membershipLevel = membershipLevel;
        } else {
            this.membershipLevel = "Standard";
        }
    }

    public void setTotalPurchases(double totalPurchases) {
        if (totalPurchases >= 0) {
            this.totalPurchases = totalPurchases;
            updateMembershipLevel();
        } else {
            System.out.println("⚠️ Warning: Total purchases cannot be negative! Setting to 0.");
            this.totalPurchases = 0.0;
        }
    }

    // ADDITIONAL METHODS (minimum 2)

    public boolean isVIP() {
        return totalPurchases > 50000;
    }

    public void addPurchase(double amount) {
        if (amount > 0) {
            this.totalPurchases += amount;
            updateMembershipLevel();
            System.out.println("✅ Added " + String.format("%.2f", amount) + " KZT to " + name);
        } else {
            System.out.println("❌ Рurchase amount must be positive!");
        }
    }

    private void updateMembershipLevel() {
        String oldLevel = this.membershipLevel;
        if (totalPurchases >= 100000) {
            this.membershipLevel = "Platinum";
        } else if (totalPurchases >= 50000) {
            this.membershipLevel = "Gold";
        } else if (totalPurchases >= 20000) {
            this.membershipLevel = "Silver";
        } else {
            this.membershipLevel = "Standard";
        }

        if (!oldLevel.equals(this.membershipLevel)) {
            System.out.println("🎉 Membership upgraded to " + this.membershipLevel + "!");
        }
    }

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

    public String getFormattedPurchases() {
        return String.format("%.2f KZT", totalPurchases);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", membershipLevel='" + membershipLevel + '\'' +
                ", totalPurchases=" + String.format("%.2f", totalPurchases) +
                " KZT}";
    }
}