import java.io.Serializable;

public class Expense implements Serializable {
	private static final long serialVersionUID = 1L;
	private String description;
	private int expenseAmount;

	public Expense(String description, int amount) {
		this.description = description;
		this.expenseAmount = amount;
	}

	public String getDescription() {
		return description;
	}

	public int getExpenseAmount() {
		return expenseAmount;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setExpenseAmount(int expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public String toString() {
		return "\nDescription: " + description + "\nExpense Amount: " + expenseAmount;
	}
}
