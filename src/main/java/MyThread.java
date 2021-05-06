public class MyThread extends Thread{
    private Runnable task;

    MyThread() {
        super();
    }

    public void setTask(Runnable task) {
        this.task = task;
    }

    @Override
    public void run() {
        task.run();
    }
}
