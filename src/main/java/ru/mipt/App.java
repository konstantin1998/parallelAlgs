package ru.mipt;

import java.util.Random;

public class App {
    private static int keyLimit = 100_000;

    public static void main(String[] args) throws InterruptedException {
        ExternalTree tree = new ExternalTree();
        prepopulate(tree);

        Thread t1 = new Thread(new Executor(tree));
        Thread t2 = new Thread(new Executor(tree));
        

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    private static void prepopulate(ExternalTree tree) {
        Random r = new Random();
        double threshold = 0.5;
        for(int i = 1; i <= keyLimit; i++) {
            double p = r.nextDouble();
            if(p > threshold) {
                tree.insert(new Node(i));
            }
        }
    }
}
