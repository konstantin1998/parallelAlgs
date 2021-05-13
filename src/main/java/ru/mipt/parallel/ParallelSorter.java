package ru.mipt.parallel;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ParallelSorter {
    private int nThreads = 1;

    public void sort(List<Integer> list) {
        ForkJoinPool pool = new ForkJoinPool(nThreads);
        pool.invoke(new QuickSortMultiThreading(0, list.size() - 1, list));
    }
}
