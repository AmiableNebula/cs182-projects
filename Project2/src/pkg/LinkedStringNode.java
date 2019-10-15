package pkg;

public class LinkedStringNode {
	public LinkedStringNode next;
	public char data;
	
	public LinkedStringNode (char data) {
		this.data = data;
	}
	
	public LinkedStringNode (char data, LinkedStringNode next) {
		this.data = data;
		this.next = next;
	}
}