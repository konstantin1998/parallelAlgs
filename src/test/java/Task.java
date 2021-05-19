import ru.mipt.ExternalTree;
import ru.mipt.Node;

public class Task implements Runnable{
    private final ExternalTree tree;
    private final int[] keys;

    public Task(ExternalTree tree, int[] keys) {
        this.tree = tree;
        this.keys = keys;
    }

    @Override
    public void run() {
        for(int key: keys) {
            tree.insert(new Node(key));
        }
    }
}
