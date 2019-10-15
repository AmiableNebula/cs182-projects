package pkg;

public class ReferenceQueue {
	private ReferenceQueueNode head = null;
	private int nodes = 0;
	
	public ReferenceQueue() {
		head = null;
		nodes = 0;
	}
	
	public boolean isEmpty() {
		return nodes == 0;
	}
	
	public int length() {
		return nodes;
	}
	
	public void enqueue (Object data) {
		if (isEmpty()) {
			head = new ReferenceQueueNode (data);
		} else {
			findNode (length() - 1).next = new ReferenceQueueNode (data);
		}
		
		nodes++;
	}
	
	public Object peek() {
		if (isEmpty()) {
			return null;
		} else {
			return head.data;
		}
	}
	
	public Object dequeue() {
		if (isEmpty()) {
			return null;
		} else {
			Object data = head.data;
			head = head.next;
			nodes--;
			return data;
		}
	}
	
	// Acts like pulling from a stack
	public Object dequeueBack() {
		if (isEmpty()) {
			return null;
		} else {
			Object data = findNode (length() - 1).data;
			findNode (length() - 2).next = null;
			nodes--;
			return data;
		}
	}
	
	private ReferenceQueueNode findNode (int index) {
		ReferenceQueueNode node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		
		return node;
	}
}