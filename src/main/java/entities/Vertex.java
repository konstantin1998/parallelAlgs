package entities;

import lombok.Data;

@Data
public class Vertex {
    private String id;
    private int distance;
    private Colour colour;
    private Vertex parent;

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Vertex)) {
            return false;
        }
        Vertex v = (Vertex) o;
        return id.equals(v.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
