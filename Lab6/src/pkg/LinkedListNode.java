package pkg;

public class LinkedListNode {
	public LinkedListNode next;
	public Object data;
	
	public LinkedListNode (Object data) {
		this.data = data;
	}
	
	public LinkedListNode (Object data, LinkedListNode next) {
		this.data = data;
		this.next = next;
	}
}