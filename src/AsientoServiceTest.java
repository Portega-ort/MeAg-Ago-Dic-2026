import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class AsientoServiceTest {

    private AsientoService asientoService;

    @BeforeEach
    public void setUp() {
        // Se ejecuta antes de cada test para tener una instancia limpia
        asientoService = new AsientoService();
    }

    @Test
    public void testInicializarSalaVIP() {
        asientoService.inicializarSala("VIP");

        assertEquals(5, asientoService.filas, "La sala VIP debe tener 5 filas");
        assertEquals(5, asientoService.columnas, "La sala VIP debe tener 5 columnas");
        assertEquals('D', asientoService.asientos[0][0]);
        assertEquals('D', asientoService.asientos[0][4]);
        assertEquals('L', asientoService.asientos[1][0]);
    }

    @Test
    public void testInicializarSalaRegular() {
        asientoService.inicializarSala("REGULAR");

        assertEquals(5, asientoService.filas, "La sala regular debe tener 5 filas");
        assertEquals(8, asientoService.columnas, "La sala regular debe tener 8 columnas");
        assertEquals('D', asientoService.asientos[0][7]);
        assertEquals('L', asientoService.asientos[1][0]);
    }

    @Test
    public void testSeleccionarAsientoExitoso() {
        // 1. Inicializamos la sala
        asientoService.inicializarSala("VIP");

        // 2. Simulamos las entradas del usuario:
        //    - Ingresar el asiento "A-2"
        //    - Confirmar con "S" (Sí)
        //    - Escribir "FIN" para salir del ciclo de selección
        String inputSimulado = "A-2\nS\nFIN\n";
        Scanner scanner = new Scanner(inputSimulado);

        // 3. Ejecutamos el método pasando el scanner simulado
        asientoService.seleccionarAsientos(scanner, "VIP");

        // 4. Verificamos que el asiento A-2 (fila 0, columna 1) ahora esté marcado como ocupado ('X')
        assertEquals('X', asientoService.asientos[0][1], "El asiento A-2 debería estar marcado como 'X' tras seleccionarlo y confirmarlo");
    }

    @Test
    public void testSeleccionarAsientoCancelado() {
        asientoService.inicializarSala("VIP");

        // Simulamos: elegir A-2, pero rechazar la confirmación con "N" (No), y luego "FIN"
        String inputSimulado = "A-2\nN\nFIN\n";
        Scanner scanner = new Scanner(inputSimulado);

        asientoService.seleccionarAsientos(scanner, "VIP");

        // Como se canceló la confirmación, el asiento debe conservar su estado original ('D')
        assertEquals('D', asientoService.asientos[0][1], "El asiento no debe cambiar a 'X' si el usuario cancela la confirmación");
    }

    @Test
    public void testMostrarMapaImprimeEstructura() {
        asientoService.inicializarSala("VIP");

        // 1. Guardamos la salida estándar original de la consola
        PrintStream originalOut = System.out;

        // 2. Creamos un flujo temporal para capturar lo que imprima el mapa
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // 3. Ejecutamos el método que dibuja el mapa
        asientoService.mostrarMapa();

        // 4. Restauramos la consola normal
        System.setOut(originalOut);

        // 5. Verificamos que el texto generado contenga los elementos visuales clave
        String salidaConsola = outputStreamCaptor.toString();

        assertTrue(salidaConsola.contains("[ P A N T A L L A ]"), "El mapa debe mostrar la pantalla");
        assertTrue(salidaConsola.contains("A |"), "El mapa debe mostrar las filas por letra (ej. A)");
        assertTrue(salidaConsola.contains("Leyenda:"), "El mapa debe mostrar la sección de leyenda");
    }
}
