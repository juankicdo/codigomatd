import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario que ingrese un número del 0 al 9
        System.out.print("Ingrese un número del 0 al 9 para representar la cantidad de vértices: ");
        int numVertices = scanner.nextInt();

        // Calcular las posibilidades y mostrar el resultado
        long posibilidades = calcularPosibilidades(numVertices);
        System.out.println("La cantidad de contraseñas posibles es: " + posibilidades);
    }

    // Función para calcular las posibilidades
    private static long calcularPosibilidades(int numVertices) {
        int[][] matriz = generarMatriz(numVertices);
        mostrarMatriz(matriz, "Matriz Original:");

        int[][] resultado = elevarMatrizAlCubo(matriz);
        mostrarMatriz(resultado, "Matriz Elevada al Cubo:");

        // Sumar todos los elementos de la matriz resultado para obtener las posibilidades
        long posibilidades = 0;
        for (int i = 0; i < numVertices + 1; i++) {
            for (int j = 0; j < numVertices + 1; j++) {
                posibilidades += resultado[i][j];
            }
        }

        return posibilidades;
    }

    // Función para generar la matriz con bucles
    private static int[][] generarMatriz(int numVertices) {
        int[][] matriz = new int[numVertices + 1][numVertices + 1];

        for (int i = 0; i < numVertices + 1; i++) {
            for (int j = 0; j < numVertices + 1; j++) {
                matriz[i][j] = 1; // Establecer conexión entre los vértices (también consigo mismos)
            }
        }

        return matriz;
    }

    // Función para mostrar la representación de la matriz
    private static void mostrarMatriz(int[][] matriz, String mensaje) {
        System.out.println(mensaje);
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Función para elevar la matriz al cubo
    private static int[][] elevarMatrizAlCubo(int[][] matriz) {
        int n = matriz.length;
        int[][] resultado = new int[n][n];

        // Inicializar la matriz resultado como la matriz original
        for (int i = 0; i < n; i++) {
            System.arraycopy(matriz[i], 0, resultado[i], 0, n);
        }

        // Elevar la matriz al cubo
        for (int k = 2; k <= 3; k++) {
            resultado = multiplicarMatrices(resultado, matriz);
        }

        return resultado;
    }

    // Función para multiplicar dos matrices
    private static int[][] multiplicarMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] resultado = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    resultado[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return resultado;
    }
}