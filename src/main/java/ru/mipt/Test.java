package ru.mipt;

public class Test {
    private Object o = new Object();
    private int counter = 0;

    public void inc() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public void process() {
        synchronized (o) {
            System.out.println(o.hashCode());
        }
    }
}
