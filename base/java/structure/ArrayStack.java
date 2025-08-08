package base.java.structure;

@SuppressWarnings("unchecked")
public class ArrayStack<E> {
	Node<E>[] arr;
	int size;
	int MAX_SIZE;
	int end;
	
	final static int INITIAL_SIZE = 10; // 초기 배열의 사이즈
	final static double POWER = 1.5; // 배열이 커지는 속도
	
	ArrayStack() {
		size = INITIAL_SIZE;
		arr = (Node<E>[]) new Node[size];
		MAX_SIZE = size;
		end = 0;
	}
	
	ArrayStack(int initSize) {
		size = initSize;
		arr = (Node<E>[]) new Node[size];
		MAX_SIZE = size;
		end = 0;
	}
	
	private void __copy() {
		MAX_SIZE = (int)(size * POWER);
		Node<E>[] newArr = (Node<E>[]) new Node[size];
		
		for (int i = 0; i < size; i++) {
			newArr[i] = arr[i];
		}
		arr = newArr;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}
	
	public E pop() {
		if (isEmpty())
			throw new NullPointerException();
		return arr[--end].value;
	}
	
	public void push(E element) {
		Node<E> node = new Node<>(element);
		if (size + 1 == MAX_SIZE) {
			__copy();
		}
		arr[end++] = node;
	}
	
	public E peek() {
		return arr[end].value;
	}
}