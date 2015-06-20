package theater;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ClientsList {

	private static ClientsList singletonClientList;
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
		return false; //if we make the Client equals() method compare IDs, we can use Client here instead of String
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
		return null;  //search for Client rather than ID?
	}


}