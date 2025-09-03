/*
 * 스티커 붙이기
 * https://www.acmicpc.net/problem/18808
 * 
 * 방법이 주어지고, 이 방법에 맞게 푸는 문제.
 * 전형적인 시뮬레이션 문제이지만, 시뮬레이션 단계 중 스티커를 붙일 자리를 찾아야 하므로 브루트포스 문제이기도 하다.
 * 스티커를 붙일 때 붙을 수 있는 자리를 완전탐색을 통해 진행해야한다.
 * 
 * 1. 겹치거나 노트북을 벗어나지 않으면서 스티커를 붙임. 왼쪽 위부터 우선순위
 * 2. 붙일 수 없다면 90를 회전해 1단계를 다시 수행한다.
 * 3. 만약 270도를 회전시켰는 데에도 붙이지 못했다면 스티커는 버린다.
 * 
 * 완전 탐색의 경우는 1번이다.
 * 1번에서 왼쪽 위부터 순회해가면서 봐야 한다.
 * 이 문제의 경우 1부터 인덱스를 시작하게 한다. (한번 바꿔봄. 원래는 0부터 시작하게 했음.)
 * 
 * 초기화
 * 스티커, 노트북을 1부터 시작하는 인덱스에 삽입한다.
 * 솔루션
 * 0. 스티커를 입력받는다.
 * 1. 스티커마다 노트북에 붙이러 노력한다.
 * 1-1. 스티커를 1, 1 -> col - sticker_col,row - sticker_row까지 이동시키며 for 반복문을 4번 돌린다(스티커 - 노트북 매칭용 2번, 스티커 이동용 2번)
 * 1-2. 스티커를 붙일 수 있다면 노트북에 업데이트한다.
 * 2. 만약 붙일 수 없다면, 90도 회전을 한 다음, 1번을 다시 시도한다.
 * 3. 2번을 3번 반복해 270도 회전했음에도 안 된다면, 스티커를 버린다.
 * 
 * 90도 회전
 * 만약 좌표 i, j가 있다면, 시계방향으로 90도 회전하면 row - j + 1, i
 * 
 * 3 3 짜리
 * 
 * 
 * 2, 1 => 1, 2 => 2, 3 => 3, 2 => 2, 1
 * 
 * 1, 1 => 1, 3 => 3, 3 => 3, 1
 * col, row : 3
 * 
 * i, j -> row - j + 1, col - i + 1
 * 
 * new_i = a * col + b * row + c * i + d * j;
 * 
 * 1 = 3a + 3b + 2c + 1d
 * 2 = 3a + 3b + 1c + 2d
 * 3 = 3a + 3b + 2c + 3d
 * 2 = 3a + 3b + 3c + 2d
 * 1 = 3a + 3b + 1c + 1d
 * 3 = 3a + 3b + 1c + 3d
 * 
 * c - d = -1
 * c = d - 1
 * 3d + 3a + 3b = 3
 * a + b = 1 - d
 * 3 - 3d + 3d - 3 + 2d = 2
 * d = 1
 * c = 0
 * a + b = 0
 * a * col = -b * row
 * new col = old row
 * 
 * col : 3, row : 4
 * 1, 1 => 1, 3 => 3, 4
 * 1 = a * col + b * row + 1 * 1
 * 3 = a * col + b * row + 3 * 1
 * a col == - b row
 * a = row , b = - col
 * 
 * 2 = 3a + 3b + 2c + 1d
 * 3 = 3a + 3b + 1c + 2d
 * 2 = 3a + 3b + 2c + 3d
 * 1 = 3a + 3b + 3c + 2d
 * 
 * c - d = -1
 * c = d - 1
 * 3a + 3b + 2d  - 2 + d = 2
 * 3a + 3b + 3d = 4
 * 4 - 3d + 2d - 2 + 3d = 2
 * 2d = 0
 * d = 0
 * c = -1
 * col * a + row * b = 4
 * 
* col : 3, row : 4
 * 1, 1 => 1, 3 => 3, 4 => 4, 1
 * 3 = a * col + b * row + -1
 * 4 = a * row + b * col + -1
 * 4 = a * col + b * row
 * 5 = a * row + b * col
 * 
 * new col = old row
 * new row = col - old col + 1
 * 
 * 모든 과정이 완료되면 풀 스캔해 스티커가 붙은 칸의 개수를 카운팅한다.
 * 
 * 오답노트
 * 1. 식을 만들 때 조심하자. - 이거의 경우 6개의 변수가 있는 방정식이었다. 하나씩 풀어가면서 쓰자.
 * 2. 1부터 시작할 때에도 조심하자.
 */
import java.io.*;
import java.util.*;

public class j_18808 {
    static boolean[][] notebook;
    static boolean[][] sticker = new boolean[11][11];

    // 90도 회전, 회전 처리만 해줌.
    static void rotate90(int col, int row) {
        // new col = old row
        // new row = col - old row + 1
        boolean[][] tmp = new boolean[row + 1][col + 1];
        for (int i = 1; i <= col; i++) {
            for (int j = 1; j <= row; j++) {
                tmp[j][col - i + 1] = sticker[i][j];
            }
        }

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                sticker[i][j] = tmp[i][j];
            }
        }
    }

    static boolean pasteSticker(int posY, int posX, int col, int row) {
        for (int i = 1; i <= col; i++) {
            for (int j = 1; j <= row; j++) {
                // 스티커 붙일 수 없을 때 검증 => 스티커 true && notebook true
                // 노트북에 붙일 때 스티커의 시작 위치 1은 빼야함.
                if (sticker[i][j] && notebook[posY + i - 1][posX + j - 1]) {
                    return false;
                }
            }
        }

        for (int i = 1; i <= col; i++) {
            for (int j = 1; j <= row; j++) {
                // 스티커 붙이기
                if (sticker[i][j]) {
                    notebook[posY + i - 1][posX + j - 1] = true;
                }
            }
        }
        return true;
    }

    static void printSticker(int col, int row) {
        System.out.println("스티커 출력");
        for (int i = 1; i <= col; i++) {
            for (int j = 1; j <= row; j++){
                System.out.printf("%d ", sticker[i][j] ? 1 : 0);
            }
            System.out.println();
        }
    }

    static void solution(int N, int M, int col, int row) {
        int tmp;
        // 1번 진행
        for (int a = 0; a < 4; a++) {
            for (int i = 1; i <= N - col + 1; i++) { //여기서 동일한 사이즈면 제대로 시작하지 않음.
                for (int j = 1; j <= M - row + 1; j++) {   
                    if (pasteSticker(i, j, col, row)) { //붙이기 성공
                        return;
                    }
                }
            }
            // 실패 - 회전, col, row 바꿔줌.
            rotate90(col, row);
            tmp = row;
            row = col;
            col = tmp;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, M, K, answer = 0;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        notebook = new boolean[N + 1][M + 1];

        int col, row;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            col = Integer.parseInt(st.nextToken());
            row = Integer.parseInt(st.nextToken());

            for (int a = 1; a <= col; a++) {
                st =  new StringTokenizer(br.readLine());
                for (int b = 1; b <= row; b++) {
                    // 스티커 입력, 모두 입력받으므로 따로 초기화가 필요 없음.
                    if (st.nextToken().equals("1")) {
                        sticker[a][b] = true;
                    } else {
                        sticker[a][b] = false;
                    }
                }
            }
            solution(N, M, col, row);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (notebook[i][j] == true) {
                    answer += 1;
                }
            }
        }
        System.out.println(answer);
    }
}
