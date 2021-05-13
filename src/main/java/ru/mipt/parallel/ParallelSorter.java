package ru.mipt.parallel;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ParallelSorter {

    public void sort(List<Integer> list) {
        int nThreads = 4;
        ForkJoinPool pool = new ForkJoinPool(nThreads);
        pool.invoke(new QuickSortMultiThreading(0, list.size() - 1, list));
    }
}
