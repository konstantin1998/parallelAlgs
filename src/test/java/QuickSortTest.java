import org.junit.Test;
import ru.mipt.QuickSort;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {
    @Test
    public void shouldSortInAscendingOrderWhenManyItems(){
        //given
        int n = 100;
        List<Integer> actual = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        for(int i = 0; i < n; i ++) {
            actual.add(n - i);
            expected.add(i + 1);
        }
        //when
        QuickSort qs = new QuickSort();
        qs.setList(actual);
        qs.setRight(actual.size() - 1);
        qs.setLeft(0);
        qs.run();
        //then
        assertEquals(expected, actual);
    }
}
