package PR1;

import java.util.Iterator;

public class ClassIterator implements Iterator<Integer> {
	private Node cursor;
	
	public ClassIterator (Node first) {
		cursor = first;
	}

	@Override
	public boolean hasNext() { //Devuelve true o false ¿hay un primer elemento?
		return this.cursor != null;
	}
	
	
	@Override
	public Integer next() { //devuelve un valor y avanza al siguiente
		Integer valor = this.cursor.getInfo(); //primero guarda el valor
		this.cursor = this.cursor.getNext();   //avanza al siguiente
		return valor;
	}
	
	
//	public Integer previous() {
//		Integer valor = this.cursor.getInfo();
//		this.cursor = this.cursor.getprevious();
//		return valor;
//	}
	
	
	public Integer valor() {
		return this.cursor.getInfo();
	}
	
	
	public void avanzar() {
		this.cursor = this.cursor.getNext();
	}
	
	
//	public void retrocer() {
//		this.cursor = this.cursor.getprevious();
//	}

}
