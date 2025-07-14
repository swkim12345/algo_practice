/*
 * 스도쿠
 * 백트래킹을 이용해 푸는 문제
 * 1. 재귀함수를 이용해 dfs로 풀기(마킹 필수)
 * 
 * 오답노트
 * 1. != 잘못 봄
 * 2. 변수가 많아지고, 제대로 변수명을 설정하지 않으니 이상한 곳에 사용한 경우(boolean체크해야 하는 곳에서 arr체크..)
 * 3. boxX를 통해 제대로 구했는 데, 거기에 다시 x를 더해 스도쿠를 이상하게 이동시키는 경우
 * 4. 종료조건을 제대로 삽입하지 못함.
 * 5. 0은 비어있는 것인데, 이를 조건 검사에 같이 넣어버려서 원치 않은 리턴이 발생함.
 * 6. 줄 검사시 박스 내에는 검사를 하지 않던지, 아니면 |을 걸어서 비트연산을 걸어야 함
 * 7...... -> 박스 내에서 검사를 하지 않지 말고, 비트연산을 하되, 나중에 검증을 해줘야 함.
 */

import java.io.*;
import java.util.ArrayList;

public class j_2580 {
	static int arr[][] = new int[9][9];
	static ArrayList<Pair> checklist = new ArrayList<>();
	
	static class Pair {
		public int first, second;
		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
	
	static int recursive(int idx) {
		// 종료조건 - checklist 끝까지 아무런 문제 없이 도달했을 경우 0 리턴
		if (checklist.size() == idx) return 0;

		int y = checklist.get(idx).first, x = checklist.get(idx).second;
		boolean count[] = new boolean[10];
		
		for (int i = 1; i <= 9; i++) {
			count[i] = false;
		}
		// 박스 검사
		int boxX = x / 3 * 3, boxY = y / 3 * 3;
		
		for (int i = boxY; i < boxY + 3; i++) {
			for (int j = boxX; j < boxX + 3; j++) {
				count[arr[i][j]] = true;
			}
		}
		
		// 줄 검사
		for (int i = 0; i < 9; i++) {
			count[arr[i][x]] = true;
		}

		for (int i = 0; i < 9; i++) {
			count[arr[y][i]] = true;
		}
		
		// 순회하면서 다음 recursive
		for (int i = 1; i <= 9; i++) {
			if (count[i] == false) {
				count[i] = true;
				arr[y][x] = i;
				if (recursive(idx + 1) == 0) return 0;
				else {
					arr[y][x] = 0;
				}
			}	
		}
		return -1;
	}
	
    // 빈칸을 찾아 넣어놓는 역할을 맡음
	static void count() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (arr[i][j] == 0) {
					checklist.add(new Pair(i, j));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputs;
		for (int i = 0; i < 9; i++) {
			inputs = br.readLine().split(" ");
			for (int j = 0; j < 9; j++) {
				arr[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		
		count();
		recursive(0);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.printf("%d ", arr[i][j]);
			}
			System.out.println();
		}
	}
}