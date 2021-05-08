package utils;

import entities.Vertex;

import java.util.ArrayList;
import java.util.List;

public class PrefSumCounter {
    private final int blockSize;
    private final int maxSequentialSize;

    public PrefSumCounter(int blockSize) {
        this.blockSize = blockSize;
        this.maxSequentialSize = 2 * blockSize;
    }

    public List<Integer> countPrefSum(List<Integer> degrees) {
        if(degrees.size() < maxSequentialSize){
            return countPrefSumSequentially(degrees, 0, degrees.size() - 1);
        }
        List<Integer> sums = new ArrayList<>(degrees.size());
        return null;
    }

    public static List<Integer> countPrefSumSequentially(List<Integer> degrees, int left, int right) {
        List<Integer> sums = new ArrayList<>(degrees.size());
        sums.add(degrees.get(left));
        for(int i = 1; i <= right; i++) {
            sums.add(degrees.get(i) + sums.get(i - 1));
        }
        return sums;

    }
}
