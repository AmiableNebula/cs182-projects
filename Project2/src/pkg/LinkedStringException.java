package pkg;

public class LinkedStringException extends Throwable {
	private static final long serialVersionUID = -4467275154393663820L;
	private String message;
	
	public LinkedStringException (String message) {
		this.message = message;
	}
	
	public void printMessage() {
		System.err.println ("LinkedStringException: " + message);
	}
}