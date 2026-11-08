
public class Main {
    public static void main(String[] args) throws Exception {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8")); 
        System.out.println("=========================================");
        System.out.println("  TCC - ESTRUCTURAS DE DATOS (Java)");
        System.out.println("=========================================");
        int op;
        do {
            System.out.println("\n----------- MENÚ PRINCIPAL -----------");
            System.out.println("1. Arreglo unidimensional");
            System.out.println("2. Matriz");
            System.out.println("3. Lista");
            System.out.println("4. Pila");
            System.out.println("5. Cola");
            System.out.println("6. Árbol (binario de búsqueda)");
            System.out.println("7. Grafo");
            System.out.println("8. Comparar pila y cola (misma entrada)");
            System.out.println("0. Salir");
            op = Entrada.leerEnRango("Seleccione una opción: ", 0, 8);
            switch (op) {
                case 1 -> Arreglo.menu();
                case 2 -> Matriz.menu();
                case 3 -> Lista.menu();
                case 4 -> Pila.menu();
                case 5 -> Cola.menu();
                case 6 -> Arbol.menu();
                case 7 -> Grafo.menu();
                case 8 -> Cola.comparar();
                default -> System.out.println("\nHasta pronto.");
            }
        } while (op != 0);
    }
}
