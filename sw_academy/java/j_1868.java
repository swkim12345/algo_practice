package sw_academy.java;

/*
 * 파핑파핑 지뢰찾기
 * 
 * 문제 요약
 * 지뢰가 없는 칸 클릭 횟수를 최소한으로 만드는 클릭 횟수 구하기
 * 
 * 문제 풀이
 * 1. 처음에는 8방향으로 무조건 찾으면서 검사. 하지만, 이럴 경우 지뢰가 있는 데, 대각선으로 건너뛰는 경우가 발생함.
 * 2. 전처리를 이용해 지뢰가 있는 곳 주변을 fill. 이후 fill이 된 곳에 도달하면 더이상 탐색하지 않음. 이것도 덮어 쓰면서 문제 발생
 */

import java.io.*;
import java.util.*;
import java.awt.*;

public class j_1868 {
    static final int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static final int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
    static final int empty = 0;
    static final int bomb = -1;
    static final int round = -2;

    static int[][] arr;
    static int N;

    static boolean isInside(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    } 

}
