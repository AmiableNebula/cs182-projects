package pkg;

public class StackException extends Throwable {
	private static final long serialVersionUID = 805443784796693329L;
	private String message;
	
	public StackException (String message) {
		this.message = message;
	}
	
	public void printMessage() {
		System.out.println ("Stack exception: " + message);
	}
}