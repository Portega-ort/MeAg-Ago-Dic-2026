import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== BIENVENIDO A CINE APP ===");

        SalaService salaService = new SalaService();
        String tipoSala = salaService.seleccionarSala(scanner);

        while (true) {
            System.out.print("¿Deseas seleccionar " + tipoSala + "? (S/N): ");
            String confirmSala = scanner.nextLine().trim().toUpperCase();
            if (confirmSala.equals("S") || confirmSala.equals("SI")) {
                break;
            } else if (confirmSala.equals("N") || confirmSala.equals("NO")) {
                tipoSala = salaService.seleccionarSala(scanner);
            } else {
                System.out.println("[X] Opción inválida, responde S/N.");
            }
        }

        AsientoService asientoService = new AsientoService();

        asientoService.inicializarSala(tipoSala);

        asientoService.seleccionarAsientos(scanner, tipoSala);

        System.out.println("\nGracias por usar Cine App.");
        scanner.close();
    }
}