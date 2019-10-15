package pkg;

public class CreditCard {
	private int accountNumber;
	private String name;
	private double balance;
	private int rewardPoints;
	private String nextDueDate;
	
	public CreditCard (int accountNumber, String name, double balance, int rewardPoints, String nextDueDate) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
		this.rewardPoints = rewardPoints;
		this.nextDueDate = nextDueDate;
	}
	
	public void charge (double amount) {
		if (balance - amount < 0) {
			System.err.println ("Not enough funds!");
		} else {
			balance -= amount;
			rewardPoints += amount / 100;
		}
	}
	
	public void advance (double amount) {
		balance += amount;
		addInterest (0.03);
		
	}
	
	public void pay (double amount) {
		balance += amount;
	}
	
	public void addInterest (double amount) {
		balance = balance + (balance * amount);
	}
	
	public void displayDetails() {
		System.out.println ("Account #" + accountNumber);
		System.out.println ("Account holder's name: " + name);
		System.out.println ("Balance: $" + balance);
		System.out.println ("Current rewards points: " + rewardPoints);
		System.out.println ("Next due date: " + nextDueDate);
	}
}