import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class Theater {
	private String name;
	private int seatCapacity;
	private ArrayList<Client> clients; //depends on the list discussion. should be changed to correction collection class
	private ArrayList<Member> members;
	private static Theater singletonTheater;
	
	private Theater(String name, int seatCapacity){
		this.name = name;
		this.seatCapacity = seatCapacity;
	}
	
	public static Theater instance(String name, int capacity){
		if(singletonTheater == null){
			singletonTheater = new Theater(name, capacity);
		}
		return singletonTheater;
	}
	
	public Client addClient(String name, String address, String phoneNumber){
		Client client = new Client(name, address, phoneNumber);
		return client;
	}
	
	public boolean removeClient(String id){
		if(id=="true"){
			//find client by id and remove from clients
			return true;
		}
		else{
			return false;
		}
	}
	
	public Iterator<Client> listClients(){
		return clients.iterator();
	}
	
	public Member addMember(String name, String address, String phone, String creditCardNumber, Date expiration){
		Member customer = new Member(name, address, phone, creditCardNumber, expiration);
		return customer;
	}
	
	public boolean removeMember(String id){
			//remove member
		return true;
	}
	
	public Iterator<Member> listMembers(){
		return members.iterator();
	}
	
	public CreditCard addCreditCard(String id, String creditCardNumber, Date expiration){
		//search for member by id
		CreditCard card = new CreditCard(creditCardNumber, expiration);
		return card;
	}
	
	/**
	 * Searches all members for a creditCard then removes it
	 * @param creditCardNumber
	 * @return
	 */
	public boolean removeCreditCard(String creditCardNumber){
		Iterator<Member> itr = members.iterator();
		boolean removed = false;
		
		while(itr.hasNext()){
			Member member = itr.next();
			//Will look into this member and try to remove CC
			if(member.removeCreditCard(creditCardNumber))
					removed = true;
		}		
	return removed;
	}
	
	public Show addShow(String clientID, String name, Date startDate, Date endDate){
		return new Show(name, startDate, endDate);
	}
	
	public Iterator<Show> listShows(){
		return clients.iterator().next().getShows(); //obviously REALLY BAD code. Thankfully just temporary.
	}
	
	public boolean save(){
		return true;
	}
	
	public static Theater load(){
		return null;		
	}
	
	private void readObject(ObjectInputStream input){
		
	}

}
