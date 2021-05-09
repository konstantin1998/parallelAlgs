import org.junit.Test;
import ru.mipt.ListSorter;
import ru.mipt.QuickSort;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTest {
//    @Test
//    public void test() {
//        int size = 10;
//        List<Integer> list1 = initialize(size);
//        List<Integer> list2 = initialize(size);
//
//        assertEquals(list1, list2);
//    }

    private List<Integer> initialize(int size) {
        int seed = 1;
        int bound = 10_000_000;
        Random r = new Random(seed);

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < size; i++){
            list.add(r.nextInt(bound));
        }
        return list;
    }

    @Test
    public void shouldWorkFasterThanSequentialAlgorithm(){
        int size = 10_000_000;
        List<Integer> listToSort = initialize(size);
        ListSorter ls = new ListSorter();
        ls.quickSort(listToSort, 0, listToSort.size() - 1);

        listToSort = initialize(size);
        QuickSort qs = new QuickSort();
        qs.setList(listToSort);
        qs.setRight(listToSort.size() - 1);
        qs.setLeft(0);
        qs.run();

        listToSort = initialize(size);
        long sequentialAlgStart = System.currentTimeMillis();
        ls.quickSort(listToSort, 0, listToSort.size() - 1);
        long sequentialTime = System.currentTimeMillis() - sequentialAlgStart;
        System.out.println("sequential time: " + sequentialTime);

        listToSort = initialize(size);
        qs.setList(listToSort);
        qs.setRight(listToSort.size() - 1);
        qs.setLeft(0);
        long paralAlgStart = System.currentTimeMillis();
        qs.run();
        long parallelTime = System.currentTimeMillis() - paralAlgStart;
        System.out.println("parallel time: " + parallelTime);

        assertTrue(parallelTime < sequentialTime / 2);
    }
}
