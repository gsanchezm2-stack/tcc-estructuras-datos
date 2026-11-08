/** Matriz: arreglo bidimensional organizado en filas y columnas. Admite repetidos. */
public class Matriz {
    private final int[][] m;
    public Matriz(int filas, int cols) { m = new int[filas][cols]; }
    public int filas() { return m.length; }
    public int cols() { return m[0].length; }
    public boolean valida(int f, int c) { return f >= 0 && f < filas() && c >= 0 && c < cols(); }
    public int get(int f, int c) { return m[f][c]; }
    public void set(int f, int c, int v) { m[f][c] = v; }

    public void mostrar() {
        StringBuilder h = new StringBuilder("        ");
        for (int c = 0; c < cols(); c++) h.append(String.format("  col %-3d", c));
        System.out.println(h);
        for (int f = 0; f < filas(); f++) {
            StringBuilder s = new StringBuilder(String.format("fila %-3d ", f));
            for (int c = 0; c < cols(); c++) s.append(String.format("| %5d  ", m[f][c]));
            System.out.println(s + "|");
        }
    }

    public void recorrer() {
        System.out.println("Recorrido por filas:");
        for (int f = 0; f < filas(); f++)
            for (int c = 0; c < cols(); c++) System.out.println("  [" + f + "][" + c + "] = " + m[f][c]);
    }

    public static void menu() {
        System.out.println("\n=== MATRIZ ===");
        System.out.println("Arreglo bidimensional: cada elemento se identifica por (fila, columna).");
        int lim = 10;
        int f, c;
        while (true) {
            f = Entrada.leerCantidad("Número de filas (1-" + lim + "): ", lim);
            c = Entrada.leerCantidad("Número de columnas (1-" + lim + "): ", lim);
            if (f * c <= Entrada.LIMITE) break;
            System.out.println("  [Error] El total (" + f * c + ") supera el límite de " + Entrada.LIMITE + " elementos.");
        }
        System.out.println("Total de elementos a ingresar: " + (f * c));
        Matriz mt = new Matriz(f, c);
        for (int i = 0; i < f; i++)
            for (int j = 0; j < c; j++)
                mt.set(i, j, Entrada.leerEntero("Valor para fila " + i + ", columna " + j + ": "));
        mt.mostrar();
        int op;
        do {
            System.out.println("\n1. Consultar (fila, col)  2. Modificar  3. Recorrer  4. Mostrar  0. Volver al menú");
            op = Entrada.leerEnRango("Opción: ", 0, 4);
            switch (op) {
                case 1, 2 -> {
                    int i = Entrada.leerEntero("Fila: "), j = Entrada.leerEntero("Columna: ");
                    if (!mt.valida(i, j)) System.out.println("  [Error] Posición fuera de rango (filas 0-" + (f - 1) + ", columnas 0-" + (c - 1) + ").");
                    else if (op == 1) System.out.println("  m[" + i + "][" + j + "] = " + mt.get(i, j));
                    else {
                        int ant = mt.get(i, j);
                        mt.set(i, j, Entrada.leerEntero("Nuevo valor: "));
                        System.out.println("  m[" + i + "][" + j + "]: " + ant + " -> " + mt.get(i, j));
                        mt.mostrar();
                    }
                }
                case 3 -> mt.recorrer();
                case 4 -> mt.mostrar();
                default -> { }
            }
        } while (op != 0);
    }
}
