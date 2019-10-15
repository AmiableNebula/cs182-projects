package pkg;

public class Recursion {
	public static void main (String[] args) {
		System.out.println (power (2, 32));
	}
	
	public static long power (long b, long p) {
		if (p == 0) {
			return 1;
		} else if (p == 1) {
			return b;
		} else {
			return (b * power (b, p - 1));
		}
	}
}