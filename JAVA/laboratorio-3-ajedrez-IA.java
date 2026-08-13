/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package laboratorio3andrésaguilera;

import java.util.Random;

/**
 *
 * @author aagui
 */
public class Laboratorio3AndrésAguilera  {

    static final int FILAS = 8;
    static final int COLUMNAS = 8;

    static int filaCaballo = 0;
    static int columnaCaballo = 0;

    //Matriz
    static int[][] tablero = new int[FILAS][COLUMNAS];

    static Random random = new Random();

    // Mejor resultado encontrado
    static int[][] mejorTablero = new int[FILAS][COLUMNAS];

    static int mejorCantidadMensajes = 0;
    static int mejorCantidadMovimientos = 0;
    static int mejorIntento = 0;

    static int mejorFilaInicial = 0;
    static int mejorColumnaInicial = 0;

    static int mejorFilaFinal = 0;
    static int mejorColumnaFinal = 0;

    public static void main(String[] args) {

        int intentos = 0;
        boolean recorridoCompleto = false;

        // Realizamos hasta 200 intentos
        while (intentos < 200 && recorridoCompleto == false) {

            intentos++;

            // Generamos un tablero nuevo
            generarTablero();

            // Guardamos la posición inicial
            int filaInicial = filaCaballo;
            int columnaInicial = columnaCaballo;

            // Ejecutamos el recorrido
            int[] resultado = ejecutarRecorrido();

            int mensajesEntregados = resultado[0];
            int movimientosRealizados = resultado[1];

            // Revisamos si se entregaron los 8 mensajes
            if (mensajesEntregados == 8) {

                recorridoCompleto = true;

                mejorTablero = copiarMatriz(tablero);

                mejorCantidadMensajes = mensajesEntregados;
                mejorCantidadMovimientos = movimientosRealizados;

                mejorIntento = intentos;

                mejorFilaInicial = filaInicial;
                mejorColumnaInicial = columnaInicial;

                mejorFilaFinal = filaCaballo;
                mejorColumnaFinal = columnaCaballo;

            } else if (mensajesEntregados > mejorCantidadMensajes) {

                // Guardamos el intento que entregó más mensajes
                mejorTablero = copiarMatriz(tablero);

                mejorCantidadMensajes = mensajesEntregados;
                mejorCantidadMovimientos = movimientosRealizados;

                mejorIntento = intentos;

                mejorFilaInicial = filaInicial;
                mejorColumnaInicial = columnaInicial;

                mejorFilaFinal = filaCaballo;
                mejorColumnaFinal = columnaCaballo;
            }
        }

        System.out.println();
        System.out.println("Final:");

        if (recorridoCompleto) {
            System.out.println("Se completo el recorrido.");
        } else {
            System.out.println("No se completo el recorrido.");
        }

        System.out.println("Intentos realizados: " + intentos);
        System.out.println("Mensajes entregados: " + mejorCantidadMensajes);
        System.out.println("Movimientos realizados: " + mejorCantidadMovimientos);

        System.out.println("Posicion inicial: fila "
                + (mejorFilaInicial + 1)
                + ", columna "
                + (mejorColumnaInicial + 1));

        System.out.println("Posicion final: fila "
                + (mejorFilaFinal + 1)
                + ", columna "
                + (mejorColumnaFinal + 1));

        System.out.println("Mejor intento: " + mejorIntento);

        System.out.println();
        System.out.println("TABLERO FINAL");

        mostrarTablero(mejorTablero);
    }

    // Metodo generarTablero
    public static void generarTablero() {

        // Limpiamos el tablero
        for (int i = 0; i < FILAS; i++) {

            for (int j = 0; j < COLUMNAS; j++) {

                tablero[i][j] = 0;
            }
        }

        //Casilla bloqueada
        for (int i = 0; i < 6; i++) {
            int fila = random.nextInt(FILAS);
            int columna = random.nextInt(COLUMNAS);
            // Mientras la casilla no este libre
            while (tablero[fila][columna] != 0) {
                fila = random.nextInt(FILAS);
                columna = random.nextInt(COLUMNAS);
            }

            tablero[fila][columna] = -1;
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

        // Colocar el caballo
        filaCaballo = random.nextInt(FILAS);
        columnaCaballo = random.nextInt(COLUMNAS);
        while (tablero[filaCaballo][columnaCaballo] != 0) {
            filaCaballo = random.nextInt(FILAS);
            columnaCaballo = random.nextInt(COLUMNAS);
        }
        tablero[filaCaballo][columnaCaballo] = 1;
    }

    //Metodo mostrarTablero
    public static void mostrarTablero(int[][] matriz) {

        System.out.println();

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // Metodo esMovimientoValido
    public static boolean esMovimientoValido(int fila, int columna) {

        //fila este dentro del tablero
        if (fila < 0 || fila >= FILAS) {
            return false;
        }

        //columna este dentro del tablero
        if (columna < 0 || columna >= COLUMNAS) {
            return false;
        }

        // -1 significa casilla bloqueada
        if (tablero[fila][columna] == -1) {
            return false;
        }

        //casilla visitada
        if (tablero[fila][columna] > 0) {
            return false;
        }

        return true;
    }

    //Metodo contarMovimientosDisponibles
    public static int contarMovimientosDisponibles(int fila, int columna) {
        int movimientosValidos = 0;

        // Los 8 movimientos posibles del caballo
        int[] movimientoFila = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] movimientoColumna = {1, 2, 2, 1, -1, -2, -2, -1};

        // Revisamos los 8 movimientos
        for (int i = 0; i < 8; i++) {
            int nuevaFila = fila + movimientoFila[i];
            int nuevaColumna = columna + movimientoColumna[i];

            if (esMovimientoValido(nuevaFila, nuevaColumna)) {
                movimientosValidos++;
            }
        }

        return movimientosValidos;
    }

    //Metodo seleccionarMovimiento
    public static int seleccionarMovimiento() {

        // Los 8 movimientos posibles del caballo
        int[] movimientoFila = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] movimientoColumna = {1, 2, 2, 1, -1, -2, -2, -1};

        // Vectores para guardar los candidatos
        int[] filasCandidatas = new int[8];
        int[] columnasCandidatas = new int[8];

        int menorCantidad = 9;
        int cantidadCandidatos = 0;

        // Revisamos los 8 movimientos
        for (int i = 0; i < 8; i++) {

            int nuevaFila = filaCaballo + movimientoFila[i];
            int nuevaColumna = columnaCaballo + movimientoColumna[i];

            //movimiento es posible
            if (esMovimientoValido(nuevaFila, nuevaColumna)) {
                int disponibles = contarMovimientosDisponibles(nuevaFila, nuevaColumna);

                if (disponibles < menorCantidad) {
                    menorCantidad = disponibles;
                    cantidadCandidatos = 0;
                    filasCandidatas[cantidadCandidatos] = nuevaFila;
                    columnasCandidatas[cantidadCandidatos] = nuevaColumna;
                    cantidadCandidatos++;

                } else if (disponibles == menorCantidad) {
                    filasCandidatas[cantidadCandidatos] = nuevaFila;
                    columnasCandidatas[cantidadCandidatos] = nuevaColumna;
                    cantidadCandidatos++;
                }
            }
        }

        // Si no hay movimientos validos
        if (cantidadCandidatos == 0) {
            return 0;
        }

        //Seleccionar aleatoriamente uno de los candidatos
        int posicion = random.nextInt(cantidadCandidatos);

        filaCaballo = filasCandidatas[posicion];
        columnaCaballo = columnasCandidatas[posicion];

        return 1;
    }

    //Metodo copiarMatriz
    public static int[][] copiarMatriz(int[][] matriz) {

        int[][] copia = new int[FILAS][COLUMNAS];

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                copia[i][j] = matriz[i][j];
            }
        }

        return copia;
    }

    //Metodo ejecutarRecorrido
    public static int[] ejecutarRecorrido() {

        int mensajesEntregados = 0;
        int movimientosRealizados = 0;

        int numeroVisita = 1;

        boolean hayMovimientos = true;

        //Bucle para repetir busqueda de movimientos
        while (mensajesEntregados < 8 && hayMovimientos) {

            // Seleccionamos el siguiente movimiento
            int movimiento = seleccionarMovimiento();

            // Si no existe movimiento valido
            if (movimiento == 0) {
                hayMovimientos = false;

            } else {

                movimientosRealizados++;

                numeroVisita++;

                //contar mensajes
                if (tablero[filaCaballo][columnaCaballo] == -2) {
                    mensajesEntregados++;
                }

                //Orden de la visita
                tablero[filaCaballo][columnaCaballo] = numeroVisita;
            }
        }

        // Retornamos los resultados
        int[] resultado = new int[2];

        resultado[0] = mensajesEntregados;
        resultado[1] = movimientosRealizados;

        return resultado;
    }
}
