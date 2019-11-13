import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Project5 {
	public static void main (String[] args) {
		
	}
}

/**
 * Provides a CLI through Java's standard I/O streams for interacting with {@link PhoneBook};
 * @author Sarah Ramsey-McNeil
 */
class PhoneBookMenu {
	private PhoneBook pb;
	
	/**
	 * Creates a new instance of this class and links it to a PhoneBook
	 * @param path Path to the file from which the PhoneBook is generated
	 */
	public PhoneBookMenu (String path) {
		pb = new PhoneBook (path);
	}
	
	/**
	 * Starts the command-line interface
	 */
	public void start() {
		System.out.println ("Enter a command. For a list of commands, type \"help\"");
		Scanner scanner = new Scanner (System.in);
		
		while (true) {
			String nl = scanner.nextLine();
			
			if (nl.equals ("quit")) {
				System.out.println ("Terminated");
				break;
			} else if (nl.equals ("help")) {
				System.out.println ("Valid commands: add, delete, find, change, count, quit");
			} else if (nl.equals ("add")) {
				System.out.print ("Enter the name of who to add: ");
				String name = scanner.nextLine();
				System.out.print ("Now enter their phone number: ");
				String phoneNumber = scanner.nextLine();
				pb.add (name, phoneNumber);
				System.out.println ("Added!");
			} else if (nl.equals ("delete")) {
				System.out.print ("Enter the name of who to delete: ");
				pb.delete (scanner.nextLine());
				System.out.println ("Deleted!");
			} else if (nl.equals ("find")) {
				System.out.print ("Enter the name of who to find: ");
				System.out.println ("Found phone number: " + pb.find (scanner.nextLine()).getPhoneNumber());
			} else if (nl.equals ("change")) {
				System.out.print ("Enter the name of who to change: ");
				String name = scanner.nextLine();
				System.out.print ("Now enter their new phone number: ");
				String phoneNumber = scanner.nextLine();
				pb.change (name, phoneNumber);
				System.out.println ("Changed!");
			} else if (nl.equals ("count")) {
				System.out.println ("Count: " + pb.count());
			} else {
				System.out.println ("Unrecognized command. For a list of commands, type \"help\"");
			}
		}
		
		scanner.close();
	}
}

/**
 * Represents a list of Persons stored in an external file
 * @author Sarah Ramsey-McNeil
 */
class PhoneBook {
	private BinarySearchTree bst;
	private String path;

	/**
	 * Creates a new PhoneBook from the contents of a file
	 * @param path The path to the file from which the PhoneBook is generated
	 */
	public PhoneBook (String path) {
		try {
			bst = new BinarySearchTree();
			this.path = path;
			String file = Files.readString (Paths.get (path));

			int start = 0;
			for (int i = 0; i < file.length(); i++) {
				if (file.charAt (i) == '\n') {
					String substring = file.substring (start, i);
					start = i + 1;

					for (int j = 0; j < substring.length(); j++) {
						if (substring.charAt (j) == ':') {
							add (substring.substring (0, j), substring.substring (j + 2, substring.length()));
						}
					}
				}
			}
		} catch (IOException e) {
			System.err.println ("Couldn't read file");
			e.printStackTrace();
		}
	}

	/**
	 * Adds a new Person to the PhoneBook
	 * @param name The Person's name
	 * @param phoneNumber The Person's phone number
	 */
	public void add (String name, String phoneNumber) {
		Person person = new Person (name, phoneNumber);
		if (!bst.contains (person.getName())) {
			bst.insert (person);
		} else {
			throw new RuntimeException ("Duplicate names not allowed");
		}
	}

	/**
	 * Removes a Person from the PhoneBook
	 * @param name The name of the Person to be removed
	 */
	public void delete (String name) {
		if (bst.contains (name)) {
			bst.delete (name);
		} else {
			throw new RuntimeException ("Person not found");
		}
	}

	/**
	 * Returns a Person from the PhoneBook
	 * @param name The name of the Person to be returned
	 * @return The found Person
	 */
	public Person find (String name) {
		if (bst.contains (name)) {
			return bst.search (name).getData();
		} else {
			throw new RuntimeException ("Person not found");
		}
	}

	/**
	 * Edits a Person's phone number
	 * @param name The Person's name
	 * @param phoneNumber The Person's new phone number
	 */
	public void change (String name, String phoneNumber) {
		if (bst.contains (name)) {
			BinarySearchTreeNode node = bst.search (name);
			node = new BinarySearchTreeNode (new Person (name, phoneNumber), node.getLeftChild(), node.getRightChild());
		}
	}

	/**
	 * Returns the number of Persons in the PhoneBook
	 * @return The number of Persons in the PhoneBook
	 */
	public int count() {
		return bst.count();
	}

	/**
	 * Saves the PhoneBook to its external file
	 */
	public void quit() {
		try (PrintWriter pw = new PrintWriter (path)) {
			pw.print (bst.toString());
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