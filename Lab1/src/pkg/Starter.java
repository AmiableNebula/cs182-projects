package pkg;

public class Starter {
	public static void main (String[] args) {
		Address address = new Address ("Rockwell", "Valencia", 91355);
		// Address address = new Address();
		System.out.print (address.toString());
	}
}