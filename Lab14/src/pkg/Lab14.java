package pkg;

public class Lab14 {
	public static void main (String[] args) {
		
	}
}

// Reference-based binary tree
class BinaryTree <E> {
	// Private variables for in-class use
	private BinaryTreeNode <E> root = null;

	// Creates a new binary tree and sets its root to hold "data" and adopt "leftChild" and "rightChild"
	public BinaryTree (E data, BinaryTreeNode <E> leftChild, BinaryTreeNode <E> rightChild) {
		root = new BinaryTreeNode <E> (data, leftChild, rightChild);
	}

	// Returns whether or not the binary tree is empty
	public boolean isEmpty() {
		return root == null;
	}

	// Prints each node in the binary tree in order
	public void traverseInOrder() {
		if (!isEmpty()) {
			traverseInOrder (root);
		} else {
			System.err.println  ("Empty tree");
		}
	}

	// Helper method for traverseInOrder()
	private void traverseInOrder (BinaryTreeNode <E> node) {
		if (node != null) {
			traverseInOrder (node.getLeftChild());
			System.out.print (node.getData() + " ");
			traverseInOrder (node.getRightChild());
		}
	}

	// Prints each node in the binary tree in preorder
	public void traversePreOrder() {
		if (!isEmpty()) {
			traversePreOrder (root);
		} else {
			System.err.println  ("Empty tree");
		}
	}

	// Helper method for traversePreOrder()
	private void traversePreOrder (BinaryTreeNode <E> node) {
		if (node != null) {
			System.out.print (node.getData() + " ");
			traverseInOrder (node.getLeftChild());
			traverseInOrder (node.getRightChild());
		}
	}

	// Prints each node in the binary tree in postorder
	public void traversePostOrder() {
		if (!isEmpty()) {
			traversePostOrder (root);
		} else {
			System.err.println  ("Empty tree");
		}
	}

	// Helper method for traversePostOrder()
	private void traversePostOrder (BinaryTreeNode <E> node) {
		if (node != null) {
			traverseInOrder (node.getLeftChild());
			traverseInOrder (node.getRightChild());
			System.out.print (node.getData() + " ");
		}
	}
}

// Node used by binary tree
class BinaryTreeNode <E> {
	// Private variables for in-class use
	private E data;
	private BinaryTreeNode <E> leftChild;
	private BinaryTreeNode <E> rightChild;

	// Constructor for creating a new binary tree node
	public BinaryTreeNode (E data, BinaryTreeNode <E> leftChild, BinaryTreeNode <E> rightChild) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	// Getter methods
	public E getData() {return data;}
	public BinaryTreeNode <E> getLeftChild() {return leftChild;}
	public BinaryTreeNode <E> getRightChild() {return rightChild;}
}