/**
 * @author Sammy Yang
 * @author TsengCyen Yang
 * @author Prathna Ung
 * @author Dat Huynh
 * Some codes where from Class Project 1 written by @author Brahma Dathan and Sarnath Ramnath
 */

import java.io.Serializable;
/**
 * Expense object, each expense have a description and amount
 *
 */
public class Expense implements Serializable {
	private static final long serialVersionUID = 1L;
	private String description;
	private int expenseAmount;
	/**
	 * Represents a single expense
	 *
	 * @param description
	 *            Name of the description
	 * @param amount
	 *            amount of description
	 */
	public Expense(String description, int amount) {
		this.description = description;
		this.expenseAmount = amount;
	}
	/**
	 * Getter for description
	 *
	 * @return description of expense
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Getter for expense amount
	 *
	 * @return expense amount 
	 */
	public int getExpenseAmount() {
		return expenseAmount;
	}
	/**
	 * Setter for description
	 *
	 * @param description
	 *            description of the expense
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Setter for expense amount
	 *
	 * @param expenseAmount
	 *            the amount of the expense
	 */
	public void setExpenseAmount(int expenseAmount) {
		this.expenseAmount = expenseAmount;
	}
	/**
	 * Override toString method
	 *
	 * @return expense toString method.
	 */
	@Override
	public String toString() {
		return "\nDescription: " + description + "\nExpense Amount: " + expenseAmount;

	}
}