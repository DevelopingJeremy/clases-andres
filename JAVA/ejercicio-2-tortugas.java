package com.mycompany.proyecto.andres;
import java.util.Random;
import java.util.Scanner;

public class ProyectoAndres {

    // Matriz donde se dibuja el recorrido
    static char[][] piso = new char[30][30];

    // Posición de la tortuga
    static int filaTortuga;
    static int columnaTortuga;

    // Posición de la liebre
    static int filaLiebre;
    static int columnaLiebre;

    // Dirección de la tortuga
    // 0 = Arriba
    // 1 = Derecha
    // 2 = Abajo
    // 3 = Izquierda
    static int direccion = 0;

    // Estado de la pluma
    static boolean plumaAbajo = false;

    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        inicializarPiso();

        colocarPersonajes();

        menu();

    }

    // Llena toda la matriz con espacios

    public static void inicializarPiso() {

        for (char[] piso1 : piso) {
            for (int j = 0; j < piso1.length; j++) {
                piso1[j] = '.';
            }
        }

    }

    // Coloca la tortuga y la liebre en posiciones aleatorias

    public static void colocarPersonajes() {

        Random random = new Random();

        filaTortuga = random.nextInt(30);
        columnaTortuga = random.nextInt(30);

        filaLiebre = random.nextInt(30);
        columnaLiebre = random.nextInt(30);

    }

    // Menú principal

    public static void menu() {

        int comando;

        do {

            System.out.println();
            System.out.println("1. Pluma arriba");
            System.out.println("2. Pluma abajo");
            System.out.println("3. Girar derecha");
            System.out.println("4. Girar izquierda");
            System.out.println("5. Avanzar");
            System.out.println("6. Imprimir");
            System.out.println("9. Salir");

            System.out.print("Digite un comando: ");
            comando = teclado.nextInt();

            switch (comando) {

                case 1 -> plumaAbajo = false;

                case 2 -> plumaAbajo = true;

                case 3 -> girarDerecha();

                case 4 -> girarIzquierda();

                case 5 -> {
                    System.out.print("¿Cuántos pasos desea avanzar?: ");
                    int pasos = teclado.nextInt();

                    moverTortuga(pasos);

                    moverLiebre(pasos);
                }

                case 6 -> imprimirPiso();

                case 9 -> System.out.println("Fin del programa.");

                default -> System.out.println("Comando inválido.");

            }

        } while (comando != 9);

    }

    // Cambia la dirección hacia la derecha

    public static void girarDerecha() {

        direccion++;

        if (direccion == 4) {

            direccion = 0;

        }

    }

    // Cambia la dirección hacia la izquierda

    public static void girarIzquierda() {

        direccion--;

        if (direccion == -1) {

            direccion = 3;

        }

    }

    // Mueve la tortuga
    public static void moverTortuga(int pasos) {

        for (int i = 0; i < pasos; i++) {

            // Verificar si la pluma esta escribiendo
            if (plumaAbajo == true) {
                piso[filaTortuga][columnaTortuga] = '*';
            }

            switch (direccion) {

                // Arriba
                case 0 -> {
                    if (filaTortuga > 0) {
                        filaTortuga--;
                    }
                }

                // Derecha
                case 1 -> {
                    if (columnaTortuga < 29) {
                        columnaTortuga++;
                    }
                }

                // Abajo
                case 2 -> {
                    if (filaTortuga < 29) {
                        filaTortuga++;
                    }
                }

                // Izquierda
                case 3 -> {
                    if (columnaTortuga > 0) {
                        columnaTortuga--;
                    }
                }

            }

            if (plumaAbajo == true) {
                piso[filaTortuga][columnaTortuga] = '*';
            }

        }

    }

    // Mueve la liebre
    public static void moverLiebre(int pasos) {

        pasos *= 2;

        for (int i = 0; i < pasos; i++) {

            if (plumaAbajo == true) {
                piso[filaLiebre][columnaLiebre] = '+';
            }

            switch (direccion) {

                // Tortuga arriba -> Liebre abajo
                case 0 -> {
                    if (filaLiebre < 29) {
                        filaLiebre++;
                    }
                }

                // Tortuga derecha -> Liebre izquierda
                case 1 -> {
                    if (columnaLiebre > 0) {
                        columnaLiebre--;
                    }
                }

                // Tortuga abajo -> Liebre arriba
                case 2 -> {
                    if (filaLiebre > 0) {
                        filaLiebre--;
                    }
                }

                // Tortuga izquierda -> Liebre derecha
                case 3 -> {
                    if (columnaLiebre < 29) {
                        columnaLiebre++;
                    }
                }

            }

            if (plumaAbajo == true) {
                piso[filaLiebre][columnaLiebre] = '+';
            }

        }

    }

    // Imprime la matriz

    public static void imprimirPiso() {

        System.out.println();

        for (int filaActual = 0; filaActual < piso.length; filaActual++) {

            for (int columnas = 0; columnas < piso[filaActual].length; columnas++) {

                // Si la tortuga está en esta posición,
                // se imprime una T

                if (filaActual == filaTortuga && columnas == columnaTortuga) {

                    System.out.print("T ");

                }

                // Si la liebre está aquí,
                // se imprime una L

                else if (filaActual == filaLiebre && columnaActual == columnaLiebre) {

                    System.out.print("L ");

                }

                // En cualquier otro caso
                // se imprime el contenido de la matriz

                else {

                    System.out.print(piso[filaActual][columnas] + " ");

                }

            }

            System.out.println();

        }

    }

}