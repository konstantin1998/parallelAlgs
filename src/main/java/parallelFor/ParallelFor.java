package parallelFor;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelFor {
    private Collection<Runnable> tasks;
    private final int nThreads;

    public ParallelFor(int n) {
        nThreads = n;
    }

    public void setTasks(Collection<Runnable> tasks) {
        this.tasks = tasks;
    }

    public void executeTasks() {
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        for(Runnable task : tasks) {
            executor.submit(task);
        }
        executor.shutdown();
    }
}
