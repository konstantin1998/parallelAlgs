package parallelFor;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class ParallelForTest {

    @Test
    public void expectCorrectWorkWhenSeveralThreadsEnabled() {
        //given
        int numberOfItems = 4;
        int initialValue = 0;
        List<Integer> actual = getList(numberOfItems, initialValue);
        int desiredValue = 1;
        List<Runnable> tasks = new ArrayList<>();
        tasks.add(() -> actual.set(0, desiredValue));
        tasks.add(() -> actual.set(1, desiredValue));
        tasks.add(() -> actual.set(2, desiredValue));
        tasks.add(() -> actual.set(3, desiredValue));
        //when
        executeTasks(tasks, numberOfItems);
        List<Integer> expected = getList(numberOfItems, desiredValue);
        //then
        assertEquals(actual, expected);
    }

    @Test
    public void workCorrectlyWhenOneThreadIsEnabled() {
        //given
        int numberOfItems = 4;
        int initialValue = 0;
        List<Integer> actual = getList(numberOfItems, initialValue);
        int desiredValue = 1;

        List<Runnable> tasks = new ArrayList<>();
        tasks.add(() -> actual.set(0, desiredValue));
        tasks.add(() -> actual.set(1, desiredValue));
        tasks.add(() -> actual.set(2, desiredValue));
        tasks.add(() -> actual.set(3, desiredValue));
        //when
        int numThreads = 1;
        executeTasks(tasks, numThreads);
        List<Integer> expected = getList(numberOfItems, desiredValue);
        //then
        assertEquals(actual, expected);
    }

    private List<Integer> getList(int numberOfItems, int desiredValue){
        List<Integer> list = new ArrayList<>(numberOfItems);
        for(int i = 0; i < numberOfItems; i++) {
            list.add(desiredValue);
        }
        return list;
    }

    private void executeTasks(List<Runnable> tasks, int numberOfThreads) {
        ParallelFor parallelFor = new ParallelFor(numberOfThreads);
        parallelFor.setTasks(tasks);
        parallelFor.executeTasks();
    }

}
