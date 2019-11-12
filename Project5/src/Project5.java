import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Project5 {
	public static void main (String[] args) {
		PhoneBookMenu pbm = new PhoneBookMenu (new PhoneBook ("src/phonebook.txt"));
		pbm.quit();
	}
}

/**
 * Wrapper for {@link PhoneBook} methods
 * @author Sarah Ramsey-McNeil
 */
class PhoneBookMenu {
	// Linked PhoneBook
	private PhoneBook pb;
	
	/**
	 * Creates a new PhoneBook menu and links it to a PhoneBook
	 * @param pb The PhoneBook to link to
	 */
	public PhoneBookMenu (PhoneBook pb) {
		this.pb = pb;
	}
	
	/**
	 * Adds a new Person to the PhoneBook
	 * @param name The Person's name
	 * @param phoneNumber The Person's phone number
	 */
	public void add (String name, String phoneNumber) {
		pb.add (new Person (name, phoneNumber));
	}
	
	/**
	 * Removes a Person from the PhoneBook given their name
	 * @param name The Person's name
	 */
	public void delete (String name) {
		pb.delete (name);
	}
	
	/**
	 * Returns a Person from the PhoneBook given their name
	 * @param name The Person's name
	 * @return The Person
	 */
	public Person find (String name) {
		return pb.find (name);
	}
	
	/**
	 * Edits a Person's phoneNumber given their name
	 * @param name The name of the Person whose phone number to edit
	 * @param phoneNumber The new phone number
	 */
	public void change (String name, String phoneNumber) {
		pb.change (name, phoneNumber);
	}
	
	/**
	 * Returns the number of Persons in the PhoneBook
	 * @return The number of Persons in the PhoneBook
	 */
	public int count() {
		return pb.count();
	}
	
	/**
	 * Saves the contents of the PhoneBook to its external file
	 */
	public void quit() {
		pb.quit();
	}
}

/**
 * Represents a list of Persons stored in an external file. It is recommended that you access this class through {@link PhoneBookMenu} 
 * rather than directly
 * @author Sarah Ramsey-McNeil
 */
class PhoneBook {
	private BinarySearchTree pb = new BinarySearchTree();
	private String path;
	
	/**
	 * Creates a new PhoneBook from the contents of a given file
	 * @param path The path to the file from which the PhoneBook is generated
	 */
	public PhoneBook (String path) {
		try {
			this.path = path;
			String file = Files.readString (Paths.get (path));
			
			int start = 0;
			for (int i = 0; i < file.length(); i++) {
				if (file.charAt (i) == '\n') {
					String substring = file.substring (start, i);
					start = i + 1;
					
					for (int j = 0; j < substring.length(); j++) {
						if (substring.charAt (j) == ':') {
							add (new Person (substring.substring (0, j), substring.substring (j + 2, substring.length())));
						}
					}
				}
			}
		} catch (IOException e) {
			System.err.println ("Couldn't read file");
			e.printStackTrace();
		}
	}
	
	public void add (Person person) {
		if (!pb.contains (person.getName())) {
			pb.insert (person);
		} else {
			throw new RuntimeException ("Duplicate names not allowed");
		}
	}
	
	public void delete (String name) {
		if (pb.contains (name)) {
			pb.delete (name);
		} else {
			throw new RuntimeException ("Person not found");
		}
	}
	
	public Person find (String name) {
		if (pb.contains (name)) {
			return pb.search (name).getData();
		} else {
			throw new RuntimeException ("Person not found");
		}
	}
	
	public void change (String name, String phoneNumber) {
		if (pb.contains (name)) {
			BinarySearchTreeNode node = pb.search (name);
			node = new BinarySearchTreeNode (new Person (name, phoneNumber), node.getLeftChild(), node.getRightChild());
		}
	}
	
	public int count() {
		return pb.count();
	}
	
	public void quit() {
		try (PrintWriter pw = new PrintWriter (path)) {
			pw.print (pb.toString());
		} catch (FileNotFoundException e) {
			System.err.println ("Couldn't write to file");
			e.printStackTrace();
		}
	}
}

// Represents a person's name and phone number
class Person {
	private String name;
	private String phoneNumber;
	
	// Creates a new Person with the specified name and phoneNumber
	public Person (String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	// Returns this Person's name
	public String getName() {
		return name;
	}
	
	// Returns this Person's phoneNumber
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	// Returns this Person's name and phoneNumber
	@Override
	public String toString() {
		return getName() + ": " + getPhoneNumber() + "\n";
	}
}

// Reference-based binary search tree
class BinarySearchTree {
	private BinarySearchTreeNode root = null;
	
	// Returns whether or not the tree contains the given name
	public boolean contains (String name) {
		return contains (name, root);
	}
	
	// Helper method for contains()
	private boolean contains (String name, BinarySearchTreeNode node) {
		if (node != null) {
			if (node.getData().getName().equals (name)) {
				return true;
			} else if (name.compareTo (node.getData().getName()) < 0) {
				return contains (name, node.getLeftChild());
			} else if (name.compareTo (node.getData().getName()) > 0) {
				return contains (name, node.getRightChild());
			}
		}
		
		return false;
	}
	
	// Returns the node containing the given name
	public BinarySearchTreeNode search (String name) {
		return search (name, root);
	}
	
	// Helper method for search()
	private BinarySearchTreeNode search (String name, BinarySearchTreeNode node) {
		if (node != null) {
			if (node.getData().getName().equals (name)) {
				return node;
			} else if (name.compareTo (node.getData().getName()) < 0) {
				return search (name, node.getLeftChild());
			} else if (name.compareTo (node.getData().getName()) > 0) {
				return search (name, node.getRightChild());
			}
		}
		
		throw new RuntimeException ("Node not found");
	}
	
	// Inserts the given Person into the tree
	public void insert (Person person) {
		root = insert (person, root);
	}
	
	// Helper method for insert()
	private BinarySearchTreeNode insert (Person person, BinarySearchTreeNode node) {
		if (node == null) {
			node = new BinarySearchTreeNode (person, null, null);
		} else if (person.getName().compareTo (node.getData().getName()) < 0) {
			node = new BinarySearchTreeNode (node.getData(), insert (person, node.getLeftChild()), node.getRightChild());
		} else if (person.getName().compareTo (node.getData().getName()) > 0) {
			node = new BinarySearchTreeNode (node.getData(), node.getLeftChild(), insert (person, node.getRightChild()));
		}
		
		return node;
	}
	
	// Deletes the given Person from the tree
	public void delete (String name) {
		root = delete (name, root);
	}
	
	// Helper method for delete()
	private BinarySearchTreeNode delete (String name, BinarySearchTreeNode node) {
		if (root == null) {
			throw new RuntimeException ("Empty tree");
		} else if (name.compareTo (node.getData().getName()) < 0) {
			node = new BinarySearchTreeNode (node.getData(), delete (name, node.getLeftChild()), node.getRightChild());
		} else if (name.compareTo (node.getData().getName()) > 0) {
			node = new BinarySearchTreeNode (node.getData(), node.getLeftChild(), delete (name, node.getRightChild()));
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
				delete (node.getData().getName(), temp.getRightChild());
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
	
	// Returns the number of nodes in this tree
	public int count() {
		return count (root);
	}
	
	// Helper method for count()
	private int count (BinarySearchTreeNode node) {
		if (root == null) {
			return 0;
		} else {
			int count = 1;
			if (node.getLeftChild() != null) {
				count += count (node.getLeftChild());
			} if (node.getRightChild() != null) {
				count += count (node.getRightChild());
			}
			
			return count;
		}
	}
	
	// Lists the contents of every node as a single string
	@Override
	public String toString() {
		return toString (root);
	}
	
	// Helper method for toString()
	private String toString (BinarySearchTreeNode node) {
		StringBuilder stringBuilder = new StringBuilder();
		if (node == null) {
			return "";
		} else {
			stringBuilder.append (toString (node.getLeftChild()));
			stringBuilder.append (toString (node.getRightChild()));
			stringBuilder.append (node.getData().toString());
			return stringBuilder.toString();
		}
	}
}

// Node used by binary search tree
class BinarySearchTreeNode {
	private Person data;
	private BinarySearchTreeNode leftChild;
	private BinarySearchTreeNode rightChild;
	
	// Creates a node containing "data" with children "leftChild" and "rightChild"
	public BinarySearchTreeNode (Person data, BinarySearchTreeNode leftChild, BinarySearchTreeNode rightChild) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	// Getter methods
	public Person getData() {return data;}
	public BinarySearchTreeNode getLeftChild() {return leftChild;}
	public BinarySearchTreeNode getRightChild() {return rightChild;}
}