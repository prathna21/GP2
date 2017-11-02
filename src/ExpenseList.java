import java.io.*;
import java.util.*;

public class ExpenseList implements Serializable {
	private static final long serialVersionUID = 1L;
	private static ExpenseList expenseList;
	private List<Expense> expenses = new LinkedList<Expense>();

	public ExpenseList() {

	}

	public static ExpenseList instance() {
		if (expenseList == null) {
			return (expenseList = new ExpenseList());
		} else {
			return expenseList;
		}
	}

	public boolean insertExpense(Expense expense) {
		expenses.add(expense);
		return true;
	}
	public List<Expense> getList() {
        if (expenses.isEmpty()) {
            return null;
        } else {
            return expenses;
        }
    }
	public Iterator<Expense> getExpenses() {
		Iterator<Expense> it = expenses.iterator();
		return it;
	}

	@Override
    public String toString() {
		return "\n" + expenses.toString();
	}

}
