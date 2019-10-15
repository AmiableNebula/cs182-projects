package pkg;

public class VerifyIdentifier {
	private static char[] validStarters = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	private static char[] validEnders = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	public static void main (String[] args) {
		System.out.println (verifyIdentifier ("Sarah"));
	}
	
	public static boolean verifyIdentifier (String str) {
		if (str.length() == 1) {
			return verifyStarter (str.charAt (0));
		} else if (verifyEnder (str.charAt (str.length() - 1))) {
			return verifyIdentifier (str.substring (0, str.length() - 1));
		} else {
			return false;
		}
	}
	
	private static boolean verifyStarter (char chr) {
		for (int i = 0; i < validStarters.length; i++) {
			if (chr == validStarters [i]) {
				return true;
			}
		}
		
		return false;
	}
	
	private static boolean verifyEnder (char chr) {
		for (int i = 0; i < validEnders.length; i++) {
			if (chr == validEnders [i]) {
				return true;
			}
		}
		
		return false;
	}
}