import org.junit.Test;
import ru.mipt.ListSorter;
import ru.mipt.QuickSort;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {

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
        warmUpJvm();

        int size = 10_000_000;
        List<Integer> listToSort = initialize(size);

        ListSorter ls = new ListSorter();

        long sequentialAlgStart = System.currentTimeMillis();
        ls.quickSort(listToSort, 0, listToSort.size() - 1);
        long sequentialTime = System.currentTimeMillis() - sequentialAlgStart;
        System.out.println("sequential time: " + sequentialTime);


        listToSort = initialize(size);
        QuickSort qs = new QuickSort(listToSort);

        long paralAlgStart = System.currentTimeMillis();
        qs.run();
        long parallelTime = System.currentTimeMillis() - paralAlgStart;
        System.out.println("parallel time: " + parallelTime);

        assertTrue(parallelTime < sequentialTime / 2);
    }

    private void warmUpJvm() {
        int size = 1_000_000;
        List<Integer> listToSort = initialize(size);
        ListSorter ls = new ListSorter();
        ls.quickSort(listToSort, 0, listToSort.size() - 1);

        listToSort = initialize(size);
        QuickSort qs = new QuickSort(listToSort);
        qs.run();
    }
}
