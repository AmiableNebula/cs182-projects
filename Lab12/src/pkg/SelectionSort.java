package pkg;

import java.util.Random;

public class SelectionSort implements Runnable {
	public static void main (String[] args) {
		// Get program start time
		long startTime = System.nanoTime();
		
		// Create several threads that run selection sorts on randomized arrays
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread (new SelectionSort());
			thread.run();
		}
		
		// Print how long the program's been running
		System.out.println ("Time taken: " + ((System.nanoTime() - startTime) / 1000000000d) + " secs");
	}
	
	@Override
	public void run() {
		// Initiate selection sort on randomized array
		selectionSort (genRandomArray (999999));
	}
	
	public int[] selectionSort (int[] array) {
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
	
	public int[] genRandomArray (int length) {
		// Create an empty array with the specified length
		int[] array = new int [length];
		
		// Populate the array with randomly generated integers
		Random random = new Random();
		for (int i = 0; i < array.length; i++) {
			array [i] = random.nextInt();
		}
		
		return array;
	}
}