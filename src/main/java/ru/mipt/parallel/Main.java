package ru.mipt.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static List<Integer> initialize(int size) {
        int seed = 1;
        int bound = 10_000_000;
        Random r = new Random(seed);

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < size; i++){
            list.add(r.nextInt(bound));
        }
        return list;
    }

    public static void main(String args[])
    {
        int n = 10_000_000;
        List<Integer> arr = initialize(n);

        // Forkjoin ThreadPool to keep
        // thread creation as per resources
        ForkJoinPool pool
                = new ForkJoinPool(4);

        // Start the first thread in fork
        // join pool for range 0, n-1
        long time = System.currentTimeMillis();
        pool.invoke(
                new QuickSortMultiThreading(
                        0, n - 1, arr));

        System.out.println("time:" + (System.currentTimeMillis() - time));
    }
}
