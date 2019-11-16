public class Lab18 {
	public static void main (String[] args) {
		hashCode ("zfgRunOgre");
	}
	
	public static void hashCode (String str) {
		try {
			System.out.println (Integer.parseInt (str));
		} catch (NumberFormatException e) {
			int hashCode = 0;
			for (int i = 0; i < str.length(); i++) {
				hashCode += str.charAt (i) * 2;
			}
			
			System.out.println (hashCode);
		}
	}
}