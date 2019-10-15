package pkg;

public class Main {
	public static void main (String[] args) {
		LinkedString linkedString = new LinkedString ("Test");
		System.out.println (linkedString.toString());
		System.out.println (linkedString.concat (new LinkedString ("Me")));
	}
}