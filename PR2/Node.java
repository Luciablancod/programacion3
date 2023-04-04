package PR2;

public class Node {
	
	// Cada nodo tiene un valor y un nodo izquierdo y un derecho. Ademas
	//sabe si es hoja o no lo es.
	
	private Integer value;
	private Node left;
	private Node right;
	
	public Node(Integer value) {
		this.value = value;
		this.left = null;
		this.right =null;
	}


	public Integer getValue() {
		return value;
	}


	public void setValue(Integer value) {
		this.value = value;
	}


	public Node getLeft() {
		return left;
	}


	public void setLeft(Node left) {
		this.left = left;
	}


	public Node getRight() {
		return right;
	}


	public void setRight(Node right) {
		this.right = right;
	}
	

	public boolean isLeave() {
		if(this.left == null && this.right== null) {
			return true;
		}
		return false;
	}
}
