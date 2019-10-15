package pkg;

public class Main {
	public static void main (String[] args) {
		
	}
}

class Book <E, F> {
	public E title;
	public F isbn;
	
	public Book (E title, F isbn) {
		this.title = title;
		this.isbn = isbn;
	}
}

class RuleBook extends Book <String, String> {
	public RuleBook (String title, String isbn) {
		super (title, isbn);
	}
}