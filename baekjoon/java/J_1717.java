/*
 * 집합의 표현
 * 
 * 유니온파인드(서로소 집합 문제)를 사용하는 문제 (union, find)
 * 각각의 값은 초기에는 연결되어 있지 않음.
 * 합집합, 같은 집합에 포함되어 있는 지 확인하는 연산을 통해 집합을 확인함.
 * 1. 초기 설정 - 자신의 부모는 자기 자신으로 처리
 * 2. 합연산시 - 숫자가 작은 것을 부모로 처리. 값을 압축하는 연산도 필요함.
 * 3. 같은 집합 확인 - 부모의 숫자가 같은 지 확인(값 압축하지 않을 경우 재귀를 통해 부모까지 확인 필요)
 * 
 * 먼저 찾아보지 말고 내 기억속에 있는 데로 풀어보자.
 * 
 * 오답노트
 * 배열에 []대신 ()를 썼음. 잠이 덜깼나...
 * 
 * 이후 답 참조
 * https://www.acmicpc.net/source/96308616
 * switch case를 이용해 query 나누기
 * BufferedWriter를 이용해 속도 높임.
 * union 시 부모가 같으면 대입하지 않음.
 * 이외에는 동일함.
 */
import java.io.*;
import java.util.*;

public class J_1717 {
    static int node[] = new int[1000001];

    static int find(int a) {
        if (a == node[a]) return a; //자기 자신이 부모일때
        else {
            return node[a] = find(node[a]); // 경로 압축 (재귀)
        }
    }

    static void union(int a, int b) {
        a = find(a); //부모 찾기
        b = find(b);

        node[b] = a; // a < b라면
    }

    static void query(int q, int a, int b) {
        if (q == 0) {
            union(a, b);
        } else {
            if (find(a) == find(b)) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // N까지 자기 자신 초기화
        for (int i = 1; i <= N; i++) {
            node[i] = i;
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            query(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
            );
        }  
    }
}
