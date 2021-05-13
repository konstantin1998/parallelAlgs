package ru.mipt.parallel;

// Java program for the above approach
import ru.mipt.sequential.ListSorter;

import java.util.List;
import java.util.Random;
import java.util.concurrent.RecursiveTask;

public class QuickSortMultiThreading extends RecursiveTask<Integer> {

    int start, end;
    List<Integer> list;
    int block = 100_000;
    ListSorter sorter = new ListSorter();

    /**
     * Finding random pivoted and partition
     * array on a pivot.
     * There are many different
     * partitioning algorithms.
     * @param start
     * @param end
     * @return
     */
    private int partion(int start, int end)
    {

        int i = start, j = end;

        // Decide random pivot
        int pivote = new Random()
                .nextInt(j - i)
                + i;

        // Swap the pivote with end
        // element of array;
        swap(pivote, j);
        j--;

        // Start partioning
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

        // Swap pivote to its
        // correct position
        swap(j + 1, end);
        return j + 1;
    }

    private void swap(int i, int j) {
        int t = list.get(i);
        list.set(i, list.get(j));
        list.set(j, t);
    }

    // Function to implement
    // QuickSort method
    public QuickSortMultiThreading(int start,
                                   int end,
                                   List<Integer> arr)
    {
        this.list = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute()
    {
        // Base case
        if (end - start < block) {
            sorter.quickSort(list, start, end);
            return null;
        }


        // Find partion
        int p = partion(start, end);

        // Divide array
        QuickSortMultiThreading left
                = new QuickSortMultiThreading(start,
                p - 1,
                list);

        QuickSortMultiThreading right
                = new QuickSortMultiThreading(p + 1,
                end,
                list);

        // Left subproblem as separate thread
        left.fork();
        right.compute();

        // Wait untill left thread complete
        left.join();

        // We don't want anything as return
        return null;
    }

}

