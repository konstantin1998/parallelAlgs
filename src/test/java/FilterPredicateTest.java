import org.junit.Test;
import ru.mipt.FilterPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class FilterPredicateTest {
    @Test
    public void shouldRemoveDuplicatedElements() {
        //given
        List<Integer> actual = new ArrayList<>();
        int item = 1;
        actual.add(item);
        actual.add(item);
        List<Integer> expected = new ArrayList<>();
        expected.add(item);
        //when
        actual = actual.stream().filter(new FilterPredicate(item)).collect(Collectors.toList());
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterCorrectlyWhenDistinctElements() {
        //given
        int n = 10;
        List<Integer> actual = new ArrayList<>();
        int pivotElem = n - (n - 1) / 2;
        for(int i = 0; i < n; i ++) {
            actual.add(n - i);
        }
        //when
        List<Integer> expected = actual.stream().filter((item) -> item > pivotElem).collect(Collectors.toList());
        actual = actual.stream().filter(new FilterPredicate(pivotElem)).collect(Collectors.toList());
        //then
        assertEquals(expected.size(), actual.size());
    }
}
