package bfs.sequential;

import entities.Colour;
import entities.Graph;
import entities.Vertex;

import java.util.ArrayDeque;
import java.util.Deque;

public class SequentialBfs {
    public void run(Graph graph){

        for(Vertex v : graph.getVertices()) {
            v.setColour(Colour.WHITE);
            v.setDistance(-1);
            v.setParent(null);
        }
        Vertex source = graph.getSource();
        source.setDistance(0);
        source.setColour(Colour.GRAY);

        Deque<Vertex> queue = new ArrayDeque<>();
        queue.addLast(source);
        while(queue.size() != 0) {
            Vertex vertex = queue.pollFirst();
            for(Vertex v: graph.getAdjacentVertices(vertex)) {
                if(v.getColour().equals(Colour.WHITE)){
                    v.setColour(Colour.GRAY);
                    v.setDistance(vertex.getDistance() + 1);
                    v.setParent(vertex);
                    queue.addLast(v);
                }
            }
            vertex.setColour(Colour.BLACK);
        }
    }
}
