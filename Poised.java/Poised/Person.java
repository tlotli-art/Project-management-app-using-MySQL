
public class Person {

	// Attributes of the project class
	String name;
	String telephoneNumber;
	String email;
	String physicalAddress;

	// Constructor method to initialize objects
	public Person(String name, String telephoneNumber, String email, String physicalAddress) {
		this.name = name;
		this.telephoneNumber = telephoneNumber;
		this.email = email;
		this.physicalAddress = physicalAddress;

	}

	/*
	 * this method will return a string object that will be used outside the class
	 * to display the contents of each object
	 */
	public String toString() {
		String output = "name: " + name;
		output += "\nTelephone number: " + telephoneNumber;
		output += "\nEmail address: " + email;
		output += "\nPhysical Adress:" + physicalAddress;
		return output;
	}

}
