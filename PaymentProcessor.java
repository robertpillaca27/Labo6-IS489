package com.unsch.payment;

public class PaymentProcessor {
    private static final double TAX_RATE = 0.18;
    private static final double MINIMUM_AMOUNT = 0.0;
    private static final double DAILY_LIMIT = 1000.0;

    private final PaymentRepository paymentRepository;

    public PaymentProcessor(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentResponse processPayment(PaymentRequest request) {
        validateMinimumAmount(request.getAmount());
        validateDailyLimit(request.getCustomerId(), request.getAmount());
        
        double tax = request.getAmount() * TAX_RATE;
        double total = request.getAmount() + tax;
        
        paymentRepository.savePayment(total, tax, request.getCustomerId());
        
        return new PaymentResponse(tax, total);
    }

    private void validateMinimumAmount(double amount) {
        if (amount <= MINIMUM_AMOUNT) {
            throw new IllegalArgumentException("El monto debe ser superior al mínimo permitido.");
        }
    }

    private void validateDailyLimit(String customerId, double amount) {
        double currentDailySpent = paymentRepository.getDailyTotalSpent(customerId);
        if (currentDailySpent + amount > DAILY_LIMIT) {
            throw new IllegalStateException("Se ha excedido el límite de pago diario permitido.");
        }
    }
}
