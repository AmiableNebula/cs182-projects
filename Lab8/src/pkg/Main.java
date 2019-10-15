package pkg;

public class Main {
	public static void main (String[] args) {
		ArrayStack stack = new ArrayStack (50);
		
		stack.push ("1st");
		stack.push ("2nd");
		
		System.out.println (stack.pull());
		System.out.println (stack.pull());
		
		stack.push ("3rd");
		
		System.out.println (stack.peek());
	}
}