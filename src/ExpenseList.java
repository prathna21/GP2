import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

	public Iterator<Expense> getExpenses() {
		Iterator<Expense> it = expenses.iterator();
		while (it.hasNext()) {
			Expense obj = it.next();
			System.out.println(obj);
		}
		return it;
	}

	public String toString() {
		return "\n" + expenses.toString();
	}

}
