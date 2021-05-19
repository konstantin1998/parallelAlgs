package ru.mipt;

public class ExternalTree {
    private final Node root;

    @Deprecated
    public ExternalTree(Node root) {
        this.root = root;
    }

    public ExternalTree() {
        int fictitiousRootKey = 0;
        Node fictitiousRoot = new Node(fictitiousRootKey);

        int fictitiousRightKey = 2147483647;
        Node fictitiousRightChild = new Node(fictitiousRightKey);

        int fictitiousLeftKey = -2147483648;
        Node fictitiousLeftChild = new Node(fictitiousLeftKey);

        fictitiousRoot.setRight(fictitiousRightChild);
        fictitiousRoot.setLeft(fictitiousLeftChild);
        root = fictitiousRoot;
    }

    private Window search(int key) {
        Node grandParent = null;
        Node parent = null;
        Node current = root;

        while (!((current == null) || current.getKey() == key || current.isLeaf())) {
            grandParent = parent;
            parent = current;

            if (current.getKey() <= key) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }
        }

        return new Window(grandParent, parent, current);
    }


    public void remove(int key) {
        Window w = search(key);

        if (!contains(key)) {
            return;
        }

        if (!removeWithLocks(w)) {
            remove(key);
        }
    }


    private boolean removeWithLocks(Window w) {
        Node grandParent = w.getGrandParent();
        Node parent = w.getParent();
        Node current = w.getCurrent();

        boolean result = true;

        grandParent.getLock().lock();
        parent.getLock().lock();
        current.getLock().lock();
        try {
            if(isStillSon(grandParent, parent)) {
                if(isStillSon(parent, current)) {
                    doRemove(w);
                } else {
                    result = false;
                }
            } else {
                result = false;
            }
        } finally {
            current.getLock().unlock();
            parent.getLock().unlock();
            grandParent.getLock().unlock();
        }
        return result;
    }


    private void doRemove(Window w) {
        Node grandParent = w.getGrandParent();
        Node parent = w.getParent();
        Node current = w.getCurrent();
        if(isRightSon(grandParent, parent)) {
            if(isRightSon(parent, current)) {
                grandParent.setRight(parent.getLeft());
            } else {
                grandParent.setRight(parent.getRight());
            }
        } else {
            if(isRightSon(parent, current)) {
                grandParent.setLeft(parent.getLeft());
            } else {
                grandParent.setLeft(parent.getRight());
            }
        }
        parent.setLeft(null);
        parent.setRight(null);
    }

    private boolean isRightSon(Node parent, Node son) {
        return parent.getRight() == son;
    }

    private boolean isStillSon(Node parent, Node son) {
        return (parent.getKey() <= son.getKey() && parent.getRight() == son)
                || (parent.getKey() > son.getKey() && parent.getLeft() == son);
    }

    public boolean contains(int key) {
        Window w = search(key);
        return w.getCurrent() != null && w.getCurrent().getKey() == key;
    }

    public Node getRoot() {
        return root;
    }

    private void doInsert(Window w, Node node) {
        Node parent = w.getParent();
        if (node.getKey() >= parent.getKey()) {
            insertRight(parent, node);
        } else {
            insertLeft(parent, node);
        }
    }

    private void insertRight(Node parent, Node node) {
        Node newNode;
        if (parent.getRight().getKey() > node.getKey()) {
            newNode = new Node(parent.getRight().getKey());
            newNode.setLeft(node);
            newNode.setRight(parent.getRight());
        } else {
            newNode = new Node(node.getKey());
            newNode.setLeft(parent.getRight());
            newNode.setRight(node);
        }
        parent.setRight(newNode);
    }

    private void insertLeft(Node parent, Node node) {
        Node newNode;
        if (parent.getLeft().getKey() > node.getKey()) {
            newNode = new Node(parent.getLeft().getKey());
            newNode.setRight(parent.getLeft());
            newNode.setLeft(node);
        } else {
            newNode = new Node(node.getKey());
            newNode.setRight(node);
            newNode.setLeft(parent.getLeft());
        }
        parent.setLeft(newNode);
    }

    public void insert(Node node) {
        int key = node.getKey();

        if (contains(key)) {
            return;
        }

        Window w = search(key);

        if (!insertWithLocks(w, node)) {
            insert(node);
        }
    }

    private boolean insertWithLocks(Window w, Node node) {
        Node parent = w.getParent();
        Node current = w.getCurrent();

        boolean result = true;

        parent.getLock().lock();
        current.getLock().lock();
        try {
            if(isStillSon(parent, current) && current.isLeaf()) {
                doInsert(w, node);
            } else {
                result = false;
            }
        } finally {
            current.getLock().unlock();
            parent.getLock().unlock();
        }
        return result;
    }
}
