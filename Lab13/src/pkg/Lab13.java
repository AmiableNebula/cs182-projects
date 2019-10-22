package pkg;

public class Lab13 {
	public static void main (String[] args) {
		
	}
}

interface BinaryTreeInterface <E> {
	public abstract boolean isEmpty();
	public abstract int size();
	public abstract void empty();
	public abstract void addNode (E data);
}

// Binary tree ADT
class BinaryTree <E> implements BinaryTreeInterface <E> {
	// Private variables for in-class use
	private BinaryTreeNode <E> root;
	private int nodes;
	
	// Constructor for creating an empty binary tree
	public BinaryTree() {
		root = null;
		nodes = 0;
	}
	
	// Returns whether or not the binary tree is empty
	@Override
	public boolean isEmpty() {
		return root == null && nodes == 0;
	}
	
	// Returns the number of nodes in the binary tree
	@Override
	public int size() {
		return nodes;
	}
	
	// Empties the binary tree
	@Override
	public void empty() {
		root = null;
		nodes = 0;
	}
	
	// Adds a node to the binary tree
	@Override
	public void addNode (E data) {
		if (isEmpty()) {
			root = new BinaryTreeNode <E> (data, null, null);
		} else {
			
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

// Custom exception used by binary tree
@SuppressWarnings ("serial")
class BinaryTreeException extends Exception {
	// Private variable for in-class use
	private String message;
	
	// Constructor for creating a new binary tree exception
	public BinaryTreeException (String message) {
		this.message = message;
	}
	
	// Prints the provided message to the error stream
	public void printMessage() {
		System.err.println (message);
	}
}