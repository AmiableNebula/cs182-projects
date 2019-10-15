package pkg;

public class Main {
	public static void main (String[] args) {
		InfixToPostfix infixToPostfix = new InfixToPostfix();
		StringRecognizer stringRecognizer = new StringRecognizer();
		
		System.out.println (infixToPostfix.convert ("a * (b + c)"));
		System.out.println (infixToPostfix.evaluate (infixToPostfix.convert ("2 * (3 + 4)")));
		System.out.println (stringRecognizer.isInLanguage ("tacocat$tacocat"));
		System.out.println (stringRecognizer.isBalanced ("(({)}})"));
	}
}