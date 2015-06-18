import java.util.ArrayList;
import java.util.Iterator;


public class ClientsList<E> extends ArrayList<E>{
	private ArrayList<Client> clients;
	private static ClientsList<Client> singletonClientList;
	
	private ClientsList(){
		
	}
	
	public static ClientsList<Client> clientListInstance(){
		if(singletonClientList == null){
			singletonClientList = new ClientsList<Client>();
		}
		return singletonClientList;
	}
	
	public boolean add(Client client){
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
	
	public Iterator<Client> getList(){
		return clients.iterator();
	}
	
	public Client search(String id){
		for(int i = 0; i < clients.size(); i++){
			if(clients.get(i).getID().equals(id)){
				return clients.get(i);
			}				
		}
		return null;
	}
}