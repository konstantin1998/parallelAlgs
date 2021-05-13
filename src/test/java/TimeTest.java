import org.junit.Test;
import ru.mipt.sequential.ListSorter;
import ru.mipt.parallel.ParallelSorter;

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
    public void shouldGiveTheSameResultAsSequentialAlgorithm(){

        int size = 10_000_000;
        List<Integer> listToSort1 = initialize(size);

        ListSorter ls = new ListSorter();

        long sequentialAlgStart = System.currentTimeMillis();
        ls.quickSort(listToSort1, 0, listToSort1.size() - 1);
        long sequentialTime = System.currentTimeMillis() - sequentialAlgStart;
        System.out.println("sequential time: " + sequentialTime);


        List<Integer> listToSort2 = initialize(size);
        ParallelSorter sorter = new ParallelSorter();
        long paralAlgStart = System.currentTimeMillis();
        sorter.sort(listToSort2);
        long parallelTime = System.currentTimeMillis() - paralAlgStart;
        System.out.println("parallel time: " + parallelTime);

        assertEquals(listToSort1, listToSort2);
    }

}
