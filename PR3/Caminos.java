package PR3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Caminos {
	
	private Grafo<?> grafo;
	private HashMap<Integer,String> colores;
	
	public Caminos(Grafo<?> grafo) {
		super();
		this.grafo = grafo;
		this.colores = new HashMap<>();
	}
	
	public ArrayList<ArrayList<Integer>> getAllCaminos(int origen, int destino){ 
		if(grafo.contieneVertice(origen) && grafo.contieneVertice(destino)) {// antes de buscar encaminos en el grafo me fijo primero si existen estos vertices en el grafo
			Iterator<Integer> vertices = grafo.obtenerVertices();		//obtengo todos los vertices del grafo
			
			while(vertices.hasNext()) {				//itero cada vertice y entro si existe un siguiente
				int vertice = vertices.next(); 		//lo guardo en una variable
				colores.put(vertice, "blanco");		//agrego el vertice en elhasmap de colores y le pongo como valor "blanco", lo pinto de blanco
			}
			
			return getAllCaminos_visit(origen,destino); //llamo al metodo recursivo y retorno su resultado
		}
		return new ArrayList<>();	//si en el grafo no existen los vertices entonces devuelvo un array vacio
	}
	
	private ArrayList<ArrayList<Integer>> getAllCaminos_visit(int vertice, int destino){
		ArrayList<ArrayList<Integer>> resultadoTotal = new ArrayList<>(); //Creo el array de arrays que voy a retornar
		
		if(vertice == destino) {		//Situacion de corte porque hace que se corte la recursion - pregunto si el vertice en el que estoy es el destino que busco
			ArrayList<Integer> resultado = new ArrayList<>(); 	//si entre, entonces creo un array de integers
			resultado.add(vertice);								//agrego el vertice actual osea mi destino
			
//			System.out.println("Resultado: "+ resultado);
			
			resultadoTotal.add(resultado);						//y al array resultadoTotal le agrego el array con mi vertice destino
			
//			System.out.println("Resultado total: "+ resultadoTotal);
			
			return resultadoTotal;								//retorno lo que agregue al resultadoTotal
			
			
		}else {										//si en cambio todavia no llegue a mi vertice destino
			
			colores.put(vertice, "amarillo");				//pinto de amarillo el vertice actual para empezar a recorrer un camino
			
//			System.out.println("Vertice amarillo: "+ vertice);
			Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(vertice); //pido un iterador de vertices adyacentes de mi vertice actual
			
			while(adyacentes.hasNext()) {					//itero los adyacentes mientras exista un siguiente
				int adyacente = adyacentes.next();			//guardo el adyacente en una variable
				
				
				
				if(colores.get(adyacente).equals("blanco")) {	//pregunto si el adyacente en el que estoy esta blanco, 
														//si esta amarillo es porque me encontre con un vertice que ya forma parte de mi camino actual
						
//					System.out.println("Adyacente con que el llamo a la recursion: "+ adyacente);
					ArrayList<ArrayList<Integer>> todosLosSubCaminos= getAllCaminos_visit(adyacente,destino);  //LLama recursivamente con el adyacente que estoy recorriendo y el destino al que quiero llegar
																				//y guardo el resultado un array de array 
					
//					System.out.println("Subcaminos: "+ todosLosSubCaminos);		
					for(ArrayList<Integer> subCamino : todosLosSubCaminos){		//si entro es porque tengo subcaminos para agregar
						
//						System.out.println("Subcamino array:" + subCamino);	
						subCamino.add(0, vertice); 								//entonces agrego el vertice actual en el que estoy 
//						System.out.println("Agrego " +vertice + " en la posicion cero del subcamino");
						resultadoTotal.add(subCamino);							// y despues el subcamino que encontro desde sus adyacentes
						
//						System.out.println("Al resultadoTotal agrego mi subCamino " + subCamino);
//						System.out.println("ResultadoTotal queda: " + resultadoTotal);			
						
						//voy a iterar el for hasta agregar todos los caminos a destino que obtuvo desde algun adyacente al vertice actual
					}
				}
			}
			colores.put(vertice, "blanco"); // si salio del while porque ya no tiene mas adyacentes para recorrer entonces pinto de blanco mi actual vertice en el que estoy
		}
//		System.out.println("Retorno resultadoTotal: " + resultadoTotal);
		return resultadoTotal;  	//retorno lo que haya agregado a resultadoTotal o en su defecto estara vacio
	}
	

}
