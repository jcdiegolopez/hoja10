package uvg.edu.gt;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class App 
{
    public static void main(String[] args) 
    {
        grafo guategrafo = new grafo();
        ArrayList<String[]> ubicaciones = leerArchivo("guategrafo.txt");
        if (ubicaciones == null) {
            System.out.println("Error al leer el archivo");
            return;
        }
        guategrafo.loadGraph(ubicaciones);
        Integer[][] distancias = guategrafo.FloydWarshall();
        ejecutarMenu(guategrafo, distancias);
    }

    public static ArrayList<String[]> leerArchivo(String archivo) {
        try {
            ArrayList<String[]> archivoLista = new ArrayList<>();
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                archivoLista.add(linea.split(","));
            }
            br.close();
            fr.close();
            return archivoLista;
        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
            return null;
        }
    }

    public static void imprimirMatrizDistancias(Integer[][] dist, ArrayList<nodo> nodos) {
        int n = dist.length;
        System.out.println("Matriz de distancias:");
        
        System.out.print("             ");
        for (nodo nodo : nodos) {
            System.out.printf("%-14s", nodo.getNombre());
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.printf("%-14s", nodos.get(i).getNombre());
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.printf("%-14s", "INF");
                } else {
                    System.out.printf("%-14d", dist[i][j]);
                }
            }
            System.out.println();
        }
    }

    public static void ejecutarMenu(grafo guategrafo, Integer[][] distancias) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de ingresar el número

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del nodo de origen: ");
                    String origen = scanner.nextLine().trim().toLowerCase();
                    System.out.print("Ingrese el nombre del nodo de destino: ");
                    String destino = scanner.nextLine().trim().toLowerCase();
                    int distancia = distancias[guategrafo.getNodoIndex(origen)][guategrafo.getNodoIndex(destino)];
                    if (distancia == Integer.MAX_VALUE) {
                        System.out.println("No hay camino entre los nodos ingresados.");
                    } else {
                        System.out.printf("La distancia más corta entre %s y %s es %d\n", origen, destino, distancia);
                    }
                    break;
                case 2:
                    imprimirMatrizDistancias(distancias, guategrafo.getNodos());
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del nodo de origen: ");
                    String origenNuevo = scanner.nextLine().trim().toLowerCase();
                    System.out.print("Ingrese el nombre del nodo de destino: ");
                    String destinoNuevo = scanner.nextLine().trim().toLowerCase();
                    System.out.print("Ingrese el peso del arco: ");
                    int peso = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    guategrafo.addBorde(new nodo(origenNuevo), new nodo(destinoNuevo), peso);
                    distancias = guategrafo.FloydWarshall();
                    System.out.println("Arco agregado correctamente.");
                    break;
                case 4:
                    System.out.print("Ingrese el nombre del nodo de origen: ");
                    String origenEliminar = scanner.nextLine().trim().toLowerCase();
                    System.out.print("Ingrese el nombre del nodo de destino: ");
                    String destinoEliminar = scanner.nextLine().trim().toLowerCase();
                    guategrafo.removeEdge(new nodo(origenEliminar), new nodo(destinoEliminar));
                    distancias = guategrafo.FloydWarshall();
                    System.out.println("Arco eliminado correctamente.");
                    break;
                case 5:
                    ArrayList<nodo> centro = guategrafo.findCenter(distancias);
                    System.out.println("El centro del grafo es/son:");
                    for (nodo n : centro) {
                        System.out.println(n.getNombre());
                    }
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    opcion = 0;
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }

            System.out.println();
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("Menú de opciones:");
        System.out.println("1. Buscar distancia más corta entre dos nodos");
        System.out.println("2. Imprimir matriz de distancias");
        System.out.println("3. Agregar un arco");
        System.out.println("4. Eliminar un arco");
        System.out.println("5. Mostrar el centro del grafo");
        System.out.println("0. Salir");
        System.out.print("Ingrese una opción: ");
    }
}
