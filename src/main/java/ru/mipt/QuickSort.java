package ru.mipt;

import lombok.Data;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
public class QuickSort {
    private int left;
    private int right;
    private int block = 10;
    private List<Integer> list;
    private ListSorter sorter = new ListSorter();


    public void run() {
        if(right - left < block) {
            sorter.quickSort(list, left, right);
            return;
        }
        int middle = left + (right - left) / 2;
        int pivotElem = list.get(middle);

        List<Integer> lessThanPivot = filter((Integer item) -> item < pivotElem);
        List<Integer> moreOrEqualThanPivot = filter((item) -> item >= pivotElem);

        insertIntoList(lessThanPivot, left);
        insertIntoList(moreOrEqualThanPivot, right - moreOrEqualThanPivot.size() + 1);

        QuickSort qs1 = new QuickSort();
        qs1.setList(list);
        qs1.setLeft(left);
        qs1.setRight(left + lessThanPivot.size() - 1);
        qs1.run();

        QuickSort qs2 = new QuickSort();
        qs2.setList(list);
        qs2.setLeft(left + lessThanPivot.size());
        qs2.setRight(right);
        qs2.run();


//        Thread t1 = new Thread(() -> {
//            QuickSort qs = new QuickSort();
//            qs.setLeft(left);
//            qs.setRight(middle - 1);
//            qs.run();
//        });
//        Thread t2 = new Thread(() -> {
//            QuickSort qs = new QuickSort();
//            qs.setLeft(middle + 1);
//            qs.setRight(right);
//            qs.run();
//        });
//        t1.start();
//        t2.start();
//
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
        if(startPosition + items.size() - 1 >= list.size()) {
            throw new RuntimeException("index error: items.size = " + items.size() + ", start position = " + startPosition);
        }
        for(int i = 0; i < items.size(); i++) {
            list.set(startPosition + i, items.get(i));
        }
    }
}
