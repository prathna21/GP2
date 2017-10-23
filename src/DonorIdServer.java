
/**
 * @author Sammy Yang
 * @author TsengCyen Yang
 * @author Prathna Ung
 * @author Dat Huynh
 * Some codes where from Class Project 1 written by @author Brahma Dathan and Sarnath Ramnath
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Maintain the Donor Id
 *
 */
public class DonorIdServer implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idCounter;
	private static DonorIdServer server;
	private DonorIdServer() {
		idCounter = 1;
	}
	
	/**
	 * Supports the singleton pattern
	 *
	 * @return The singleton object
	 */
	public static DonorIdServer instance() {
		if (server == null) {
			return (server = new DonorIdServer());
		} else {
			return server;
		}
	}

	/**
	 * Getter for id
	 *
	 * @return Id of the donor
	 */
	public int getId() {
		return idCounter++;
	}
	
	/**
	 * String form of the collection
	 * 
	 * @return Id of the donor
	 */
	@Override
	public String toString() {
		return ("IdServer" + idCounter);
	}
	
	/**
	 * Retrieves the server object
	 *
	 * @param input
	 *            Inputstream for deserialization
	 */
	public static void retrieve(ObjectInputStream input) {
		try {
			server = (DonorIdServer) input.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * Supports serialization
	 *
	 * @param Output
	 *            the stream to be written to
	 */
	public void writeObject(ObjectOutputStream output) throws IOException {
		try {
			output.defaultWriteObject();
			output.writeObject(server);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Supports serialization
	 *
	 * @param Input
	 *            the stream to be read from
	 */
	public void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
		try {
			input.defaultReadObject();
			if (server == null) {
				server = (DonorIdServer) input.readObject();
			} else {
				input.readObject();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
