/** Cola (FIFO) implementada con nodos enlazados, con referencias a frente y final. Admite repetidos. */
public class Cola {
    private static class Nodo { int dato; Nodo sig; Nodo(int d) { dato = d; } }
    private Nodo frente, fin;
    private int tam;

    public boolean vacia() { return tam == 0; }
    public int tamano() { return tam; }
    public void encolar(int v) {
        Nodo n = new Nodo(v);
        if (fin == null) frente = fin = n; else { fin.sig = n; fin = n; }
        tam++;
    }
    public int desencolar() {
        int v = frente.dato;
        frente = frente.sig;
        if (frente == null) fin = null;
        tam--;
        return v;
    }
    public int frente() { return frente.dato; }

    public void mostrar() {
        if (vacia()) { System.out.println("  (cola vacía)  frente = null, final = null"); return; }
        StringBuilder s = new StringBuilder("  SALE <- FRENTE [");
        for (Nodo a = frente; a != null; a = a.sig) s.append(a.dato).append(a.sig != null ? " | " : "");
        System.out.println(s + "] FINAL <- ENTRA   (tamaño " + tam + ")");
        System.out.println("  Frente = " + frente.dato + ", Final = " + fin.dato);
    }

    public static void menu() {
        System.out.println("\n=== COLA (FIFO: primero en entrar, primero en salir) ===");
        int n = Entrada.leerCantidad("¿Cuántos datos va a ingresar? (1-" + Entrada.LIMITE + "): ", Entrada.LIMITE);
        Cola c = new Cola();
        for (int i = 0; i < n; i++) c.encolar(Entrada.leerEntero("Ingrese el dato #" + (i + 1) + " a encolar: "));
        c.mostrar();
        int op;
        do {
            System.out.println("\n1. Encolar  2. Desencolar  3. Ver frente  4. Mostrar  0. Volver al menú");
            op = Entrada.leerEnRango("Opción: ", 0, 4);
            switch (op) {
                case 1 -> {
                    if (c.tamano() >= Entrada.LIMITE) System.out.println("  [Error] Cola llena (límite " + Entrada.LIMITE + ").");
                    else { c.encolar(Entrada.leerEntero("Valor a encolar: ")); c.mostrar(); }
                }
                case 2 -> {
                    if (c.vacia()) System.out.println("  [Error] La cola está vacía: no hay elemento que desencolar.");
                    else { System.out.println("  Sale por el frente: " + c.desencolar()); c.mostrar(); }
                }
                case 3 -> System.out.println(c.vacia() ? "  [Aviso] La cola está vacía; no hay frente." : "  Elemento al frente: " + c.frente());
                case 4 -> c.mostrar();
                default -> { }
            }
        } while (op != 0);
    }

    public static void comparar() {
        System.out.println("\n=== COMPARACIÓN PILA vs COLA (misma secuencia de entrada) ===");
        int n = Entrada.leerCantidad("¿Cuántos datos va a ingresar? (1-" + Entrada.LIMITE + "): ", Entrada.LIMITE);
        Pila p = new Pila(Entrada.LIMITE);
        Cola c = new Cola();
        StringBuilder entrada = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int v = Entrada.leerEntero("Ingrese el dato #" + (i + 1) + ": ");
            p.apilar(v); c.encolar(v);
            entrada.append(v).append(i < n - 1 ? ", " : "");
        }
        System.out.println("\nOrden de entrada : " + entrada);
        System.out.println("\nPila:"); p.mostrar();
        System.out.println("Cola:"); c.mostrar();
        StringBuilder sp = new StringBuilder(), sc = new StringBuilder();
        while (!p.vacia()) { sp.append(p.desapilar()).append(p.vacia() ? "" : ", "); }
        while (!c.vacia()) { sc.append(c.desencolar()).append(c.vacia() ? "" : ", "); }
        System.out.println("\nOrden de salida de la PILA (LIFO): " + sp);
        System.out.println("Orden de salida de la COLA (FIFO): " + sc);
        System.out.println("Explicación: la pila retira primero el último que entró (cima); la cola retira primero el que lleva más tiempo (frente).");
    }
}
