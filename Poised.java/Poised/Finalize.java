import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

//Class to change status of project to finalized
public class Finalize {

	// method to finalize project
	public void finishedProject() throws FileNotFoundException {

		// object to locate Project to finalize
		FindLines locateInLine = new FindLines();
		String data = locateInLine.findInLine();
		String position[] = data.split(",");

		/*
		 * if amount Due is less than amount paid print a invoice showing customer name
		 * and contact details write invoice on text document
		 */
		if (Long.parseLong(position[5]) > Long.parseLong(position[6])) {

			long difference = Long.parseLong(position[5]) - Long.parseLong(position[6]);

			System.out.println("Invoice\n");
			System.out.println("Customer: " + position[16] + "\n" + "\nContact Details" + position[17] + "\nCall:"
					+ position[18] + "\nEmail: " + position[19]);
			System.out.println("\nAmount Due: " + "R" + difference);
			try {
				FileWriter invoice = new FileWriter("invoice.txt");
				invoice.write("Invoice\n");
				invoice.write("Customer: " + position[16] + "\n" + "Contact Details\n" + "Call:" + position[17]
						+ "\nEmail: " + position[18] + "\nAddress: " + position[19]);
				invoice.write("\nAmount Due: " + "R" + difference);
				System.out.println("Invoice created!");
				invoice.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}

		// if amount is fully paid tell user
		else if (Long.parseLong(position[5]) > Long.parseLong(position[6])) {
			System.out.println("Amount fully paid");
		}

		try {
			// Connect to the PoisePMS database, via the jdbc:mysql: channel on localhost
			// (this PC)
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
					"otheruser", "Mongey12");

			// Create a direct line to the database for running our queries
			Statement statement = connection.createStatement();

			// Current Date
			Date d1 = new Date();
			String date = new SimpleDateFormat("yyyy-MM-dd").format(d1);

			// Add Completed Project to completed_projects table:
			statement.executeUpdate("INSERT INTO completed_projects VALUES (" + position[0] + ", '" + position[1] + "' "
					+ " ," + " '" + date + "' " + ")");
			System.out.println("done!");

		} catch (SQLException e) {
			// Catch SQLException
			e.printStackTrace();
		}

	}
}