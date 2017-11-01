
/**
 * @author Sammy Yang
 * @author TsengCyen Yang
 * @author Prathna Ung
 * @author Dat Huynh
 * Some codes where from Class Project 1 written by @author Brahma Dathan and Sarnath Ramnath
 */

import java.io.*;
import java.util.*;

public class Organization implements Serializable {
	private static final long serialVersionUID = 1L;
	private DonorList donorList;
	private ExpenseList expenseList;
	private static Organization organization;

	public Organization() {
		donorList = donorList.instance();
		expenseList = expenseList.instance();
	}

	/**
	 * Supports the singleton pattern
	 *
	 * @return the singleton object
	 */
	public static Organization instance() {
		if (organization == null) {
			DonorIdServer.instance();
			return (organization = new Organization());
		} else {
			return organization;
		}

	};

	/**
	 * Add a new donor
	 *
	 * @param name
	 *            Name of donor
	 * @param phone
	 *            Phone number of donor
	 * @return True if it's added
	 */
	public Donor addDonor(String name, String phone) {
		Donor donor = new Donor(name, phone);
		if (donorList.insertDonor(donor)) {
			return (donor);
		}
		return null;
	}

	public String addExpense(String description, int expenseAmount) {
		Expense expense = new Expense(description, expenseAmount);
		if (expenseList.insertExpense(expense)) {
			return "Pass";
		}
		return null;
	}

	/**
	 * Add a new credit card to a donor
	 *
	 * @param donorID
	 *            The ID of donor
	 * @param creditCard
	 *            Credit card of donor
	 * @param amount
	 *            Credit card amount to be donated each month
	 * @return True if it's added
	 */
	public CreditCard addCreditCard(String donorID, String creditCard, int amount) {
		CreditCard card = new CreditCard(donorID, creditCard, amount);
		return card;
	}
	public BankAccount addBankAccount(String donorID, String bankAccount, int amount) {
        BankAccount bank = new BankAccount(donorID, bankAccount, amount);
        return bank;
    }
	/**
	 * Process the donation for each donor that has a credit card or multiple credit
	 * cards with amount. The total amount will be displayed.
	 */
	public void processDonation() {

		System.out.println("");
		Iterator<Donor> it = donorList.getList().iterator();
		int total = 0;
		while (it.hasNext()) {
			Donor obj = it.next();
			total += obj.processDonations();
		}
		System.out.println("| The total amount of donations is : " + total + " |");
	}

	/**
	 * List all the transaction that has been process for each credit cards and
	 * amount
	 *
	 * @return credit cards and amount
	 */
	public Iterator listAllTransaction() {
		if (donorList.getList() != null) {
			Iterator<Donor> result = donorList.getList().iterator();
			while (result.hasNext()) {
				result.next().getTransactions();
			}
			return result;
		} else {
			return null;
		}
	}

	/**
	 * Display all the donor name and phone number in the organization
	 */
	public void listAllDonors() {
		Iterator<Donor> it = donorList.getDonor();

		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	/**
	 * Display all the expense's descriptions and amounts in the organization
	 */
	public void listAllExpenses() {
		boolean showList = false;
		Iterator<Expense> it = expenseList.getExpenses();

		if (it.hasNext()) {
			showList = true;
		}

		if (showList) {
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		} else {
			System.out.println("| No Expenses to Show |");
		}
	}

	/**
	 * Display selected donor name, ID, phone number, credit card and amount
	 * information
	 *
	 * @param donorID
	 *            The ID of donor
	 * @return donor information
	 */
	public void listSpecificDonor(String donorID) {
		Donor donor = donorList.searchDonor(donorID);
		if (donor == null) {
			System.out.println("\n- No such member -");
			return;
		}
		donor.getCardsIssued();
		donor.getBanksIssued();
	}

	/**
	 * Search for donor ID and remove the donor
	 *
	 * @param donorID
	 *            The ID of donor
	 * @return True when donor has been removed
	 */
	public boolean removeDonor(String donorID) {
		Donor donor = donorList.searchDonor(donorID);
		if (donor == null) {
			System.out.println("\n- No such member -");
			return false;
		}
		return donorList.removeDonor(donorID);
	}

	/**
	 * Search for donor ID and credit card number and remove the credit card
	 *
	 * @param donorID
	 *            The ID of donor
	 * @param cardNumber
	 *            The credit card number of donor
	 * @return True when credit card has been removed
	 */

	public boolean removeBankAccount(String donorID, String bankAccount) {
		Donor donor = donorList.searchDonor(donorID);
		if (donor == null) {
			System.out.println("\n- No such member -");
			return false;
		}
		return donor.removeBankAccount(donorID, bankAccount);
	}

	public boolean removeCreditCard(String donorID, String cardNumber) {
		Donor donor = donorList.searchDonor(donorID);
		if (donor == null) {
			System.out.println("\n- No such member -");
			return false;
		}
		return donor.removeCreditCard(donorID, cardNumber);
	}

	/**
	 * Search for selected donor
	 *
	 * @return Donor is return if found
	 */
	public Donor searchDonor(String donorID) {
		return donorList.searchDonor(donorID);
	}

	/**
	 * Get all the transaction that have been process for selected donor
	 *
	 * @param donorID
	 *            The ID of donor
	 * @return All the transacations for selected donor
	 */
	public Iterator getTransaction(String donorID) {
		Donor donor = donorList.searchDonor(donorID);
		if (donor == null) {
			return (null);
		}
		return donor.getTransactions();

	}

	/**
	 * Exit program
	 */
	public void exit() {
		System.exit(0);
	}

	/**
	 * This method saves the current state of the overall data
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("OrganizationData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(organization);
			output.writeObject(DonorIdServer.instance());

			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	/**
	 * This method retrieve the save data
	 */
	public static Organization retrieve() {
		try {
			FileInputStream file = new FileInputStream("OrganizationData");
			ObjectInputStream input = new ObjectInputStream(file);
			organization = (Organization) input.readObject();
			DonorIdServer.retrieve(input);
			return organization;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}

}