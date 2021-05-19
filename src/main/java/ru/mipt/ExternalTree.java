package ru.mipt;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

        while (!((current == null) || (current.getKey() == key && current.isLeaf()))) {
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

    public void put(Node node) {
        Window w = search(node.getKey());
        Node grandParent = w.getGrandParent();
        Node parent = w.getParent();
        Node current = w.getCurrent();
        if(grandParent != null) {
            synchronized (grandParent) {
                doPut(node, grandParent);
            }
            return;
        }

        if(parent != null) {
            synchronized (parent) {
                doPut(node, parent);
            }
            return;
        }

        if(current != null) {
            synchronized (current) {
                doPut(node, current);
            }
            return;
        }

    }

    private void doPut(Node node, Node base) {
        Lock lock = new ReentrantLock();

    }

    public void remove(int key) {

        Window w = search(key);

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

//    private boolean removeNodeFromLockedGrandparent(Window w, int key) {
//        Node grandParent = w.getGrandParent();
//        Node parent = w.getParent();

//    }
//
//    private boolean removeNodeFromLockedParent(Window w, int key) {
//        Node parent = w.getParent();
//        Node current = w.getCurrent();
//        if(isStillSon(parent, current)) {
//            current.getLock().lock();
//            try {
//                if (current.isLeaf() && current.getKey() == key) {
//                    doRemove(w);
//                } else {
//                    return true;
//                }
//            } finally {
//                current.getLock().unlock();
//            }
//
//        } else {
//            return true;
//        }
//        return false;
//    }


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
        return w.getCurrent() != null;
    }

    public Node getRoot() {
        return root;
    }
}
