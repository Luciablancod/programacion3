package PR3;

import java.util.HashMap;
import java.util.Iterator;


public class DepthFirstSearch { //DFS - Recorrido en profundidad
		//Recursivamente visita todos los adyacentes no visitados de un vértice
	
	
	private Grafo<?> grafo;  //grafo
	private HashMap<Integer,String> colores; //CLAVE: vertice, VALOR: el color(blanco, amarillo o negro)
	private int tiempo;	
	private HashMap<Integer,Integer> descubrimiento; //CLAVE: vertice, VALOR: tiempo de cuando lo pinto amarillo
	private HashMap<Integer,Integer> finalizacion; //CLAVE: vertice, VALOR: tiempo de cuando lo pinto de negro
	
	
	public DepthFirstSearch(Grafo<?> grafo) {
		super();
		this.grafo = grafo;
		this.colores = new HashMap<>(); //
		this.tiempo = 0;
		this.descubrimiento = new HashMap<>();
		this.finalizacion = new HashMap<>();
	}
	
	
//------------DFS - basico
	public void dfs() {
		Iterator<Integer> vertices = this.grafo.obtenerVertices(); //obtiene un iterador de todos los vertices del grafo a recorrer
		while(vertices.hasNext()) { 			//itero con un while los vertices, si hay un siguiente entro
			int vertice = vertices.next(); 		//guardo en una variable el vertice(clave)
			colores.put(vertice, "blanco");    //agrego al HashMap "colores" al vertice como clave y como valor "blanco"
		}										//eso se repite con todos los vertices
		
		this.tiempo = 0;		//inicializo el tiempo en cero
		
												//vuelve a armar el iterador para recorrerlos y ver cual sigue en blanco y no fue pintado
		vertices = this.grafo.obtenerVertices(); //como si fuera actualizar ?
		
		while(vertices.hasNext()) {				//vuelvo recorrer los vertices
			int vertice = vertices.next();
														//cuando es String uso "equals" no el "=="
			if(colores.get(vertice).equals("blanco")) { //al HashMap de vertices pintados, le pido el vertice en el que estoy y le pregunto si esta pintado de blanco
				dfs_visit(vertice);						//si es blanco el valor del vertice, entonces llamo al dfs_visit y le envio como parametro el vertice en el qe estoy
			}
		}
	}
	
	public void dfs_visit(Integer verticeId) {  	
		colores.put(verticeId, "amarillo");			//lo primero que hace es, agregar al hashmap de colores con el valor amarillo
		tiempo++;									//incrementa el tiempo en 1
		descubrimiento.put(verticeId, tiempo);		//agrega al hashmap "descubrimiento" el vertice y el tiempo actual
		
		Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(verticeId); //obtiene un iterador de vertices adyacentes al vertice que me llego de parametro(en el que estoy parada)
		
		while(adyacentes.hasNext()) {	//recorro los vertices adyacentes si existe un sig
			
			int adyacente = adyacentes.next();		//guardo el siguiente en una variable
			
			if(colores.get(adyacente).equals("blanco")) {	//me fijo si el vertice adyacente en el hashmap "colores" esta blanco
				 dfs_visit(adyacente); 	// si el vertice es blanco entonces es porque todavia no lo visite y llamo recursivamente al dfs_visit para viitarlo			
			}
		}								
												//si ya recorrio todos sus adyacentes 
		colores.put(verticeId, "negro");		//entonces lo pinto de negro
		tiempo++; 								//incremento en 1 el tiempo
		finalizacion.put(verticeId, tiempo);	//agrego ese vertice adyacente al hashmap de finalizados con el tiempo actual
		
	}
	

//-------------DFS para encontrar ciclos	
	
	public boolean hayCiclo() { //va a existir un ciclo cuando encuentre un "arco back"
		Iterator<Integer> vertices = grafo.obtenerVertices();
		
		while(vertices.hasNext()) {
			int vertice = vertices.next();
			colores.put(vertice, "blanco");
		}
		
		this.tiempo = 0;
		vertices = grafo.obtenerVertices();
		
		boolean existe = false;
		
		while(vertices.hasNext() && !existe) {
			int vertice = vertices.next();
			
			if(colores.get(vertice).equals("blanco")) {
				existe = this.dfs_visitCiclo(vertice);
			}
		}
		
		return existe;
		
	}
	
	public boolean dfs_visitCiclo(Integer verticeId) {  	
		colores.put(verticeId, "amarillo");			//lo primero que hace es, agregar al hashmap de colores con el valor amarillo
		tiempo++;									//incrementa el tiempo en 1
		descubrimiento.put(verticeId, tiempo);		//agrega al hashmap "descubrimiento" el vertice y el tiempo actual
		
		Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(verticeId); //obtiene un iterador de vertices adyacentes al vertice que me llego de parametro(en el que estoy parada)
		boolean encontre= false;					//EJ3 = inicializo la variable que voy a retornar en false
		
		while(adyacentes.hasNext() && !encontre) {	//recorro los vertices adyacentes si existe un sig y EJ3= si todavia no me encontre con otro vertice amarillo
			
			int adyacente = adyacentes.next();		//guardo el siguiente en una variable
			
			if(colores.get(adyacente).equals("blanco")) {	//me fijo si el vertice adyacente en el hashmap "colores" esta blanco
				encontre = dfs_visitCiclo(adyacente); 	// si el vertice es blanco entonces es porque todavia no lo visite y llamo recursivamente al dfs_visit para viitarlo
													//DATO = no solo llamo recursivamente, sino que tengo que guardar el resultado de esa recursividad
													// por lo tanto lo guardo en la variable "encontre" que es la que se retorna
			
			}else if(colores.get(adyacente).equals("amarillo")) {	//EJ3= este "else if" me sirve para encontrar si existe un ciclo pero podria no estar y la variable booleana tambien. 
																//Entonces si seria un clasico DFS, ahora este metodo solo sirve para detectarsi existe o no ciclo
															// EJ3= si el adyacente no es blanco, entonces pregunto si es amarillo
				encontre = true;							//si entré es porque el vertice adyacente es amarillo, entonces es porque ya lo habia vistado, 
															//entonces, significa que encontre un "arco back" es decir hay un ciclo y "encontre" se vuelve true
			}
		}										//EJ3= si al recorrer los vertices me encontre con un ciclo, no continua al siguiente adyacente
		
												//si ya recorrio todos sus adyacentes 
		colores.put(verticeId, "negro");		//entonces lo pinto de negro
		tiempo++; 								//incremento en 1 el tiempo
		finalizacion.put(verticeId, tiempo);	//agrego ese vertice adyacente al hashmap de finalizados con el tiempo actual
		
		return encontre;						//y retorno el valor de si el vertice es amarillo
	}
	

}
