package com.mycompany.proyecto.andres;

import java.util.Random;
import java.util.Scanner;

public class ProyectoAndres {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ProyectoMatriz.IniciarBosque();
        ProyectoMatriz.MostrarBosque();

        Menu menu = new ProyectoAndres().new Menu();
        menu.ejecutar();
    }

    public class Menu {

        Scanner entrada = new Scanner(System.in);

        public void mostrarMenu() {
            System.out.println("\n===== FOREST FIRE =====");
            System.out.println("1. Mostrar reglas");
            System.out.println("2. Avanzar generación");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
        }

        public int leerOpcion() {
            return entrada.nextInt();
        }

        public void mostrarReglas() {
            System.out.println("\n===== REGLAS FOREST FIRE =====");
            System.out.println("1. Un árbol sano puede prenderse fuego si un vecino está ardiendo.");
            System.out.println("2. Un árbol en llamas se convierte en cenizas en la siguiente iteración.");
            System.out.println("3. Las cenizas pueden regenerarse en un árbol con probabilidad baja.");
        }

        public void ejecutarOpcion(int opcion) {

            if (opcion == 1) {

                mostrarReglas();

            } else if (opcion == 2) {

                System.out.println("\nAvanzando generación...");
                evolucionarGeneracion(ProyectoMatriz.bosque);
                ProyectoMatriz.MostrarBosque();

            } else if (opcion == 3) {

                System.out.println("Fin de la simulación.");

            } else {

                System.out.println("Opción no válida.");

            }
        }

        public void ejecutar() {

            int opcion;

            do {

                mostrarMenu();
                opcion = leerOpcion();
                ejecutarOpcion(opcion);

            } while (opcion != 3);
        }
    }

    public class ReglasIncendio {

        public static final char ARBOL = 'A';
        public static final char FUEGO = 'F';
        public static final char CENIZA = 'C';

        public static final double PROBABILIDAD_REGENERAR = 0.05;

        static Random random = new Random();

        public static boolean hayVecinoEnLlamas(char[][] bosque, int fila, int columna) {

            if (fila - 1 >= 0) {
                if (bosque[fila - 1][columna] == FUEGO) {
                    return true;
                }
            }

            if (fila + 1 < bosque.length) {
                if (bosque[fila + 1][columna] == FUEGO) {
                    return true;
                }
            }

            if (columna - 1 >= 0) {
                if (bosque[fila][columna - 1] == FUEGO) {
                    return true;
                }
            }

            if (columna + 1 < bosque[fila].length) {
                if (bosque[fila][columna + 1] == FUEGO) {
                    return true;
                }
            }

            return false;
        }

        public static char calcularNuevoEstado(char[][] bosque, int fila, int columna) {

            char estadoActual = bosque[fila][columna];
            char nuevoEstado = estadoActual;

            if (estadoActual == ARBOL) {

                if (hayVecinoEnLlamas(bosque, fila, columna)) {
                    nuevoEstado = FUEGO;
                }

            } else if (estadoActual == FUEGO) {

                nuevoEstado = CENIZA;

            } else if (estadoActual == CENIZA) {

                double numeroAleatorio = random.nextDouble();

                if (numeroAleatorio < PROBABILIDAD_REGENERAR) {
                    nuevoEstado = ARBOL;
                }
            }

            return nuevoEstado;
        }
    }

    public class ProyectoMatriz {

        static final int FILAS = 15;
        static final int COLUMNAS = 15;

        // Matriz del bosque
        static char[][] bosque = new char[FILAS][COLUMNAS];

        static final char ARBOL = 'A';
        static final char FUEGO = 'F';
        static final char CENIZA = 'C';

        static Random random = new Random();

        public static void IniciarBosque() {

            for (int i = 0; i < FILAS; i++) {

                for (int j = 0; j < COLUMNAS; j++) {

                    bosque[i][j] = ARBOL;
                }
            }

            // Árboles en llamas
            bosque[random.nextInt(FILAS)][random.nextInt(COLUMNAS)] = FUEGO;
            bosque[random.nextInt(FILAS)][random.nextInt(COLUMNAS)] = FUEGO;
        }

        public static void MostrarBosque() {

            System.out.println();

            for (int i = 0; i < FILAS; i++) {

                for (int j = 0; j < COLUMNAS; j++) {

                    System.out.print(bosque[i][j] + " ");
                }

                System.out.println();
            }
        }
    }

    public static void evolucionarGeneracion(char[][] bosque) {

        for (int fila = 0; fila < bosque.length; fila++) {

            for (int columna = 0; columna < bosque[fila].length; columna++) {

                if (bosque[fila][columna] == 'A') {

                    if (ReglasIncendio.hayVecinoEnLlamas(bosque, fila, columna)) {
                        bosque[fila][columna] = 'I';
                    }

                } else if (bosque[fila][columna] == 'C') {

                    char nuevoEstado = ReglasIncendio.calcularNuevoEstado(
                            bosque, fila, columna);

                    if (nuevoEstado == 'A') {
                        bosque[fila][columna] = 'A';
                    }
                }
            }
        }

        for (int fila = 0; fila < bosque.length; fila++) {

            for (int columna = 0; columna < bosque[fila].length; columna++) {

                if (bosque[fila][columna] == 'F') {

                    bosque[fila][columna] = 'C';

                } else if (bosque[fila][columna] == 'I') {

                    bosque[fila][columna] = 'F';
                }
            }
        }
    }
}