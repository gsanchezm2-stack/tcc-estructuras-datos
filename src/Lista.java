
public class Lista {
    private static class Nodo {
        int dato; Nodo sig;
        Nodo(int d) { dato = d; }
    }
    private Nodo cabeza;
    private int tam;

    public int tamano() { return tam; }

    public void insertarFinal(int v) {
        Nodo n = new Nodo(v);
        if (cabeza == null) cabeza = n;
        else { Nodo a = cabeza; while (a.sig != null) a = a.sig; a.sig = n; }
        tam++;
    }

    public void insertarEn(int pos, int v) {
        Nodo n = new Nodo(v);
        if (pos == 0) { n.sig = cabeza; cabeza = n; }
        else { Nodo a = cabeza; for (int i = 0; i < pos - 1; i++) a = a.sig; n.sig = a.sig; a.sig = n; }
        tam++;
    }

    public int buscar(int v) {
        int i = 0;
        for (Nodo a = cabeza; a != null; a = a.sig, i++) if (a.dato == v) return i;
        return -1;
    }

    public boolean eliminar(int v) {
        if (cabeza == null) return false;
        if (cabeza.dato == v) { cabeza = cabeza.sig; tam--; return true; }
        for (Nodo a = cabeza; a.sig != null; a = a.sig)
            if (a.sig.dato == v) { a.sig = a.sig.sig; tam--; return true; }
        return false;
    }

    public void mostrar() {
        if (cabeza == null) { System.out.println("CABEZA -> null   (lista vacía)"); return; }
        StringBuilder s = new StringBuilder("CABEZA -> ");
        for (Nodo a = cabeza; a != null; a = a.sig) s.append("[").append(a.dato).append("] -> ");
        System.out.println(s + "null");
        StringBuilder p = new StringBuilder("Posición:    ");
        int i = 0;
        for (Nodo a = cabeza; a != null; a = a.sig, i++) p.append(String.format("%-" + (String.valueOf(a.dato).length() + 6) + "d", i));
        System.out.println(p + "   (tamaño = " + tam + ")");
    }

    public void recorrer() {
        System.out.println("Recorrido desde la cabeza:");
        int i = 0;
        for (Nodo a = cabeza; a != null; a = a.sig, i++) System.out.println("  posición " + i + " -> " + a.dato);
        if (cabeza == null) System.out.println("  (lista vacía)");
    }

    public static void menu() {
        System.out.println("\n=== LISTA (enlazada simple) ===");
        System.out.println("Implementación: nodos enlazados; se accede recorriendo desde la cabeza. Permite repetidos.");
        int n = Entrada.leerCantidad("¿Cuántos datos va a ingresar? (1-" + Entrada.LIMITE + "): ", Entrada.LIMITE);
        Lista l = new Lista();
        for (int i = 0; i < n; i++) l.insertarFinal(Entrada.leerEntero("Ingrese el dato #" + (i + 1) + ": "));
        l.mostrar();
        int op;
        do {
            System.out.println("\n1. Insertar  2. Buscar  3. Eliminar  4. Recorrer  5. Mostrar  0. Volver al menú");
            op = Entrada.leerEnRango("Opción: ", 0, 5);
            switch (op) {
                case 1 -> {
                    if (l.tamano() >= Entrada.LIMITE) { System.out.println("  [Error] La lista alcanzó el límite de " + Entrada.LIMITE + "."); break; }
                    int v = Entrada.leerEntero("Valor a insertar: ");
                    int pos = Entrada.leerEnRango("Posición (0-" + l.tamano() + "): ", 0, l.tamano());
                    System.out.print("Antes   : "); l.mostrar();
                    l.insertarEn(pos, v);
                    System.out.print("Después : "); l.mostrar();
                }
                case 2 -> {
                    int v = Entrada.leerEntero("Valor a buscar: ");
                    int p = l.buscar(v);
                    System.out.println(p >= 0 ? "  Encontrado en la posición " + p : "  El dato " + v + " no existe en la lista.");
                }
                case 3 -> {
                    int v = Entrada.leerEntero("Valor a eliminar: ");
                    System.out.print("Antes   : "); l.mostrar();
                    if (l.eliminar(v)) { System.out.print("Después : "); l.mostrar(); }
                    else System.out.println("  El dato " + v + " no existe; no se eliminó nada.");
                }
                case 4 -> l.recorrer();
                case 5 -> l.mostrar();
                default -> { }
            }
        } while (op != 0);
    }
}
