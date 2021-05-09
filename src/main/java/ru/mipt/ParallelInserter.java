package ru.mipt;

import java.util.List;

public class ParallelInserter extends ForkJoin{
    private final List<Integer> from;
    private final int shift;

    public ParallelInserter(List<Integer> from, List<Integer> to, int shift) {
        super();
        this.from = from;
        this.list = to;
        this.left = 0;
        this.right = from.size() - 1;
        this.shift = shift;
        this.block = 1_000_000;
    }

    @Override
    protected void runSequentialAlgorithm() {
        for(int i = this.left; i <= this.right; i++){
            this.list.set(shift + i, from.get(i));
        }
    }

    @Override
    protected void fork() {
        int middle = (right - left) / 2;

        ParallelInserter parallelInserter1 = new ParallelInserter(from, list, shift);
        parallelInserter1.setRight(middle - 1);
        parallelInserter1.setLeft(left);

        ParallelInserter parallelInserter2 = new ParallelInserter(from, list, shift);
        parallelInserter2.setRight(right);
        parallelInserter2.setLeft(middle);

        Thread t1 = new Thread(parallelInserter1::run);
        Thread t2 = new Thread(parallelInserter2::run);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
