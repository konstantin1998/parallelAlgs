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
        Node n4 = new Node(12);
        Node n5 = new Node(12);
        Node n6 = new Node(8);
        Node n7 = new Node(8);
        Node n8 = new Node(4);
        Node n9 = new Node(4);
        Node n10 = new Node(2);

        n0.setRight(n2);
        n0.setLeft(n1);

        n2.setRight(n3);
        n2.setLeft(n4);

        n4.setRight(n5);
        n4.setLeft(n6);

        n6.setRight(n7);
        n6.setLeft(n8);

        n8.setRight(n9);
        n8.setLeft(n10);

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
        tree.remove(12);
        tree.remove(8);
        tree.remove(4);
        tree.remove(2);
//        Thread t1 = new Thread(() -> {
//            tree.remove(12);
//        });
//        t1.join();
//
//        Thread t2 = new Thread(() -> {
//            tree.remove(8);
//        });
//        t2.join();
//
//        Thread t3 = new Thread(() -> {
//            tree.remove(4);
//        });
//        t3.join();
//
//        Thread t4 = new Thread(() -> {
//            tree.remove(2);
//        });
//        t4.join();

//        try {
//            t1.join();
//            t2.join();
//            t3.join();
//            t4.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        assertEquals(tree.getRoot().getKey(), 0);
        assertEquals(tree.getRoot().getRight().getKey(), 2147483647);
        assertEquals(tree.getRoot().getLeft().getKey(), -2147483648);

        assertNull(tree.getRoot().getLeft().getLeft());
        assertNull(tree.getRoot().getLeft().getRight());

        assertNull(tree.getRoot().getRight().getLeft());
        assertNull(tree.getRoot().getRight().getRight());
    }
}
