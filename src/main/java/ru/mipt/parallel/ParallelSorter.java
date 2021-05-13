package ru.mipt.parallel;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ParallelSorter {
    private final int nThreads = 1;

    public void sort(List<Integer> list) {
        ForkJoinPool pool = new ForkJoinPool(nThreads);
        pool.invoke(new SortTask(0, list.size() - 1, list));
    }
}
