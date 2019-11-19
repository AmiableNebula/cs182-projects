public class ExtraCredit {
	public static void main (String[] args) {
		
	}

	// Returns whether or not two binary trees are identical
	public static boolean isIdentical (BinaryTree t1, BinaryTree t2) {
		return t1.toString().compareTo (t2.toString()) == 0;
	}

	// Returns a reversed linked list based on the input
	public static LinkedList reverseLinkedList (LinkedList ll) {
		LinkedList nl = new LinkedList();
		int index = ll.size() - 1;
		for (int i = 0; i < ll.size(); i++) {
			nl.insert (i, ll.getNode (index).getData());
			index--;
		}

		return nl;
	}
}

// --------------------------------------------------------------------------------------------------------------------------------

// Reference-based binary tree
class BinaryTree {
	private BinaryTreeNode root = null;

	// Returns whether or not the tree contains the given data
	public boolean contains (int data) {
		return contains (data, root);
	}

	// Helper method for contains()
	private boolean contains (int data, BinaryTreeNode node) {
		if (node != null) {
			if (node.getData() == data) {
				return true;
			} else if (data < node.getData()) {
				return contains (data, node.getLeftChild());
			} else if (data > node.getData()) {
				return contains (data, node.getRightChild());
			} else {
				throw new RuntimeException ("Could not determine if tree contains given data");
			}
		}

		return false;
	}

	// Returns the node containing the given data
	public BinaryTreeNode search (int data) {
		return search (data, root);
	}

	// Helper method for search()
	private BinaryTreeNode search (int data, BinaryTreeNode node) {
		if (node != null) {
			if (node.getData() == data) {
				return node;
			} else if (data < node.getData()) {
				return search (data, node.getLeftChild());
			} else if (data > node.getData()) {
				return search (data, node.getRightChild());
			}
		}

		throw new RuntimeException ("Could not find given data in tree");
	}

	// Inserts the given data into the tree
	public void insert (int data) {
		root = insert (data, root);
	}

	// Helper method for insert()
	private BinaryTreeNode insert (int data, BinaryTreeNode node) {
		if (node == null) {
			node = new BinaryTreeNode (data, null, null);
		} else if (data < node.getData()) {
			node = new BinaryTreeNode (node.getData(), insert (data, node.getLeftChild()), node.getRightChild());
		} else if (data > node.getData()) {
			node = new BinaryTreeNode (node.getData(), node.getLeftChild(), insert (data, node.getRightChild()));
		} else {
			throw new RuntimeException ("Could not insert given data into tree");
		}

		return node;
	}

	// Deletes the given data from the tree
	public void delete (int data) {
		root = delete (data, root);
	}

	// Helper method for delete()
	private BinaryTreeNode delete (int data, BinaryTreeNode node) {
		if (node == null) {
			throw new RuntimeException ("Could not delete given data");
		} else if (data < node.getData()) {
			node = new BinaryTreeNode (node.getData(), delete (data, node.getLeftChild()), node.getRightChild());
		} else if (data > node.getData()) {
			node = new BinaryTreeNode (node.getData(), node.getLeftChild(), delete (data, node.getRightChild()));
		} else {
			if (node.getLeftChild() == null && node.getRightChild() == null) {
				node = null;
			} else if (node.getLeftChild() == null && node.getRightChild() != null) {
				node = node.getRightChild();
			} else if (node.getLeftChild() != null && node.getRightChild() == null) {
				node = node.getLeftChild();
			} else {
				BinaryTreeNode temp = nextInOrder (node.getRightChild());
				node = new BinaryTreeNode (temp.getData(), node.getLeftChild(), node.getRightChild());
				delete (node.getData(), temp.getRightChild());
			}
		}

		return node;
	}

	// Helper method for delete()
	private BinaryTreeNode nextInOrder (BinaryTreeNode node) {
		if (node.getLeftChild() == null) {
			return node;
		} else {
			return nextInOrder (node.getLeftChild());
		}
	}

	// Returns a string representation of this tree
	@Override
	public String toString() {
		return toString (root);
	}

	// Helper method for toString()
	private String toString (BinaryTreeNode node) {
		String str = "";
		if (node == null) {
			return "";
		} else {
			str += toString (node.getLeftChild());
			str += toString (node.getRightChild());
			str += node.getData();
			return str;
		}
	}
}

// Node used by binary tree
class BinaryTreeNode {
	private int data;
	private BinaryTreeNode leftChild;
	private BinaryTreeNode rightChild;

	// Creates a node containing "data" with children "leftChild" and "rightChild"
	public BinaryTreeNode (int data, BinaryTreeNode leftChild, BinaryTreeNode rightChild) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	// Getter methods
	public int getData() {return data;}
	public BinaryTreeNode getLeftChild() {return leftChild;}
	public BinaryTreeNode getRightChild() {return rightChild;}
}

//--------------------------------------------------------------------------------------------------------------------------------

// Reference-based linked list
class LinkedList {
	private int nodes;
	private LinkedListNode head;

	// Creates an empty linked list
	public LinkedList() {
		nodes = 0;
		head = null;
	}

	// Returns the number of nodes in this list
	public int size() {
		return nodes;
	}

	// Inserts a node containing the given data into this list
	public void insert (int index, String data) {
		if (index >= 0 && index <= nodes) {
			if (index == 0) {
				LinkedListNode node = new LinkedListNode (data, head);
				head = node;
			} else {
				LinkedListNode previous = getNode (index - 1);
				LinkedListNode current = previous.getNext();
				LinkedListNode newNode = new LinkedListNode (data, current);
				previous = new LinkedListNode (previous.getData(), newNode);
			}

			nodes++;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	// Deletes the node containing the given data from this list
	public void delete (int index) {
		if (index >= 0 && index <= nodes) {
			if (index == 0) {
				head = head.getNext();
			} else {
				LinkedListNode previous = getNode (index - 1);
				LinkedListNode current = previous.getNext();
				previous = new LinkedListNode (previous.getData(), current.getNext());
			}

			nodes--;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	// Returns this list's node at the specified index
	public LinkedListNode getNode (int index) {
		LinkedListNode current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}

		return current;
	}
}

// Node used by linked list
class LinkedListNode {
	private LinkedListNode next;
	private String data;

	// Creates a node containing the given data
	public LinkedListNode (String data) {
		this.data = data;
	}

	// Creates a node containing the given data and a reference to a next node
	public LinkedListNode (String data, LinkedListNode next) {
		this.data = data;
		this.next = next;
	}

	// Getter methods
	public String getData() {return data;}
	public LinkedListNode getNext() {return next;}
}