package uvg.edu.gt;

/**
 * borde
 */
public class borde {
    private nodo origen;
    private nodo destino;
    private int peso;

    public borde(nodo origen, nodo destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso  = peso;
    }

    public int getPeso() {
        return peso;
    }

    public nodo getOrigen() {
        return origen;
    }

    public nodo getDestino() {
        return destino;
    }

    public void setOrigen(nodo origen) {
        this.origen = origen;
    }

    public void setDestino(nodo destino) {
        this.destino = destino;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String toString() {
        return origen.getNombre() + " -> " + destino.getNombre() + " " + peso + "km";
    }

    
}