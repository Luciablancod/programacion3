package PR3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BreadthFirstSearch { //BFS - Recorrido en Amplitud
	//Se realiza un recorrido por capas del grafo, Es una generalización del recorrido por niveles de un árbol

	private Grafo<?> grafo; 
	private HashMap<Integer, Boolean> visitados; //CLAVE: vertice, VALOR: true o false 
	private ArrayList<Integer> fila;	 
	private ArrayList<Integer> salida;
	
	public BreadthFirstSearch(Grafo<?> grafo) {
		super();
		this.grafo = grafo;
		this.visitados = new HashMap<>();
		this.fila = new ArrayList<>();
		this.salida= new ArrayList<>();
	}
	
	public void bfs() {
		//inicializo vacios los arrays
		fila = new ArrayList<>();
		salida = new ArrayList<>();

		Iterator<Integer> vertices = this.grafo.obtenerVertices(); //pido todos los vertices que tiene el grafo

		while(vertices.hasNext()) { //los itero y entro si existe un siguiente vertices
			int vertice = vertices.next(); 		//guardo el vertice en una variable
			visitados.put(vertice, false);		//agrego al HasMap de visitados el vertice y le seteo false
		}
		vertices = this.grafo.obtenerVertices(); //vuelvo a pedir el iterador SI o SI 
												//es mucho muy importante que se vuelvan a llamar el iterador de vertice
		
		while(vertices.hasNext()) {				//vuelvo a iterar los vertices
			int vertice = vertices.next();
			if(!this.visitados.get(vertice)) {	//si el vertice todavia no fue vistado
				bfs_visit(vertice);				//llamo al bfs_visit y le envio como parametro el vertice en el que estoy
			}
		}
	}

	private void bfs_visit(int origen){		
		
		this.visitados.put(origen, true);	//Agrego como visitado el vertice con true
		this.fila.add(origen);				//agrego el vertice a la fila
		this.salida.add(origen);			//y tambien lo agrego a la salida que es resultado final, donde voy almacenando los visitados en orden 
		
		while(!fila.isEmpty()) {		//si la fila no esta vacia, entro
			
			int x = fila.get(0);		//guardo el vertice en la posicion cero en una variable
			this.fila.remove(0);		//y depsues la borro del array fila
			
//			int x = fila.remove(0);		//tambien lo podria guardar y borrar a la vez 
			
			Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(x); //pido un iterador de vertices adyacente a X
			
			while(adyacentes.hasNext()) { //si existe un vertice siguiente, entro
				int y = adyacentes.next(); 		//guardo el vertice en un variable Y
				if(!this.visitados.get(y)) { 	//si el adyacente Y no fue visitado
					
					this.visitados.put(y, true); //lo seteo como visitado 
					this.fila.add(y);			//lo agrego a la fila
					this.salida.add(y);			//al resultado
				}
			}								//y asi voy iterando por todos los adyacentes del vertice que me pasaron por parametro
		}									//cuando termino el BFS() continua con otro vertice que todavia esta seteado FALSE en vistados
	}										

	public ArrayList<Integer> getSalida() {
		return salida;
	}

}
