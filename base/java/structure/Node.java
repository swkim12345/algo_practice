package base.java.structure;

public class Node<E> {
	public E value;
	public Node<E> before; //배열방식에서는 필요 없음.
	
	Node() {
		this.value = null;
	}

	Node(E value) {
		this.value = value;
	}
}