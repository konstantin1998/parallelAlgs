package utils;

import java.util.ArrayList;
import java.util.List;

public class Splitter {
    //private final List<Integer> borders;
    //private final int nTasks;
    private final int minimalSizeToSplit = 500;

//    public Splitter(int nTasks ) {
//        this.nTasks = nTasks;
//    }

    public List<Group> split(List<Integer> prefixSums, int nTasks) {
        List<Group> groups = new ArrayList<>();
//        if(prefixSums.size() < minimalSizeToSplit) {
//            Group group = new Group();
//            group.setLeft(0);
//            group.setRight(prefixSums.size() - 1);
//            groups.add(group);
//            return groups;
//        }
        return null;
    }
}
