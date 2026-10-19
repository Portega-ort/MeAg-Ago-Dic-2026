import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class AsientoService {

    private final int FILAS = 5;
    private final int COLUMNAS = 8;

    private final char[][] sala = {
            {'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D'},
            {'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L'},
            {'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L'},
            {'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L'},
            {'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L'}
    };

    public List<String> seleccionarAsientos(Scanner scanner) {

        List<String> seleccionados = new ArrayList<>();

        String[] nombresFilas = {"A", "B", "C", "D", "E"};

        System.out.println("\n--- SELECCION DE ASIENTOS ---");

        boolean continuar = true;

        while (continuar) {

            mostrarMapa(nombresFilas, seleccionados);

            System.out.println("\nLeyenda:");
            System.out.println("[ ] = Libre");
            System.out.println("[X] = Ocupado");
            System.out.println("[D] = discapacidad");
            System.out.println("[*] = Seleccionado");

            System.out.println("\nAsientos seleccionados: "
                    + seleccionados.size());

            System.out.print(
                    "Escribe un asiento (ej. A3), FIN para terminar: "
            );

            String entrada = scanner.nextLine().trim().toUpperCase();

            if (entrada.equals("FIN")) {
                continuar = false;
                continue;
            }

            // Validar formato: una letra y un numero
            if (!entrada.matches("[A-E][1-8]")) {
                System.out.println(
                        "Error: usa una fila A-E y un asiento del 1 al 8."
                );
                continue;
            }

            int fila = entrada.charAt(0) - 'A';
            int columna = entrada.charAt(1) - '1';

            // Si ya estaba seleccionado, quitarlo
            if (seleccionados.contains(entrada)) {
                seleccionados.remove(entrada);
                System.out.println(
                        "Has quitado el asiento " + entrada + "."
                );
                continue;
            }

            // Comprobar si esta ocupado
            if (sala[fila][columna] == 'O') {
                System.out.println(
                        "Error: el asiento " + entrada + " esta ocupado."
                );
                continue;
            }

            // Los asientos accesibles tienen reserva especial
            if (sala[fila][columna] == 'D') {
                System.out.println(
                        "Este asiento esta reservado para accesibilidad."
                );
                System.out.println(
                        "Seleccion no disponible en esta version."
                );
                continue;
            }

            seleccionados.add(entrada);

            System.out.println(
                    "Asiento " + entrada + " seleccionado correctamente."
            );
        }

        System.out.println("\n--- RESUMEN DE RESERVACION ---");

        if (seleccionados.isEmpty()) {
            System.out.println("No seleccionaste ningun asiento.");
        } else {
            System.out.println("Tus asientos son: " + seleccionados);
            System.out.println(
                    "Total de asientos: " + seleccionados.size()
            );
        }

        return seleccionados;
    }

    private void mostrarMapa(
            String[] nombresFilas,
            List<String> seleccionados) {

        System.out.println("\n                  PANTALLA");
        System.out.println("     ----------------------------------------");

        for (int i = 0; i < FILAS; i++) {

            System.out.print(nombresFilas[i] + "    ");

            for (int j = 0; j < COLUMNAS; j++) {

                String asiento = nombresFilas[i] + (j + 1);

                if (seleccionados.contains(asiento)) {
                    System.out.print("[*]");
                } else if (sala[i][j] == 'O') {
                    System.out.print("[X]");
                } else if (sala[i][j] == 'D') {
                    System.out.print("[D]");
                } else {
                    System.out.print("[ ]");
                }

                System.out.print("  ");
            }

            System.out.println();
        }

        System.out.print("     ");
        for (int columna = 1; columna <= COLUMNAS; columna++) {
            System.out.printf("%-5d", columna);
        }
        System.out.println();
    }
}