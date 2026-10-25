import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class AsientoService {
    private char[][] asientos;
    private int filas;
    private int columnas;

    public void inicializarSala(String tipoSala) {
        int limiteDiscapacitados;

        if (tipoSala.equals("VIP")) {
            this.filas = 5; // A-E
            this.columnas = 5; // 1-5 (Total: 25 asientos)
            limiteDiscapacitados = 5;
        } else {
            this.filas = 5; // A-E
            this.columnas = 8; // 1-8 (Total: 40 asientos)
            limiteDiscapacitados = 8;
        }

        asientos = new char[filas][columnas];
        int discapacitadosAsignados = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (discapacitadosAsignados < limiteDiscapacitados) {
                    asientos[i][j] = 'D'; // Asigna 'D' hasta llegar al límite (5 o 10)
                    discapacitadosAsignados++;
                } else {
                    asientos[i][j] = 'L'; // El resto se marcan como Libres
                }
            }
        }
    }

    public void seleccionarAsientos(Scanner scanner, String tipoSala) {
        boolean seleccionando = true;
        List<String> carrito = new ArrayList<>();

        while (seleccionando) {
            System.out.println("\n--- MAPA DE ASIENTOS (" + tipoSala + ") ---");
            mostrarMapa();

            if (!carrito.isEmpty()) {
                System.out.println("\nAsientos en tu carrito actual: " + String.join(", ", carrito));
            }

            System.out.print("\nIngresa el asiento (ej. A-1) o escribe 'FIN' para terminar: ");
            String inputOriginal = scanner.nextLine();

            if (inputOriginal.trim().equalsIgnoreCase("FIN")) {
                seleccionando = false;
                continue;
            }

            // EXTRACCIÓN INFALIBLE: Separamos letras y números ignorando cualquier símbolo basura
            String letras = inputOriginal.toUpperCase().replaceAll("[^A-Z]", "");
            String numeros = inputOriginal.replaceAll("[^0-9]", "");

            // Si encontró exactamente una letra y al menos un número...
            if (letras.length() == 1 && !numeros.isEmpty()) {
                char letraFila = letras.charAt(0);
                int fila = letraFila - 'A';
                int col = Integer.parseInt(numeros) - 1;

                if (fila >= 0 && fila < filas && col >= 0 && col < columnas) {
                    if (asientos[fila][col] == 'L' || asientos[fila][col] == 'D') {
                        String nombreAsiento = letraFila + "-" + (col + 1);
                        System.out.print("¿Deseas seleccionar el asiento " + nombreAsiento + "? (S/N): ");
                        String confirmAsiento = scanner.nextLine().trim().toUpperCase();
                        if (confirmAsiento.equals("S") || confirmAsiento.equals("SI")) {
                            asientos[fila][col] = 'X';
                            carrito.add(nombreAsiento);
                            System.out.println("\n[!] Asiento " + nombreAsiento + " agregado a tu lista.");
                        } else if (confirmAsiento.equals("N") || confirmAsiento.equals("NO")) {
                            System.out.println("\n[!] Selección cancelada.");
                        } else {
                            System.out.println("\n[X] Opción inválida. Selección cancelada.");
                        }
                    } else {
                        System.out.println("\n[X] Error: El asiento ya esta ocupado.");
                    }
                } else {
                    System.out.println("\n[!] Asiento invalido o fuera del mapa. El limite es " + (char)('A' + filas - 1) + "-" + columnas);
                }
            } else {
                System.out.println("\n[!] Formato incorrecto. Recuerda usar Letra-Numero (ej. A-1).");
            }
        }

        System.out.println("\n=== TICKET Y RESUMEN DE COMPRA ===");
        mostrarMapa();

        if (!carrito.isEmpty()) {
            System.out.println("\nTotal de boletos: " + carrito.size());
            System.out.println("Asientos confirmados: [ " + String.join(" | ", carrito) + " ]");
        } else {
            System.out.println("\nNo seleccionaste ningun asiento en esta sesion.");
        }
    }

    private void mostrarMapa() {
        // Pantalla centrada
        if (columnas == 8) {
            System.out.println("\n             [ P A N T A L L A ]\n");
        } else {
            System.out.println("\n        [ P A N T A L L A ]\n");
        }

        // Imprimir la línea superior de la cuadrícula
        System.out.print("  ");
        for (int j = 0; j < columnas; j++) {
            System.out.print("+---");
        }
        System.out.println("+");

        // Imprimir cada fila con su letra a la izquierda
        for (int i = 0; i < filas; i++) {
            char letraFila = (char) ('A' + i);
            System.out.print(letraFila + " |"); // Letra lateral

            for (int j = 0; j < columnas; j++) {
                char estado = asientos[i][j];
                // Si está libre, lo dibujamos como un recuadro vacío para igualar tu boceto
                if (estado == 'L') {
                    System.out.print("   |");
                } else {
                    System.out.print(" " + estado + " |");
                }
            }
            System.out.println();

            // Línea separadora horizontal de la cuadrícula
            System.out.print("  ");
            for (int j = 0; j < columnas; j++) {
                System.out.print("+---");
            }
            System.out.println("+");
        }

        // Imprimir los números en la parte de abajo
        System.out.print("  ");
        for (int j = 1; j <= columnas; j++) {
            if (j < 10) {
                System.out.print("  " + j + " ");
            } else {
                System.out.print(" " + j + " ");
            }
        }

        System.out.println("\n\nLeyenda: [ ] Libre | [X] Ocupado | [D] Discapacitados");
    }
}