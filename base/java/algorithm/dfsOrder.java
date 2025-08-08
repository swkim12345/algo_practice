package base.java.algorithm;


/*
 * 순회 방식을 작성한 알고리즘
 * Node가 다음과 같이 주어진다고 가정할 때 다음과 같이 순회
 */
public class dfsOrder {
    static class Node {
        int value;
        Node left;
        Node right;
    }

    // 전위 순회 - top -> left -> right
    public void preOrder(Node node) {
        System.out.printf("value : %d\n", node.value);
        
        if (node.left != null) {
            preOrder(node.left);
        }
        if (node.right != null) {
            preOrder(node.right);
        }
    }

    // 중위 순회 - left -> top -> right
    public void inOrder(Node node) {
        if (node.left != null) {
            inOrder(node.left);
        }
        System.out.printf("value : %d\n", node.value);
        
        if (node.right != null) {
            inOrder(node.right);
        }
    }

    // 후위 순회 - left -> right -> top
    public void postOrder(Node node) {
        if (node.left != null) {
            postOrder(node.left);
        }
        if (node.right != null) {
            postOrder(node.right);
        }
        System.out.printf("value : %d\n", node.value);
    }
}
