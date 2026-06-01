package main.java;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Crear el repositorio (usamos la lógica del mock)
        PaymentRepository repo = new PaymentRepository() {
            @Override
            public double getDailyTotalSpent(String id) {
                return 800.0;
            }
        };

        PaymentProcessor processor = new PaymentProcessor(repo);
        
        // 2. Pedir al usuario el monto y su ID
        System.out.print("Ingresa el monto a pagar: ");
        double monto = scanner.nextDouble();
        
        System.out.print("Ingresa el ID del usuario: ");
        String idUsuario = scanner.next();

        // 3. Procesar el pago
        try {
            PaymentRequest req = new PaymentRequest(monto, idUsuario);
            processor.processPayment(req);
            System.out.println("RESULTADO: ¡Pago procesado con éxito!");
        } catch (IllegalStateException e) {
            System.out.println("RESULTADO: " + e.getMessage());
        }

        scanner.close(); 
    }
}