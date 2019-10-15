package pkg;

public class InfixToPostfix {
	// Converts a postfix expression to infix notation
	public String convert (String input) {
		ArrayStack stack = new ArrayStack (50);
		StringBuffer output = new StringBuffer (input.length());
		
		// Iterate through the input
		for (int i = 0; i < input.length(); i++) {
			char current = input.charAt (i);
			
			// If the current character is an operand, append it to the output
			if (Character.isLetterOrDigit (current)) {
				output.append (current);
			}
			
			// If the current character is a (, push it to the stack
			else if (current == '(') {
				stack.push (current);
			}
			
			// If the current character is a ), do the following
			else if (current == ')') {
				// Append the stack to the output until the next (
				while (!stack.isEmpty() && (char) stack.peek() != '(') {
					output.append (stack.pull());
				}
				
				// Pull the topmost element from the stack
				if (!stack.isEmpty()) {
					stack.pull();
				}
			}
			
			// If the current character is an operator, do the following
			else if (isOperator (current)) {
				// If the current character's precedence <= the stack's, append the stack's to the output
				if (!stack.isEmpty() && getPrecedence (current) <= getPrecedence ((char) stack.peek())) {
					output.append (stack.pull());
				}
				
				// Push the current character onto the stack
				stack.push (current);
			}
		}
		
		// Empty the stack into the output
		while (!stack.isEmpty()) {
			output.append (stack.pull());
		}
		
		return output.toString();
	}
	
	// Evaluates an infix expression
	public int evaluate (String input) {
		ArrayStack stack = new ArrayStack (50);
		
		// Interate through the input
		for (int i = 0; i < input.length(); i++) {
			char current = input.charAt (i);
			
			// If the current character is a digit, push it to the stack
			if (Character.isDigit (current)) {
				stack.push (Integer.parseInt (String.valueOf (current)));
			}
			
			// If the current character is an operator, do the following
			else if (isOperator (current)) {
				// Check if the stack has at least two characters in it
				if (stack.size() >= 2) {
					// Evaluate the top two digits on the stack with the current operator and push to the stack
					int v1 = (int) stack.pull(), v2 = (int) stack.pull();
					switch (current) {
						case '+':
							stack.push (v1 + v2);
							break;
						case '-':
							stack.push (v1 - v2);
							break;
						case '*':
							stack.push (v1 * v2);
							break;
						case '/':
							stack.push (v1 / v2);
							break;
						case '%':
							stack.push (v1 % v2);
							break;
					}
				}
			}
		}
		
		return (int) stack.pull();
	}
	
	// Returns whether the input is an operator
	private boolean isOperator (char input) {
		return input == '+' || input == '-' || input == '*' || input == '/' || input == '%' || input == '(' || input == ')';
	}
	
	// Returns the precedence of the input
	private int getPrecedence (char input) {
		switch (input) {
			case '+': case '-':
				return 0;
			case '*': case '/': case '%':
				return 1;
		}
		
		return -1;
	}
}