import java.util.ArrayList;
import java.util.List;

public class MyThread extends Thread{
    public static void main(String[] args) {
        List<Integer> items = new ArrayList<>();
        int n = 10;
        for(int i = 0; i < n; i++) {
            items.add(i);
        }
        System.out.println(items.subList(0, 100));
    }
}
