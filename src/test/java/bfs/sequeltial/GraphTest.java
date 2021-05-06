package bfs.sequeltial;

import entities.Graph;
import entities.Vertex;
import org.junit.Test;
import bfs.sequential.SequentialBfs;
import entities.Graph;
import entities.Vertex;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class GraphTest {
    @Test
    public void returnAdjacentVerticesCorrectlyWhenRequested(){
        int numVertices = 4;
        List<Vertex> vertices = new ArrayList<>();
        for(int i = 0; i < numVertices; i++) {
            Vertex v = new Vertex();
            v.setId(String.valueOf(i));
            vertices.add(v);
        }

        Map<Vertex, Collection<Vertex>> adjacencyList = new HashMap<>();

        List<Vertex> reachableVertices = new ArrayList<>();
        reachableVertices.add(vertices.get(1));
        reachableVertices.add(vertices.get(2));
        adjacencyList.put(vertices.get(0), reachableVertices);

        List<Vertex> anotherVertices = new ArrayList<>();
        anotherVertices.add(vertices.get(3));
        adjacencyList.put(vertices.get(2), anotherVertices);
        Graph graph = new Graph(adjacencyList, vertices.get(0));

        assertEquals(graph.getAdjacentVertices(graph.getSource()), reachableVertices);
    }
}
