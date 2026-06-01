package main.java;

public class PaymentProcessor {
    private final PaymentRepository paymentRepository;

    public PaymentProcessor(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void processPayment(PaymentRequest request) {
        // Consultamos al repositorio mockeado
        double currentDailySpent = paymentRepository.getDailyTotalSpent(request.getCustomerId());
        
        // Código mínimo para cumplir el requerimiento del test
        if (currentDailySpent + request.getAmount() > 1000.0) {
            throw new IllegalStateException("Se ha excedido el límite de pago diario permitido.");
        }
    }
}
