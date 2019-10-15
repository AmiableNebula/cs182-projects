package pkg;

public class StringRecognizer {
	public boolean isInLanguage (String input) {
		// If the input doesn't contain exactly one separator, return false
		int separatorCount = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt (i) == '$') {
				separatorCount++;
			}
		}
		if (separatorCount != 1 ) {
			return false;
		}
		
		// Separate the input into substrings along the separator
		String substring1 = "";
		String substring2 = "";
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt (i) == '$') {
				substring1 = input.substring (0, i);
				substring2 = input.substring (i + 1, input.length());
			}
		}
		
		// Reverse the second substring
		ArrayStack stack = new ArrayStack (50);
		for (int i = 0; i < substring2.length(); i++) {
			stack.push (substring2.charAt (i));
		}
		substring2 = "";
		while (!stack.isEmpty()) {
			substring2 += stack.pull();
		}
		
		// Compare the first and second substrings
		if (substring1.length() != substring2.length()) {
			return false;
		}
		for (int i = 0; i < substring1.length(); i++) {
			if (substring1.charAt (i) != substring2.charAt (i)) {
				return false;
			}
		}

		return true;
	}

	public boolean isBalanced (String input) {
		ArrayStack parenthesisStack = new ArrayStack (50);
		ArrayStack bracketStack = new ArrayStack (50);

		for (int i = 0; i < input.length(); i++) {
			char current = input.charAt (i);

			if (current == '(') {
				parenthesisStack.push (current);
			} else if (current == '{') {
				bracketStack.push (current);
			} else if (current == ')' && !parenthesisStack.isEmpty()) {
				parenthesisStack.pull();
			} else if (current == '}' && !bracketStack.isEmpty()) {
				bracketStack.pull();
			}
		}

		return parenthesisStack.isEmpty() && bracketStack.isEmpty();
	}
}