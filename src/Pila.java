/** Pila (LIFO) implementada con arreglo de capacidad fija. Admite repetidos. */
public class Pila {
    private final int[] datos;
    private int cima = -1;

    public Pila(int capacidad) { datos = new int[capacidad]; }
    public boolean vacia() { return cima == -1; }
    public boolean llena() { return cima == datos.length - 1; }
    public int tamano() { return cima + 1; }
    public void apilar(int v) { datos[++cima] = v; }
    public int desapilar() { return datos[cima--]; }
    public int cima() { return datos[cima]; }

    public void mostrar() {
        if (vacia()) { System.out.println("  (pila vacía)  cima = -1"); return; }
        System.out.println("  +-------+");
        for (int i = cima; i >= 0; i--)
            System.out.println("  | " + String.format("%5d", datos[i]) + " |" + (i == cima ? "  <-- CIMA (índice " + i + ")" : "  índice " + i));
        System.out.println("  +-------+");
    }

    public static void menu() {
        System.out.println("\n=== PILA (LIFO: último en entrar, primero en salir) ===");
        int n = Entrada.leerCantidad("¿Cuántos datos va a ingresar? (1-" + Entrada.LIMITE + "): ", Entrada.LIMITE);
        Pila p = new Pila(Entrada.LIMITE);
        for (int i = 0; i < n; i++) {
            p.apilar(Entrada.leerEntero("Ingrese el dato #" + (i + 1) + " a apilar: "));
        }
        p.mostrar();
        int op;
        do {
            System.out.println("\n1. Apilar  2. Desapilar  3. Ver cima  4. Mostrar  0. Volver al menú");
            op = Entrada.leerEnRango("Opción: ", 0, 4);
            switch (op) {
                case 1 -> {
                    if (p.llena()) System.out.println("  [Error] Pila llena (capacidad " + Entrada.LIMITE + ").");
                    else { p.apilar(Entrada.leerEntero("Valor a apilar: ")); p.mostrar(); }
                }
                case 2 -> {
                    if (p.vacia()) System.out.println("  [Error] La pila está vacía: no hay elemento que desapilar.");
                    else { System.out.println("  Se retira de la cima: " + p.desapilar()); p.mostrar(); }
                }
                case 3 -> System.out.println(p.vacia() ? "  [Aviso] La pila está vacía; no hay cima." : "  Elemento en la cima: " + p.cima());
                case 4 -> p.mostrar();
                default -> { }
            }
        } while (op != 0);
    }
}
