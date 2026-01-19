package model;


public class Customer {
    private int customerId;
    private String name;
    private String membershipLevel;
    private double totalPurchases;


    public Customer(int customerId, String name, String membershipLevel, double totalPurchases) {
        setCustomerId(customerId);
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
        if (customerId <= 0) {
            throw new IllegalArgumentException("Customer ID must be positive, got: " + customerId);
        }
        this.customerId = customerId;
    }


    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        this.name = name;
    }


    public void setMembershipLevel(String membershipLevel) {
        if (membershipLevel == null || membershipLevel.trim().isEmpty()) {
            throw new IllegalArgumentException("Membership level cannot be empty");
        }
        this.membershipLevel = membershipLevel;
    }


    public void setTotalPurchases(double totalPurchases) {
        if (totalPurchases < 0) {
            throw new IllegalArgumentException("Total purchases cannot be negative, got: " + totalPurchases);
        }
        this.totalPurchases = totalPurchases;
        updateMembershipLevel();
    }


    public boolean isVIP() {
        return totalPurchases > 50000;
    }


    public void addPurchase(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Purchase amount must be positive, got: " + amount);
        }
        this.totalPurchases += amount;
        updateMembershipLevel();
        System.out.println("✅ Added " + String.format("%.2f", amount) + " KZT to " + name);
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