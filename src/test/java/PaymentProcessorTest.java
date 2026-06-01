package test.java;
import main.java.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentProcessorTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentProcessor paymentProcessor; // Error porque la clase aún no existe

    @Test
    public void testProcessPayment_ExceedsDailyLimit_ShouldThrowException() {
        PaymentRequest request = new PaymentRequest(100.0, "USER-01");
        
        // Mockito: Simulamos que la BD dice que el usuario ya gastó 950 soles hoy
        when(paymentRepository.getDailyTotalSpent("USER-01")).thenReturn(950.0);

        // Verificamos que el sistema lance una excepción al procesar 950 + 100 = 1050
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            paymentProcessor.processPayment(request);
        });

        assertEquals("Se ha excedido el límite de pago diario permitido.", exception.getMessage());
    }
}