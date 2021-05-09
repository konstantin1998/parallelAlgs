package ru.mipt;

import lombok.Data;
import lombok.Setter;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Setter
public class QuickSort extends ForkJoin{
    private List<Integer> list;
    private ListSorter sorter = new ListSorter();

    public QuickSort(List<Integer> list) {
        super();
        this.list = list;
        this.left = 0;
        this.right = list.size() - 1;
    }

    public QuickSort() {
        super();
    }

    @Override
    protected void runSequentialAlgorithm() {
        sorter.quickSort(list, left, right);
    }

    @Override
    protected void fork() {
        int middle = left + (right - left) / 2;
        int pivotElem = list.get(middle);

        List<Integer> lessThanPivot = filter((Integer item) -> item < pivotElem);
        List<Integer> moreOrEqualThanPivot = filter((item) -> item >= pivotElem);

        insertIntoList(lessThanPivot, left);
        insertIntoList(moreOrEqualThanPivot, right - moreOrEqualThanPivot.size() + 1);

        Thread t1 = new Thread(() -> {

            QuickSort qs = new QuickSort();
            qs.setList(list);
            qs.setLeft(left);
            qs.setRight(left + lessThanPivot.size() - 1);
            qs.run();
        });
        Thread t2 = new Thread(() -> {

            QuickSort qs = new QuickSort();
            qs.setList(list);
            qs.setLeft(left + lessThanPivot.size());
            qs.setRight(right);
            qs.run();
        });
        t1.start();
        t2.start();

//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private List<Integer> filter(Predicate<Integer> predicate) {
        return list.subList(left, right +1).parallelStream().filter(predicate).collect(Collectors.toList());
    }

    private void insertIntoList(List<Integer> items, int startPosition) {
        ParallelInserter inserter = new ParallelInserter(items, list, startPosition);
        inserter.run();
    }
}
