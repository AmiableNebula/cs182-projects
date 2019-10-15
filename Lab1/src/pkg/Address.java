package pkg;

public class Address {
	String street;
	String city;
	int zip;
	
	public Address() {
		street = null;
		city = null;
		zip = 0;
	}
	public Address (String street, String city, int zip) {
		this.street = street;
		this.city = city;
		this.zip = zip;
	}
	
	@Override
	public String toString() {
		return "Street: " + street + "\nCity: " + city + "\nZIP: " + zip + "\n";
	}
	
	public String getStreet() {
		return street;
	}
	public String getCity() {
		return city;
	}
	public int getZIP() {
		return zip;
	}
	
	public void setStreet (String street) {
		this.street = street;
	}
	public void setCity (String city) {
		this.city = city;
	}
	public void setZIP (int zip) {
		this.zip = zip;
	}
}