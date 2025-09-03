package base.java.structure;

public class LinkedListQueue<E> {
    private Node<E> head;  // 큐의 앞 (dequeue 위치)
    private Node<E> tail;  // 큐의 뒤 (enqueue 위치)
    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(E value) {
        offer(value);
    }

    // 큐에 데이터 추가 (enqueue)
    public void offer(E value) {
        Node<E> newNode = new Node<>(value);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.before = newNode;  // before를 next처럼 사용
            tail = newNode;
        }
        size++;
    }

    // 큐에서 데이터 제거 및 반환 (dequeue)
    public E poll() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        E value = head.value;
        head = head.before;
        if (head == null) tail = null;
        size--;
        return value;
    }

    // 큐의 앞쪽 값만 확인
    public E peek() {
        if (isEmpty()) return null;
        return head.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
