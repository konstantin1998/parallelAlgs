import org.junit.Test;
import ru.mipt.ParallelInserter;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ParallelInserterTest {
    @Test
    public void shouldInsertCorrectlyWhenShiftIsAbsent() {
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = new ArrayList<>();
        int size = 1000;
        for(int i = 0; i <= size; i++) {
            int n = 1;
            expected.add(n);
            actual.add(null);
        }

        ParallelInserter inserter = new ParallelInserter(expected, actual, 0);
        int block = 100;
        inserter.setBlock(block);
        inserter.run();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldInsertCorrectlyWhenShiftIsPresent() {
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = new ArrayList<>();
        int shift = 500;
        int size = 1000;
        for(int i = 0; i <= size; i++) {
            expected.add(i);
            if(i < shift) {
                actual.add(i);
            } else {
                actual.add(null);
            }
        }

        List<Integer> sublist = expected.subList(shift, expected.size());
        ParallelInserter inserter = new ParallelInserter(sublist, actual, shift);
        int block = 100;
        inserter.setBlock(block);
        inserter.run();

        assertEquals(expected, actual);
    }
}
