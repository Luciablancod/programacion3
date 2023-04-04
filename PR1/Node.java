package PR1;

public class Node <T> {
	
	private Integer info ;
//	private Node previo;
	private Node next;
	
	public Node() {
		this.info = null;
		this.next = null;
//		this.previo = null;
	}
	
	public Node(Integer info,  Node next) {
		super();
		this.info = info;
//		this.previo = previo;
		this.next = next;
	}

	public Integer getInfo() {
		return info;
	}

	public void setInfo(Integer  info) {
		this.info = info;
	}


	public Node getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

//	public Node getPrevio() {
//	return previo;
//}
//
//public void setPrevio(Node<T> previo) {
//	this.previo = previo;
//}
	
	
	@Override
	public String toString() {
		return "Node [info=" + info + "]";
	}
	
	
	
	

}
