package ru.mipt.sequential;

import java.util.List;

public class ListSorter {
    public void quickSort(List<Integer> list, int left, int right) {
        if (list.size() == 0)
            return;//завершить выполнение если длина массива равна 0

        if (left >= right)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = left + (right - left) / 2;
        int pivotElem = list.get(middle);

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = left, j = right;
        while (i <= j) {
            while (list.get(i) < pivotElem) {
                i++;
            }

            while (list.get(j) > pivotElem) {
                j--;
            }

            if (i <= j) {//меняем местами
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (left < j)
            quickSort(list, left, j);

        if (right > i)
            quickSort(list, i, right);
    }
}
