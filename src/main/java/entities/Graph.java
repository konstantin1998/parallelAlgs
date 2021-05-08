package entities;

import java.util.*;

public class Graph {
    private final Map<Vertex, Collection<Vertex>> adjacencyList;
    private final Vertex source;

    public Graph(Map<Vertex, Collection<Vertex>> adjacencyList, Vertex source) {
        this.adjacencyList = adjacencyList;
        this.source = source;
    }

    public Collection<Vertex> getAdjacentVertices(Vertex v) {
        Collection<Vertex> vertices = adjacencyList.get(v);

        if(vertices == null) {
            return Collections.emptyList();
        }
        return vertices;
    }

    public Collection<Vertex> getVertices() {
        Set<Vertex> vertices = new HashSet<>();
        for(Map.Entry<Vertex, Collection<Vertex>> entry: adjacencyList.entrySet()){
            vertices.add(entry.getKey());
            vertices.addAll(entry.getValue());
        }
        return vertices;
    }

    public Vertex getSource() {
        return source;
    }
}
