package ru.mipt;

import lombok.Data;
import lombok.Setter;

import java.util.List;

@Setter
public abstract class ForkJoin {
    protected int left;
    protected int right;
    protected int block = 100_000;
    protected List<Integer> list;

    abstract protected void runSequentialAlgorithm();
    abstract protected void fork();

    public void run() {
        if(right - left < block){
            runSequentialAlgorithm();
            return;
        }
        fork();
    }
}
