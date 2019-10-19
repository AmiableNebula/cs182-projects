package pkg;

public class Project4 {
	public static void main (String[] args) {
		System.out.println (new StringRecognizer().isInLanguage ("tacocat$tacocat"));
	}
}

// Reference-based deque interface
interface ReferenceDequeInterface <E> {
	public boolean isEmpty();
	public int size();
	public void addFront (E data);
	public void addBack (E data);
	public E removeFront();
	public E removeBack();
	
}

// Reference-based deque
class ReferenceDeque <E> implements ReferenceDequeInterface <E> {
	private ReferenceDequeNode <E> head = null;
	private int nodes = 0;
	
	// Returns whether or not the deque is empty
	@Override
	public boolean isEmpty() {
		return head == null && nodes == 0;
	}
	
	// Returns the number of nodes in the deque
	@Override
	public int size() {
		return nodes;
	}
	
	// Adds a node to the front of the deque
	@Override
	public void addFront (E data) {
		if (isEmpty()) {
			head = new ReferenceDequeNode <E> (data);
		} else {
			head = new ReferenceDequeNode <E> (data, head);
		}
		
		nodes++;
	}
	
	// Add a node to the back of the deque
	@Override
	public void addBack (E data) {
		if (isEmpty()) {
			head = new ReferenceDequeNode <E> (data);
		} else {
			ReferenceDequeNode <E> lastNode = findNode (size() - 1);
			lastNode = new ReferenceDequeNode <E> (lastNode.getData(), new ReferenceDequeNode <E> (data));
		}
		
		nodes++;
	}
	
	// Removes a node from the front of the deque
	@Override
	public E removeFront() {
		try {
			if (isEmpty()) {
				throw new ReferenceDequeException ("Empty deque");
			}
		} catch (ReferenceDequeException e) {
			e.printError();
		}
		
		E data = head.getData();
		head = head.getNext();
		nodes--;
		return data;
	}
	
	// Remove a node from the back of the deque
	@Override
	public E removeBack() {
		try {
			if (isEmpty()) {
				throw new ReferenceDequeException ("Empty deque");
			}
		} catch (ReferenceDequeException e) {
			e.printError();
		}
		
		E data = findNode (size() - 1).getData();
		ReferenceDequeNode <E> secondToLastNode = findNode (size() - 2);
		secondToLastNode = new ReferenceDequeNode <E> (secondToLastNode.getData());
		nodes--;
		return data;
	}
	
	// Returns the specified zero-indexed node from the deque
	private ReferenceDequeNode <E> findNode (int index) {
		ReferenceDequeNode <E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		
		return current;
	}
}

// Node used by reference-based deque
class ReferenceDequeNode <E> {
	private E data;
	private ReferenceDequeNode <E> next;
	
	public ReferenceDequeNode (E data) {
		this.data = data;
	}
	
	public ReferenceDequeNode (E data, ReferenceDequeNode <E> next) {
		this.data = data;
		this.next = next;
	}
	
	public E getData() {
		return data;
	}
	
	public ReferenceDequeNode <E> getNext() {
		return next;
	}
}

// Custom exception for reference-based deque
@SuppressWarnings ("serial")
class ReferenceDequeException extends Throwable {
	private String error;
	
	public ReferenceDequeException (String error) {
		this.error = error;
	}
	
	public void printError() {
		System.err.println (error);
	}
}

// String recognizer that implements reference-based deque
class StringRecognizer {
	// Returns whether or not the specified string is palindromatic (is that a word?) about "$"
	public boolean isInLanguage (String input) {
		// Return false if the input is an empty string
		if (input.length() == 0) {
			return false;
		}
		
		// Get the separator's index
		int separatorCount = 0;
		int separatorIndex = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt (i) == '$') {
				separatorIndex = i;
				separatorCount++;
			}
		}
		
		// If the input doesn't contain exactly one separator, return false
		if (separatorCount != 1) {
			return false;
		}
		
		// Separate the input into two substrings along the separator
		String frontHalf = input.substring (0, separatorIndex);
		String backHalf = input.substring (separatorIndex + 1, input.length());
		
		// Reverse the second substring
		ReferenceDeque <Character> referenceDeque = new ReferenceDeque <>();
		for (int i = 0; i < backHalf.length(); i++) {
			referenceDeque.addFront (input.charAt (i));
		}
		backHalf = "";
		while (!referenceDeque.isEmpty()) {
			backHalf += referenceDeque.removeFront();
		}
		
		// Compare the two substrings
		if (frontHalf.length() != backHalf.length()) {
			return false;
		} else {
			for (int i = 0; i < frontHalf.length(); i++) {
				if (frontHalf.charAt (i) != backHalf.charAt (i)) {
					return false;
				}
			}
		}
		
		// Default case
		return true;
	}
}