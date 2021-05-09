package ru.mipt;

import java.util.function.Predicate;

public class FilterPredicate implements Predicate<Integer> {
    private boolean isFirstMet = true;
    private final int pivot;
    public FilterPredicate (int pivot) {
        this.pivot = pivot;
    }
    @Override
    public boolean test(Integer item) {
        if(item == pivot && isFirstMet) {
            isFirstMet = false;
            return false;
        }
        return item >= pivot;
    }
}
