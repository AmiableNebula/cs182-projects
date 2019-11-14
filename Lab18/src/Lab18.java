public class Lab18 {
	public static void main (String[] args) {
		System.out.println (hashCode ("NotLikeThis"));
	}
	
	public static int hashCode (String str) {
		int strInt = 0;
		try {
			strInt = Integer.parseInt (str);
		} catch (NumberFormatException e) {
			for (int i = 0; i < str.length(); i++) {
				strInt += str.charAt (i) * 2;
			}
		}
		
		return strInt;
	}
}