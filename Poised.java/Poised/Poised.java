import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Poised {

	

	// main method
	public static void main(String[] args) {

		// Scanner to read user inputs
		Scanner edit = new Scanner(System.in);
		// method to handle user inputs
		try {
			userInput(edit);
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		// close the scanner
		edit.close();
	}

	// class to handle the low level code of user entering second input
	private static void userInput(Scanner edit) throws InputMismatchException {

		try {
			// Connect to the PoisePMS database, via the jdbc:mysql: channel on localhost
			// (this PC)
			// Use username "otheruser", password "Mongey12".
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
					"otheruser", "Mongey12");
			// Create a direct line to the database for running our queries
			Statement statement = connection.createStatement();
			int rowsAffected;

			// initialize variables for user input and to check if user input is correct
			String option = null;
			boolean validChoice2 = false;

			// while loop to loop through question to user if user input is invalid
			while (!validChoice2) {

				// print instruction to user
				System.out.println(
						"Welcome To Poised Project Manager\n\nEnter '1' to 'Display Project'\nEnter '2' to 'Add New Project'\nEnter '3' to edit 'Project'\nEnter '4' to finalise a project\nEnter '5' to 'Display Incomplete projects\nEnter '6'  to Display Projects Passed Their Deadline   ");

				// If user enters wrong input they should enter another input
				option = edit.nextLine();

				/*
				 * If user chooses this option, display other instruction for user and read user
				 * input
				 */
				
				//Display a Project
				if (option.equals("1")) {
					validChoice2 = true;

					// object to locate line
					FindLines locateInLine = new FindLines();
					String data = locateInLine.findInLine();

					System.out.println(data);

					String separate[] = data.split(",");

					// Create variables for new data from line
					int prjNumber = Integer.parseInt(separate[0]);
					String prjName = separate[1];
					String buildingType1 = separate[2];
					String physicalAddress1 = separate[3];
					int erfNumber1 = Integer.parseInt(separate[4]);
					long totalFeeCharged1 = Long.parseLong(separate[5]);
					long amountPaidToDate1 = Long.parseLong(separate[6]);
					String prjdeadLine = separate[7];
					String aName = separate[8];
					String aTelephoneNumber = separate[9];
					String aEmail = separate[10];
					String aPhysicalAddress = separate[11];
					String cName = separate[12];
					String cTelephoneNumber = separate[13];
					String cEmail = separate[14];
					String cPhysicalAddress = separate[15];
					String cuName = separate[16];
					String cuTelephoneNumber = separate[17];
					String cuEmail = separate[18];
					String cuPhysicalAddress = separate[19];

					// Object for Project
					Project project = new Project(prjNumber, prjName, buildingType1, physicalAddress1, erfNumber1,
							totalFeeCharged1, amountPaidToDate1, prjdeadLine);

					/*
					 * Print out the string from method from Project class together with object
					 * parameters
					 */
					System.out.println("\nProject\n");
					System.out.println(project + "\n");

					Person architect = new Architect(aName, aTelephoneNumber, aEmail, aPhysicalAddress);

					/*
					 * Print out the string from method from Person class together with object
					 * parameters
					 */
					System.out.println("\nArchitect\n");
					System.out.println(architect);

					// Object for person
					Person contractor = new Contractor(cName, cTelephoneNumber, cEmail, cPhysicalAddress);

					/*
					 * Print out the string from method from Person class together with object
					 * parameters
					 */
					System.out.println("\nContractor\n");
					System.out.println(contractor);

					// Object for person
					Person customer = new Customer(cuName, cuTelephoneNumber, cuEmail, cuPhysicalAddress);

					/*
					 * Print out the string from method from Person class together with object
					 * parameters
					 */
					System.out.println("\nCustomer\n");
					System.out.println(customer);

					/*
					 * If user chooses this option display other instruction for user and read user
					 * input
					 */

				}
				
				// Add new project
				else if (option.equals("2")) {
					validChoice2 = true;

					// declare and initialize object using Add projects class to add new project
					AddProjects newProject = new AddProjects();
					newProject.addToDatabase();

				}
				
				/*
				 * If user chooses this option display other instruction for user and read user
				 * input
				 */
				
				//Edit Project
				else if (option.equals("3")) {
					validChoice2 = true;

					System.out.println("Please enter Project Number of the project you wish to edit: ");
					String replace = edit.next();

					System.out.println(
							"Enter '1' to edit Amount Paid:\nEnter '2' to edit Dead Line:\nEnter '3' to edit Architects details\nEnter '4' to edit Contractors details\nEnter '5' to edit Customers details  ");
					String choice = edit.next();

					//Edit amount paid
					if (choice.equals("1")) {

						System.out.println("Please enter Updated 'Amount Paid': ");
						String newAmount = edit.next();
						long amount = Long.parseLong(newAmount);

						rowsAffected = statement.executeUpdate(
								"UPDATE project SET Amount_paid=" + amount + " WHERE Project_Number  =" + replace);
						System.out.println("Query complete, " + rowsAffected + " rows updated.");

					}
					// set new deadline
					else if (choice.equals("2")) {

						System.out.println("Please enter Updated 'Deadline'(YYYY-MM-DD): ");
						String deadline = edit.next();

						// executeQuery. Run an Update statement
						rowsAffected = statement.executeUpdate(
								"UPDATE project SET Deadline=" + deadline + " WHERE Project_Number  =" + replace);
						System.out.println("Query complete, " + rowsAffected + " rows updated.");

					}

					//Edit Architect details
					else if (choice.equals("3")) {

						Scanner scan1 = new Scanner(System.in);

						System.out.println("Please enter Updated 'Architect name': ");
						String name = scan1.nextLine();

						System.out.println("Please enter Updated 'Architect Telephone': ");
						String call = scan1.nextLine();

						System.out.println("Please enter Updated 'Architect Email': ");
						String email = scan1.nextLine();

						System.out.println("Please enter Updated 'Architect Physical Adress': ");
						String address = scan1.nextLine();
						
						scan1.close();

						// executeQuery. Run an Update statement
						rowsAffected = statement.executeUpdate("UPDATE project p, architect_details a "
								+ "SET p. Architect_Name=" + " '" + name + "' " + "," + " a.Architect_Name =" + " '"
								+ name + "' " + "," + " a.Architect_Telephone =" + " '" + call + "' " + ","
								+ " a.Architect_Email =" + " '" + email + "' " + "," + " a.Architect_Address =" + " '"
								+ address + "' " + " WHERE p.Architect_Name  = a.Architect_Name"
								+ " AND p.Project_Number  =" + replace);
						System.out.println("Done!");

					}
					
					// Edit Contractor details
					else if (choice.equals("4")) {

						Scanner scan2 = new Scanner(System.in);

						System.out.println("Please enter Updated 'Contractor name': ");
						String name = scan2.nextLine();

						System.out.println("Please enter Updated 'Contractor Telephone': ");
						String call = scan2.nextLine();

						System.out.println("Please enter Updated 'Contractor Email': ");
						String email = scan2.nextLine();

						System.out.println("Please enter Updated 'Contractor Physical Adress': ");
						String address = scan2.nextLine();

						scan2.close();
						
						// executeQuery. Run an Update statement
						rowsAffected = statement.executeUpdate("UPDATE project p, contractor_details co "
								+ "SET p. Architect_Contractor=" + " '" + name + "' " + "," + " co.Contractor_Name ="
								+ " '" + name + "' " + "," + " co.Contractor_Telephone =" + " '" + call + "' " + ","
								+ " co.Contractor_Email =" + " '" + email + "' " + "," + " co.Contractor_Address ="
								+ " '" + address + "' " + " WHERE p.Contractor_Name  = co.Contractor_Name"
								+ " AND p.Project_Number  =" + replace);
						System.out.println("Done!");

					}

					//Edit Customer details
					else if (choice.equals("5")) {

						Scanner scan3 = new Scanner(System.in);

						System.out.println("Please enter Updated 'Customer name': ");
						String name = scan3.nextLine();

						System.out.println("Please enter Updated 'Customer Telephone': ");
						String call = scan3.nextLine();

						System.out.println("Please enter Updated 'Customer Email': ");
						String email = scan3.nextLine();

						System.out.println("Please enter Updated 'Customer Physical Adress': ");
						String address = scan3.nextLine();

						scan3.close();
						
						// executeQuery. Run an Update statement
						rowsAffected = statement.executeUpdate("UPDATE project p, architect_details cu "
								+ "SET p. Customer_Name=" + " '" + name + "' " + "," + " cu.Customer_Name =" + " '"
								+ name + "' " + "," + " cu.Customer_Telephone =" + " '" + call + "' " + ","
								+ " cu.Customer_Email =" + " '" + email + "' " + "," + " cu.Customer_Address =" + " '"
								+ address + "' " + " WHERE p.Customer_Name  = cu.Customer_Name"
								+ " AND p.Project_Number  =" + replace);
						System.out.println("Done!");

					}
				}

				/*
				 * If user chooses this option display other instruction for user and read user
				 * input
				 */
				
				//Finalize project
				else if (option.equals("4")) {
					validChoice2 = true;

					Finalize complete = new Finalize();
					try {
						complete.finishedProject();
					} catch (FileNotFoundException e) {
						// catch FileNotFoundException
						e.printStackTrace();
					}

				}
				/*
				 * If user chooses this option display other instruction for user and read user
				 * input
				 */
				
				//Display Incomplete Projects
				else if (option.equals("5")) {
					validChoice2 = true;

					/*
					 * declare and initialize object using Display incomplete projects class to
					 * display incomplete projects to user
					 */
					DisplayIncomplete inprogress = new DisplayIncomplete();
					inprogress.incomplete();

				}

				/*
				 * If user chooses this option display other instruction for user and read user
				 * input
				 */
				
				//Display Overdue Projects
				else if (option.equals("6")) {
					validChoice2 = true;

					/*
					 * declare and initialize object using Past deadline class to display overdue
					 * projects
					 */
					PastDeadline deadLine = new PastDeadline();
					String data = deadLine.pastDue();

					String separate[] = data.split(",");

					// Create variables for new data from line
					int prjNumber = Integer.parseInt(separate[0]);
					String prjName = separate[1];
					String buildingType1 = separate[2];
					String physicalAddress1 = separate[3];
					int erfNumber1 = Integer.parseInt(separate[4]);
					long totalFeeCharged1 = Long.parseLong(separate[5]);
					long amountPaidToDate1 = Long.parseLong(separate[6]);
					String prjdeadLine = separate[7];
					String aName = separate[8];
					String aTelephoneNumber = separate[9];
					String aEmail = separate[10];
					String aPhysicalAddress = separate[11];
					String cName = separate[12];
					String cTelephoneNumber = separate[13];
					String cEmail = separate[14];
					String cPhysicalAddress = separate[15];
					String cuName = separate[16];
					String cuTelephoneNumber = separate[17];
					String cuEmail = separate[18];
					String cuPhysicalAddress = separate[19];

					// Object for Project
					Project project = new Project(prjNumber, prjName, buildingType1, physicalAddress1, erfNumber1,
							totalFeeCharged1, amountPaidToDate1, prjdeadLine);

					/*
					 * Print out the string from method from Project class together with object
					 * parameters
					 */
					System.out.println("\nProject\n");
					System.out.println(project + "\n");

					Person architect = new Architect(aName, aTelephoneNumber, aEmail, aPhysicalAddress);

					/*
					 * Print out the string from method from Person class together with object
					 * parameters
					 */
					System.out.println("\nArchitect\n");
					System.out.println(architect);

					// Object for person
					Person contractor = new Contractor(cName, cTelephoneNumber, cEmail, cPhysicalAddress);

					/*
					 * Print out the string from method from Person class together with object
					 * parameters
					 */
					System.out.println("\nContractor\n");
					System.out.println(contractor);

					// Object for person
					Person customer = new Customer(cuName, cuTelephoneNumber, cuEmail, cuPhysicalAddress);

					/*
					 * Print out the string from method from Person class together with object
					 * parameters
					 */
					System.out.println("\nCustomer\n");
					System.out.println(customer);

					/*
					 * If user chooses this option display other instruction for user and read user
					 * input
					 */

				}

			}
		} catch (SQLException e) {
			// catch a SQLException
			e.printStackTrace();
		}

	}
}
