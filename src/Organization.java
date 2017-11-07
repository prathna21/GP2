
/**
 * @author Sammy Yang
 * @author TsengCyen Yang
 * @author Prathna Ung
 * @author Dat Huynh
 * Some codes where from Class Project 1 written by @author Brahma Dathan and
 *         Sarnath Ramnath
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Organization implements Serializable {
	private static final long serialVersionUID = 1L;
	private DonorList donorList;
	private ExpenseList expenseList;
	private static Organization organization;
	private int totalDonated = 0;
	private int totalExpense = 0;
	private List<Visitable> vList = new LinkedList<Visitable>();
	/**
	 *  constructor for singleton pattern
	 *
	 */
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
	 * @return donor if it's added
	 */
	public Donor addDonor(String name, String phone) {
		Donor donor = new Donor(name, phone);
		if (donorList.insertDonor(donor)) {
			return (donor);
		}
		return null;
	}
	/**
	 * Add a new expense
	 *
	 * @param description
	 *            Name of description
	 * @param expenseAmount
	 *           amount of description
	 * @return amount if it's added
	 */
	public int addExpense(String description, int expenseAmount) {
		Expense expense = new Expense(description, expenseAmount);
		if (expenseList.insertExpense(expense)) {
			setTotalExpense(expenseAmount);
			return expenseAmount;
		} else {
			return 0;
		}
	}
	/**
	 * information of orginazation balance
	 * 
	 * 
	 * @return total amount that have been donated minus the expense
	 */
	public int organizationInfo() {

		return getTotalDonated() - getTotalExpense();
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
	 * @return credit card if it's added
	 */
	public CreditCard addCreditCard(String donorID, String creditCard, int amount) {
		CreditCard card = new CreditCard(donorID, creditCard, amount);
		vList.add(card);
		return card;
	}
	/**
	 * Add a new bank account to a donor
	 *
	 * @param donorID
	 *            The ID of donor
	 * @param bankAccount
	 *            bank account of donor
	 * @param amount
	 *            bank account amount to be donated each month
	 * @return bank account if it's added
	 */
	public BankAccount addBankAccount(String donorID, String bankAccount, int amount) {
		BankAccount bank = new BankAccount(donorID, bankAccount, amount);
		vList.add(bank);
		return bank;
	}

	/**
	 * Process the donation for each donor that has a credit card or multiple credit
	 * cards with amount. Same with bank account. The total amount will be displayed.
	 * A transaction will be kept
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
		setTotalDonated(total);
	}

	/**
	 * List all the transaction that has been process for each credit cards and
	 * amount. Same for bank account
	 *
	 * @return interator for credit cards and bank account
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
	 * list the payment info for all credit card and bank account and 
	 * the number of transaction that have been process at a given theshold
	 *
	 * @param threshold
	 *            threshold limit that will be displayed
	 */
	public void listPaymentInfo(int threshold) {
		Visitor v = new VisitorObj();
		for (int i = 0; i < vList.size(); i++) {
			if (vList.get(i) instanceof CreditCard) {
				CreditCard card = (CreditCard) vList.get(i);
				if (card.getCardAmount() < threshold) {
					v.visit(card);
				}
			}
				if (vList.get(i) instanceof BankAccount) {
					BankAccount bank = (BankAccount) vList.get(i);
					if (bank.getBankAmount() < threshold) {
						v.visit(bank);
					}

				}


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
	public boolean listAllExpenses() {
		if (expenseList.getList() != null) {
			Iterator<Expense> it = expenseList.getExpenses();
			System.out.println("| List of All Expense |");
			while (it.hasNext()) {
				System.out.println(it.next());
			}
			return true;
		}
		return false;

	}

	/**
	 * Display selected donor name, ID, phone number, credit card and amount
	 * information
	 *
	 * @param donorID
	 *            The ID of donor
	 * 
	 */
	public void listSpecificDonor(String donorID) {
		Donor donor = donorList.searchDonor(donorID);
		if (donor == null) {
			System.out.println("\n- No such member -");
			return;
		}
		donor.getAccountIssued();
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
	 * Search for donor ID and bank account number and remove the bank account
	 *
	 * @param donorID
	 *            The ID of donor
	 * @param bankAccount
	 *            The bank account number of donor
	 * @return True when bank account has been removed
	 */

	public boolean removeBankAccount(String donorID, String bankAccount) {
		Donor donor = donorList.searchDonor(donorID);
		if (donor == null) {
			System.out.println("\n- No such member -");
			return false;
		}
		return donor.removeBankAccount(donorID, bankAccount);
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
	/**
	 * Getter for total donated
	 *
	 * @return total amount donated
	 */
	public int getTotalDonated() {
		return totalDonated;
	}
	/**
	 * Setter for total donated
	 *
	 * @param totalDonated
	 *            total amount donated
	 */
	public void setTotalDonated(int totalDonated) {
		this.totalDonated += totalDonated;
	}
	/**
	 * Getter for total expense
	 *
	 * @return total expense amount
	 */
	public int getTotalExpense() {
		return totalExpense;
	}
	/**
	 * Setter for total expense
	 *
	 * @param totalExpense
	 *            total expense amount
	 */
	public void setTotalExpense(int totalExpense) {
		this.totalExpense += totalExpense;
	}


}