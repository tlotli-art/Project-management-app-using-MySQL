import java.util.Scanner;
import java.sql.*;

//class to find line
public class FindLines {

	private Scanner edit1;

	// Method to read file and locate specific line and returns it
	public String findInLine() {

		//initialize string to return
		String last = null;

		try {
			// Connect to the library PoisePMS database, via the jdbc:mysql: channel on localhost
			// (this PC)
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
					"otheruser", "Mongey12");
			// Create a direct line to the database for running our queries
			Statement statement = connection.createStatement();
			ResultSet results;
			edit1 = new Scanner(System.in);
			System.out.print("\nEnter Valid Project number of project you wish to find: ");
			String number = edit1.nextLine();

			// executeQuery: runs a SELECT statement and returns the results.
			results = statement.executeQuery("SELECT * FROM project p  " + "inner join building_type b "
					+ "on b.Project_Number = p.Project_Number " + "inner join physical_address py "
					+ "on b.ERF_Number = py.ERF_Number " + "inner join contractor_details co "
					+ "on p.Contractor_Name = co.Contractor_Name " + "inner join architect_details ar "
					+ "on p.Architect_Name = ar.Architect_Name " + "inner join customer_details cu "
					+ "on p.Customer_Name = cu.Customer_Name " + "where p.Project_Number =" + number);
			// Loop over the results, printing them all.
			while (results.next()) {
				last = results.getInt("Project_Number") + "," + results.getString("Project_Name") + ","
						+ results.getString("Building_Type") + "," + results.getString("Physical_Address") + ","
						+ results.getInt("ERF_Number") + "," + results.getLong("Total_Charged") + ","
						+ results.getLong("Amount_Paid") + "," + results.getString("Deadline") + ","
						+ results.getString("Contractor_Name") + "," + results.getString("Contractor_Telephone") + ","
						+ results.getString("Contractor_Email") + "," + results.getString("Contractor_Address") + ","
						+ results.getString("Architect_Name") + "," + results.getString("Architect_Telephone") + ","
						+ results.getString("Architect_Email") + "," + results.getString("Architect_Address") + ","
						+ results.getString("Customer_Name") + "," + results.getString("Customer_Telephone") + ","
						+ results.getString("Customer_Email") + "," + results.getString("Customer_Address");
			}

		} catch (SQLException e) {
			//catch a SQLException .
			e.printStackTrace();
		}

		return last;
	}
}