package main.java;


public interface PaymentRepository {
    // Devuelve cuánto dinero ha gastado el cliente el día de hoy
    double getDailyTotalSpent(String customerId);
}