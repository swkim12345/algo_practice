package base.java.algorithm;

/*
 * Disjoint Set - 서로소 집합 자료구조
 * 서로 중복되지 않는 부분 집합들로 나눠진 원소들에 대한 정보를 저장하고 조작하는 자료구조
 * 보통은 무방향 그래프에서만 제대로 작동함.
 * 1. 초기화 - 자기 자신을 루트노드로 가지고 있는 트리 만들기
 * 2. find - 자신의 부모 노드를 찾는 연산, 결과로 트리가 나옴, path compression을 통해 시간 단축할 수 있음.
 * 보통 재귀를 이용한 dfs로 찾게 됨.
 * 3. union - 두 트리를 합치는 연산, 합칠 때 보통 인덱스가 낮은 쪽으로 합치게 됨.
 * 
 * 
 * 방향성이 없는 그래프 -> 자기보다 낮은 인덱스를 가진 것만 탐색
 * 방향성이 있는 그래프 -> 모든 엣지를 이용해 찾아야 함, path compression 사용 가능
 * 하지만, 다양한 부모를 가질 수 있음 -> 따라서 union-find를 이용해 찾기 힘듦.
 */

import java.io.*;
import java.util.*;

public class UnionFind {
    // arr에는 부모 노드의 값을 가지고 있음.
    static int find(List<List<Integer>> graph, List<Integer> arr, int start) {
        // 종료조건 : 자기 자신이 부모인 경우
        if (start == arr.get(start)) return start;

        // path compression
        return arr.set(start, find(graph, arr, arr.get(start)));
    }

    static void union(List<List<Integer>> graph, List<Integer> arr, int a, int b) {
        a = find(graph, arr, a);
        b = find(graph, arr, b);

        arr.set(b, a); //a에 b의 트리 합성
    }

    // 트리의 높이가 중요할 경우 - rank 배열을 이용해 구현
    // 트리의 높이가 낮은 트리를 높은 트리에 삽입 -> 높이를 바탕으로 root 삽입 변경
    static int[] rank;
    static void union2(List<List<Integer>> graph, List<Integer> arr, int a, int b) {
        a = find(graph, arr, a);
        b = find(graph, arr, b);

        if (a == b) return;

        if (rank[a] < rank[b]) {
            rank[a] = b;
        } else {
            rank[b] = a;

            if (rank[a] == rank[b]) { // 높이가 동일할 경우 - 타깃 rank 1 증가.(1만큼 높이가 높아졌으므로)
                rank[a] ++;
            }
        }
    }

    // 노드의 개수를 카운팅해야 할 경우 - nodeCound를 이용해 더함.

    static int[] nodeCount;
    static int union3(List<List<Integer>> graph, List<Integer> arr, int a, int b) {
        a = find(graph, arr, a);
        b = find(graph, arr, b);

        if (a != b) {
            arr.set(b, a);
            nodeCount[a] += nodeCount[b];
            nodeCount[b] = 0;
        }
        return nodeCount[a]; // 합쳐진 후 노드 총 개수 리턴
    }
}