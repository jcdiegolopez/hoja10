package uvg.edu.gt;
import java.util.ArrayList;

public class grafo {
    private ArrayList<nodo> nodos;
    private ArrayList<borde> bordes;

    public grafo() {
        this.bordes = new ArrayList<borde>();
        this.nodos = new ArrayList<nodo>();


    }

    public void addBorde(nodo origen, nodo destino, int peso) {
        
        borde borde = new borde(origen, destino, peso);
        this.bordes.add(borde);
        if(!this.nodos.contains(origen)) {
            this.nodos.add(origen);
        }
        if(!this.nodos.contains(destino)) {
            this.nodos.add(destino);
        }
    }

    public ArrayList<borde> getBordes() {
        return bordes;
    }

    public ArrayList<nodo> getNodos() {
        return nodos;
    }

    public String toString() {
        String result = "";
        for (borde borde : bordes) {
            result += borde.getOrigen().getNombre() + " -> " + borde.getDestino().getNombre() + " " + borde.getPeso() + "km\n";
        }
        return result;
    }

    public void loadGraph(ArrayList<String[]> ubicaciones) {
        for (String[] ubicacion : ubicaciones) {
            nodo origen = new nodo(formatInput(ubicacion[0]));
            nodo destino = new nodo(formatInput(ubicacion[1]));
            int peso = Integer.parseInt(formatInput(ubicacion[2]));
            addBorde(origen, destino, peso);
        }
    }

    public String formatInput(String input) {
        return input.trim().toLowerCase();
    }

    public int getNodesSize() {
        return nodos.size();
    }

    public Integer[][] FloydWarshall() {
        int n = getNodesSize();
        Integer[][] dist = new Integer[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < n; i++) {
            dist[i][i] = 0;
        }
        for (borde borde : bordes) {
            int origen = nodos.indexOf(borde.getOrigen());
            int destino = nodos.indexOf(borde.getDestino());
            dist[origen][destino] = borde.getPeso();
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        return dist;
    }

    public int getNodoIndex(String nombre) {
        for (int i = 0; i < nodos.size(); i++) {
            if (nodos.get(i).getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

     
    
}
