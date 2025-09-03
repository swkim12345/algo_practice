/*
 * 사탕상자
 * 
 * 문제 요약
 * 사탕상자에 사탕을 빼서 주고, 넣고 빼는 액션이 있음.
 * A 1 => 몇번째 사탕을 빼서 줌. 사탕은 하나만 제거됨.
 * A 2 -> 사탕의 맛을 기준으로 몇개의 사탕을 넣거나 뺌. C가 양수면 넣고, 음수면 뺌
 * 제한조건 : 사탕의 맛은 1부터 백만 정수
 * 총 개수 : 20억 - int 범위 내.
 * 총 횟수 : 10만회
 * 
 * 문제 풀이
 * 사탕의 맛이 100만밖에 안 되고, 정수라는 것이 보장되므로 int배열로 관리하고,
 * 이를 세그먼트 트리로 관리한다.
 * 일단, 이런 경우 A가 2번인 경우는 모두 됨. (넣거나 빼는 것은 diff를 이용해 update 쿼리를 날려주면 된다.)
 * A 가 1인 경우는 1번부터 n번까지 누적합을 사용해 어디에서 빼야하는 지 알아야하는데..
 * 
 * 기존 세그먼트 트리는 p ~ q까지의 합을 구할 수 있는 데, start = 1, end = 100만, mid로 잡고 mid로 날리면
 * 순위 - 1 > 쿼리 -> start = mid + 1;
 * 순위 - 1 == 쿼리 -> mid
 * 순위 - 1 < 쿼리 -> end = mid;
 * 세그트리 빌드가 필요 없고, 쿼리와 업데이트만 있으면 됨.
 */

import java.io.*;
import java.util.*;

public class j_2243 {
    /**
     * 구간합 세그트리
     * 쿼리, 업데이트만 존재함.
     */
    static class Segtree {
        int N, SEG_N;
        int[] arr, tree;

        Segtree(int n) {
            N = n;
            SEG_N = 4 * N;
            arr = new int[N];
            tree = new int[SEG_N];
        }

        // node - segtree 용, start, end, p, q는 arr용
        int query(int node, int start, int end, int p, int q) {
            // 범위 초과
            if (q < start || end < p) return 0;

            // 범위 내면 리턴
            if (p <= start && end <= q) return tree[node];

            int mid = (start + end) / 2;

            return query(node * 2, start, mid, p, q) + query(node * 2 + 1, mid + 1, end, p, q);
        }

        int query (int p, int q) {
            return query(1, 0, N - 1, p, q);
        }

        // node - segtree용, start, end, index는 arr용, value는 바뀌는 값.
        void update(int node, int start, int end, int index, int value) {
            if (start == end) {
                tree[node] += value;
                return;
            }

            if (index < start || end < index) return;

            int mid = (start + end) / 2;

            update(node * 2, start, mid, index, value);
            update(node * 2 + 1, mid + 1, end, index, value);

            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        void update(int index, int value) {
            update(1, 0, N - 1, index, value);
        }

        // 누적합을 이용한 이분탐색 - upperbound? lowerbound?
        int find(int rank) {
            int start = 0, end = N - 1;
            int mid;

            while (start < end) {
                mid = (start + end) / 2;
                int prefixSum = query(start, end);
                if (rank < prefixSum) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }
            return start;
        }
    }

    
}

