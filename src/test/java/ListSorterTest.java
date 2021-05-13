import org.junit.Test;
import ru.mipt.sequential.ListSorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ListSorterTest {
    @Test
    public void shouldSortInAscendingOrderWhenManyItems(){
        //given
        int n = 10;
        List<Integer> actual = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        for(int i = 0; i < n; i ++) {
            actual.add(n - i);
            expected.add(i + 1);
        }
        //when
        ListSorter sorter = new ListSorter();
        sorter.quickSort(actual, 0, actual.size() - 1);
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFallDownWhenEmptyList(){
        //given
        List<Integer> actual = Collections.emptyList();
        List<Integer> expected = Collections.emptyList();
        ListSorter sorter = new ListSorter();
        //when
        sorter.quickSort(actual, 0, actual.size() - 1);
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFallDownWhenOneItem(){
        //given
        List<Integer> actual = new ArrayList<>();
        actual.add(1);
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        //when
        ListSorter sorter = new ListSorter();
        sorter.quickSort(actual, 0, actual.size() - 1);
        //then
        assertEquals(expected, actual);
    }
}
