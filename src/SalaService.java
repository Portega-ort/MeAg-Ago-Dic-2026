import java.util.Scanner;

public class SalaService {

    public String seleccionarSala(Scanner scanner) {
        System.out.println("\n--- SELECCIÓN DE TIPO DE SALA ---");
        System.out.println("1. Sala Normal ($60.00)");
        System.out.println("2. Sala VIP ($120.00 - Asientos reclinables y servicio a la sala)");
        System.out.print("Elige una opción (1 o 2): ");

        String opcion = scanner.nextLine();

        if (opcion.equals("2")) {
            System.out.println("Has seleccionado: Sala VIP");
            return "VIP";
        } else {
            System.out.println("Has seleccionado: Sala Normal");
            return "Normal";
        }
    }
}