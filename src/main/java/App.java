import entities.Vertex;

public class App {
    public static void main(String[] args) {

        Vertex v1 = new Vertex();
        v1.setId("1");
        Vertex v2 = new Vertex();
        v2.setId("1");
        System.out.println(v1.equals(v2));
    }
}
