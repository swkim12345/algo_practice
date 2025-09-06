package base.java.algorithm;

/*
 * lowerbound
 * 정렬된 값에서 입력값을 기준으로 입력값보다 크거나 같으면서 가장 작은 idx를 반환하는 함수
 */

import java.io.*;
import java.util.*;

public class Lowerbound {
    static int lowerbound(List<Integer> list, int value) {
        int max = list.size();
        int min = 0;

        while (min < max) {
            int mid = (min + max) / 2;

            if (list.get(mid) < value) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        return min;
    }
}
