/*
 * 철도 공사
 * 
 * 고유번호가 백만으로 되어 있다. 이 때, 역의 개수가 50만, 공사 횟수가 150만이다.
 * 쿼리는 모두 고유번호를 가지고 있습니다. 그러므로, 쿼리를 찾을 때는 O(1)으로 끝내야 공사횟수(쿼리)를 O(M)으로 처리해
 * 시간 초과가 나지 않습니다.
 * 폐쇄 작업은 현재 설립된 역이 2개 이상일 때만 들어온다.
 * 
 * 각 노드가 가져야 할 값은 다음과 같습니다.
 * boolean isOpen = false (디폴트)
 * int previous, next
 * 
 * 각 쿼리는 다음과 같은 액션을 취해야합니다.
 * 1. 추가
 * - 다다음 노드 고유번호 / 현재 노드 고유번호
 * - 각각 previous, next(뒤인지 앞인지에 따라 달라짐)에 고유번호를 추가한후 isOpen true로 변경
 * - 고유번호 출력
 * 2. 폐쇄
 * - 다다음 노드 고유번호 / 현재 노드 고유번호
 * - 각각 previous, next를 가지고 있다가 처리
 * 
 * 엣지케이스
 * 1일때 발생할까요? 자기 자신만 잘 가리키면 발생하지 않음.
 * 
 * 초기화
 * 역을 활성화하고, 이전, 이후 역과 처음과 마지막 역을 연결해준다.
 * 
 * 
 * 시간 초과 해결법
 * 1. BufferedWriter bw 사용, 기존 print 사용하지 않음.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class j_23309 {
    static Node[] list = new Node[1000001];
    static class Node {
        public int previous, next;
    }

    static void init(int N, StringTokenizer st) {
        int previous, now, start;

        // 처음 노드는 자기 자신을 가리킨다고 가정하자.
        // 노드를 점차 늘려난다고 가정함.
        now = Integer.parseInt(st.nextToken());
        start = previous = now;
        list[now] = new Node();
        list[now].previous = previous;
        list[now].next = now;

        for (int i = 1; i < N; i++) {
            /*
             * 이전 현재 -> 과거
             * 현재 -> nexttoken;
             * 미래 -> 과거를 업데이트
             */
            previous = now;
            now = Integer.parseInt(st.nextToken());
            list[now] = new Node();
            list[previous].next = now;
            list[now].previous = previous;
        }
        // 마지막과 현재 노드 연결 필요
        list[start].previous = now;
        list[now].next = start;
    }

    // 리턴 : 역의 고유번호
    static int connect(int target, int nw, boolean isNext) {
        // 새로 연결되는 노드 : isOpen true, previous, next 갱신
        // 새로 연결되는 노드 이전 : next 갱신
        // 새로 연결되는 노드 이후 : previous 갱신
        int previous, next, ret;

        if (isNext) {
            previous = target;
            next = list[target].next;
            ret = next;
        } else {
            next = target;
            previous = list[target].previous;
            ret = previous;
        }
        // 새로 연결
        list[nw] = new Node();
        list[nw].next = next;
        list[nw].previous = previous;

        // 갱신
        list[previous].next = nw;
        list[next].previous = nw;
        return ret;
    }

    static int close(int target, boolean isNext) {
        // 삭제되는 노드 : isOpen false 처리만 함.
        // 삭제되는 노드 이전 : next node -> next next 노드로 갱신
        // 삭제되는 노드 이후 : previous -> previous previous 노드로 갱신
        int previous, next, ret;

        if (isNext) {
            previous = target;
            ret = list[target].next;
            next = list[ret].next;
        } else {
            next = target;
            ret = list[target].previous;
            previous = list[ret].previous;
        }
        // 갱신
        list[previous].next = next;
        list[next].previous = previous;
        return ret;
    }

    static void query(int M, BufferedReader br) throws IOException {
        StringTokenizer st;
        String action;
        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int a, b = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            action = st.nextToken();
            a = Integer.parseInt(st.nextToken());
            if (action.equals("BN") || action.equals("BP")) {
                b = Integer.parseInt(st.nextToken());
            }
            
            switch (action) {
                case "BN": {
                    sb.append(connect(a, b, true)).append("\n");
                    break;
                }
                case "BP" : {
                    sb.append(connect(a, b, false)).append("\n");
                    break;
                }
                case "CN" : {
                    sb.append(close(a, true)).append("\n");
                    break;
                }
                case "CP" : {
                    sb.append(close(a, false)).append("\n");
                    break;
                }
                default :
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, M;
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        init(N, st);
        query(M, br);
    }
}
