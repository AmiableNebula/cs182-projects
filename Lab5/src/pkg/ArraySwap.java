package pkg;

public class ArraySwap {
	private static int i1 = 1;
	private static int i2 = 2;
	
	public static void main (String[] args) {
		Object[] array = new Object[] {"0th", "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th"};
		System.out.println ("Before: " + array [i1] + ", " + array [i2]);
		swap (array, i1, i2);
		System.out.println ("After: " + array [i1] + ", " + array [i2]);
	}
	
	public static void swap (Object[] array, int i1, int i2) {
		Object temp = array [i1];
		array [i1] = array [i2];
		array [i2] = temp;
	}
}