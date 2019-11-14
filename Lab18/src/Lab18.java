public class Lab18 {
	public static void main (String[] args) {
		System.out.println (hashCode ("NotLikeThis"));
	}
	
	public static int hashCode (String str) {
		// Define integer for later use
		int strInt = 0;
		try {
			// Attempt to call parseInt() on the string
			strInt = Integer.parseInt (str);
		} catch (NumberFormatException e) {
			// If parseInt() throws an exception, iterate through each character and add their integer representations
			for (int i = 0; i < str.length(); i++) {
				strInt += str.charAt (i) * 2;
			}
		}
		
		// Return the resulting integer
		return strInt;
	}
}