package ru.mipt.sequential;

import java.util.List;

public class ListSorter {
    public void quickSort(List<Integer> list, int left, int right) {
        if (list.size() == 0)
            return;

        if (left >= right)
            return;

        int middle = left + (right - left) / 2;
        int pivotElem = list.get(middle);

        int i = left, j = right;
        while (i <= j) {
            while (list.get(i) < pivotElem) {
                i++;
            }

            while (list.get(j) > pivotElem) {
                j--;
            }

            if (i <= j) {
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
                i++;
                j--;
            }
        }

        if (left < j)
            quickSort(list, left, j);

        if (right > i)
            quickSort(list, i, right);
    }
}
