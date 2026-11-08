public class Arbol {
    private static class Nodo {
        int dato; Nodo izq, der;
        Nodo(int d) { dato = d; }
    }
    private Nodo raiz;
    private int tam;

    public int tamano() { return tam; }

    public boolean insertar(int v) {
        if (buscar(v)) return false;
        raiz = insertar(raiz, v);
        tam++;
        return true;
    }
    private Nodo insertar(Nodo n, int v) {
        if (n == null) return new Nodo(v);
        if (v < n.dato) n.izq = insertar(n.izq, v); else n.der = insertar(n.der, v);
        return n;
    }

    public boolean buscar(int v) {
        Nodo a = raiz;
        while (a != null) { if (v == a.dato) return true; a = v < a.dato ? a.izq : a.der; }
        return false;
    }

    public void inorden(StringBuilder s) { inorden(raiz, s); }
    private void inorden(Nodo n, StringBuilder s) { if (n == null) return; inorden(n.izq, s); s.append(n.dato).append(' '); inorden(n.der, s); }
    public void preorden(StringBuilder s) { preorden(raiz, s); }
    private void preorden(Nodo n, StringBuilder s) { if (n == null) return; s.append(n.dato).append(' '); preorden(n.izq, s); preorden(n.der, s); }
    public void postorden(StringBuilder s) { postorden(raiz, s); }
    private void postorden(Nodo n, StringBuilder s) { if (n == null) return; postorden(n.izq, s); postorden(n.der, s); s.append(n.dato).append(' '); }

    public void mostrar() {
        if (raiz == null) { System.out.println("  (árbol vacío)"); return; }
        System.out.println("Raíz: " + raiz.dato + "   (nodos: " + tam + ")");
        System.out.println("Jerarquía (der. arriba, izq. abajo; la sangría indica el nivel):");
        dibujar(raiz, 0);
        System.out.println("Relaciones padre -> hijos:");
        relaciones(raiz);
    }
    private void dibujar(Nodo n, int nivel) {
        if (n == null) return;
        dibujar(n.der, nivel + 1);
        System.out.println("  " + "      ".repeat(nivel) + (nivel == 0 ? "[RAÍZ] " : "") + n.dato);
        dibujar(n.izq, nivel + 1);
    }
    private void relaciones(Nodo n) {
        if (n == null) return;
        System.out.println("  " + n.dato + " -> izq: " + (n.izq == null ? "-" : n.izq.dato) + ", der: " + (n.der == null ? "-" : n.der.dato));
        relaciones(n.izq); relaciones(n.der);
    }

    public static void menu() {
        System.out.println("\n=== ÁRBOL BINARIO DE BÚSQUEDA ===");
        System.out.println("Estructura no lineal jerárquica. Menores a la izquierda, mayores a la derecha. No admite repetidos.");
        int n = Entrada.leerCantidad("¿Cuántos valores va a insertar? (1-" + Entrada.LIMITE + "): ", Entrada.LIMITE);
        Arbol a = new Arbol();
        for (int i = 0; i < n; ) {
            int v = Entrada.leerEntero("Valor #" + (i + 1) + " a insertar: ");
            if (a.insertar(v)) i++;
            else System.out.println("  [Error] El valor " + v + " ya existe; el árbol no admite repetidos. Ingrese otro.");
        }
        a.mostrar();
        int op;
        do {
            System.out.println("\n1. Insertar  2. Buscar  3. Inorden  4. Preorden  5. Postorden  6. Mostrar  0. Volver al menú");
            op = Entrada.leerEnRango("Opción: ", 0, 6);
            switch (op) {
                case 1 -> {
                    if (a.tamano() >= Entrada.LIMITE) { System.out.println("  [Error] Límite de " + Entrada.LIMITE + " nodos alcanzado."); break; }
                    int v = Entrada.leerEntero("Valor a insertar: ");
                    if (a.insertar(v)) { System.out.println("  Insertado."); a.mostrar(); }
                    else System.out.println("  [Error] El valor " + v + " ya existe (repetidos no permitidos).");
                }
                case 2 -> {
                    int v = Entrada.leerEntero("Valor a buscar: ");
                    System.out.println(a.buscar(v) ? "  El valor " + v + " SÍ existe en el árbol." : "  El valor " + v + " no existe en el árbol.");
                }
                case 3, 4, 5 -> {
                    StringBuilder s = new StringBuilder();
                    if (op == 3) { a.inorden(s); System.out.println("  Inorden   (izq, raíz, der): " + s + " (queda ordenado)"); }
                    else if (op == 4) { a.preorden(s); System.out.println("  Preorden  (raíz, izq, der): " + s); }
                    else { a.postorden(s); System.out.println("  Postorden (izq, der, raíz): " + s); }
                }
                case 6 -> a.mostrar();
                default -> { }
            }
        } while (op != 0);
    }
}
