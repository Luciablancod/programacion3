package PR3;

import java.util.Iterator;


//Clase que implementa Iterator de tipo Integer. Especificar que tipo de valor itera SIEMPRE
public class IteratorAdyacentes<T> implements Iterator<Integer> { 
	
	private Iterator<Arco<T>> it;  //tiene como variable un Iterator de arcos
	
	public IteratorAdyacentes(Iterator<Arco<T>> it) {
		super();
		this.it = it; //al crearse la clase recibe un iterador de arcos
	}
	@Override
	public boolean hasNext() {  //responde si existe un siguiente elemento
		return it.hasNext();
	}

	@Override
	public Integer next() {  //devuelve el valor del vertice adyacente.
		return it.next().getVerticeDestino();
	}

}
