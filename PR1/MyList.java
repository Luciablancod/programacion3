package PR1;

import java.util.Iterator;


public class MyList implements Iterable<Integer>{  //List es iterable de integers
	
	private Node first;
	private Node last;
	private Integer size;
	
	public MyList() {
		this.first = null;
		this.last = null;
		this.size = 0;
	}
	
	//Intersar un nodo adelante en la lista
	public void insertFront(Integer n) {
		Node tmp = new Node(n,null);//creo el nodo 
		 if(this.first != null) {
				tmp.setNext(first); // al temporal le setteo como siguiente first, que puede o no ser null
				this.first = tmp;
				size++;
		 }else {
			 this.first = tmp;
		 }
		 
	}
	
	
	//Elimina el primer nodo de la lista y retorno el valor del nodo eliminado
	public Integer extractFront() {
		if (this.first == null) {
			return -1;
		}
			Node tmp = this.first;
			
			if(this.first.getNext() != null) {
				first = this.first.getNext();
				tmp.setNext(null);
				
			}else {
				first = null;
			}
			size--;
			return tmp.getInfo();
	}
	
	//Devuelve true o false si existen elementos en la lista
	public boolean isEmpty() {
		if(this.first == null) {
			return true;
		}
		return false;
	}
	
	
	//Devuelve el tamaño de la lista
	public Integer size() {
		return this.size;
	}

	@Override
	public String toString() {
		return "MyList [first=" + first + ", last=" + last + ", size=" + size + "]";
	}

	public Node getFirst() {
		return first;
	}

	public Integer get(int index) {
		if (index < size) {
			Node tmp = this.first;
			
			for(int i=0; i <size; i++) {
				tmp = tmp.getNext();
			}
			return tmp.getInfo();
		}
		return -1;
	}
	
	
	//Devuelve el indice de un elem dado si se encuentra en la lista, sino devuelve -1
	public int indexOf(Integer elem) {
	
		Node tmp = this.first;
		int index = 0;
		
		if(this.first.getInfo() == elem) {
		 return index;
		}else {
			while(tmp.getNext() != null){
			tmp = tmp.getNext();
			index++;
				if(tmp.getInfo() == elem) {
					return index;
				}
			}
		return -1;
		}
	}
	
	
	@Override
	public ClassIterator iterator() {
		return new ClassIterator(first);
	}
	
	public ClassIterator iteradorReverse(){// le dice al iterador que empieze por el el ultimo(last)
		return new ClassIterator(this.last);
	}
	
	


	
}
