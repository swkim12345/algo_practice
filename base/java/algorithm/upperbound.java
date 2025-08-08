package base.java.algorithm;

import java.io.*;
import java.util.*;

/*
 * upperbound
 * 구하는 값(value)보다 큰 값이 처음 나오는 위치를 반환
 * 만약, value와 같은 중복값 중 가장 마지막 값을 반환하려면 -1해서 반환해야 함.
 */

public class Upperbound {
    static int upperbound(List<Integer> list, int value) {
        int max = list.size();
        int min = 0;

        while (min < max) {
            int mid = (min + max) / 2;

            if (value < list.get(mid)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return min;
        // 만약, value와 같은 중복값 중 가장 마지막 반환하려면?
        // return min - 1;
    }
}
