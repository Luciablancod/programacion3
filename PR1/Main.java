package PR1;

public class Main {

	public static void main(String[] args) {
		MyList mylist = new MyList();
		
		mylist.insertFront(3);
		mylist.insertFront(6);
		mylist.insertFront(9);
		mylist.insertFront(12);

//		System.out.println( mylist.isEmpty());
//		System.out.println( mylist.size());
//		System.out.println( mylist.toString());
//		System.out.println( mylist.extractFront());
//		System.out.println( mylist.size());
		
//		System.out.println( mylist.toString());
//		int index = mylist.indexOf(3);
//		System.out.println( index);
		
		//Iterator - Itareble
        for (Integer value : mylist) {
            System.out.println(value);
        }
		
		
//		Pila mypila = new Pila();
//		
//		mypila.push(2);
//		mypila.push(4);
//		mypila.push(6);
//		mypila.push(8);
//		mypila.push(10);
//		
//		System.out.println( mypila.toString());
//		mypila.reverse();
//		System.out.println( mypila.toString());
		
		
		
	}

}
