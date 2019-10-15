package pkg;

public class LinkedList {
	public int nodes;
	public LinkedListNode head;
	
	public LinkedList() {
		nodes = 0;
		head = null;
	}
	
	public int size() {
		return nodes;
	}
	
	public void insert (int index, Object data) {
		if (index >= 0 && index <= nodes) {
			if (index == 0) {
				LinkedListNode node = new LinkedListNode (data);
				node.next = head;
				head = node;
			} else {
				LinkedListNode previous = findNode (index - 1);
				LinkedListNode current = previous.next;
				LinkedListNode newNode = new LinkedListNode (data);
				newNode.next = current;
				previous.next = newNode;
			}
			
			nodes++;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public void delete (int index) {
		if (index >= 0 && index <= nodes) {
			if (index == 0) {
				head = head.next;
			} else {
				LinkedListNode previous = findNode (index - 1);
				LinkedListNode current = previous.next;
				previous.next = current.next;
			}
			
			nodes--;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public void deleteAll() {
		head = null;
		nodes = 0;
	}
	
	public Object getNodeData (int index) {
		return findNode (index).data;
	}
	
	private LinkedListNode findNode (int index) {
		LinkedListNode current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		
		return current;
	}
}