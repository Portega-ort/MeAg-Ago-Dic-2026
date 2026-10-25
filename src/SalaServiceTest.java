import org.junit.jupiter.api.Test;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SalaServiceTest {

    @Test
    void testSeleccionarSalaNormal() {
        // Simulamos que el usuario ingresa "1"
        Scanner scanner = new Scanner("1");
        SalaService salaService = new SalaService();

        String resultado = salaService.seleccionarSala(scanner);

        assertEquals("Normal", resultado, "Debería retornar 'Normal' al elegir la opción 1");
    }

    @Test
    void testSeleccionarSalaVIP() {
        // Simulamos que el usuario ingresa "2"
        Scanner scanner = new Scanner("2");
        SalaService salaService = new SalaService();

        String resultado = salaService.seleccionarSala(scanner);

        assertEquals("VIP", resultado, "Debería retornar 'VIP' al elegir la opción 2");
    }

    @Test
    void testSeleccionarSalaConEntradaInvalidaPrevia() {
        // Simulamos que el usuario ingresa opciones incorrectas primero y luego un "1" válido
        Scanner scanner = new Scanner("3\ntexto_invalido\n1\n");
        SalaService salaService = new SalaService();

        String resultado = salaService.seleccionarSala(scanner);

        assertEquals("Normal", resultado, "Debería recuperarse de errores y retornar 'Normal' al final");
    }
}