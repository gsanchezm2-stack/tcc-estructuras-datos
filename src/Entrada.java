import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Entrada {
    public static final int LIMITE = 100;              
    private static final Scanner SC = new Scanner(System.in, StandardCharsets.UTF_8);
    private static final boolean ECO = Boolean.getBoolean("eco"); 

    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        try {
            String t = SC.nextLine().trim();
            if (ECO) System.out.println(t);
            return t;
        } catch (NoSuchElementException e) {
            System.out.println("\n[Fin de la entrada. Cerrando programa.]");
            System.exit(0);
            return "";
        }
    }

    public static int leerEntero(String mensaje) {
        while (true) {
            String t = leerTexto(mensaje);
            try {
                return Integer.parseInt(t);
            } catch (NumberFormatException e) {
                System.out.println("  [Error] \"" + t + "\" no es un entero válido. Intente de nuevo.");
            }
        }
    }

    public static int leerEnRango(String mensaje, int min, int max) {
        while (true) {
            int v = leerEntero(mensaje);
            if (v >= min && v <= max) return v;
            System.out.println("  [Error] El valor debe estar entre " + min + " y " + max + ".");
        }
    }

    public static int leerCantidad(String mensaje, int max) {
        while (true) {
            int v = leerEntero(mensaje);
            if (v <= 0) System.out.println("  [Error] La cantidad debe ser un entero positivo (mayor que 0).");
            else if (v > max) System.out.println("  [Error] La cantidad supera el límite permitido (" + max + ").");
            else return v;
        }
    }

    public static void pausa() {
        leerTexto("\nPresione ENTER para continuar...");
    }
}
