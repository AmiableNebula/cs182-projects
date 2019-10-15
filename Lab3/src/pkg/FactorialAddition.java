package pkg;

public class FactorialAddition {
	public static void main (String[] args) {
		System.out.println (factorialAddition (9));
	}
	
	public static int factorialAddition (int n) {
		if (n == 1) {
			return 1;
		} else {
			return n + factorialAddition (n - 1);
		}
	}
}