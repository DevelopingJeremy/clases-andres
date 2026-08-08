package com.mycompany.proyecto.andres;

import java.util.Random;
import java.util.Scanner;

public class ProyectoAndres {

    static final int FILAS = 10;
    static final int COLUMNAS = 10;

    static final char ARBOL = 'T';
    static final char FUEGO = 'F';
    static final char CENIZA = 'A';

    static Random random = new Random();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] bosque = new char[FILAS][COLUMNAS];
        inicializarBosque(bosque);
        int opcion = 0;
        while (opcion != 3) {
            mostrarBosque(bosque);
            System.out.println("\n===== FOREST FIRE =====");
            System.out.println("1. Avanzar una iteración");
            System.out.println("2. Mostrar reglas");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            if (opcion == 1) {
                avanzarEstado(bosque);
            }
            if (opcion == 2) {
                mostrarReglas();
            }
        }
        sc.close();
    }

    // Inicializa el bosque
    public static void inicializarBosque(char[][] bosque) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                bosque[i][j] = ARBOL;
            }
        }
        // Colocar algunos incendios iniciales
        bosque[4][4] = FUEGO;
        bosque[2][7] = FUEGO;
    }

    // Mostrar bosque
    public static void mostrarBosque(char[][] bosque) {
        System.out.println();
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(bosque[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Mostrar reglas
    public static void mostrarReglas() {
        System.out.println("\nREGLAS:");
        System.out.println("T = Árbol");
        System.out.println("F = Fuego");
        System.out.println("A = Ceniza");
        System.out.println();
        System.out.println("1. Un árbol prende fuego si tiene un vecino en llamas.");
        System.out.println("2. Un árbol en llamas pasa a ceniza en la siguiente iteración.");
        System.out.println("3. Una ceniza puede regenerarse a árbol con baja probabilidad.");
    }

    // Avanza una generación
    public static void avanzarEstado(char[][] bosque) {
        char[][] nuevo = new char[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (bosque[i][j] == ARBOL) {
                    if (tieneVecinoEnFuego(bosque, i, j)) {
                        nuevo[i][j] = FUEGO;
                    } else {
                        nuevo[i][j] = ARBOL;
                    }
                } else if (bosque[i][j] == FUEGO) {
                    nuevo[i][j] = CENIZA;
                } else {
                    // 2% de probabilidad de que la ceniza vuelva a encenderse
                    if (random.nextInt(100) < 2) {
                        nuevo[i][j] = FUEGO;
                    }
                    // 5% de probabilidad de que la ceniza se regenere como árbol
                    } else if (random.nextInt(100) < 5) {
                        nuevo[i][j] = ARBOL;
                    } else {
                        nuevo[i][j] = CENIZA;
                    }
                }
            }
        }

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                bosque[i][j] = nuevo[i][j];
            }
        }
    }

    // Verifica si existe un vecino en llamas
    public static boolean tieneVecinoEnFuego(char[][] bosque, int fila, int columna) {
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {

                if (i >= 0 && i < FILAS && j >= 0 && j < COLUMNAS) {
                    if (!(i == fila && j == columna)) {
                        if (bosque[i][j] == FUEGO) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }

}
