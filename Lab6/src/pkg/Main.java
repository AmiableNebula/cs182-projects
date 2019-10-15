package pkg;

public class Main {
	public static void main (String[] args) {
		LinkedList linkedList = new LinkedList();
		linkedList.insert (0, "1st");
		System.out.println (linkedList.getNodeData (0));
	}
}