package base.java.algorithm;

import java.util.*;

public class Combination {

    static void dfs(List<Integer> list, int r, int idx, Deque<Integer> combination) {
        if (combination.size() == r) {
            for (int value : combination) {
                System.out.printf("%d ", value);
            }
            System.out.println();
            return;
        }

        for (int i = idx; i < list.size(); i++) {
            combination.addLast(list.get(i));
            dfs(list, r, i + 1, combination);
            combination.pollLast();
        }
    }

    static void combination(List<Integer> list, int r) {
        dfs(list, r, 0, new ArrayDeque<>());
    }

    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>();

        values.add(1);
        values.add(2);
        values.add(3);
        values.add(4);
        values.add(5);

        combination(values, 3);
    }
}
