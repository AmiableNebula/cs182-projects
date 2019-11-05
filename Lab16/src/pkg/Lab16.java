package pkg;

public class Lab16 {
	public static void main (String[] args) {
		
	}
}

// Reference-based table
class Table {
	// Private variables for in-class use
	private BinarySearchTree bst;
	private int size;
	
	// Creates an empty table
	public Table() {
		bst = new BinarySearchTree();
		size = 0;
	}
	
	// Returns whether or not the table is empty
	public boolean isEmpty() {
		return bst == null && size == 0;
	}
	
	// Returns the number of nodes in the table
	public int size() {
		return size;
	}
	
	// Returns the specified node's data from the table
	public int search (int data) {
		if (!bst.contains (data)) {
			throw new TableException ("Table does not contain specified data");
		} else {
			return bst.search (data).getData();
		}
	}
	
	// Adds a node to the table
	public void insert (int data) {
		if (bst.search (data).getData() == data) {
			throw new TableException ("Data already exists");
		} else {
			bst.insert (data);
			size++;
		}
	}
	
	// Deletes a node containing specified from the table
	public void delete (int data) {
		if (isEmpty()) {
			throw new TableException ("Empty table");
		} else if (!bst.contains (data)) {
			throw new TableException ("Table does not contain data");
		} else {
			bst.delete (data);
			size--;
		}
	}
}

// Custom exception for table
@SuppressWarnings ("serial")
class TableException extends RuntimeException {
	public TableException (String str) {
		super (str);
	}
}