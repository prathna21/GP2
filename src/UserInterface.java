
/**
 * @author Sammy Yang
 * @author TsengCyen Yang
 * @author Prathna Ung
 * @author Dat Huynh
 * Some codes where from Class Project 1 written by @author Brahma Dathan and Sarnath Ramnath
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * This is the user interface of the Organization
 */
public class UserInterface implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int EXIT = 0;
	private static final int ADD_DONOR = 1;
	private static final int ADD_CREDITCARD = 2;
	private static final int PROCESS_DONATION = 3;
	private static final int LIST_TRANSACTION = 4;
	private static final int LIST_ALL_DONORS = 5;
	private static final int LIST_DONOR = 6;
	private static final int REMOVE_DONOR = 7;
	private static final int REMOVE_CREDITCARD = 8;
	private static final int SAVE = 9;
	private static final int HELP = 10;

	// WORK IN PROGRESS
	private static final int ADD_EXPENSE = 11;
	private static final int LIST_ALL_EXPENSES = 12;

	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static UserInterface userInterface;
	private static Organization organization;

	/**
	 * Made private for singleton pattern. Conditionally looks for any saved data.
	 * Otherwise, it gets a singleton Library object.
	 */
	private UserInterface() {
		if (yesOrNo("| Look for saved data and use it?")) {
			retrieve();
		} else {
			organization = Organization.instance();
		}
	}

	/**
	 * Gets a token after prompting
	 *
	 * @param prompt
	 *            - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 *
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	/**
	 * Prompts for a command from the keyboard
	 *
	 * @return a valid command
	 *
	 */
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("\n| Enter command ('10' Shows all Commands) |"));
				if (value >= EXIT && value <= LIST_ALL_EXPENSES) {
					return value;
				} else {
					System.out.println("\n- Not a valid Command - Command Values are '1' to '10' -\n");
				}
			} catch (NumberFormatException nfe) {
				System.out.println("\n- Not a valid Command - Enter a number -\n");
			}
		} while (true);
	}

	/**
	 * Method to be called for retrieving saved data. Uses the appropriate Library
	 * method for retrieval.
	 *
	 */
	private void retrieve() {
		try {
			if (organization == null) {
				organization = Organization.retrieve();
				if (organization != null) {
					System.out.println(
							"\n| The organization has been successfully retrieved from the file OrganizationData |");
				} else {
					System.out.println("| File doesnt exist; creating new organization |");
					organization = Organization.instance();
				}
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * Supports the singleton pattern
	 *
	 * @return the singleton object
	 */
	public static UserInterface instance() {

		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

	/**
	 * Orchestrates the whole process. Calls the appropriate method for the
	 * different functionalities.
	 *
	 */
	public void process() {
		System.out.println("\n| Enter in a number from 0-10 (Enter in 10 for a list of commands) |");
		int command;
		help();

		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ADD_DONOR:
				addDonor();
				break;
			case ADD_CREDITCARD:
				addCreditCard();
				break;
			case PROCESS_DONATION:
				processDonations();
				break;
			case LIST_TRANSACTION:
				listTransactions();
				break;
			case LIST_ALL_DONORS:
				listAllDonors();
				break;
			case LIST_DONOR:
				listDonor();
				break;
			case REMOVE_DONOR:
				removeDonor();
				break;
			case REMOVE_CREDITCARD:
				removeCreditCard();
				break;
			case SAVE:
				save();
				break;
			case HELP:
				help();
				break;
			case ADD_EXPENSE:
				addExpense();
				break;
			case LIST_ALL_EXPENSES:
				listAllExpenses();
				break;
			default:
				System.out.println("- Please select a VALID option -\n");
				break;
			}
		}
	}

	/**
	 * Remove credit card selected if donor has one by searching for donor and
	 * credit card number
	 */
	public void removeCreditCard() {
		String donorID = getToken("| Enter Donor ID |");
		String cardNumber = getToken("| Enter Card Number |");
		organization.removeCreditCard(donorID, cardNumber);
	}

	/**
	 * Remove select donor by searching for donor ID
	 */
	private void removeDonor() {
		String donorID = getToken("| Enter Donor ID |");
		organization.removeDonor(donorID);

	}

	/**
	 * List all a selected donor by donor ID, display the donor name, ID, phone
	 * number, credit card, and amount
	 */
	private void listDonor() {

		String donorID = getToken("| Enter Donor ID |");
		organization.listSpecificDonor(donorID);

	}

	/**
	 * This method lists all donors currently in the organization, diplay name and
	 * phone number
	 */
	private void listAllDonors() {
		System.out.println("");
		organization.listAllDonors();
	}

	/**
	 * This method lists all of the transactions that have taken place assuming the
	 * donor is still with the oraganization
	 */
	private void listTransactions() {
		if (yesOrNo("| Would you like to continue to view all the transaction? -") == true) {
			Iterator result = organization.listAllTransaction();

			if (result == null) {
				System.out.println("\n| There are no transactions to show |\n");
			} else {
				System.out.println("\n| Transactions |");
				while (result.hasNext()) {
					Transaction transaction = (Transaction) result.next();
					System.out.println(" - " + transaction.toString());
				}
				System.out.println("| End of transactions |\n");
			}
		}
	}

	/**
	 * This method adds a new Donor to the organization
	 */
	public void addDonor() {

		String name = getToken("| Enter Donor name |");
		String phone = getToken("| Enter phone |");
		Donor result;
		result = organization.addDonor(name, phone);
		if (result == null) {
			System.out.println("- Could not add member -");
		} else {
			System.out.println("\n| Member Successfully Created |");
		}
		System.out.println(result);
	}

	/**
	 *
	 */
	public void addCreditCard() {
		String donorID = getToken("| Enter Donor ID |");
		if (organization.searchDonor(donorID) == null) {
			System.out.println("- No such member -");
			return;
		}
		String Cardnumber = getToken("| Enter Card Number |");
		int amount = Integer.parseInt(getToken("| Enter amount |"));

		CreditCard card;
		card = organization.addCreditCard(donorID, Cardnumber, amount);
		if (card != null) {
			organization.searchDonor(donorID).issue(card);
			System.out.println(card);
		} else {
			System.out.println("- Credit card could not be added -");
		}
	}

	/**
	 * Process the donation for each donor that has a credit card or multiple credit
	 * cards with amount. The total amount will be displayed.
	 */
	private void processDonations() {
		if (yesOrNo("| Would you like to continue with process donation for all donors? -") == true) {
			organization.processDonation();
		}
	}

	/**
	 * Exit program
	 */
	private void exit() {
		organization.exit();

	}

	/**
	 * This method retrieve the save data
	 */
	private void save() {
		if (organization.save()) {
			System.out.println("\n| The organization has been successfully saved in the file OrganizationData |\n");
		} else {
			System.out.println("- There has been an error in saving -\n");
		}
	}

	/**
	 * Displays the help screen
	 *
	 */
	public void help() {
		System.out.println(" 0 = Exit the application\t6 = List a Specific Donor");
		System.out.println(" 1 = Add a donor\t\t7 = Remove donor");
		System.out.println(" 2 = Add a credit card\t\t8 = Remove credit card");
		System.out.println(" 3 = Process donation\t\t9 = Save");
		System.out.println(" 4 = List Transactions\t\t10 = Help");
		System.out.println(" 5 = List All Donors\n");
	}

	public void addExpense() {
		String description = getToken("| Enter a Description |");
		int expenseAmount = Integer.parseInt(getToken("| Enter expense amount |"));

		String result;
		result = organization.addExpense(description, expenseAmount);
		if (result == null) {
			System.out.println("- Could not add expense -");
		} else {
			System.out.println("\n| Expense Successfully Added |");
		}
	}

	public void listAllExpenses() {
		System.out.println("");
		organization.listAllExpenses();
	}

	/**
	 * Queries for a yes or no and returns true for yes and false for no
	 *
	 * @param prompt
	 *            The string to be prepended to the yes/no prompt
	 * @return true for yes and false for no
	 *
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no |");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	/**
	 * The method to start the application. Simply calls process().
	 *
	 * @param args
	 *            not used
	 */
	public static void main(String[] s) {
		UserInterface.instance().process();
	}

}