package main.java;

public class PaymentRequest {
    private double amount;
    private String customerId;

    public PaymentRequest(double amount, String customerId) {
        this.amount = amount;
        this.customerId = customerId;
    }
    public double getAmount() { return amount; }
    public String getCustomerId() { return customerId; }
}
