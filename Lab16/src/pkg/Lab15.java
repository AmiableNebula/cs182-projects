package pkg;

// Reference-based binary search tree
class BinarySearchTree {
	// Private variables for in-class use
	private BinarySearchTreeNode root = null;
	
	// Returns whether or not the tree contains the given data
	public boolean contains (int data) {
		return contains (data, root);
	}
	
	// Helper method for contains()
	private boolean contains (int data, BinarySearchTreeNode node) {
		if (node != null) {
			if (node.getData() == data) {
				return true;
			} else if (data < node.getData()) {
				return contains (data, node.getLeftChild());
			} else if (data > node.getData()) {
				return contains (data, node.getRightChild());
			} else {
				throw new BinarySearchTreeException ("Something went wrong :(");
			}
		}
		
		return false;
	}
	
	// Returns the node containing the given data
	public BinarySearchTreeNode search (int data) {
		return search (data, root);
	}
	
	// Helper method for search()
	private BinarySearchTreeNode search (int data, BinarySearchTreeNode node) {
		if (node != null) {
			if (node.getData() == data) {
				return node;
			} else if (data < node.getData()) {
				return search (data, node.getLeftChild());
			} else if (data > node.getData()) {
				return search (data, node.getRightChild());
			}
		}
		
		throw new BinarySearchTreeException ("Node not found");
	}
	
	// Inserts the given data into the tree
	public void insert (int data) {
		root = insert (data, root);
	}
	
	// Helper method for insert()
	private BinarySearchTreeNode insert (int data, BinarySearchTreeNode node) {
		if (node == null) {
			node = new BinarySearchTreeNode (data, null, null);
		} else if (data < node.getData()) {
			node = new BinarySearchTreeNode (node.getData(), insert (data, node.getLeftChild()), node.getRightChild());
		} else if (data > node.getData()) {
			node = new BinarySearchTreeNode (node.getData(), node.getLeftChild(), insert (data, node.getRightChild()));
		} else {
			throw new BinarySearchTreeException ("Something went wrong :(");
		}
		
		return node;
	}
	
	// Deletes the given data from the tree
	public void delete (int data) {
		root = delete (data, root);
	}
	
	// Helper method for delete()
	private BinarySearchTreeNode delete (int data, BinarySearchTreeNode node) {
		if (root == null) {
			throw new BinarySearchTreeException ("Empty tree");
		} else if (data < node.getData()) {
			node = new BinarySearchTreeNode (node.getData(), delete (data, node.getLeftChild()), node.getRightChild());
		} else if (data > node.getData()) {
			node = new BinarySearchTreeNode (node.getData(), node.getLeftChild(), delete (data, node.getRightChild()));
		} else {
			if (node.getLeftChild() == null && node.getRightChild() == null) {
				node = null;
			} else if (node.getLeftChild() == null && node.getRightChild() != null) {
				node = node.getRightChild();
			} else if (node.getLeftChild() != null && node.getRightChild() == null) {
				node = node.getLeftChild();
			} else {
				BinarySearchTreeNode temp = nextInOrder (node.getRightChild());
				node = new BinarySearchTreeNode (temp.getData(), node.getLeftChild(), node.getRightChild());
				delete (node.getData(), temp.getRightChild());
			}
		}
		
		return node;
	}
	
	// Helper method for delete()
	private BinarySearchTreeNode nextInOrder (BinarySearchTreeNode node) {
		if (node.getLeftChild() == null) {
			return node;
		} else {
			return nextInOrder (node.getLeftChild());
		}
	}
}

// Node used by binary search tree
class BinarySearchTreeNode {
	// Private variables for in-class use
	private int data;
	private BinarySearchTreeNode leftChild;
	private BinarySearchTreeNode rightChild;
	
	// Creates a node containing "data" with children "leftChild" and "rightChild"
	public BinarySearchTreeNode (int data, BinarySearchTreeNode leftChild, BinarySearchTreeNode rightChild) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	// Getter methods
	public int getData() {return data;}
	public BinarySearchTreeNode getLeftChild() {return leftChild;}
	public BinarySearchTreeNode getRightChild() {return rightChild;}
}

// Exception used by binary search tree
@SuppressWarnings ("serial")
class BinarySearchTreeException extends RuntimeException {
	public BinarySearchTreeException (String str) {
		super (str);
	}
}