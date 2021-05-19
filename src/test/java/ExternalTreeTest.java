import org.junit.Test;
import ru.mipt.ExternalTree;
import ru.mipt.Node;
import static org.junit.jupiter.api.Assertions.*;

public class ExternalTreeTest {

    @Test
    public void mustFindElementsWhenTheyArePresent() {
        Node node = getTreeForSequentialExecution();
        ExternalTree tree = new ExternalTree(node);
        int[] keys = {4, 10, 14};
        for(int key: keys) {
            assertTrue(tree.contains(key));
        }
    }

    private Node getTreeForSequentialExecution() {
        Node n1 = new Node(8);
        Node n2 = new Node(4);
        Node n3 = new Node(12);

        Node n6 = new Node(10);
        Node n7 = new Node(14);

        n1.setLeft(n2);
        n1.setRight(n3);

        n3.setLeft(n6);
        n3.setRight(n7);

        return n1;
    }

    private Node getTreeForConcurrentExecution() {
        Node n0 = new Node(0);
        Node n1 = new Node(-2147483648);
        Node n2 = new Node(2147483647);
        Node n3 = new Node(2147483647);
        Node n4 = new Node(8);
        Node n5 = new Node(6);
        Node n6 = new Node(10);
        Node n7 = new Node(8);
        Node n8 = new Node(12);
        Node n9 = new Node(10);
        Node n10 = new Node(12);
        Node n11 = new Node(6);
        Node n12 = new Node(4);
        Node n13 = new Node(2);
        Node n14 = new Node(4);

        n0.setRight(n2);
        n0.setLeft(n1);

        n2.setRight(n3);
        n2.setLeft(n4);

        n4.setRight(n6);
        n4.setLeft(n5);

        n6.setRight(n8);
        n6.setLeft(n7);

        n8.setRight(n10);
        n8.setLeft(n9);

        n5.setRight(n11);
        n5.setLeft(n12);

        n12.setRight(n14);
        n12.setLeft(n13);

        return n0;
    }

    @Test
    public void mustRemoveRightChildIfGrandparentIsPresent() {
        Node root = getTreeForSequentialExecution();
        ExternalTree tree = new ExternalTree(root);
        int key = 14;
        tree.remove(key);
        assertEquals(root.getRight().getKey(), 10);
    }

    @Test
    public void mustRemoveLeftChildIfGrandparentIsPresent() {
        Node root = getTreeForSequentialExecution();
        ExternalTree tree = new ExternalTree(root);
        int key = 10;
        tree.remove(key);
        assertEquals(root.getRight().getKey(), 14);
    }

    @Test
    public void mustRemoveElementsConcurrently() throws InterruptedException {
        Node node = getTreeForConcurrentExecution();
        ExternalTree tree = new ExternalTree(node);

        Thread t1 = new Thread(() -> {
            tree.remove(12);
        });

        Thread t2 = new Thread(() -> {
            tree.remove(8);
        });

        Thread t3 = new Thread(() -> {
            tree.remove(4);
        });


        Thread t4 = new Thread(() -> {
            tree.remove(2);
        });

        Thread t5 = new Thread(() -> {
            tree.remove(6);
        });

        Thread t6 = new Thread(() -> {
            tree.remove(10);
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();


        assertEquals(tree.getRoot().getKey(), 0);
        assertEquals(tree.getRoot().getRight().getKey(), 2147483647);
        assertEquals(tree.getRoot().getLeft().getKey(), -2147483648);

        assertNull(tree.getRoot().getLeft().getLeft());
        assertNull(tree.getRoot().getLeft().getRight());

        assertNull(tree.getRoot().getRight().getLeft());
        assertNull(tree.getRoot().getRight().getRight());
    }
}
