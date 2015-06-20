package theater;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;


public class Theater {
	//private ArrayList<Client> clients; //depends on the list discussion. should be changed to correction collection class
	private static ClientsList<Client> clientList;
	private static Theater singletonTheater;
	private String name;
	private int seatCapacity;
	private ArrayList<Member> members;
	
	private Theater(String name, int seatCapacity){
		this.name = name;
		this.seatCapacity = seatCapacity;
		clientList = ClientsList.clientListInstance();
	}
	
	public static Theater instance(String name, int capacity){
		if(singletonTheater == null){
			singletonTheater = new Theater(name, capacity);
		}
		return singletonTheater;
	}

	public static Theater load() {
		return null;
	}
	
	public Client addClient(String name, String address, String phoneNumber){
		Client client = new Client(name, address, phoneNumber);
		clientList.add(client);
		return client;
	}

//	public Iterator<Client> listClients(){
//		return clients.iterator();
//	}
	
	public boolean removeClient(String id){
		if (id == "true") {
			//find client by id and remove from clients
			return true;
		}
		else{
			return false;
		}
	}
	
	public Member addMember(String name, String address, String phone, String creditCardNumber, Calendar expiration){
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
	
	public CreditCard addCreditCard(String id, String creditCardNumber, Calendar expiration){
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

//	public Iterator<Show> listShows(){
//		return clients.iterator().next().getShows(); //obviously REALLY BAD code. Thankfully just temporary.
//	}
	
	public Show addShow(String clientID, String name, Calendar startDate, Calendar endDate){
		return new Show(name, startDate, endDate);
	}
	
	public boolean save(){
		return true;
	}
	
	private void readObject(ObjectInputStream input){
		
	}

}
