import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//Class that creates new Project
public class AddProjects {

	public void addToDatabase() {
		try {
			// Connect to the PoisePMS database, via the jdbc:mysql: channel on localhost
			// (this PC)
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
					"otheruser", "Mongey12");
			
			// Create a direct line to the database for running our queries
			Statement statement = connection.createStatement();
			int rowsAffected;
			// Set up finished, do some stuff:
		
			//Open new Scanner
			Scanner edit = new Scanner(System.in);

			// Ask user to enter new details to record new project
			System.out.println("Enter new project number: ");

			String newProjectNumber1 = edit.nextLine();

			int newProjectNumber = Integer.parseInt(newProjectNumber1);

			System.out.println("Enter new project name: ");

			String newProjectName = edit.nextLine();

			System.out.println("Enter new building type: ");

			String newBuildingType = edit.nextLine();

			System.out.println("Enter new physical address: ");

			String newPhysicalAddress = edit.nextLine();

			System.out.println("Enter new erf number: ");

			String newErfNumber1 = edit.nextLine();
			int newErfNumber = Integer.parseInt(newErfNumber1);

			System.out.println("Enter new total fee charged: ");

			String newTotalFeeCharged1 = edit.nextLine();
			Long newTotalFeeCharged = Long.parseLong(newTotalFeeCharged1);

			System.out.println("Enter new amount paid to date: ");

			String newAmountPaidToDate1 = edit.nextLine();
			Long newAmountPaidToDate = Long.parseLong(newAmountPaidToDate1);

			System.out.println("Enter new dead line (YYYY-MM-DD): ");

			String newDeadLine = edit.nextLine();

			System.out.println("Enter new project's architect name and surname: ");

			String newArchitectName = edit.nextLine();

			System.out.println("Enter new project architect's phone number: ");

			String newArchitectPhoneNumber = edit.nextLine();

			System.out.println("Enter new project architect's email address: ");

			String newArchitectEmail = edit.nextLine();

			System.out.println("Enter new project architect's physical address: ");

			String newArchitectPhysicalAddress = edit.nextLine();

			System.out.println("Enter new project contractor name and surname: ");

			String newContractorName = edit.nextLine();

			System.out.println("Enter new project contractor phone number: ");

			String newContractorPhoneNumber = edit.nextLine();

			System.out.println("Enter new project contractor email address: ");

			String newContractorEmail = edit.nextLine();

			System.out.println("Enter new project contractor physical address: ");

			String newContractorPhysicalAddress = edit.nextLine();

			System.out.println("Enter new project's customer name and surname: ");

			String newCustomerName = edit.nextLine();

			System.out.println("Enter new project's customer phone number: ");

			String newCustomerPhoneNumber = edit.nextLine();

			System.out.println("Enter new project's customer email address: ");

			String newCustomerEmail = edit.nextLine();

			System.out.println("Enter new project's customer physical address: ");

			String newCustomerPhysicalAddress = edit.nextLine();

			edit.close();
			/*
			 * If user doesn't enter project name use customer name and building type as new
			 * project name
			 */
			if (newProjectName.equals("")) {

				String name[] = newCustomerName.split(" ");
				String newNewProjectName = newBuildingType + " " + name[name.length - 1];
				;

				newProjectName = newNewProjectName;
			}

			// Add a new project to Project Table
			rowsAffected = statement
					.executeUpdate("INSERT INTO project VALUES (" + newProjectNumber + ", '" + newProjectName + "' "
							+ " ," + newTotalFeeCharged + " ," + newAmountPaidToDate + ", '" + newDeadLine + "', '"
							+ newArchitectName + "' , '" + newContractorName + "' , '" + newCustomerName + "' " + ")");
			rowsAffected++;

			// Add new details to building_type Table
			rowsAffected = statement.executeUpdate("INSERT INTO building_type VALUES (" + newProjectNumber + ", "
					+ newErfNumber + ", '" + newBuildingType + "' " + ")");
			rowsAffected++;

			// Add new details to physical_address Table
			rowsAffected = statement.executeUpdate(
					"INSERT INTO physical_address VALUES (" + newErfNumber + ", '" + newPhysicalAddress + "' " + ")");
			rowsAffected++;

			// Add Add new details to architect_details Table
			rowsAffected = statement.executeUpdate("INSERT INTO architect_details VALUES (" + " '" + newArchitectName
					+ "' , '" + newArchitectPhoneNumber + "' " + ", '" + newArchitectEmail + "' , '"
					+ newArchitectPhysicalAddress + "' " + ")");
			rowsAffected++;

			// Add Add new details to contractor_details Table
			rowsAffected = statement.executeUpdate("INSERT INTO contractor_details VALUES (" + " '" + newContractorName
					+ "' , '" + newContractorPhoneNumber + "' " + ", '" + newContractorEmail + "' , '"
					+ newContractorPhysicalAddress + "' " + ")");
			rowsAffected++;

			// Add new details to customer_details Table
			rowsAffected = statement.executeUpdate(
					"INSERT INTO customer_details VALUES (" + " '" + newCustomerName + "' , '" + newCustomerPhoneNumber
							+ "' " + ", '" + newCustomerEmail + "' , '" + newCustomerPhysicalAddress + "' " + ")");
			System.out.println("Query complete, " + rowsAffected + " rows added.");

		} catch (SQLException e) {
			// catch SQLException 
			e.printStackTrace();
		}
	}

}
