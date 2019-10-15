package pkg;

public class SelectionSort {
	public static void main (String[] args) {
		
	}
	
	public static int[] selectionSort (int[] array) {
		// Iterate through the array
		for (int i = 0; i < array.length; i++) {
			// Store the first value you see (and its index)
			int smallest = array [i];
			int smallestIndex = i;
			
			// Iterate through the rest of the array
			for (int j = i; j < array.length; j++) {
				// Update the stored values to correspond with the smallest value you see
				if (array [j] < smallest) {
					smallest = array [j];
					smallestIndex = j;
				}
			}
			
			// Swap the smallest value with the value you started at
			int temp = array [i];
			array [i] = smallest;
			array [smallestIndex] = temp;
		}
		
		return array;
	}
}