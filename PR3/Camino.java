package PR3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Camino { 
	
	private Grafo<?> grafo;
	private HashMap<Integer,String> colores;
	private int origen;
	private int destino;
	
	public Camino(Grafo<?> grafo, Integer origen, Integer destino) {
		super();
		this.grafo = grafo;
		this.destino = destino;
		this.origen = origen;
		this.colores = new HashMap<>();

		
	}
	
	//RETORNA UN CAMINO - El que primero encuentra  ------------------------------------------------
	
	public ArrayList<Integer> getCamino() {
		ArrayList<Integer> resultado = new ArrayList<>();  

		if(grafo.contieneVertice(this.origen) && grafo.contieneVertice(this.destino)) {
			Iterator<Integer> vertices= this.grafo.obtenerVertices();

			while(vertices.hasNext()) {
				int vertice = vertices.next();
				colores.put(vertice, "blanco");
			}

//			System.out.println("Llamo al getCamino: "+ origen);
			ArrayList<Integer> camino = getCamino(origen);
			resultado = camino;
		}
		return resultado;
	}
	
	
	private ArrayList<Integer> getCamino(Integer vertice){ //vertice actual, en principio es el vertice origen
		
		ArrayList<Integer> resultado = new ArrayList<>();  //inicializo el array resultante 
		
		if(vertice == this.destino) { 		//pregunto si el vertice en el que estoy es el destino
			resultado.add(vertice); 		// si vertice es mi destino, lo encontre, entonces lo agrego a resultado
			
//			System.out.println("Agregue a resultado mi destino: "+ vertice);
			return resultado;				// y retorno el resultado.
			
			
		}else {								//si el vertice en el que estoy no es mi destino
			
			colores.put(vertice, "amarillo");	//lo pinto de amarillo en el hashmap de colores
//			System.out.println("Pinte de amarillo: "+ vertice);
			Iterator<Integer> verticesAdy = grafo.obtenerAdyacentes(vertice);	//Busco el iterador de vertices adyacentes al vertice actual

			while(verticesAdy.hasNext() && resultado.isEmpty()) { 		//empiezo a iterar y entro si existe un siguiente adyacente y si todavia no llegue a destino
				int adyacente = verticesAdy.next();		//guardo el adyacente en una variable
				
//				System.out.println("Adyacente: " + adyacente);
				
				if(colores.get(adyacente).equals("blanco")) { //pregunto si el adyacente es blanco, si es asi es porque no forma 
															//parte de mi camino actual, "es una rama que estoy aun recorriendo", si es amarillo y entro entraria en un ciclo
					
					ArrayList<Integer> camino = getCamino(adyacente); //creo el array camino que es mi camino de vertices amarillos por los que voy pasando
																	//llama recursivamente al metodo
//					System.out.println("Camino actual: " + camino);
					if (camino != null) {				//si mi camino contine algun vertice, es decir si encontre mi destino
						
						resultado.add(vertice); 			//agrego mi vertice actual - con el que primero llame (origen)
						resultado.addAll(camino);			//agrego el camino de vertices desde mi que encontre en la recursividad
					}
				}
			}
//			this.colores.put(vertice, "blanco"); //vuelvo a pintar de blanco el vertice actual antes de retornar 
												//para que pueda ser usado este vertice en otro camino
			
		}
		
		return resultado; //aca retorno cuando 
	}
	
	
	//RETORNA EL CAMINO MAS LARGO ------------------------------------------------------------
	
	public ArrayList<Integer> getCaminoMasLargo() {
		ArrayList<Integer> resultado = new ArrayList<>();

		if(grafo.contieneVertice(this.origen) && grafo.contieneVertice(this.destino)) { //controla que existan origen y destino en el grafo
			Iterator<Integer> vertices= this.grafo.obtenerVertices();		

			while(vertices.hasNext()) {
				int vertice = vertices.next();
				colores.put(vertice, "blanco");
			}

//			System.out.println("Llamo al getCamino: "+ origen);
			resultado = getCaminoMasLargo(origen);
		}
		
		return resultado;
	}
	
	
	private ArrayList<Integer> getCaminoMasLargo(Integer vertice){ 
		ArrayList<Integer> resultado = new ArrayList<>();	//inicializo el array a retornar

		if(vertice == this.destino) {		//Situacion de corte - si mi vertices actual es mi destino 
			resultado.add(vertice);			//agrego mi vertice destino al resultado y lo retorno
			return resultado;
		}else {
			colores.put(vertice, "amarillo");   //si no estoy en mi destino, lo pinto de amarillo
//			System.out.println("Amarillo: " + vertice);
			Iterator<Integer> verticesAdy = grafo.obtenerAdyacentes(vertice); //pido un iterador de vertices adyacentes a vertice actual

			while(verticesAdy.hasNext()) {		//itero los adyacentes 
				int adyacente = verticesAdy.next();		//guardo en una variable el adyacente que voy a empezar a explorar
//				System.out.println("Adyacente: " + adyacente);
				if(colores.get(adyacente).equals("blanco")) { 		//si el adyacente es blanco, entonces aun no forma parte del camino
					ArrayList<Integer> camino = getCaminoMasLargo(adyacente);  //entonces puedo explorar los adyacentes de adyacente llamando recursivamente
					
					camino.add(0,vertice);		//IMPORTANTE! - agrego primero el vertice actual y despues
					
					//Ahora que ya agregue en primera pos el vertice actual y despues tengo el camino que encontro o no encontro entonces tendria como unico elemento el vertice actual
					
					if((camino.size() >1) && (camino.size() > resultado.size()) || resultado.isEmpty() ) { //si llegue aca es porque termine de explorar 
					//pregunto si mi camino al menos tiene un vertice, si el camino que encontre es mayor al array resultado o en su defecto si resultado todavia esta vacio va aentrar igual
						
						
						resultado = camino;		//todo los vertices que me hicieorn llegar a destino inclusive el actual
					}
				}
			}							//voy a iterar todos los adyacentes para terminar quedandome con el mas largo
			this.colores.put(vertice, "blanco");  //cuando salgo de iterar los adyacentes de vertice actual, lo pinto de blanco por si en otro camino tiene que pasarse nuevamente por el vertice actual
		}
		return resultado;	//cuando explore todos los adyacentes de origen retorno lo que me haya quedado en resultado
	}
	
	
	//RETORNA EL CAMINO MAS CORTO ------------------------------------------------------------
	
	public ArrayList<Integer> getCaminoMasCorto() {
		ArrayList<Integer> resultado = new ArrayList<>();

		if(grafo.contieneVertice(this.origen) && grafo.contieneVertice(this.destino)) {
			Iterator<Integer> vertices= this.grafo.obtenerVertices();

			while(vertices.hasNext()) {
				int vertice = vertices.next();
				colores.put(vertice, "blanco");
			}

//			System.out.println("Llamo al getCamino: "+ origen);
			resultado = getCaminoMasCorto(origen);
		}
		
		return resultado;
	}
	
	
	private ArrayList<Integer> getCaminoMasCorto(Integer vertice){
		ArrayList<Integer> resultado = new ArrayList<>();

		if(vertice == this.destino) {
			resultado.add(vertice);
			return resultado;
		}else {
			colores.put(vertice, "amarillo");
//			System.out.println("Amarillo: " + vertice);
			Iterator<Integer> verticesAdy = grafo.obtenerAdyacentes(vertice);

			while(verticesAdy.hasNext()) {
				int adyacente = verticesAdy.next();
//				System.out.println("Adyacente: " + adyacente);
				if(colores.get(adyacente).equals("blanco")) {
					ArrayList<Integer> camino = getCaminoMasCorto(adyacente);
					camino.add(0,vertice);
					
					if((camino.size() >1) && (camino.size() < resultado.size()) || resultado.isEmpty() ) {
						resultado = camino;
					}
				}
			}
			this.colores.put(vertice, "blanco");
		}
		return resultado;
	}
	
	

}
