import java.util.*;
public class Grafo {
    private final Map<String, List<String>> ady = new LinkedHashMap<>();
    private int aristas;
    public static final int MAX_VERTICES = 20;

    public boolean existe(String v) { return ady.containsKey(v); }
    public int numVertices() { return ady.size(); }
    public int numAristas() { return aristas; }
    public boolean existeArista(String a, String b) { return ady.get(a).contains(b); }

    public boolean agregarVertice(String v) {
        if (v.isEmpty() || ady.containsKey(v)) return false;
        ady.put(v, new ArrayList<>());
        return true;
    }
    public void agregarArista(String a, String b) {
        ady.get(a).add(b); ady.get(b).add(a); aristas++;
    }
    public List<String> vecinos(String v) { return ady.get(v); }

    public List<String> bfs(String inicio) {
        List<String> orden = new ArrayList<>();
        Set<String> vis = new HashSet<>(); Deque<String> q = new ArrayDeque<>();
        q.add(inicio); vis.add(inicio);
        while (!q.isEmpty()) {
            String u = q.poll(); orden.add(u);
            for (String w : ady.get(u)) if (vis.add(w)) q.add(w);
        }
        return orden;
    }
    public List<String> dfs(String inicio) {
        List<String> orden = new ArrayList<>();
        dfs(inicio, new HashSet<>(), orden);
        return orden;
    }
    private void dfs(String u, Set<String> vis, List<String> orden) {
        vis.add(u); orden.add(u);
        for (String w : ady.get(u)) if (!vis.contains(w)) dfs(w, vis, orden);
    }

    public void mostrar() {
        System.out.println("Vértices: " + ady.size() + ", Aristas: " + aristas + " (no dirigido, sin lazos)");
        System.out.println("Lista de adyacencia:");
        for (var e : ady.entrySet()) System.out.println("  " + e.getKey() + " -> " + (e.getValue().isEmpty() ? "(sin vecinos)" : String.join(", ", e.getValue())));
        List<String> ids = new ArrayList<>(ady.keySet());
        System.out.println("Matriz de adyacencia:");
        StringBuilder h = new StringBuilder(String.format("%8s", ""));
        for (String id : ids) h.append(String.format("%6s", id));
        System.out.println(h);
        for (String f : ids) {
            StringBuilder s = new StringBuilder(String.format("%8s", f));
            for (String c : ids) s.append(String.format("%6d", ady.get(f).contains(c) ? 1 : 0));
            System.out.println(s);
        }
    }

    private static String leerVerticeExistente(Grafo g, String msg) {
        while (true) {
            String v = Entrada.leerTexto(msg);
            if (g.existe(v)) return v;
            System.out.println("  [Error] El vértice \"" + v + "\" no existe. Existentes: " + g.ady.keySet());
        }
    }

    private static boolean pedirArista(Grafo g) {
        String a = leerVerticeExistente(g, "  Vértice origen: ");
        String b = leerVerticeExistente(g, "  Vértice destino: ");
        if (a.equals(b)) System.out.println("  [Error] No se permiten lazos (un vértice conectado consigo mismo).");
        else if (g.existeArista(a, b)) System.out.println("  [Error] La arista " + a + " - " + b + " ya existe.");
        else { g.agregarArista(a, b); System.out.println("  Arista agregada: " + a + " - " + b); return true; }
        return false;
    }

    private static void recorrido(Grafo g, boolean anchura) {
        String ini = leerVerticeExistente(g, "Vértice inicial del recorrido: ");
        List<String> r = anchura ? g.bfs(ini) : g.dfs(ini);
        System.out.println("  Recorrido en " + (anchura ? "anchura (BFS)" : "profundidad (DFS)") + " desde " + ini + ": " + String.join(" -> ", r));
        List<String> no = new ArrayList<>(g.ady.keySet());
        no.removeAll(r);
        System.out.println(no.isEmpty() ? "  Todos los vértices son alcanzables." : "  Vértices NO alcanzables desde " + ini + ": " + no);
    }

    public static void menu() {
        System.out.println("\n=== GRAFO (no dirigido, sin lazos, sin aristas repetidas) ===");
        System.out.println("Estructura no lineal: vértices conectados por aristas. Los identificadores de vértice son únicos.");
        Grafo g = new Grafo();
        int n = Entrada.leerCantidad("Cantidad de vértices (1-" + MAX_VERTICES + "): ", MAX_VERTICES);
        for (int i = 0; i < n; ) {
            String id = Entrada.leerTexto("Identificador del vértice #" + (i + 1) + ": ");
            if (id.isEmpty()) System.out.println("  [Error] El identificador no puede estar vacío.");
            else if (!g.agregarVertice(id)) System.out.println("  [Error] El identificador \"" + id + "\" ya existe; debe ser único.");
            else i++;
        }
        int maxA = n * (n - 1) / 2;
        int m = Entrada.leerEnRango("Cantidad de aristas (0-" + maxA + "): ", 0, maxA);
        for (int i = 0; i < m; ) {
            System.out.println("Arista #" + (i + 1) + ":");
            if (pedirArista(g)) i++;
        }
        g.mostrar();
        int op;
        do {
            System.out.println("\n1. Agregar vértice  2. Agregar arista  3. Consultar vecinos  4. Recorrido en anchura  5. Recorrido en profundidad  6. Mostrar  0. Volver al menú");
            op = Entrada.leerEnRango("Opción: ", 0, 6);
            switch (op) {
                case 1 -> {
                    if (g.numVertices() >= MAX_VERTICES) { System.out.println("  [Error] Límite de " + MAX_VERTICES + " vértices."); break; }
                    String id = Entrada.leerTexto("Identificador del nuevo vértice: ");
                    System.out.println(g.agregarVertice(id) ? "  Vértice agregado." : "  [Error] Identificador vacío o repetido.");
                }
                case 2 -> pedirArista(g);
                case 3 -> {
                    String v = leerVerticeExistente(g, "Vértice a consultar: ");
                    System.out.println("  Vecinos de " + v + ": " + (g.vecinos(v).isEmpty() ? "(ninguno)" : g.vecinos(v)));
                }
                case 4 -> recorrido(g, true);
                case 5 -> recorrido(g, false);
                case 6 -> g.mostrar();
                default -> { }
            }
        } while (op != 0);
    }
}
