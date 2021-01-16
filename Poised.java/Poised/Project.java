
public class Project {

	// Attributes of the project class

	int projectNumber;
	String projectName;
	String buildingType;
	String physicalAddress;
	int erfNumber;
	long totalFeeCharged;
	long amountPaidToDate;
	String deadLine;

	// Constructor method to initialize objects

	public Project(int projectNumber, String projectName, String buildingType, String physicalAddress, int erfNumber,
			long totalFeeCharged, long amountPaidToDate, String deadLine) {
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.buildingType = buildingType;
		this.physicalAddress = physicalAddress;
		this.erfNumber = erfNumber;
		this.totalFeeCharged = totalFeeCharged;
		this.amountPaidToDate = amountPaidToDate;
		this.deadLine = deadLine;
	}

	/*
	 * this method will return a string object that will be used outside the class
	 * to display the contents of each object
	 */

	public String toString() {
		String output = "Project Number: " + projectNumber;
		output += "\nProject Name: " + projectName;
		output += "\nBuilding type: " + buildingType;
		output += "\nPhysical Adress:" + physicalAddress + "\n";
		output += "\nERF Number: " + erfNumber;
		output += "\nTotal fee charged: R" + totalFeeCharged;
		output += "\nTotal amount paid to date: R" + amountPaidToDate;
		output += "\nProject deadline: " + deadLine;
		return output;
	}

}
