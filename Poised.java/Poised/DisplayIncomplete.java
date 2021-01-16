import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//class to display incomplete projects
public class DisplayIncomplete {

	// method to find and display incomplete projects
	public void incomplete() {

		String complete = null;

		try {
			// Connect to the PoisePMS database, via the jdbc:mysql: channel on localhost
			// (this PC)
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
					"otheruser", "Mongey12");

			// Create a direct line to the database for running our queries
			Statement statement = connection.createStatement();
			ResultSet results;
			
			// executeQuery: runs a SELECT statement and returns the results.
			results = statement.executeQuery("SELECT * FROM project p " + "left join completed_projects c"
					+ " on p.Project_Number = c.Project_Number " + "where c.Project_Number is NULL;"

			);
			// Loop over the results, printing them all.
			while (results.next()) {
				complete = results.getInt("Project_Number") + "," + results.getString("Project_Name") + ","
						+ results.getLong("Total_Charged") + "," + results.getLong("Amount_Paid") + ","
						+ results.getString("Deadline") + "," + results.getString("Contractor_Name") + ","
						+ results.getString("Architect_Name") + "," + results.getString("Customer_Name");
				
				String separate[] = complete.split(",");

				System.out.println("Incomplete Project!\n" + "Project Number: " + separate[0] + "\nProject Name: "
						+ separate[1] + "\nTotal Charged: " + separate[2] + "\nTotal Paid: " + separate[3]
						+ "\nDeadline: " + separate[4] + "\nContractor Name: " + separate[5] + "\nArchitect Name: "
						+ separate[6] + "\nCustomer Name: " + separate[7] + "\n");
			}

		} catch (SQLException e) {
			// SQLException
			e.printStackTrace();
		}

	}

}
