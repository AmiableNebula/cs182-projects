package pkg;

public class Fibonacci {
	public static void main (String[] args) {
		System.out.println (fibonacci (9));
	}
	
	public static int fibonacci (int n) {
		int previous = 1;
		int current = 1;
		int next = 0;
		
		if (n <= 0) {
			return 0;
		} else if (n <= 2) {
			return 1;
		} else {
			for (int i = 3; i <= n; i++) {
				next = previous + current;
				previous = current;
				current = next;
			}
			
			return current;
		}
	}
}