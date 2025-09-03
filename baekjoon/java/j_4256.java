/*
 * 트리
 * 
 * 문제 요약
 * 전위 순회, 중위 순회가 주어질 때, 후위순회를 출력하는 문제
 * 트리는 보장됨
 * 
 * 문제 해결방식
 * inorder를 기준으로 트리를 재구축
 * preorder idx, inorder idx, size를 넘김
 * -> 가운데는 preorder를 이용해 찾음, 가운데를 바탕으로 left tree size / right tree size 앎
 * 종료조건 : size가 1일 때 - 자기 자신 value로 할당하고 리턴
 * 0일 때 - 바로 리턴
 * 다 만들고 트리 head를 바탕으로 post order로 순회하면서 프린트.
 * 
 * 주의점
 * 테스트 케이스가 주어지지 않는 문제
 * O(N) 로 풀리는 문제임
 */

import java.io.*;
import java.util.*;

public class j_4256 {
    static class Node {
        int value;
        Node left;
        Node right;
        Node parent;

        Node() {
            this.value = 0;
            left = null;
            right = null;
            parent = null;
        }
    }

    static int[] preorder = new int[1001];
    static int[] inorder = new int[1001];
    static int listSize;

    static void buildTree(int preorderIdx, int inorderIdx, int size, Node node) {

        node.value = preorder[preorderIdx];

        // 종료조건 2 - size 1
        if (size == 1) {
            return; 
        }
        // 상위 노드 size --
        size--;

        int newInorderIdx = inorderIdx;

        // 왼쪽 트리 사이즈 구하기
        while (newInorderIdx <= listSize) {
            if (inorder[newInorderIdx] == preorder[preorderIdx]) {
                break;
            }
            newInorderIdx ++;
        }
        int leftTreeSize = newInorderIdx - inorderIdx;
        int rightTreeSize = size - leftTreeSize;

        // 재귀, 탐색 중단조건 - size 0일때
        if (leftTreeSize > 0) {
            node.left = new Node();
            
            buildTree(preorderIdx + 1, inorderIdx, leftTreeSize, node.left);
        }

        if (rightTreeSize > 0) {
            node.right = new Node();

            buildTree(preorderIdx + 1 + leftTreeSize, newInorderIdx + 1, rightTreeSize, node.right);
        }
    }

    static void postOrder(Node top, StringBuilder sb) {
        if (top.left != null) {
            postOrder(top.left, sb);
        }
        if (top.right != null) {
            postOrder(top.right, sb);
        }
        sb.append(top.value).append(" ");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        Node top;
        
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            top = new Node();
            
            listSize = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= listSize; j++) {
                preorder[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= listSize; j++) {
                inorder[j] = Integer.parseInt(st.nextToken());
            }

            buildTree(1, 1, listSize, top);
            postOrder(top, sb);
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
