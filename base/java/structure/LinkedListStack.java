package base.java.structure;

public class LinkedListStack<E>  {
	Node<E> start;
	Node<E> end;
	int size;

	LinkedListStack() {
		size = 0;
		start = new Node<E>(); 
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public E pop() throws NullPointerException {
		if (isEmpty())
			throw new NullPointerException();
		E value = end.value;
		end = end.before;
		size--;
		return value;
	}
	
	public void push(E element) {
		Node<E> node = new Node<>(element);
		node.before = end;
		end = node;
	}
	
	public E peek() {
		return end.value;
	}
}