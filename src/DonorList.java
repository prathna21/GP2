
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
 * The collection class for Donor objects
 *
 */
public class DonorList implements Serializable {
	private static final long serialVersionUID = 1L;
	private static DonorList donorList;
	private List<Donor> donors = new LinkedList<Donor>();

	/**
	 * Private constructor for singleton pattern
	 *
	 */
	public DonorList() {

	}

	/**
	 * Supports the singleton pattern
	 *
	 * @return The singleton object
	 */
	public static DonorList instance() {
		if (donorList == null) {
			return (donorList = new DonorList());
		} else {
			return donorList;
		}
	}

	/**
	 * Checks whether a donor with a given member id exists.
	 *
	 * @param donorId
	 *            The id of the donor
	 * @return True if donor exists
	 *
	 */
	public Donor searchDonor(String donorId) {
		for (Iterator<Donor> iterator = donors.iterator(); iterator.hasNext();) {
			Donor donor = iterator.next();
			if (donor.getId().equals(donorId)) {
				return donor;
			}
		}
		return null;
	}

	/**
	 * Inserts a donor into the collection
	 *
	 * @param donor
	 *            The donor to be inserted
	 * @return True if the donor could be inserted.
	 */
	public boolean insertDonor(Donor donor) {
		donors.add(donor);
		return true;
	}

	/**
	 * Get list of donor into the collection
	 *
	 * @return String of donors.
	 */
	public Iterator<Donor> getDonor() {
		Iterator<Donor> it = donors.iterator();
		while (it.hasNext()) {
			Donor obj = it.next();
			System.out.println(obj);
		}
		return it;
	}

	/**
	 * This method gets the lists of donors
	 *
	 * @return the list of donors
	 */
	public List<Donor> getList() {
		if (donors.isEmpty()) {
			return null;
		} else {
			return donors;
		}
	}

	/**
	 * Remove a donor into the collection
	 *
	 * @return True if the donor could be removed.
	 */
	public boolean removeDonor(String donorId) {
		for (Iterator<Donor> iterator = donors.iterator(); iterator.hasNext();) {
			Donor donor = iterator.next();
			if (donor.getId().equals(donorId)) {
				donor.removeTransaction();
				donors.remove(donor);
				System.out.println("\n- Donor: "+donorId+" was successfully removed -");

				return true;
			}
		}
		return false;

	}

	/**
	 * Override toString method
	 *
	 * @return Donor toString method.
	 */
	@Override
	public String toString() {
		return donors.toString();
	}
}