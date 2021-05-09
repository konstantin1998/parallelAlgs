import org.junit.Test;
import ru.mipt.QuickSort;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {
    @Test
    public void shouldSortInAscendingOrderWhenManyItems(){
        int n = 1_000_000;
        test(n);
    }

    @Test
    public void shouldSortInAscendingOrderWhenOneItem(){
        int n = 1;
        test(n);
    }

    private void test(int n) {
        //given
        List<Integer> actual = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        for(int i = 0; i < n; i ++) {
            actual.add(n - i);
            expected.add(i + 1);
        }
        //when
        QuickSort qs = new QuickSort(actual);
        qs.setBlock(Math.max(100, n / 100));
        qs.run();
        //then
        assertEquals(expected, actual);
    }
}
