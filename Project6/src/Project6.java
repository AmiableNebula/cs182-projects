import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Project6 {
	public static void main (String[] args) {
		start (new CallCenter());
	}
	
	public static void start (CallCenter cc) {
		System.out.print ("Enter a command: ");
		Scanner scanner = new Scanner (System.in);
		
		while (true) {
			String nl = scanner.nextLine();
			
			if (nl.equals ("exit")) {
				System.out.println ("Quitting...");
				break;
			} else if (nl.equals ("call")) {
				System.out.print ("Enter the name of the caller: ");
				cc.takeCall (new Caller (scanner.nextLine()));
				System.out.println ("Call added");
			} else if (nl.equals ("hang-up")) {
				System.out.print ("Which employee should hang up? ");
				String employee = scanner.nextLine();
				
				if (employee.equals ("fresher")) {
					cc.endCall (cc.fresher);
				} else if (employee.equals ("technicalLead")) {
					cc.endCall (cc.technicalLead);
				} else if (employee.equals ("manager")) {
					cc.endCall (cc.manager);
				} else {
					System.err.println ("Invalid employee");
					continue;
				}
				
				System.out.println ("Call removed");
			} else {
				System.err.println ("Invalid command");
			}
		}
		
		scanner.close();
	}
}

class CallCenter {
	public Employee fresher;
	public Employee technicalLead;
	public Employee manager;
	private Queue <Caller> queue;

	public CallCenter() {
		fresher = new Employee();
		technicalLead = new Employee();
		manager = new Employee();
		queue = new LinkedList<>();
	}

	public void takeCall (Caller caller) {
		if (!fresher.busy()) {
			fresher.takeCall (caller);
		} else if (!technicalLead.busy()) {
			technicalLead.takeCall (caller);
		} else if (!manager.busy()) {
			manager.takeCall (caller);
		} else {
			queue.add (caller);
		}
	}

	public void endCall (Employee employee) {
		employee.endCall();
		
		if (!queue.isEmpty()) {
			employee.takeCall (queue.remove());
		}
	}
}

class Employee {
	private Caller caller;

	public void takeCall (Caller caller) {
		if (busy()) {
			System.err.println ("Call already in progress");
		} else {
			this.caller = caller;
		}
	}

	public void endCall() {
		if (!busy()) {
			System.err.println ("No call in progress");
		} else {
			caller = null;
		}
	}

	public boolean busy() {
		return caller != null;
	}
}

class Caller {
	@SuppressWarnings ("unused")
	private String name;
	
	public Caller (String name) {
		this.name = name;
	}
}