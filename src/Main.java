
import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== BIENVENIDO A CINE APP ===");

        // Sprint 1 - Tarea 1: Seleccion de Sala
        SalaService salaService = new SalaService();
        String tipoSala = salaService.seleccionarSala(scanner);

        System.out.println("Tipo de sala seleccionada: " + tipoSala);

        // Sprint 1 - Tarea 2: Seleccion de Asientos
        AsientoService asientoService = new AsientoService();

        List<String> asientos =
                asientoService.seleccionarAsientos(scanner);

        System.out.println("=== RESUMEN DE RESERVACION ===");
        System.out.println("Sala seleccionada: " + tipoSala);
        System.out.println("Asientos elegidos: " + asientos);
        System.out.println("Total de asientos: " + asientos.size());

        scanner.close();
    }
}
