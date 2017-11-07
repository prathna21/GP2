/**
 * @author Sammy Yang
 * @author TsengCyen Yang
 * @author Prathna Ung
 * @author Dat Huynh
 * Some codes where from Class Project 1 written by @author Brahma Dathan and Sarnath Ramnath
 */

import java.util.List;
/**
* this is an interface that visit object will implement
*/
public interface Visitor{
	public void visit(List<Visitable> visitor);
	public void visit(CreditCard card);
	public void visit(BankAccount bank);

}
