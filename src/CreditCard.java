
/**
 * @author Sammy Yang
 * @author TsengCyen Yang
 * @author Prathna Ung
 * @author Dat Huynh
 * Some codes where from Class Project 1 written by @author Brahma Dathan and Sarnath Ramnath
 */

import java.io.Serializable;

/**
 * This class holds and manages the information of all credit card information
 * for the Organization
 */
public class CreditCard implements Serializable, Visitable {
	private static final long serialVersionUID = 1L;
	private String donorId;
	private String creditCard;
	private int cardAmount;
	private int timesProcessed = 0;


	/**
	 * This constructor sets the default values for general credit card information
	 * assuming user defines all 3 variables
	 *
	 * @param donorId
	 *            Donor's ID number
	 * @param creditCard
	 *            Donor's credit card number
	 * @param amount
	 *            Payment the donor will pay each pay cycle
	 */

	public CreditCard(String donorId, String creditCard, int amount) {

		this.donorId = donorId;
		this.creditCard = creditCard;
		this.cardAmount = amount;
	}

	/**
	 * The removeCard method checks to see if incoming information is already
	 * present, if so, the credit card can be removed, otherwise, it can't be
	 * removed because it doesn't exist
	 *
	 * @param donorId
	 * @param creditCard
	 * @return True if this card exists
	 * @return False if this card doesn't exist
	 */
	public boolean removeCard(String donorId, String creditCard) {
		if (this.donorId == donorId & this.creditCard == creditCard) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * The getDonorId method returns the donor's ID number
	 *
	 * @return Donor's ID number
	 */
	public String getDonorId() {
		return donorId;
	}

	/**
	 * The getCreditCard method returns the donor's credit card number
	 *
	 * @return Donor's credit card number
	 */
	public String getCreditCard() {
		return creditCard;
	}

	/**
	 * The getAmount method returns the payment amount the donor is paying each pay
	 * cycle
	 *
	 * @return Payment donor pays per cycle
	 */
	public int getCardAmount() {
		return cardAmount;
	}

	/**
	 * The setCreditCard method allows the user to set or change the credit card
	 * number
	 *
	 * @param newCardNumber
	 *            Donor's desired credit card number
	 */
	public void setCreditCard(String newCardNumber) {
		creditCard = newCardNumber;
	}

	/**
	 * The toString method take the information of this credit card and displays it
	 * to the user is a more readable format
	 */
	public String toString() {
		return "\n| Credit Card Info |\nDonor ID: " + getDonorId()
				+ "\nCredit Card: " + creditCard + "\nPayment Rate: " + cardAmount + "\n-------------------------";
	}

	/**
	 * The method will keep track of how many times the card was processed
	 * 
	 * @param processed The amount of times processed
	 */
	public void setTimesProcessed(int processed) {
		timesProcessed = processed;
	}

	/**
	 * This method will return the amount of times the card was processed
	 */
	public int getTimesProcessed() {
		return timesProcessed;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see Visitable#accept(Visitor)
	 */
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);

	}
}