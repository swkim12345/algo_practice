/*
 * 백조의 호수
 * 두마리의 백조가 서로를 만날 수 있는 날짜를 계산하는 프로그램
 * 물공간과 접촉한 빙판은 녹아서, 길을 만듦.
 * bfs, queue를 이용해 문제를 푸는 데, queue는 빙판과 닿은 공간이 남은 값을 넣어놓는다.
 * 1. 호수를 전체적으로 스캔해 큐에 빙판이 하나라도 접촉되어 있는 값을 삽입한다.
 * 2. 빙판 녹는 과정 진행
 * 2-1. 큐에서 팝해 근처에 있는 빙판을 녹이고(업데이트), 빙판이었던 값을 큐에 삽입한다.
 * 2-1.을 매일 반복하면 하루가 지나는 것으로 취급하면 된다. - 삽입 시 백조에서 시작한 값이면 0을 추가한다.
 * 2-2. queue에 삽입하는 값은 pair로써 (y, x) 값을 가진다.
 * 2-3. 큐에 있는 값을 모두 사용하면, 새로 넣은 큐로 갱신한다. (얼음이 닿여있는 곳을 넣은 큐)
 * 
 * 막히는 부분
 * 1. 백조이 있는 물과 백조가 없는 물이 만날 경우 bfs를 어떻게 돌려야 하는가?
 * - 백조가 있는 물에서 시작해서 돌리면 된다.
 * 2. 큐는 몇개가 필요한가?
 * - 백조가 있는 물 큐 / 백조가 없는 물 큐 <- 1번 막히는 부분이 자동으로 풀림. 백조가 없는 물 큐를 먼저 돌리면 됨.
 * 3. visitied 에 백조의 아이디값을 넣어야 하나?
 * - 아니. queue를 돌릴 때, 만약 물을 만남 + visited 맵에 찍혀있지 않는다면 바로 bfs를 돌려버리면 된다.
 * - visited는 백조의 맵을 나타낼때에만 사용하자.
 * 4. visited를 사용했을 때, 두 다른 백조는 어떻게 표현할까?
 * - 둘 중 하나의 백조를 나타낼때에만 visited를 사용하자. -> 그럼 큐가 3개네...
 * - 순서는 백조 없는 물, visited를 나타내지 않는 백조, visited를 나타내는 백조 큐.
 * 
 * 팁
 * bfs 문제를 풀 때 + 맵 문제일 때에는 visited map + queue를 이용해 문제를 풀자.
 * -> visited를 왜 쓰냐? - bfs로 방문한 곳을 다시 방문하지 말려고. - 여기에 뭔 짓을 하지 말자.
 * 
 * 답 킹승혁님 감사합니다.
 * visited map 에 인덱스를 작성하고, 인덱스를 바탕으로 넓혀가는 문제로 풀자.
 * 인덱스가 만났을 때, union find를 바탕으로 낮은 인덱스로 합치자.
 * 
 * 오답노트
 * 1. static 할당 중 arraylist와 같은 자료구조는 바로 할당하자.
 * 2. index 관리할 때 잘 하자..
 * 3. 새를 못찾는데? -> 이거 물 처리와 동일하게 해줘야 함.
 * 4. find시 재귀로 찾을 때 부모의 부모를 find로 넣어줘야함.
 * 5. visited 검사를 안 해줬어요; bfs, dfs 등
 * 6. map 녹은 거 갱신을 안 해줬어요
 * 7. 유니온 연산에 들어가지지 않음. - visit 와 map을 계속 헷갈리며 사용함.
 * 
 * 전반적으로 변수를 헷갈리고, index를 관리를 제대로 하지 못함.
 */

import java.io.*;
import java.util.*;

public class j_3197 {
    final static int water = 0, ice = 1, bird = 2;
    final static int notVisited = 0;
    final static Pair[] mv = 
        {new Pair(-1, 0), new Pair(0, 1), new Pair(1, 0), new Pair(0, -1)};
    
    static ArrayList<Integer> node = new ArrayList<>(); // union find를 위해 넣어둔 배열
    static ArrayList<Integer> birds = new ArrayList<>(); //새의 인덱스 값을 넣는 배열
    static int[][] map;
    static int[][] visit; // 인덱스값을 넣어주는 visit
    static Deque<Pair> bfsQue = new ArrayDeque<>(), que = new ArrayDeque<>();

    static class Pair {
        public int first, second;
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static int find(int one) {
        if (one == node.get(one)) return one;
        else {
            // 재귀적으로 업데이트하면서 리턴
            node.set(one, find(node.get(one)));
            return node.get(one);
        }
    }

    static void union(int one, int two) {
        one = find(one);
        two = find(two);

        node.set(one, two);
    }

    // 물 근처 얼음이 있는 지 확인 후 주어진 큐에 삽입하는 함수
    static void pushIceNearByWater(int x, int y, int R, int C, Deque<Pair> que) {
        for (int i = 0; i < 4; i++) {
            int nx = mv[i].second + x;
            int ny = mv[i].first + y;
            if (ny < 0 || ny >= R || nx < 0 || nx >= C) {
                continue;
            }
            if (map[ny][nx] == ice) {
                que.addLast(new Pair(ny, nx));
                visit[ny][nx] = visit[y][x]; //visit 미리 갱신처리
            }
            if (map[ny][nx] == water && visit[ny][nx] != visit[y][x]) {
                union(visit[ny][nx], visit[y][x]);
            }
        }
    }

    static void solution(int R, int C) {
        int step = 0;
        int x, y;
        int firstBird = birds.get(0), secondBird = birds.get(1);

        while (true) {
            step++;
            int size = que.size();
            /*
             * 방문한 노드 - water로 갱신
             */
            while (size-- > 0) {
                Pair tmp = que.pollFirst();
                y = tmp.first;
                x = tmp.second;
                if (map[y][x] == water) continue;
                map[y][x] = water;
                System.out.printf("%d %d\n", x, y);

                pushIceNearByWater(x, y, R, C, que);
            }
            // 종료조건 : 만약 백조의 부모가 동일하면 출력 후 종료
            if (find(firstBird) == find(secondBird)) {
                System.out.println(step);
                break;
            }
        }
    }

    // ice가 근처에 있다면 true, 없으면 false 리턴
    static boolean pushWater(int x, int y, int R, int C) {
        boolean isNearIce = false;
        for (int i = 0; i < 4; i++) {
            int nx = mv[i].second + x;
            int ny = mv[i].first + y;
            if (ny < 0 || ny >= R || nx < 0 || nx >= C) {
                continue;
            }
            if (visit[ny][nx] != 0) continue;
            // 물, 새, 큐에 삽입, 삽입 후 visited 추가
            if (map[ny][nx] != ice) {
                visit[ny][nx] = visit[y][x];
                bfsQue.addLast(new Pair(ny, nx));
                // 새라면 idx 삽입해서 관리해야해요
                if (map[ny][nx] == bird) {
                    birds.add(visit[y][x]);
                }
            }
            // 얼음, bfs 큐가 아닌 큐에 삽입, visited 추가하면 안 됨.
            else {
                que.addLast(new Pair(ny, nx));
            }
        }
        return isNearIce;
    }

    static void bfs(int R, int C) {
        Pair tmp;
        int x, y;
        while (bfsQue.size() > 0) {
            tmp = bfsQue.pollFirst();
            y = tmp.first;
            x = tmp.second;
            if (pushWater(x, y, R, C)) {
                que.addLast(new Pair(y, x));
            }
        }
    }

    static void initial(int R, int C) {
        // 먼저 bfs를 전체적으로 돌려줌.
        // 돌려줄 때, 인덱스값을 넣어줌.
        int idx = notVisited + 1;

        int x, y;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                if (map[i][j] != ice && visit[i][j] == notVisited) {
                    y = i;
                    x = j;
                    
                    node.add(idx); //인덱스에 부모 인덱스를 넣어서 초기화.
                    visit[y][x] = idx++; //인덱스 값 넣어서 초기화
                    bfsQue.push(new Pair(y, x));
                    bfs(R, C);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int R, C;
        que = new ArrayDeque<>();
 
        String tmp;

        tmp = br.readLine();
        R = Integer.parseInt(tmp.split(" ")[0]);
        C = Integer.parseInt(tmp.split(" ")[1]);
        map = new int[R][C];
        visit = new int[R][C];
        node.add(notVisited); //0은 더미값임.

        for (int i = 0; i < R; i++) {
            tmp = br.readLine();

            for (int j = 0; j < C; j++) {
                if (tmp.charAt(j) == '.') {
                    map[i][j] = water;
                } else if (tmp.charAt(j) == 'X') {
                    map[i][j] = ice;
                } else {
                    map[i][j] = bird;
                }
            }
        }
        initial(R, C);

        solution(R, C);
    }
}
