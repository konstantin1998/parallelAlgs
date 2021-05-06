package entities;

import lombok.Data;

@Data
public class Vertex {
    private String id;
    private int distance;
    private Colour colour;
    private Vertex parent;
}
