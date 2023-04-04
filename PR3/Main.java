package PR3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class Main {

	public static <T> void main(String[] args) {
		
		GrafoDir myGraph = new GrafoDir();
		
		
//		myGraph.agregarVertice(2);
//		myGraph.agregarVertice(3);
//		myGraph.agregarVertice(5);
//		myGraph.agregarVertice(7);
//		
//		myGraph.agregarArco(2, 3, null);
//		myGraph.agregarArco(3, 2, null);
//		myGraph.agregarArco(2, 5, null);
//		myGraph.agregarArco(5, 3, null);
//		myGraph.agregarArco(3, 7, null);
//		myGraph.agregarArco(7, 5, null);
//		
//		
//		Iterator<Integer> grafo = myGraph.obtenerVertices();
//		
//		System.out.print("Vertices: ");
//		while(grafo.hasNext()) {
//			System.out.print( grafo.next() + " - ");
//		}
//		
//		System.out.println(" ");
//		System.out.println(" ");
//		
//		System.out.print("Adyacentes del vertice 2: " );
//		Iterator<Integer> adyacentes = myGraph.obtenerAdyacentes(2);
//		while(adyacentes.hasNext()) {
//			System.out.print(adyacentes.next() + " - ");
//		}
//		
//		System.out.println(" ");
//		
//		System.out.print("Adyacentes del vertice 3: " );
//		Iterator<Integer> adyacentess = myGraph.obtenerAdyacentes(3);
//		while(adyacentess.hasNext()) {
//			System.out.print(adyacentess.next() + " - ");
//		}
//		
//		System.out.println(" ");
//		
//		System.out.print("Adyacentes del vertice 5: " );
//		Iterator<Integer> adyacentesss = myGraph.obtenerAdyacentes(5);
//		while(adyacentesss.hasNext()) {
//			System.out.print(adyacentesss.next() + " - ");
//		}
//		
//		System.out.println(" ");
//		
//		System.out.print("Adyacentes del vertice 7: " );
//		Iterator<Integer> adyacentessss = myGraph.obtenerAdyacentes(7);
//		while(adyacentessss.hasNext()) {
//			System.out.print(adyacentessss.next() + " - ");
//		}
//		
//		System.out.println(" ");
//		System.out.println(" ");
//		
//		System.out.println("Todos los arcos de mi grafo: " );
//		Iterator<Arco<T>> allArcos = myGraph.obtenerArcos();
//		while(allArcos.hasNext()) {
//			System.out.println( "- "+ allArcos.next() );
//		}
//		
//		System.out.println(" ");
//		System.out.println("DFS - Recorrido en Profundidad ");
//		
//		DepthFirstSearch dfs = new DepthFirstSearch(myGraph);
//		System.out.println("Existe ciclo: "+ dfs.hayCiclo());
//		
//		
//		System.out.println(" ");
//		System.out.println("BFS - Recorrido en Amplitud " );
//		
		myGraph.agregarVertice(1);
		myGraph.agregarVertice(2);
		myGraph.agregarVertice(3);
		myGraph.agregarVertice(4);
		myGraph.agregarVertice(5);
		myGraph.agregarVertice(6);
		myGraph.agregarVertice(7);
		myGraph.agregarVertice(10);
		
		
		myGraph.agregarArco(10, 1, null);
		myGraph.agregarArco(1, 2, null);
		myGraph.agregarArco(2, 4, null);
		myGraph.agregarArco(4, 6, null);
		myGraph.agregarArco(4, 1, null);
		myGraph.agregarArco(1, 3, null);
		myGraph.agregarArco(3, 5, null);
		myGraph.agregarArco(5, 4, null);
		myGraph.agregarArco(4, 7, null);
		
//		Iterator<Arco<T>> arcos= myGraph.obtenerArcos();
//		while(arcos.hasNext()) {
//			System.out.println("Arco:" + arcos.next() );
//		}
		
//		BreadthFirstSearch bfs = new BreadthFirstSearch(myGraph);
//		bfs.bfs();
//		System.out.println(bfs.getSalida());
		
		
		System.out.println(" ");
		System.out.println("DFS - Caminos ");
		
		Camino dfs = new Camino(myGraph,1,6);
//		
		System.out.println("Camino (el primero que encontro): "+ dfs.getCamino());
		System.out.println("Camino más largo: "+ dfs.getCaminoMasLargo());
		System.out.println("Camino más corto: "+ dfs.getCaminoMasCorto());
		
		Caminos dfsC = new Caminos(myGraph);
		System.out.println("Todos los caminos: "+ dfsC.getAllCaminos(1,6));
		
	}
}