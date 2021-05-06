package bfs.sequeltial;

import bfs.sequential.SequentialBfs;
import entities.Graph;
import entities.Vertex;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;

public class SequentialBfsTest {
    @Test
    public void makeCorrectDistanceStampsWhenGraphIsTree() {
        //given
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

        //when
        SequentialBfs bfs = new SequentialBfs();
        bfs.run(graph);
        //then
        List<Integer> expectedDistances = new ArrayList<>();
        expectedDistances.add(0);
        expectedDistances.add(1);
        expectedDistances.add(1);
        expectedDistances.add(2);

        List<Integer> actualDistances = vertices.stream().map(Vertex::getDistance).collect(Collectors.toList());

        assertEquals(expectedDistances, actualDistances);
    }
}
