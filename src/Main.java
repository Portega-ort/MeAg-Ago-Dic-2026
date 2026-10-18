import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== BIENVENIDO A CINE APP ===");

        // Sprint 1 - Tarea 1: Selección de Sala
        SalaService salaService = new SalaService();
        String tipoSala = salaService.seleccionarSala(scanner);
    }
}