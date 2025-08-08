/*
 * 식당 입구 대기 줄
 * 1 a - 학생 옴. 
 * 2 - 1인분 준비 - 카운팅
 * 0인분 준비 - 학생 처리 안 됨
 * 1인분 이상 준비 - 학생지우기
 * 음식이 준비되는 경우는 기다리는 학생이 1 이상일
 * 
 * 출력
 * 1. 대기학생이 최대 - 학생수 / 맨 뒤에 대기하는 학생의 번호
 * 2. 만약 대기학생이 최대인 경우가 여러번 - 학생 번호가 가장 작은 경우 출력
 */

import java.io.*;
import java.util.*;

public class j_26042 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int maxSize = 0, id = Integer.MAX_VALUE;
        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            if (st.nextToken().equals("1")) {
                queue.addLast(Integer.parseInt(st.nextToken()));

                if (queue.size() > maxSize || (queue.size() == maxSize && queue.peekLast() < id)) {
                    maxSize = queue.size();
                    id = queue.peekLast();
                } 
            } else {
                queue.pollFirst();
            }
        }
        System.out.printf("%d %d\n", maxSize, id);
    }
}