/**
 * @author Sammy Yang
 * @author TsengCyen Yang
 * @author Prathna Ung
 * @author Dat Huynh
 * Some codes where from Class Project 1 written by @author Brahma Dathan and Sarnath Ramnath
 */

import java.io.*;

/**
 * This class holds and manages the information of all credit card information
 * for the Organization
 */
public class BankAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    private String donorId;
    private String bankAccount;
    private int bankAmount;


    /**
     * This constructor sets the default values for general credit card information
     * assuming user defines all 4 variables
     *
     * @param donorId
     *            Donor's ID number
     * @param bankAccount
     *            Donor's bank Account number
     * @param amount
     *            Payment the donor will pay each pay cycle
     */

    public BankAccount(String donorId, String bankAccount, int bankAmount) {

        this.donorId = donorId;
        this.bankAccount = bankAccount;
        this.bankAmount = bankAmount;
    }

    /**
     * The removeCard method checks to see if incoming information is already
     * present, if so, the credit card can be removed, otherwise, it can't be
     * removed because it doesn't exist
     *
     * @param donorId
     * @param bankAccount
     * @return True if this bank account exists
     * @return False if this bank account doesn't exist
     */
    public boolean removeBankAccount(String donorId, String bankAccount) {
        if (this.donorId == donorId & this.bankAccount == bankAccount) {
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
     * The getBankAccount method returns the donor's bank account number
     *
     * @return Donor's bank account number
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * The getAmount method returns the payment amount the donor is paying each pay
     * cycle
     *
     * @return Payment donor pays per cycle
     */
    public int getBankAmount() {
        return bankAmount;
    }

    /**
     * The setBankAccount method allows the user to set or change the bank account
     * number
     *
     * @param newBankAccount
     *            Donor's desired bank account number
     */
    public void setbankAccount(String newBankAccount) {
        bankAccount = newBankAccount;
    }

    /**
     * The toString method take the information of this credit card and displays it
     * to the user is a more readable format
     */
    public String toString() {
        return "\n| Bank Account Info |\nDonor ID: " + getDonorId()
                + "\nBank Account: " + bankAccount + "\nPayment Rate: " + bankAmount + "\n-------------------------";
    }
}