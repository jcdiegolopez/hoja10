package uvg.edu.gt;
import java.util.Objects;

public class nodo {
    private String nombre;

    public nodo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        nodo otro = (nodo) obj;
        return nombre.equals(otro.nombre);
    }


    public String toString() {
        return nombre;
    }
}