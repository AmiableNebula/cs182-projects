package pkg;

public class Main {
	private static int harmonicTarget = 9;
	private static int[] array = new int[] {5, 6, 7, 8, 9};
	private static int arrayTarget = 9;
	private static CreditCard creditCard = new CreditCard (123456789, "Sarah", 420, 69, "Sep 11, 2019");
	
	public static void main (String[] args) {
		System.out.println (recursiveHarmonic (harmonicTarget));
		System.out.println (recursiveArraySearch (array, arrayTarget, 0));
		System.out.println();
		creditCard.displayDetails();
	}
	
	public static double recursiveHarmonic (int n) {
		if (n < 0) {
			return -1;
		} else if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return (1d / n) + recursiveHarmonic (n - 1);
		}
	}
	
	public static int recursiveArraySearch (int[] array, int target, int index) {
		if (index >= array.length) {
			return -1;
		} else if (array [index] == target) {
			return index;
		} else {
			return recursiveArraySearch (array, target, index + 1);
		}
	}
}