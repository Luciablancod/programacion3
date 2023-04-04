package PR2;

import java.util.ArrayList;

public class Tree {

	//Solo tiene como atributo el nodo raiz
	private Node root;

	public Tree() {
		this.root = null;
	}
	
	//El usuario llama al metodo add publico y le pasa el num que quiere 
	//agregar, si el arbol esta vacio crea un nodo con el valor N y lo 
	//declara raiz. De otra forma, llama al metodo add privado y le pasa
	//como parametro la raiz del arbol y el N a agregar
	
	public void add(Integer n) {
		if (root == null) {
			root = new Node(n);
		}else {
			this.add(this.root, n);
		}
	}
	
	
	//Primero pregunta si N es menor al nodo en el que estoy parado
	//si es verdadero pregunta si el nodo en el que estoy tiene izquierdo
	//si no tiene, creo un nuevo nodo y lo seteo como el nodo izq al que estoy
	//hace lo mismo pero para el nodo derecho en el que estoy
	
	private void add(Node actual, Integer n) {
		if(actual.getValue() > n ) {
			if(actual.getLeft() == null) {
				Node tmp = new Node(n);
				actual.setLeft(tmp);
			}else {
				this.add(actual.getLeft(), n);
			}
		}else if(actual.getValue() < n){
			if(actual.getRight() == null) {
				Node tmp = new Node(n);
				actual.setRight(tmp);
			}else {
				this.add(actual.getRight(), n);
			}
		}
	}
	
	public Integer getRoot(){
		if(this.root != null) {
		return this.root.getValue();
		}
		
		return -1;
	}
	
	public boolean isEmpty(){
		if(this.root == null) {
			return true;
		}
		return false;
	}
	
	
	//Imprime el recorrido Pre order, es decir, la primera vez que pasa
	//por cada nodo, 
	public void printPreOrder() {
		printPreOrder(root);
	}
	
	private void printPreOrder(Node n) {
		if(n == null) {				//Imprime un guion si el nodo es null
			System.out.print("-");
			return;
		}										//si no es null
		System.out.print(n.getValue() + " ");	//Imprime su valor
		printPreOrder(n.getLeft());		//Primero llama recursivamente a su nodo izq(puede ser null)
		printPreOrder(n.getRight());	//Segundo llama recursivamente a su nodo derecho(que puede ser null)
	}
	
	//Funciona igual al preOrder solo que primero pregunta por el nodo 
	//izquierdo, luego imprime su valor y luego pregunta por su nodo 
	//derecho
	
	public void printInOrder() {
		if(root.getValue() != null) {
			printInOrder(root);
		}
		
	}
	
	private void printInOrder(Node n) {
		if(n == null) {
			System.out.print("-");
			return;
		}
		printInOrder(n.getLeft());
		System.out.print(n.getValue());
		printInOrder(n.getRight());
		
	}
	
	//Funciona igual al preOrder e inOrder solo que primero pregunta por el nodo 
	//izquierdo,  luego pregunta por su nodo derecho y por ultimo 
	//imprime su valor
	
	public void printPosOrder() {
		printPosOrder(root);
	}
	
	private void printPosOrder(Node n) {
		if(n == null) {
			System.out.print("-");
			return;
		}
		printPosOrder(n.getLeft());
		printPosOrder(n.getRight());
		System.out.print(n.getValue() + " ");
		
	}
	

	//Devuelve el valor mas izquierdo del arbol, osea el valor 
	//del nodo con mayor valor
	
	public Integer getMaxElement() {
		Integer value = null;	// inicializo la variable a retornar
		if(root != null) {		// comprueba que tenga al menos una raiz
			value = getMaxElement(root); //llama al metodo privado
			return value;		//retorna el valor que obtuvo 
		}else {
			return -1;			// si no tiene raiz devuelve -1
		}	
	}
	
	private Integer getMaxElement(Node n) {	//recibe un nodo 
		if(n.getRight() != null) {		//si tiene nodo der
			return getMaxElement(n.getRight()); //llama recursivamente con el nodo derecho del nodo en el que estoy parada
		}else {								// hasta que no exista un nodo derecho
			return n.getValue();			//entonces devuelve el valor del nodo en el que esta
		}
	
	}
	
	
	//Devuelve true o false si encuentra o no el valor que se le pasa
	public boolean hasElem(Integer valor) {
		boolean tieneElem = false;        // se incializa la variable en false.
		
		if(root != null ) {				//compruebo que exista una raiz 
			if(hasElem(root,valor) == true) { //si existe llamo al metodo privado con el valor a buscar y la raiz 
				tieneElem = true;             // si lo que devuelve es true tieneElem se vuelve true
			}
		}
		return tieneElem;					//por ultimo retorna tieneElem pudiendo ser true o false
	}
	
	private boolean hasElem(Node n,Integer valor) {  //le paso como variable el nodo en el que estoy y el valor a buscar
		if(n == null){				//si el nodo es null devuelvo false. Esto lo hago porque puedo llamas a un nodo izquierdo o derecho que tal vez no exista
			return false;
		}else if(n.getValue() == valor) { // me fijo si el valor del nodo es igual al valor a buscar
			return true;
			
		}else if(n.getValue() > valor){			// si el valor del nodo en el que estoy es mayor al valor a buscar entonces 
				return hasElem(n.getLeft(), valor); // llamo recursivamente a hasElem con el nodo izquierdo y el valor
		}else {
				return hasElem(n.getRight(), valor); //de otra forma llamo recursivamente con el derecho y el valor a buscar
		}
	}
	
	//Devuelve un array con los valores de las hojas del arbol
	public ArrayList<Integer> getFrontera(){ 
		
		if(this.root != null) {       //si existe raiz llamo al metodo privado con la raiz
			return getFrontera(root);
		}else {
			return new ArrayList<>(); // si no existe raiz devuelvo un array vacio
		}
	}
	
	private ArrayList<Integer> getFrontera(Node nodo){ // nodo va a significar el nodo actual con el que llamo
		ArrayList frontera = new ArrayList<>();  // inicializo un array vacio

		if(nodo != null) {                // controlo que el nodo que le llego como parametro 
										//no sea null, ya que podria ser null en las recursiones
			
			if(nodo.getLeft() == null && nodo.getRight() == null ) {  // si es hoja, lo agrego al array 
				frontera.add(nodo.getValue());							
			}else {
				frontera.addAll(getFrontera(nodo.getLeft())); 		//sino llamo con el izq y el dercho del nodo
				frontera.addAll(getFrontera(nodo.getRight()));		//en el que estoy pudiendo existir o no pero 
			}														//pero ya se que hoja no es el nodo actual
		}
		
		return frontera;				//por ultimo retorno lo que se agrego al array
	}
	
	public int getHeight() {
		if(this.root != null) {
			return getHeight(root);
			
		}else {
			return -1;
		}
	}
	
	private int getHeight(Node nodo) {
		int izq= 0;		//inicializo las alturas, derecha e izquierda
		int der=0;
		
		if(nodo.getLeft() != null) {
			izq+= getHeight(nodo.getLeft()) +1; //cada vez que llamo recursivamente a izq o der
		}										//a la vez agrego 1 a la altura
		
		if(nodo.getRight() != null) {
			der += getHeight(nodo.getRight())  +1;
		}
		
		if(izq > der) {  // comparo cual es mas alta y devuelvo el mayor
			return izq;
		}else {
			return der;
		}
	}
	
	
	//Devuelve los valores de la rama mas larga
	public ArrayList<Integer> getLongestBranch(){
		if(this.root != null) {							//se fija si existe raiz
			return getLongestBranch(root);				//si existe, retorna el resultado del metodo priv
		}else {
			return new ArrayList<>();				//si no existe, retorno un arreglo vacio
		}
	}
	
	private ArrayList<Integer> getLongestBranch(Node nodo) {		//recibe el nodo actual
		ArrayList izq = new ArrayList<>(); 			//crea un array izquierdo
		ArrayList der = new ArrayList<>();			// y un array derecho

		izq.add(nodo.getValue());					//agrega el valor del nodo actual al array derecho e izq
		der.add(nodo.getValue());

		if(nodo.getLeft() != null) {						// si el nodo actual tiene un izquierdo 
			izq.addAll(getLongestBranch(nodo.getLeft()));	//llama recursivamente y va a añadiendo recursivamente los valores
		}

		if(nodo.getRight() != null) {						//lo mismo si tiene un derecho
			der.addAll(getLongestBranch(nodo.getRight()));
		}

		if(izq.size() > der.size()) {		//compara los tamaños de los arrays y devuelve el mayor 
			return izq;
		}else {
			return der;
		}
	}
	
	public ArrayList<Integer> getElementAtLevel(int level){			//devuelve un array con los valores de ese nivel pasado por parametro
		if(this.getHeight() == level || this.getHeight() > level) {  // si el nivel que se paso por parametro es igual o menor a la altura del arbol
			int currentLevel = 0;									//inicializo la variable donde llevo el nivel actual y lo inicializo con cero
			return getElementAtLevel(root,level,currentLevel);		// llamo al metodo privado con la raiz, el nivel a retornar y el nivel actual (cero)
		}else {
			return new ArrayList<>();							//si el nivel que se busca es mayor a la altura devuelvo un array vacio
		}
	}
	
	public ArrayList<Integer> getElementAtLevel(Node nodo, int level,int current){ 
		ArrayList<Integer> elements = new ArrayList<>();					//inicializo un array vacio
		
		if(current == level && nodo != null) {	//si el nivel actual es el que quiero retornar y el nodo en el que estoy existe
			elements.add(nodo.getValue());			//agrego el valor del nodo al array y lo retorno
			return elements;
		}else if(current < level && nodo != null) {		//en cambio si el nivel en el que estoy es menor al que se quiere retornar
			current++;									//actualizo el nivel actual y le sumo 1 
			elements.addAll(getElementAtLevel(nodo.getLeft(),level,current)); // al array agrego todo lo que me devuelva el llamado recursivo 
			elements.addAll(getElementAtLevel(nodo.getRight(),level,current)); //tanto hacia la derecha como izquierda
		}
		
		return elements;     //por ultimo retorno el array final
	}
	
	public boolean delete(Integer valor) {
		if(isEmpty()) {
			return false;
		}else {
			return delete(null,root,valor); //si al menos tiene un nodo el arbol llamo al metodo privado
		}
	}
	
	private boolean delete(Node padre,Node nodo,Integer valor) { // tiene como parametros el padre del nodo 
									//actual, el nodo en el que estoy parado y el valor que se quiere borrar
		boolean existe = false;		//inicializo la variable a retornar en false
		

		if(nodo != null) {		//si el nodo en el que estoy parada
			if(nodo.getValue() == valor) {	//si el nodo en el que estoy es el valor que quiero borrar
				
				//Pregunto que tipo de nodo es
				
				//CASO 1 - hoja
				if(nodo.getLeft() == null && nodo.getRight() == null) { //Entonces, si es hoja 
					if(padre == null) {			// si el arbol solo tiene un nodo y es el que quiero borrar
						this.root = null;      //como la raiz no tiene padre y quiero borrar el unico nodo, lo seteo null
					}else {
						deleteHoja(padre,valor);	// si es hoja pero su padre existe llamo al metodo deleteHoja
					}
					
				}
				
				//CASO 2 - EL NODO A BORRAR TIENE UN SOLO HIJO
				else if(nodo.getLeft() == null || nodo.getRight() == null){ // SI el nodo a borrar solo tiene un hijo
					
					if(padre == null) { // cuando la raiz tiene un solo hijo(izq o der) y quiero borrar la raiz
						if(padre.getLeft() != null) {
							this.root = padre.getLeft();		//si la raiz tiene un izq entonces, ahora raiz es izq
						}else {
							this.root = padre.getRight(); // sino es porque su hijo es derecho y ahora sera la raiz
						}
					}else if(nodo.getValue() > padre.getValue()){ //si el valor del nodo a borrar es mayor al padre se que es el hijo der			
						padre.setRight(getNodoFromOnlyBranch(nodo)); // le setteo al padre el nodo derecho preguntado a otro metodo cual 
																	//es nodo hijo del nodo actual
					}else {
						padre.setLeft(getNodoFromOnlyBranch(nodo));  //lo mismo pero si el nodo a borrar es el izquierdo
					}
				}else { //CASO 3 - EL NODO A BORRAR TIENE HIJO DERECHO E IZQUIERDO
					
					int nmi = getNMI(nodo);	 //Busca el valor del NMI de su hijo derecho 
					delete(nmi);			//Elimina ese valor del arbol utilizando recursivamente el metodo delete
					
					if(padre == null) {   //si es la raiz que se quiere eliminar como no tiene padre 
						this.root.setValue(nmi); // directamente se setea la raiz con NMI 
					}else {
						nodo.setValue(nmi); // de otra forma se settea el nodo actual que se quiere borrar con el NMI
					}
				}
				
				existe= true;  // si llego a este punto es porque se borro el nodo y entonces existe se vuelve true

			}else {             //si el nodo en el que estoy no es null pero no es el nodo que se quiere eliminar
				if(nodo.getValue() > valor) {           //comparo el valor del nodo actual para saber si llamo recursivamente a izquierda o derecha
					existe = delete(nodo,nodo.getLeft(),valor);
				}else {
					existe = delete(nodo,nodo.getRight(),valor); //cada vez que llamo recursivamente guardo el resultado en la variable existe 
				}
			}
		}
		
		return existe; //retorno el resultado final
	}
	
	private void deleteHoja(Node padre, Integer valor) {
		if(padre.getLeft().getValue() == valor) {
			padre.setLeft(null);
		}else {
			padre.setRight(null);
		}
	}
	
	private Node getNodoFromOnlyBranch(Node nodo) {
		if(nodo.getLeft() != null) {
			return nodo.getLeft();
		}else {
			return nodo.getRight();
		}
	}
	
	private int getNMI(Node nodo) { //Busca el nodo mas izquierdo desde un nodo
		if(nodo != null) {
			return getNMI(nodo.getLeft());
		}else {
			return nodo.getValue();
		}
	}
}
