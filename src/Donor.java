
/**
 * @author Sammy Yang
 * @author TsengCyen Yang
 * @author Prathna Ung
 * @author Dat Huynh
 * Some codes where from Class Project 1 written by @author Brahma Dathan and
 *         Sarnath Ramnath
 */
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Donor object, each donor have a name and phone number
 *
 */
public class Donor implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String phone;
	private String id;
	private static final String MEMBER_STRING = "D";
	private List<CreditCard> cards = new LinkedList<CreditCard>();
	private List<BankAccount> banks = new LinkedList<BankAccount>();
	private List<Transaction> transactions = new LinkedList<Transaction>();
	private List<Visitable> visitor = new LinkedList<Visitable>();

	/**
	 * Represents a single donor
	 *
	 * @param name
	 *            Name of the donor
	 * @param phone
	 *            Phone number of the donor
	 */
	public Donor(String name, String phone) {
		this.name = name;
		this.phone = phone;
		id = MEMBER_STRING + (DonorIdServer.instance()).getId();
	}

	/**
	 * Stores the card as issued to the donor
	 *
	 * @param card
	 *            The card to be issued
	 * @return True if the card could be issued else false.
	 */
	public boolean issue(CreditCard card) {
		if (cards.add(card)) {
			visitor.add(card);
			return true;
		}
		return false;
	}
	/**
	 * Stores the card as issued to the donor
	 *
	 * @param bank
	 *            The card to be issued
	 * @return True if the bank account could be issued else false.
	 */
	public boolean issue(BankAccount Bank) {
		if (banks.add(Bank)) {
			visitor.add(Bank);
			return true;
		}
		return false;
	}

	/**
	 * Remove credit card issued to the donor
	 *
	 * @param donorID
	 *            Id of donor
	 * @param cardNumber
	 *            The card number issued to donor
	 * @return True if the card is removed else false
	 */
	public boolean removeCreditCard(String donorID, String cardNumber) {
		for (ListIterator<CreditCard> iterator = cards.listIterator(); iterator.hasNext();) {
			CreditCard hold = iterator.next();
			String id1 = hold.getCreditCard();

			if (id1.equals(cardNumber)) {
				iterator.remove();
				System.out.println("\n- Credit card number: " + cardNumber + " was successfully removed -");
				return true;
			}
		}
		System.out.println("\n- Credit card number not found -");
		return false;
	}
	/**
	 * Remove bank account issued to the donor
	 *
	 * @param donorID
	 *            Id of donor
	 * @param bankAccount
	 *            The bank account number issued to donor
	 * @return True if the card is removed else false
	 */
	public boolean removeBankAccount(String donorID, String bankAccount) {
		for (ListIterator<BankAccount> iterator = banks.listIterator(); iterator.hasNext();) {
			BankAccount hold = iterator.next();
			String id1 = hold.getBankAccount();

			if (id1.equals(bankAccount)) {
				iterator.remove();
				System.out.println("\n- Bank Account number: " + bankAccount + " was successfully removed -");
				return true;
			}
		}

		System.out.println("\n- Bank Account number not found -");
		return false;
	}

	/**
	 * This method will remove all transactions associated with a removed Donor
	 * object
	 * Note: not used in this project
	 */
	public void removeTransaction() {
		Iterator<CreditCard> it = cards.iterator();
		while (it.hasNext()) {
			CreditCard obj = it.next();
			Iterator<Transaction> iter = transactions.iterator();
			while (iter.hasNext()) {
				Transaction transaction = iter.next();
				if (obj.getCreditCard().equals(transaction.getPaymentNumber())) {
					transactions.remove(transaction);
				}
			}

		}

	}

	/**
	 * Get information of the account issued 
	 */
	public void getAccountIssued() {
		String string = "| Donor Name: " + name + " | ID: " + id + " | Phone: " + phone + " |\n";
		getCardsIssued();
		getBanksIssued();
	}
	/**
	 * An iterator to the credit cards issued 
	 *
	 * @return Iterator to the collection of issued credit cards
	 */
	public Iterator<CreditCard> getCardsIssued() {

		String string = "";

		for (Iterator<CreditCard> iterator = cards.iterator(); iterator.hasNext();) {
			CreditCard card = iterator.next();
			string += "   - CreditCard: " + card.getCreditCard() + " | $" + card.getCardAmount() + "|\n";
		}
		toString();
		System.out.println(string);
		return (cards.listIterator());
	}
	/**
	 * An iterator to the bank account issued 
	 *
	 * @return Iterator to the collection of issued bank account
	 */
	public Iterator<BankAccount> getBanksIssued() {

		String string = "";

		for (Iterator<BankAccount> iterator = banks.iterator(); iterator.hasNext();) {
			BankAccount bank = iterator.next();
			string += "   - Bank Account: " + bank.getBankAccount() + " | $" + bank.getBankAmount() + "|\n";
		}

		toString();
		System.out.println(string);
		return (banks.listIterator());
	}

	/**
	 * Gets an iterator to a collection of selected transactions
	 *
	 * @return The iterator to the transaction collection
	 */
	public Iterator<Transaction> getTransactions() {

		Iterator<Transaction> it = transactions.iterator();
		while (it.hasNext()) {
			Transaction obj = it.next();
			System.out.println(obj);
		}
		return it;
	}




	/**
	 * Getter for name
	 *
	 * @return Donor name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for phone number
	 *
	 * @return Phone number
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Getter for id
	 *
	 * @return Donor id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter for name
	 *
	 * @param newName
	 *            Donor's new name
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Setter for phone
	 *
	 * @param newName
	 *            Donor's new phone
	 */
	public void setPhone(String newPhone) {
		phone = newPhone;
	}

	/**
	 * Checks whether the donor is equal to the one with the given id
	 *
	 * @param id
	 *            ID of the donor who should be compared
	 * @return True iff the donor ids match
	 */
	public boolean equals(String id) {
		return this.id.equals(id);
	}

	/**
	 * String form of the donor
	 *
	 */
	@Override
	public String toString() {
		String string = "| Donor Name: " + name + " | ID: " + id + " | Phone: " + phone + " |";
		return string;
	}

	/**
	 * Process all the credit card and bank account with amount assoicated with Donor
	 *
	 * @return Total amount process
	 */
	public double processDonations() {
		double total = 0;
		for (Iterator<CreditCard> cardIterator = getCardsIssued(); cardIterator.hasNext();) {
			CreditCard card = cardIterator.next();
			transactions.add(new Transaction("Credit Card", card.getCreditCard(), card.getCardAmount()));
			total += card.getCardAmount();
			card.setTimesProcessed(card.getTimesProcessed() + 1);
		}
		for (Iterator<BankAccount> bankIterator = getBanksIssued(); bankIterator.hasNext();) {
			BankAccount card = bankIterator.next();
			transactions.add(new Transaction("Bank Account", card.getBankAccount(), card.getBankAmount()));
			card.setTimesProcessed(card.getTimesProcessed() + 1);
			total += card.getBankAmount();
		}


		return total;
	}





}