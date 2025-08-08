package base.java.structure;

// 원형 큐 구현
@SuppressWarnings("unchecked")
public class ArrayQueue<E> {
    private E[] data;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public ArrayQueue(int initialCapacity) {
        this.capacity = initialCapacity;
        this.data = (E[]) new Object[initialCapacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public void offer(E value) {
        if (isFull()) {
            _copy(); // 자동으로 2배 확장
        }
        data[rear] = value;
        rear = (rear + 1) % capacity;
        size++;
    }

    public E poll() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        E value = data[front];
        data[front] = null; // GC 도움
        front = (front + 1) % capacity;
        size--;
        return value;
    }

    public E peek() {
        if (isEmpty()) return null;
        return data[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private boolean isFull() {
        return size == capacity;
    }

    // ⭐ 내부 배열을 두 배로 복사하는 메서드
    private void _copy() {
        int newCapacity = capacity * 2;
        E[] newData = (E[]) new Object[newCapacity];

        // 기존 요소를 순서대로 복사
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % capacity];
        }

        this.data = newData;
        this.front = 0;
        this.rear = size;
        this.capacity = newCapacity;
    }
}