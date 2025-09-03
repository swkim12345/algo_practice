package base.java.algorithm;

/*
 * 리스트를 입력받아 순열을 출력하는 함수
 * 출력의 순서 또한 중요하므로, 리스트 전역을 순회해야 함.
 */

import java.util.*;

public class Permutation {
    static void dfs(List<Integer> list, int r, Deque<Integer> permutation, boolean[] visited) {
        if (permutation.size() == r) {
            for (int value : permutation) {
                System.out.printf("%d ", value);
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (visited[i] == true) continue;
            visited[i] = true;
            permutation.addLast(list.get(i));
            dfs(list, r, permutation, visited);
            visited[i] = false;
            permutation.pollLast();
        }
    }

    static void permutation(List<Integer> list, int r) {
        dfs(list, r, new ArrayDeque<>(), new boolean[list.size()]);
    }

    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>();

        values.add(1);
        values.add(2);
        values.add(3);
        values.add(4);
        values.add(5);

        permutation(values, 5);
    }
}
