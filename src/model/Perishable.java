package model;

public interface Perishable {

    boolean isExpired();
    String getExpiryDate();
    void markAsExpired();
    int getDaysUntilExpiry();

}