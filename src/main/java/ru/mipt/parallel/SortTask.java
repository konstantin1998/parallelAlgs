package ru.mipt.parallel;

import ru.mipt.sequential.ListSorter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.RecursiveTask;

public class SortTask extends RecursiveTask<Integer> {

    private final int start;
    private final int end;
    private final List<Integer> list;
    private final int block = 100_000;
    private final ListSorter sorter = new ListSorter();

    public SortTask(int start, int end, List<Integer> arr) {
        this.list = arr;
        this.start = start;
        this.end = end;
    }

    private int partition(int start, int end) {
        int i = start, j = end;
        int pivot = new Random().nextInt(j - i) + i;

        swap(pivot, j);
        j--;

        while (i <= j) {
            if (list.get(i) <= list.get(end)) {
                i++;
                continue;
            }

            if (list.get(j) >= list.get(end)) {
                j--;
                continue;
            }

            swap(i, j);
            j--;
            i++;
        }

        swap(j + 1, end);
        return j + 1;
    }

    private void swap(int i, int j) {
        int t = list.get(i);
        list.set(i, list.get(j));
        list.set(j, t);
    }

    @Override
    protected Integer compute() {
        if (end - start < block) {
            sorter.quickSort(list, start, end);
            return null;
        }

        int p = partition(start, end);
        SortTask left = new SortTask(start, p - 1, list);
        SortTask right = new SortTask(p + 1, end, list);

        left.fork();
        right.compute();
        left.join();

        return null;
    }

}

