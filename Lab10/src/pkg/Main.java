package pkg;

public class Main {
	public static void main (String[] args) {
		ReferenceQueue referenceQueue = new ReferenceQueue();
		
		referenceQueue.enqueue ("1st");
		referenceQueue.enqueue ("2nd");
		referenceQueue.enqueue ("3rd");
		
		System.out.println (referenceQueue.peek());
		System.out.println (referenceQueue.dequeueBack());
	}
}