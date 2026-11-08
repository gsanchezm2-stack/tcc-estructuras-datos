public class Arreglo {
    private final int[] datos;

    public Arreglo(int tamano) { datos = new int[tamano]; }

    public int tamano() { return datos.length; }
    public boolean indiceValido(int i) { return i >= 0 && i < datos.length; }
    public int consultar(int i) { return datos[i]; }
    public void modificar(int i, int v) { datos[i] = v; }
    public void registrar(int i, int v) { datos[i] = v; }

    public void mostrar() {
        StringBuilder idx = new StringBuilder("Índice : ");
        StringBuilder val = new StringBuilder("Valor  : ");
        for (int i = 0; i < datos.length; i++) {
            idx.append(String.format("[%3d] ", i));
            val.append(String.format(" %3d  ", datos[i]));
        }
        System.out.println(idx);
        System.out.println(val);
    }

    public void recorrer() {
        System.out.println("Recorrido secuencial:");
        for (int i = 0; i < datos.length; i++) System.out.println("  posición " + i + " -> " + datos[i]);
    }

    public static void menu() {
        System.out.println("\n=== ARREGLO UNIDIMENSIONAL ===");
        System.out.println("Estructura lineal de tamaño fijo; cada elemento se accede por su índice (inicia en 0).");
        int n = Entrada.leerCantidad("¿Cuántos datos va a ingresar? (1-" + Entrada.LIMITE + "): ", Entrada.LIMITE);
        Arreglo a = new Arreglo(n);
        for (int i = 0; i < n; i++) a.registrar(i, Entrada.leerEntero("Ingrese el dato #" + (i + 1) + " (posición " + i + "): "));
        a.mostrar();
        int op;
        do {
            System.out.println("\n1. Consultar por índice  2. Modificar  3. Recorrer  4. Mostrar  0. Volver al menú");
            op = Entrada.leerEnRango("Opción: ", 0, 4);
            switch (op) {
                case 1 -> {
                    int i = Entrada.leerEntero("Índice a consultar: ");
                    if (!a.indiceValido(i)) System.out.println("  [Error] Índice fuera de rango (válidos: 0-" + (n - 1) + ").");
                    else System.out.println("  a[" + i + "] = " + a.consultar(i));
                }
                case 2 -> {
                    int i = Entrada.leerEntero("Índice a modificar: ");
                    if (!a.indiceValido(i)) System.out.println("  [Error] Índice fuera de rango (válidos: 0-" + (n - 1) + ").");
                    else {
                        int ant = a.consultar(i);
                        a.modificar(i, Entrada.leerEntero("Nuevo valor: "));
                        System.out.println("  a[" + i + "]: " + ant + " -> " + a.consultar(i));
                        a.mostrar();
                    }
                }
                case 3 -> a.recorrer();
                case 4 -> a.mostrar();
                default -> { }
            }
        } while (op != 0);
    }
}
