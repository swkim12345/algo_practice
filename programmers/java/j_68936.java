/*
쿼드압축 후 개수 세기

1. 분할정복으로 해결

솔루션
1. 시작점 (x, y), 길이(1, 2, 4...)를 재귀로 가지고 있음.
처음 시작은 (0,0), 행길이.
가장 처음 값을 변수로 가짐, 만약 변수가 달라지면 바로 재귀 (4등분)
시작점은 x / x + len / 2 // y / y + len, len / 2 로 재귀

오답노트
- 자바는 리터럴 할당이 불가능함.
- 
*/

class Solution {
    static int[] add(int[] first, int[] second) {
        first[0] += second[0];
        first[1] += second[1];
        return first;
    }

    public int[] recursive(int[] pos, int len, int[][] arr) {
        int val = arr[pos[0]][pos[1]];
        int[] answer = {0, 0};

        for (int i = pos[0]; i < pos[0] + len; i++) {
            for (int j = pos[1]; j < pos[1] + len; j++) {
                if (arr[i][j] != val) {
                    // 4분할 재귀 호출
                    answer = add(answer, recursive(new int[]{pos[0], pos[1]}, len / 2, arr));
                    answer = add(answer, recursive(new int[]{pos[0] + len / 2, pos[1]}, len / 2, arr));
                    answer = add(answer, recursive(new int[]{pos[0], pos[1] + len / 2}, len / 2, arr));
                    answer = add(answer, recursive(new int[]{pos[0] + len / 2, pos[1] + len / 2}, len / 2, arr));
                    return answer;
                }
            }
        }

        // 모두 같은 값일 경우
        answer[val]++;
        return answer;
    }

    public int[] solution(int[][] arr) {
        int[] pos = {0, 0};
        int len = arr.length;

        return recursive(pos, len, arr);
    }
}