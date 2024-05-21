package uvg.edu.gt;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class GrafoTest {
    private grafo grafo;

    @Before
    public void setUp() {
        grafo = new grafo();
    }

    @Test
    public void testAddBorde() {
        nodo origen = new nodo("A");
        nodo destino = new nodo("B");
        grafo.addBorde(origen, destino, 10);
        assertEquals(1, grafo.getBordes().size());
    }

    @Test
    public void testRemoveEdge() {
        nodo origen = new nodo("A");
        nodo destino = new nodo("B");
        grafo.addBorde(origen, destino, 10);
        assertEquals(1, grafo.getBordes().size());
        grafo.removeEdge(origen, destino);
        assertEquals(0, grafo.getBordes().size());
    }

    @Test
    public void testFloydWarshall() {
        // Definir grafo de prueba
        nodo nodoA = new nodo("A");
        nodo nodoB = new nodo("B");
        nodo nodoC = new nodo("C");
        grafo.addBorde(nodoA, nodoB, 10);
        grafo.addBorde(nodoA, nodoC, 15);
        grafo.addBorde(nodoB, nodoC, 5);

        // Obtener matriz de distancias
        Integer[][] distancias = grafo.FloydWarshall();

        // Verificar que las distancias son correctas
        assertEquals(0, (int) distancias[0][0]);
        assertEquals(10, (int) distancias[0][1]);
        assertEquals(15, (int) distancias[0][2]);
        assertEquals(Integer.MAX_VALUE, (int) distancias[1][0]);
        assertEquals(0, (int) distancias[1][1]);
        assertEquals(5, (int) distancias[1][2]);
        assertEquals(Integer.MAX_VALUE, (int) distancias[2][0]);
        assertEquals(Integer.MAX_VALUE, (int) distancias[2][1]);
        assertEquals(0, (int) distancias[2][2]);
    }

    @Test
    public void testGetNodoIndex() {
        nodo nodoA = new nodo("A");
        nodo nodoB = new nodo("B");
        nodo nodoC = new nodo("C");
        grafo.addBorde(nodoA, nodoB, 10);
        grafo.addBorde(nodoA, nodoC, 15);

        assertEquals(0, grafo.getNodoIndex("A"));
        assertEquals(1, grafo.getNodoIndex("B"));
        assertEquals(2, grafo.getNodoIndex("C"));
        assertEquals(-1, grafo.getNodoIndex("D")); // Nodo no existente
    }
}
