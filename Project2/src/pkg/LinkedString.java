package pkg;

public class LinkedString implements LinkedStringInterface {
	private int nodes = 0;
	private LinkedStringNode head = null;
	
	public LinkedString (String string) {
		for (int i = 0; i < string.length(); i++) {
			addNode (string.charAt (i));
		}
	}
	
	public LinkedString (char[] charArray) {
		for (int i = 0; i < charArray.length; i++) {
			addNode (charArray [i]);
		}
	}
	
	@Override
	public LinkedString concat (LinkedString linkedString) {
		return new LinkedString (this.toString() + linkedString.toString());
	}
	
	@Override
	public char charAt (int index) {
		try {
			if (index < 0 || index > this.length()) {
				throw new LinkedStringException ("Index out of bounds");
			} 
		} catch (LinkedStringException e) {
			e.printMessage();
		}
		
		return findNode (index).data;
	}
	
	@Override
	public LinkedString substring (int start, int end) {
		try {
			if (start < 0 || end < 0 || start > this.length() || end > this.length()) {
				throw new LinkedStringException ("Index out of bounds");
			} else if (start > end) {
				throw new LinkedStringException ("Starting index cannot be greater than ending index");
			}
		} catch (LinkedStringException e) {
			e.printMessage();
		}
		
		String str = "";
		for (int i = start; i < end; i++) {
			str += findNode (i).data;
		}
		
		return new LinkedString (str);
	}
	
	@Override
	public int length() {
		return nodes;
	}
	
	@Override
	public boolean isEmpty() {
		return (nodes == 0);
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < nodes; i++) {
			str += findNode (i).data;
		}
		
		return str;
	}
	
	private LinkedStringNode findNode (int index) {
		LinkedStringNode node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		
		return node;
	}
	
	private void addNode (char data) {
		if (nodes == 0) {
			head = new LinkedStringNode (data);
		} else {
			findNode (nodes - 1).next = new LinkedStringNode (data);
		}
		
		nodes++;
	}
}