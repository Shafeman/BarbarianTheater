/**
 * Barbarians: Douglas Brian Shaffer, Nathan Kangas, Johnathan Franco
 * This class holds a list of Clients for the Theater. It maintains a list
 * and has methods for adding, removing, searching and listing Clients,
 * as well as saving and loading the list.
 */
package theater;

import java.io.IOException;
import java.io.Serializable;


@SuppressWarnings("serial")
public class ClientsList extends TheaterPatronList<Client, String> implements Serializable {

	private static ClientsList singletonClientList;

    /**
	 * ClientsList Constructor, creates a singleton list as an ArrayList.
	 */
	private ClientsList() {
	}

	/**
	 * clientListInstance is used to create a single ClientList if one does
	 * not exist, maintaining the ClientList's singleton property
	 * @return
	 */
	public static ClientsList clientListInstance() {
		if(singletonClientList == null){
			singletonClientList = new ClientsList();
		}
		return singletonClientList;
	}

	/**
	 * add takes a Client and adds it to the list.
	 * @param client
	 * @return
	 */
	public boolean add(Client client) {
		return super.add(client);
	}
	
	/**
	 * remove takes a client ID as a string and searches for it in the list.
	 * If the ID is found, that client is removed, provided the client has
	 * no future shows scheduled. remove returns true if the client was removed
	 * and false otherwise.
	 * @param id
	 * @return
	 */
	public boolean remove(String id){		
		Client client = search(id);
		if (client ==  null){
			return false;
		}
		return super.remove(id);
	}

	/**
	 * writeOjbect is used to write the the ClientList to an output stream.
	 * @param output
	 */
    private void writeObject(java.io.ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(singletonClientList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    /**
     * readResolve returns the singleton ClientList
     * @return
     */
    private Object readResolve() {
        return singletonClientList;
    }

    /**
     * readObject is used when loading the ClientList. It takes an ObjectInputStream
     * and ensures the singleton property is upheld if the stream has
     * a ClientList.
     * @param input
     */
    private void readObject(java.io.ObjectInputStream input) {
        try {
            if (singletonClientList != null) {

                input.readObject();
                return;
            } else {
                input.defaultReadObject();
                if (singletonClientList == null) {
                    singletonClientList = (ClientsList) input.readObject();
                } else {
                    input.readObject();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
}