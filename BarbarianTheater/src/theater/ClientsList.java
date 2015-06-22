package theater;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ClientsList implements Serializable {

	private static ClientsList singletonClientList;
    //private static ClientsList singletonClientList = ClientsList.clientListInstance();
    private List<Client> clients;

	private ClientsList() {

		clients = new ArrayList<Client>();
	}

	public static ClientsList clientListInstance() {
		if(singletonClientList == null){
			singletonClientList = new ClientsList();
		}
		return singletonClientList;
	}

	public boolean add(Client client) {
		return clients.add(client);
	}
	
	public boolean remove(String id){
		for(int i = 0; i < clients.size(); i++){
			if(clients.get(i).getID().equals(id)){
				clients.remove(i);
				return true;
			}				
		}
		return false;
	}

	public List<Client> getList() {
		return clients;
	}
	

	
	public Client search(String id){
		for(int i = 0; i < clients.size(); i++){
			if(clients.get(i).getID().equals(id)){
				return clients.get(i);
			}				
		}
		return null;
	}

    private void writeObject(java.io.ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(singletonClientList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private Object readResolve() {
        return singletonClientList;
    }

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