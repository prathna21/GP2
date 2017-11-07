/**
 * @author Sammy Yang
 * @author TsengCyen Yang
 * @author Prathna Ung
 * @author Dat Huynh
 * Some codes where from Class Project 1 written by @author Brahma Dathan and Sarnath Ramnath
 */

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * The collection class for expense objects
 *
 */
public class ExpenseList implements Serializable {
	private static final long serialVersionUID = 1L;
	private static ExpenseList expenseList;
	private List<Expense> expenses = new LinkedList<Expense>();
	/**
	 *  constructor for singleton pattern
	 *
	 */
	public ExpenseList() {

	}
	/**
	 * Supports the singleton pattern
	 *
	 * @return The singleton object
	 */
	public static ExpenseList instance() {
		if (expenseList == null) {
			return (expenseList = new ExpenseList());
		} else {
			return expenseList;
		}
	}
	/**
	 * Inserts a expense into the collection
	 *
	 * @param expense
	 *            The expense to be inserted
	 * @return True if the expense could be inserted.
	 */
	public boolean insertExpense(Expense expense) {
		expenses.add(expense);
		return true;
	}
	/**
	 * This method gets the lists of expense
	 *
	 * @return the list of expense
	 */
	public List<Expense> getList() {
		if (expenses.isEmpty()) {
			return null;
		} else {
			return expenses;
		}
	}
	/**
	 * Get list of expense of the collection
	 *
	 * @return Iterator of expense.
	 */
	public Iterator<Expense> getExpenses() {
		Iterator<Expense> it = expenses.iterator();
		return it;
	}
	/**
	 * Override toString method
	 *
	 * @return expenseList toString method.
	 */
	@Override
	public String toString() {
		return "\n" + expenses.toString();
	}

}