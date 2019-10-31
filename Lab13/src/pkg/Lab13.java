package pkg;

public class Lab13 {
	public static void main (String[] args) {
		
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