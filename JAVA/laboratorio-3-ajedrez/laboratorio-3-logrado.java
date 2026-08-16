/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package laboratorio3andrésaguilera;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author aagui
 */
public class Laboratorio3AndrésAguilera {

    static final int FILAS = 8;
    static final int COLUMNAS = 8;

    static int filaCaballo = 0;
    static int columnaCaballo = 0;

    //Matriz Tablero de Ajedrez
    static int[][] tablero = new int[FILAS][COLUMNAS];

    static Random random = new Random();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IniciarTablero();
        MostrarTablero();
        System.out.println("Caballo esta en la fila " + filaCaballo + " Caballo esta en columna " + columnaCaballo);

    }

    //Iniciar metodo tablero
    public static void IniciarTablero() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j] = 0;
            }
        }
        filaCaballo = random.nextInt(FILAS);

        columnaCaballo = random.nextInt(COLUMNAS);

        //Casilla bloqueada
        for (int i = 0; i < 6; i++) {
            tablero[random.nextInt(FILAS)][random.nextInt(COLUMNAS)] = -1;
        }
        //Casilla con mensaje
        for (int i = 0; i < 8; i++) {
            int filaValida = random.nextInt(FILAS);
            int columnaValida = random.nextInt(COLUMNAS);

            while (esMovimientoValido(filaValida, columnaValida) == false
                    || tablero[filaValida][columnaValida] == -2) {
                filaValida = random.nextInt(FILAS);
                columnaValida = random.nextInt(COLUMNAS);
            }
            tablero[filaValida][columnaValida] = -2;
        }
    }

    //Metodo mostrar tablero 
    public static void MostrarTablero() {
        System.out.println();
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }

    }

    //Metodo es Movimiento Valido
    public static boolean esMovimientoValido(int fila, int columna) {

        if (fila >= FILAS || fila <= -1) {
            return false;
        }
        
        if (columna >= COLUMNAS || columna <= -1) {
            return false;
        }

        if (tablero[fila][columna] > 0 || tablero[fila][columna] == -1) {
            return false;
        }

        return true;
    }

    //Metodo ejecutar recorrido 
    public static void ejecutarRecorrido() {

        int[] movimientoFila = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] movimientoColumna = {1, 2, 2, 1, -1, -2, -2, -1};

        for (int i = 0; i < 8; i++) {
            int nuevaFila = filaCaballo + movimientoFila[i];
            int nuevaColumna = columnaCaballo + movimientoColumna[i];

            if (esMovimientoValido(nuevaFila, nuevaColumna) == true) {

            }

        }

    }

}

