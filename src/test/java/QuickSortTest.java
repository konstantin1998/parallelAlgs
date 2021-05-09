import org.junit.Test;
import ru.mipt.QuickSort;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {
    @Test
    public void shouldSortInAscendingOrderWhenManyItems(){
        int n = 10_000_000;
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
        QuickSort qs = new QuickSort();
        qs.setBlock(100_000);
        qs.setList(actual);
        qs.setRight(actual.size() - 1);
        qs.setLeft(0);
        qs.run();
        //then
        assertEquals(expected, actual);
    }
}
