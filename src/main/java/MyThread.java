import java.util.ArrayList;
import java.util.List;

public class MyThread extends Thread{
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        int[] arr = new int[1_000_000];
        for(int i = 0; i < 1_000_000; i = i + 100_000) {
            arr[i] = 0;
        }
        System.out.println("total time: " + (System.currentTimeMillis() - startTime));
    }

    private static void runThreads(){
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1");
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2");
        });

        t1.start();
        t2.start();
    }
}
