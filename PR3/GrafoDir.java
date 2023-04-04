package PR3;

import java.util.Iterator;

import PR3.Arco;
import PR3.IteratorAdyacentes;

import java.util.ArrayList;
import java.util.HashMap;

public class GrafoDir<T> implements Grafo<T>{
	
	//HashMap utiliza clave - valor, en este caso la clave el Integer y el valor son la lista de arcos que tiene
	//se busca por clave 
	
	private HashMap<Integer, ArrayList<Arco<T>>> vertices; //CLAVE: Integer, VALOR: ArrayList<Arco<T>>
	private int cantArcos;

	public GrafoDir() {
		super();
		this.vertices = new HashMap<Integer, ArrayList<Arco<T>>>();
		this.cantArcos = 0;
	}
	
	//----> DATO:  vertices.get(verticeId) me permite acceder a los arcos de "verticeId"
	
	//----> DATO: vertices.keySet() obtiene todas las claves(solo clave osea devuelve los Integers) del grafo, lo puedo iterar con un for o un iterator agregandole ".iterator();"
	
	
	@Override
	public void agregarVertice(int verticeId) {   
		if(!contieneVertice(verticeId)) {			//se fija si ya existe ese vertice en el grafo
			vertices.put(verticeId, new ArrayList<Arco<T>>());  // si NO existe agrega a vertices el "verticeId" e inicializa su array de arcos en cero
		}
	}

	@Override
	public void borrarVertice(int verticeId) {
		if(contieneVertice(verticeId)) {

			borrarArcoDestino(verticeId);  //primero, llamo a que se eliminen los arcos que tienen como destino verticeId
			int cantArcosAdy = vertices.get(verticeId).size(); //despues, cuento la cant de arcos adyacentes
			
			vertices.remove(verticeId); // elimino la clave y sus arcos adyacentes
			cantArcos -= cantArcosAdy; // los descuento del total de arcos
		}
	}
	
	//metodo que elimina los arcos que tienen como destino X clave
	public void borrarArcoDestino(int verticeId) { 
		for(int vertice: vertices.keySet()) { //recorre todas las claves
			
			Iterator<Arco<T>> arcos = vertices.get(vertice).iterator(); //por cada clave(integer), pide los valores(arcos) que tiene y los devuvle en iterador
			while(arcos.hasNext() ) { 			//itero los arcos con un while si existe un arco siguiente 
					
				Arco<T> arco = arcos.next();	//creo un arco y guardo el siguiente arco
				if(arco.getVerticeDestino() == verticeId) {  //pregunto si este arco tiene como destino el vertice a eliminar
					arcos.remove();			//si tiene como destino esa clave entonces 
					
					cantArcos--;
				}
			}
		}
	}


	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) { //recibe verticeOrigen, verticeDestino y la etiqueta
		if(contieneVertice(verticeId1) && contieneVertice(verticeId1)){ //si existen ambos vertices

			if(!existeArco(verticeId1,verticeId2)) {					// y no existe un arco que tengo como origen "verticeId1" y como destino "veticeId2"
																	
				Arco arco = new Arco(verticeId1,verticeId2,etiqueta); //creo un arco con los valores que me pasaron
				vertices.get(verticeId1).add(arco);  //agarro del hasMap(vertices) el vertice origen(verticeId1) y le agrego el arco que cree anteriormente 
				cantArcos++; // a mi cuenta de arcos le agrego 1
			}
		}
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		
		if(existeArco(verticeId1,verticeId2)) {
			//----------OPCION CORTA-------------- ha checkear que funcione
			
//				Arco<T> arcoDelete = obtenerArco(verticeId1,verticeId2); //la idea era pedir el arco que quiero borrar
//				vertices.remove(verticeId1, arcoDelete); // y borralo.  Pero creo que es mas un invento
				
			//------------OPCION LARGA-----------------  
			//Esta opcion no esta tan mal, la logica va bien pero necesito de un indice si o si para borrar el arco que quiero
				
//			Iterator<Arco<T>> arcosId = obtenerArcos(verticeId1);
//			
//			while(arcosId.hasNext()) {
//				if(arcosId.next().getVerticeDestino() == verticeId2) {
//					arcosId.remove();        //algo asi deberia estar bien
//					cantArcos--;
//				}
//			}
			
			//-------------OPCION ANTIGUA ----------- It's OK 
			
			ArrayList< Arco<T> >arcos= vertices.get(verticeId1);//me traigo todos los arcos que tiene el vertice origen "verticeId1"
															 	// dato no menor, lo traigo en un array asi puedo saber el index. Con un iterador no funcionaria
																
			
				for(int i=0; i<arcos.size();i++) {//recorro el array de arcos del vertice origen con un for clasico para conservar el index 
					if(arcos.get(i).getVerticeDestino()==verticeId2) {//por cada arco pregunto si el valor del verticeDestino es igual a verticeId2
						arcos.remove(i);//entro y elimino ese arco especificandole el index en el que se encuentra en arraylist de arcos
						cantArcos--;//decremento la cantidad de arcos despues de eliminar
					}
				}
		}
	}

	@Override
	public boolean contieneVertice(int verticeId) { //retorna true si existe el vertice y false si no existe
		return vertices.containsKey(verticeId);  //utilizo el " .containsKey " propio de HashMap para saver si mi grafo tiene el vertice "verticeId"
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) { 
		if(contieneVertice(verticeId1) && contieneVertice(verticeId2)) { // si existen ambos vertices entro
			
			Iterator<Arco<T>> arcosVer = obtenerArcos(verticeId1);  //pido un iterador de los arcos que tenga el vertice destino "verticeId1"
																//importante especificar que tipo de iterador es 
			
			while(arcosVer.hasNext()) { 		//itero los arcos del vertice origen, si existe un siguien entro
				if(arcosVer.next().getVerticeDestino() == verticeId2) { // al arco siguiente le pido su vertice destino, y si es igual al vertice destino "verticeId2"
					return true; 						//retorno true directamente y corto la iteracion de los arcos porque no voy a encontrar otro arco con el mismo destino
				}
			}
		}
		return false; // si sali de recorrer todos los arcos del vertice origen y nunca encontre el vertice destino que buscaba retorno false
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {

		if(contieneVertice(verticeId1) && contieneVertice(verticeId2)) { //si existen los dos vertices en el HashMap
			if(existeArco(verticeId1, verticeId2)) {				// y si existe un arco con origen ""verticeId1" y destino "verticeId2" 
				Iterator<Arco<T>> arcos = obtenerArcos(verticeId1); // obtengo un iterador todos los arcos del vertice origen 

				while(arcos.hasNext()) {						//itero los arco con un while, si existe un siguiente
					if(arcos.next().getVerticeDestino() == verticeId2) { //si el arco siguiente tiene como destino el vertice buscado "verticeId2"
						return arcos.next();  //retorno el arco, ya encontre el arco que buscaba
					}
				}
			}
		}
		return null;  // si salgo de iterar los arcos del vertice origen y nunca encontre el vertice destino que buscaba retorno null
	}

	@Override
	public int cantidadVertices() {
		return vertices.size(); //retorno la cantidad de claves del HasMap
	}

	@Override
	public int cantidadArcos() {
		return this.cantArcos; //retorno la variable 
	}

	@Override
	public Iterator<Integer> obtenerVertices() { 
		return vertices.keySet().iterator();  //Retorno un iterador de todas las claves(integers) que tiene el HashMap vertices
											//.keySet me permite acceder a los valores de las claves (integers)
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) { //iterador valores de vertices adyacentes
		
		Iterator<Arco<T>> arcosAdy = vertices.get(verticeId).iterator(); // de los vertices agarro el verticeId y pido el iterador de valores en este caso arcos 

		return new IteratorAdyacentes<T>(arcosAdy); //creo la clase "IteradorAdyacentes" que cuando haces .next() devuelve el valor del adyacente
													//esta clase implementa Iterator y al contructor le paso el Iterator de arcos para que funcione
 	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		Iterator<Integer> verticesGraph = obtenerVertices(); 	//pide un iterador de vertices
		ArrayList<Arco<T>> arcosList = new ArrayList<>();	//inicializa un array de arcos 
		
		while(verticesGraph.hasNext()) {			//por cada vertice del grafo pregunta si tiene un sig
			Integer vertice= verticesGraph.next();	// si tiene un sig entra y guarda en una variable el valor de vertice
			Iterator<Arco<T>> arcos= obtenerArcos(vertice); //pide todos los arcos que tenga el vertices en el que estoy
			
			arcos.forEachRemaining(arcosList::add);  //hace magia, itera "arcos" y los agrega a arcosList
		}
		return arcosList.iterator(); //por ultimo, devuelvo un iterador de la lista de arcos.
									//Transforme una lista de arcos en un iterador
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		if(contieneVertice(verticeId)) {			//si contiene el vertice 
			return vertices.get(verticeId).iterator(); // retorno un iterdor de arcos. A vertices le pido el vertice "verticeId" y pido el iterador de arcos.
		}else {
			return null;	//sino, retorno null
		}
	}



}
