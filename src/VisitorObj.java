/**
 * @author Sammy Yang
 * @author TsengCyen Yang
 * @author Prathna Ung
 * @author Dat Huynh
 * Some codes where from Class Project 1 written by @author Brahma Dathan and Sarnath Ramnath
 */

import java.io.Serializable;
import java.util.List;
/**
* implement the the Visitor interface
* this create the visitor object
*/
public class VisitorObj implements Visitor, Serializable {

	/** (non-Javadoc)
	 * @see Visitor#visit(java.util.List)
	 */
	@Override
	public void visit(List<Visitable> visitor) {
		for (Visitable v : visitor) {
			v.accept(this);
		}

	}

	/** (non-Javadoc)
	 * @see Visitor#visit(CreditCard)
	 */
	@Override
	public void visit(CreditCard card) {
		System.out.println("| Credit Card: " + card.getCreditCard() + "\t| Payment: " + card.getCardAmount()
				+ "\t|  Times Processed: " + card.getTimesProcessed());

	}

	/** (non-Javadoc)
	 * @see Visitor#visit(BankAccount)
	 */
	@Override
	public void visit(BankAccount bank) {
		System.out.println("| Bank Account: " + bank.getBankAccount() + "\t| Payment: " + bank.getBankAmount()
				+ "\t|  Times Processed: " + bank.getTimesProcessed());

	}

}
