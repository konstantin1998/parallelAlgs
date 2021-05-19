package ru.mipt;

import lombok.Data;

import java.util.concurrent.locks.ReentrantLock;

@Data
public class Node {
    private Node right;
    private Node left;
    private int key;
    private Object value;
    private ReentrantLock lock = new ReentrantLock();

    public Node(int key) {
        this.key = key;
    }

    public boolean isLeaf() {
        return right == null && left == null;
    }
}
