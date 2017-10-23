
/**
 * @author Sammy Yang
 * @author TsengCyen Yang
 * @author Prathna Ung
 * @author Dat Huynh
 * Some codes where from Class Project 1 written by @author Brahma Dathan and Sarnath Ramnath
 */
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Represents a single Transaction (creditCard and amount) Use to add credit
 * card number and amount to a member
 *
 */

public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
	private Calendar date;
	private String creditCard;
	private int amount;

	/**
	 * Creates the transaction with a given credit card and amount donated. The date
	 * is the current date.
	 *
	 * @param creditCard
	 *            The credit card of transaction
	 * @param amount
	 *            The amount to donate
	 *
	 */
	public Transaction(String creditCard, int amount) {
		this.creditCard = creditCard;
		this.amount = amount;
		date = new GregorianCalendar();
		date.setTimeInMillis(System.currentTimeMillis());

	}

	/**
	 * Returns the credit card field
	 *
	 * @return creditCard field
	 */
	public String getCreditCard() {
		return creditCard;
	}

	/**
	 * Set credit card field
	 */
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	/**
	 * Returns the amount field
	 *
	 * @return amount field
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Set amount field
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * Returns the date as a String
	 *
	 * @return Date with month, date, and year
	 */
	public String getDate() {
		return date.get(Calendar.MONTH) + "/" + date.get(Calendar.DATE) + "/" + date.get(Calendar.YEAR);

	}

	/**
	 * String form of the transaction
	 *
	 */
	@Override
	public String toString() {
		return ("| Credit Card: " + creditCard + "\t| Payment: " + amount + "\t| Date: " + getDate());
	}
}