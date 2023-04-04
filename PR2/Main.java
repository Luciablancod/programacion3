package PR2;

public class Main {

	public static void main(String[] args) {
		
		Tree myTree = new Tree();
		
		myTree.add(20);
		myTree.add(30);
		myTree.add(10);
		myTree.add(8);
		myTree.add(13);
		myTree.add(24);
		myTree.add(31);
		myTree.add(40);
		
		myTree.printPreOrder();
//		myTree.printInOrder();
//		myTree.printPosOrder();
		
//		System.out.print(myTree.getMaxElement());
//		System.out.print(myTree.hasElem(20));
		
//		System.out.print(myTree.getFrontera());
//		System.out.print(myTree.getHeight());
//		System.out.print(myTree.getLongestBranch());
//		System.out.print(myTree.getElementAtLevel(3));
		System.out.print(myTree.delete(8));
		myTree.printPreOrder();
	}

}
