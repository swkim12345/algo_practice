/*
 * 컨베이어 벨트 위의 로봇
 * https://www.acmicpc.net/problem/20055
 * 
 * 벨트도 회전, 로봇도 회전
 * 답은 몇번째 단계를 진행중이었는 지 출력 -> 각 단계에서 종료조건인 내구도가 0인 칸이 k개 이상인 지 확인해야 한다.
 * 자료구조는 컨베이어 밸트의 내구도 저장 array, 로봇 객체를 저장하는 arraylist
 * 로봇은 컨베이어 밸트의 위치를 저장해야 한다.
 * 시작, 끝값, 내구도가 0인 것을 저장하는 answer 등을 가지고 있어야 한다.
 * 초기화
 * 컨베이어 벨트 내구도 저장 배열
 * 로봇 저장 ArrayList - Integer(컨베이어 벨트의 위치만 저장)
 * 처음, 끝(로봇 올리고, 내리는 곳(start, end)) / cnt (내구도가 0인 값을 저장)
 * 솔루션
 * 1, 2, 3, 4단계를 스탭 바이 스탭으로 진행
 * 진행할 때마다 cnt를 검사하고, 만약 cnt >= K라면 단계 번호(1, 2, 3,4)를 저장
 * 앞에 로봇이 있는 지를 확인해야 함.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class j_20055 {
    static int[] belt;
    static LinkedList<Integer> robots = new LinkedList<>();

    static void deleteRobot(int end) {
        while (robots.size() > 0 && robots.peekLast() == end) {
            robots.pollLast();
        }
    }
    static int beltMove(int location, int N) {
        return (location - 1 + 2 * N) % (2 * N);
    }

    static int robotMove(int location, int N) {
        return (location + 1) % (2 * N);
    }

    // N : 벨트길이 / 2, k : 내구도 임계값
    static void solution(int N, int K) {
        // cnt : 내구도가 0인 벨트, step : 단계(1 ~ 4단계 아님. 모든 사이클 한번 돌았을 때가 한 단계)
        // start - 로봇 올려놓는 곳, end - 로봇 가져가는 곳
        int cnt = 0, start = 0, end = N - 1, step = 0, tmp;

        while (cnt < K) { // 종료조건 : cnt == K
            step += 1;
            // 1단계 - 벨트 이동
            start = beltMove(start, N);
            end = beltMove(end, N);
            deleteRobot(end);

            // 2단계 - 로봇 이동 (내구도 남음 && 로봇이 없어야 함.)
            for (int i = robots.size() - 1; i >= 0; i--) {
                tmp = robotMove(robots.get(i), N);

                // 내구도 테스트
                if (belt[tmp] > 0) { // 벨트 내구도 확인
                    if (i + 1 == robots.size() || (i + 1 < robots.size() && robots.get(i + 1) != tmp)) {
                        robots.set(i, tmp);
                        belt[tmp] -= 1;
                        if (belt[tmp] == 0) {
                            cnt += 1;
                        }
                    }
                }    
            }
            deleteRobot(end);
            // 3단계 - 로봇 올리기
            if (belt[start] > 0) {
                robots.addFirst(start);
                belt[start] -= 1;
                if (belt[start] == 0) {
                    cnt += 1;
                }
            }
            // 4단계 - 내구도가 0인 칸의 개수가 K개 이상이면 과정 종료
            if (cnt >= K) {
                break;
            }
        }
        System.out.println(step);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, K, tmp;
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        belt = new int[2 * N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            tmp = Integer.parseInt(st.nextToken());
            belt[i] = tmp;
        }
        solution(N, K);
    }
}
