import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== BIENVENIDO A CINE APP ===");

        SalaService salaService = new SalaService();
        String tipoSala = salaService.seleccionarSala(scanner);

        AsientoService asientoService = new AsientoService();

        asientoService.inicializarSala(tipoSala);

        asientoService.seleccionarAsientos(scanner, tipoSala);

        System.out.println("\nGracias por usar Cine App.");
        scanner.close();
    }
}