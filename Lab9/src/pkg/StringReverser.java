package pkg;

import java.util.Stack;

public class StringReverser {
	public static void main (String[] args) {
		Stack <Character> stack = new Stack <Character>();
		String input = "Hello, world!";
		String output = "";
		
		for (int i = 0; i < input.length(); i++) {
			stack.push (input.charAt (i));
		}
		
		while (!stack.isEmpty()) {
			output += stack.pop();
		}
		
		System.out.println (output);
	}
}