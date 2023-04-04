package PR1;

public class Pila  { 
	
	//Pila no es una lista y tampoco va a usar todos los metodos de MyList por 
	//eso la usa como atributo
	
	private MyList lista;
	
	public Pila() {
		this.lista = new MyList();
	}
	
	
	//Agrega elelementos
	public void push(Integer n) {
		lista.insertFront(n);
	}
	
	
	
	//Elimina el ultimo elemento agregado a la pila y retorna su valor
	public Integer pop() {
		return lista.extractFront();
	}
	
	
	//Obtiene el ultimo elemento agregado
	public Integer top() {
		return lista.get(0);
	}
	
	//Revierte el orden de la pila
	public void reverse() {
		MyList reverseList = new MyList();
		
		while(!this.lista.isEmpty()) {
			Integer info = lista.extractFront();
			reverseList.insertFront(info);
			
		}
		this.lista = reverseList;
	}


	@Override
	public String toString() {
		return "Pila [lista=" + lista + "]";
	}
	
	

}
