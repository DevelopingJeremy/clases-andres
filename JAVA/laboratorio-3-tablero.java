package com.mycompany.proyecto.andres;

import java.util.Random;

public class ProyectoAndres {

    // Tamaño del tablero
    static final int TAMANO = 8;

    // Cantidades solicitadas
    static final int MENSAJES = 8;
    static final int BLOQUEOS = 6;
    static final int MAX_INTENTOS = 200;

    // Generador de números aleatorios
    static Random random = new Random();

    // Movimientos posibles del caballo
    static int[] movimientoFila = {
        2, 2, -2, -2,
        1, 1, -1, -1
    };

    static int[] movimientoColumna = {
        1, -1, 1, -1,
        2, -2, 2, -2
    };

    public static void main(String[] args) {

        int[][] mejorTablero = null;

        int mejorCantidadMensajes = -1;
        int mejorCantidadMovimientos = 0;
        int mejorIntento = 0;

        int mejorFilaInicial = 0;
        int mejorColumnaInicial = 0;

        int mejorFilaFinal = 0;
        int mejorColumnaFinal = 0;

        boolean recorridoCompleto = false;

        int intentosRealizados = 0;

        // =========================================================
        // REALIZAR HASTA 200 INTENTOS
        // =========================================================

        for (int intento = 1; intento <= MAX_INTENTOS; intento++) {

            intentosRealizados++;

            // Crear tablero
            int[][] tablero = new int[TAMANO][TAMANO];

            // Generar caballo, mensajes y bloqueos
            int[] posicionInicial = generarTablero(tablero);

            int filaInicial = posicionInicial[0];
            int columnaInicial = posicionInicial[1];

            // Ejecutar recorrido
            int[] resultado = ejecutarRecorrido(
                    tablero,
                    filaInicial,
                    columnaInicial
            );

            int mensajesEntregados = resultado[0];
            int movimientosRealizados = resultado[1];
            int filaFinal = resultado[2];
            int columnaFinal = resultado[3];

            // =====================================================
            // GUARDAR EL MEJOR INTENTO
            // =====================================================

            if (mensajesEntregados > mejorCantidadMensajes) {

                mejorCantidadMensajes = mensajesEntregados;
                mejorCantidadMovimientos = movimientosRealizados;
                mejorIntento = intento;

                mejorFilaInicial = filaInicial;
                mejorColumnaInicial = columnaInicial;

                mejorFilaFinal = filaFinal;
                mejorColumnaFinal = columnaFinal;

                mejorTablero = copiarMatriz(tablero);
            }

            // =====================================================
            // SI ENTREGÓ LOS 8 MENSAJES, TERMINAMOS
            // =====================================================

            if (mensajesEntregados == MENSAJES) {

                recorridoCompleto = true;

                break;
            }
        }

        // =========================================================
        // MOSTRAR RESULTADOS
        // =========================================================

        System.out.println();
        System.out.println("======================================");
        System.out.println("       EL CABALLO MENSAJERO");
        System.out.println("======================================");

        if (recorridoCompleto) {
            System.out.println("Recorrido completado: SI");
        } else {
            System.out.println("Recorrido completado: NO");
        }

        System.out.println("Intentos realizados: " + intentosRealizados);
        System.out.println("Mensajes entregados: " + mejorCantidadMensajes);
        System.out.println("Movimientos realizados: " + mejorCantidadMovimientos);

        System.out.println();
        System.out.println("Posicion inicial: "
                + "(" + mejorFilaInicial + ", "
                + mejorColumnaInicial + ")");

        System.out.println("Posicion final: "
                + "(" + mejorFilaFinal + ", "
                + mejorColumnaFinal + ")");

        System.out.println("Mejor intento: " + mejorIntento);

        System.out.println();
        System.out.println("TABLERO FINAL:");
        System.out.println();

        mostrarTablero(mejorTablero);
    }

    // =============================================================
    // GENERAR TABLERO
    // =============================================================

    public static int[] generarTablero(int[][] tablero) {

        // ---------------------------------------------------------
        // Primero ponemos todas las casillas en 0
        // ---------------------------------------------------------

        for (int fila = 0; fila < TAMANO; fila++) {

            for (int columna = 0; columna < TAMANO; columna++) {

                tablero[fila][columna] = 0;
            }
        }

        // ---------------------------------------------------------
        // Colocar caballo
        // ---------------------------------------------------------

        int filaCaballo = random.nextInt(TAMANO);
        int columnaCaballo = random.nextInt(TAMANO);

        /*
         * De momento colocamos 1.
         * Esto representa la primera casilla visitada.
         */

        tablero[filaCaballo][columnaCaballo] = 1;

        // ---------------------------------------------------------
        // Colocar los 8 mensajes
        // ---------------------------------------------------------

        int mensajesColocados = 0;

        while (mensajesColocados < MENSAJES) {

            int fila = random.nextInt(TAMANO);
            int columna = random.nextInt(TAMANO);

            // Solo colocamos si la casilla está libre
            if (tablero[fila][columna] == 0) {

                tablero[fila][columna] = -2;

                mensajesColocados++;
            }
        }

        // ---------------------------------------------------------
        // Colocar los 6 bloqueos
        // ---------------------------------------------------------

        int bloqueosColocados = 0;

        while (bloqueosColocados < BLOQUEOS) {

            int fila = random.nextInt(TAMANO);
            int columna = random.nextInt(TAMANO);

            // Solo colocar si está libre
            if (tablero[fila][columna] == 0) {

                tablero[fila][columna] = -1;

                bloqueosColocados++;
            }
        }

        // Devolver la posición inicial
        return new int[]{
            filaCaballo,
            columnaCaballo
        };
    }

    // =============================================================
    // MOSTRAR TABLERO
    // =============================================================

    public static void mostrarTablero(int[][] tablero) {

        for (int fila = 0; fila < TAMANO; fila++) {

            for (int columna = 0; columna < TAMANO; columna++) {

                System.out.printf("%4d", tablero[fila][columna]);
            }

            System.out.println();
        }
    }

    // =============================================================
    // VALIDAR MOVIMIENTO
    // =============================================================

    public static boolean esMovimientoValido(
            int[][] tablero,
            int fila,
            int columna) {

        // ---------------------------------------------------------
        // Verificar que no salga del tablero
        // ---------------------------------------------------------

        if (fila < 0 || fila >= TAMANO) {
            return false;
        }

        if (columna < 0 || columna >= TAMANO) {
            return false;
        }

        // ---------------------------------------------------------
        // Verificar que no sea una casilla bloqueada
        // ---------------------------------------------------------

        if (tablero[fila][columna] == -1) {
            return false;
        }

        // ---------------------------------------------------------
        // Verificar que no haya sido visitada
        // ---------------------------------------------------------

        if (tablero[fila][columna] > 0) {
            return false;
        }

        // Si llegó hasta aquí, es válida
        return true;
    }

    // =============================================================
    // CONTAR MOVIMIENTOS DISPONIBLES
    // =============================================================

    public static int contarMovimientosDisponibles(
            int[][] tablero,
            int fila,
            int columna) {

        int cantidad = 0;

        // Revisamos los 8 movimientos del caballo
        for (int i = 0; i < 8; i++) {

            int nuevaFila =
                    fila + movimientoFila[i];

            int nuevaColumna =
                    columna + movimientoColumna[i];

            if (esMovimientoValido(
                    tablero,
                    nuevaFila,
                    nuevaColumna)) {

                cantidad++;
            }
        }

        return cantidad;
    }

    // =============================================================
    // SELECCIONAR MOVIMIENTO
    // =============================================================

    public static int seleccionarMovimiento(
            int[][] tablero,
            int fila,
            int columna) {

        /*
         * Aquí guardaremos los movimientos que tengan
         * la menor cantidad de opciones futuras.
         */

        int[] candidatos = new int[8];

        int cantidadCandidatos = 0;

        // Comenzamos con un número muy grande
        int menorCantidad = 100;

        // ---------------------------------------------------------
        // Revisar los 8 movimientos
        // ---------------------------------------------------------

        for (int i = 0; i < 8; i++) {

            int nuevaFila =
                    fila + movimientoFila[i];

            int nuevaColumna =
                    columna + movimientoColumna[i];

            // ¿El movimiento se puede realizar?
            if (esMovimientoValido(
                    tablero,
                    nuevaFila,
                    nuevaColumna)) {

                // Contamos las opciones futuras
                int opcionesFuturas =
                        contarMovimientosDisponibles(
                                tablero,
                                nuevaFila,
                                nuevaColumna
                        );

                // -------------------------------------------------
                // Encontramos una opción mejor
                // -------------------------------------------------

                if (opcionesFuturas < menorCantidad) {

                    menorCantidad = opcionesFuturas;

                    // Empezamos nuevamente la lista
                    cantidadCandidatos = 0;

                    candidatos[cantidadCandidatos] = i;

                    cantidadCandidatos++;
                }

                // -------------------------------------------------
                // Hay empate
                // -------------------------------------------------

                else if (opcionesFuturas == menorCantidad) {

                    candidatos[cantidadCandidatos] = i;

                    cantidadCandidatos++;
                }
            }
        }

        // ---------------------------------------------------------
        // No hay movimientos
        // ---------------------------------------------------------

        if (cantidadCandidatos == 0) {
            return -1;
        }

        // ---------------------------------------------------------
        // Elegir aleatoriamente entre los mejores
        // ---------------------------------------------------------

        int posicionAleatoria =
                random.nextInt(cantidadCandidatos);

        return candidatos[posicionAleatoria];
    }

    // =============================================================
    // COPIAR MATRIZ
    // =============================================================

    public static int[][] copiarMatriz(int[][] original) {

        int[][] copia =
                new int[TAMANO][TAMANO];

        for (int fila = 0; fila < TAMANO; fila++) {

            for (int columna = 0; columna < TAMANO; columna++) {

                copia[fila][columna] =
                        original[fila][columna];
            }
        }

        return copia;
    }

    // =============================================================
    // EJECUTAR RECORRIDO
    // =============================================================

    public static int[] ejecutarRecorrido(
            int[][] tablero,
            int fila,
            int columna) {

        int mensajesEntregados = 0;

        int movimientosRealizados = 0;

        /*
         * La posición inicial ya fue visitada.
         * Por eso está marcada como 1.
         */

        int numeroVisita = 1;

        // ---------------------------------------------------------
        // Continuar mientras existan mensajes por entregar
        // ---------------------------------------------------------

        while (mensajesEntregados < MENSAJES) {

            // Buscar el mejor movimiento
            int movimiento = seleccionarMovimiento(
                    tablero,
                    fila,
                    columna
            );

            // -----------------------------------------------------
            // Si no existe movimiento, termina el intento
            // -----------------------------------------------------

            if (movimiento == -1) {
                break;
            }

            // -----------------------------------------------------
            // Calcular nueva posición
            // -----------------------------------------------------

            int nuevaFila =
                    fila + movimientoFila[movimiento];

            int nuevaColumna =
                    columna + movimientoColumna[movimiento];

            // -----------------------------------------------------
            // Verificar si había un mensaje
            // -----------------------------------------------------

            if (tablero[nuevaFila][nuevaColumna] == -2) {

                mensajesEntregados++;
            }

            // -----------------------------------------------------
            // Actualizar número de visita
            // -----------------------------------------------------

            numeroVisita++;

            tablero[nuevaFila][nuevaColumna] =
                    numeroVisita;

            // -----------------------------------------------------
            // Actualizar posición del caballo
            // -----------------------------------------------------

            fila = nuevaFila;
            columna = nuevaColumna;

            movimientosRealizados++;
        }

        /*
         * Devolvemos:
         *
         * posición 0 = mensajes entregados
         * posición 1 = movimientos realizados
         * posición 2 = fila final
         * posición 3 = columna final
         */

        return new int[]{
            mensajesEntregados,
            movimientosRealizados,
            fila,
            columna
        };
    }
}
