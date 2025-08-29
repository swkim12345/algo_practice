/*
 * 사각형 찾기
 * 
 * 문제 요약
 * 인접행렬로 주어진 간선을 이용해 4개의 정점으로 이뤄진 사각형의 개수를 세는 문제
 * 서로 다른 정점들로 이뤄진 길이가 4인 사이클 찾기. 다만, 방문순서가 다르면 다른 경우로 간주함
 * 양방향 그래프임.
 * 
 * 문제 해결
 * 전체 점에서 4개를 중복 없이, 순서에 상관없이 선택하는 문제
 * 양방향 그래프이므로, 만약 사각형이 형성되면 * 8을 이용해 사각형의 총 개수를 구할 수 있음(순방향, 역방향)
 * 최악시간의 경우 250C4, 1억 5천정도의 시간이 걸림. 시간이 매우 빠듯하므로, 출력또한 최적화가 필요함
 * 
 * 주의
 * 양방향 그래프임.
 * 자기자신에 대해 연결된 그래프는 아님.
 * 사각형의 개수는 int 범위를 초과할 수 있음. (대략 최대 250 ** 4, 36억)
 * 사각형이 구성되는 경우는 abcd, acbd, acdb 가 있음.
 * 
 * 다른 풀이
 * 두 점을 선택한 다음, 연결된 간선끼리 비교해 두 점이 동일하다면 추가.
 * 비교할 때에는 그 전에 정렬이 필요함. sort를 이용해 정렬한 다음, 투포인터를 이용해 이동해가며 비교
 * 간선끼리 모두 비교한 다음 같은 것이 2개 이상 있다면 ?C2 * 8
 * O(N**3)정도의 시간복잡도 -> 1천만회정도.
 */

import java.io.*;
import java.util.*;

public class j_12024 {
    static int[][] arr;
    static List<List<Integer>> edges;

    /**
     * 중복 없는 순열을 구한 뒤, 연결되어 있으면 * 8을 한 뒤, 모두 합해 리턴하는 함수
     * 4개의 점을 구해야함.
     * 시간복잡도 O(N**4) 보다 약간 작음 250C4
     * @param N - 배열의 크기
     * @return - 사각형의 개수 리턴
     */
    // static long solution(int N) {
    //     long ans = 0;

    //     for (int a = 0; a < N; a++) {
    //         for (int b = a + 1; b < N; b++) {
    //             for (int c = b + 1; c < N; c++) {
    //                 for (int d = c + 1; d < N; d++) {
    //                     if (arr[a][b] == 1 && arr[b][c] == 1 && arr[c][d] == 1 && arr[d][a] == 1)
    //                         ans += 8; // 사각형이 완성되면, 반대방향으로 회전도 가능함.
    //                     if (arr[a][c] == 1 && arr[c][b] == 1 && arr[b][d] == 1 && arr[d][a] == 1)
    //                         ans += 8; // a c b d
    //                     if (arr[a][c] == 1 && arr[c][d] == 1 && arr[d][b] == 1&& arr[b][a] == 1) {
    //                         ans += 8; // a c d b
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     return ans;
    // }

    // upperbound로 찾은 다음 인덱스를 리턴하는 함수
    static int upperbound(int target, List<Integer> list) {
        int start = 0; int end = list.size();
        int mid;

        while (start < end) {
            mid = (start + end) / 2;
            if (list.get(mid) <= target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start + 1;
    }

    // 공간복잡도를 희생해 리스트를 이용한 풀이. O(N**3)
    static long solution(int N) {
        long ans = 0;
        long cnt;
        int aPnt, bPnt;
        int aTarget, bTarget;

        // 두 점을 선택한 다음, 각각 연결된 점을 찾고 연결된 점을 이용해 사각형을 구하는 풀이.
        // 점 선택 시 ?P2
        for (int a = 0; a < N; a++) {
            for (int b = 0; b < N; b++) {
                if (a == b) continue;
                List<Integer> aEdges = edges.get(a);
                List<Integer> bEdges = edges.get(b);
                cnt = 0;
                aPnt = 0; bPnt = 0;

                // 나머지 두 점을 선택하기 위해 ?P2, 다만 투포인터를 이용해 복잡도 줄임.
                while (aPnt < aEdges.size() && bPnt < bEdges.size()) {
                    aTarget = aEdges.get(aPnt);
                    bTarget = bEdges.get(bPnt);
                    if (aTarget == a || aTarget == b) {
                        aPnt++;
                        continue;
                    }
                    else if (bTarget == a || bTarget == b) {
                        bPnt++;
                        continue;
                    }

                    if (aTarget == bTarget) {
                        cnt++;
                        aPnt++; bPnt++;
                    } else if (aTarget > bTarget) {
                        bPnt++;
                    } else {
                        aPnt++;
                    }
                }

                // 2 점을 순열로 선택하면 됨
                ans += cnt * (cnt - 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        edges = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            edges.add(new ArrayList<>(N));
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                edges.get(i).add(j);
            }
        }

        for (int i = 0; i < N; i++) {
            edges.get(i).sort((o1, o2) -> Integer.compare(o1, o2));
        }

        System.out.println(solution(N));
    }
}