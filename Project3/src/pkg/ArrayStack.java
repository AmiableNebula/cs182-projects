package pkg;

public class ArrayStack {
	private Object[] stack;
	private int items;
	
	public ArrayStack (int size) {
		stack = new Object [size];
		this.items = 0;
	}
	
	public void push (Object data) {
		stack [items] = data;
		items++;
	}
	
	public Object peek() {
		return stack [items - 1];
	}
	
	public Object pull() {
		items--;
		return stack [items];
	}
	
	public int size() {
		return items;
	}
	
	public boolean isEmpty() {
		return items == 0;
	}
}