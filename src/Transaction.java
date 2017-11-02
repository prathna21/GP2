/**
 * @author Sammy Yang
 * @author TsengCyen Yang
 * @author Prathna Ung
 * @author Dat Huynh Some codes where from Class Project 1 written by @author Brahma Dathan and
 *         Sarnath Ramnath
 */
import java.io.*;
import java.util.*;

/**
 * Represents a single Transaction (creditCard and amount) Use to add credit card number and amount
 * to a member
 *
 */

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private Calendar date;
    private String paymentNumber;
    private int amount;
    private String type;
    private int totalAmount;

    /**
     * Creates the transaction with a given credit card and amount donated. The date is the current
     * date.
     *
     * @param creditCard The credit card of transaction
     * @param amount The amount to donate
     *
     */
    public Transaction(String type, String paymentNumber, int amount) {
        this.paymentNumber = paymentNumber;
        this.amount = amount;
        this.setTotalAmount(amount);
        this.type = type;
        date = new GregorianCalendar();
        date.setTimeInMillis(System.currentTimeMillis());

    }
    public String getType(){
        return type;
    }

    /**
     * Returns the credit card field
     *
     * @return creditCard field
     */
    public String getPaymentNumber() {
        return paymentNumber;
    }

    /**
     * Set credit card field
     */
    public void setCreditCard(String paymentNumber) {
        this.paymentNumber = paymentNumber;
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
        return date.get(Calendar.MONTH) + "/" + date.get(Calendar.DATE) + "/" +
            date.get(Calendar.YEAR);

    }

    /**
     * String form of the transaction
     *
     */
    @Override
    public String toString() {
        if ( type.equals("Credit Card")) {
            return ("| Credit Card: " + paymentNumber + "\t| Payment: " +
                amount + "\t| Date: " + getDate());
        } else {
            return ("| Bank Account: " + paymentNumber + "\t| Payment: " +
                amount + "\t| Date: " + getDate());
        }

    }
    public int getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}