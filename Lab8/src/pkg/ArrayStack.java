package pkg;

public class ArrayStack {
	private Object[] stack;
	private int size;
	private int items;
	
	
	public ArrayStack (int size) {
		stack = new Object [size];
		this.size = size;
		this.items = 0;
	}
	
	public void push (Object data) {
		try {
			if (items == size) {
				throw new StackException ("Full stack");
			} else {
				stack [items] = data;
				items++;
			}
		} catch (StackException e) {
			e.printMessage();
		}
	}
	
	public Object peek() {
		try {
			if (items == 0) {
				throw new StackException ("Empty stack");
			} else {
				return stack [items - 1];
			}
		} catch (StackException e) {
			e.printMessage();
		}
		
		return null;
	}
	
	public Object pull() {
		try {
			if (items < 0) {
				throw new StackException ("Empty stack");
			} else {
				items--;
				return stack [items];
			}
		} catch (StackException e) {
			e.printMessage();
		}
		
		return null;
	}
	
	public void pullAll() {
		items = 0;
	}
}