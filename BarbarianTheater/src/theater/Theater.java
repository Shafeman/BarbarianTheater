package theater;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;


public class Theater {
	//private ArrayList<Client> clients; //depends on the list discussion. should be changed to correction collection class
	private static ClientsList clientList;
	private static MemberList memberList;
	private static Theater singletonTheater;
	private String name;
	private int seatCapacity;

	
	private Theater(String name, int seatCapacity){
		this.name = name;
		this.seatCapacity = seatCapacity;
		clientList = ClientsList.clientListInstance();
		memberList = MemberList.memberListInstance();
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
	
	public boolean removeClient(String id){
		if (isClientIDRemoved(id)) {

			return true;
		}
		else{
			return false;
		}
	}

	private boolean isClientIDRemoved(String id) {

		Client clientToRemove = clientList.search(id);

		if (clientToRemove != null && clientToRemove.getShows().size() == 0) {

			clientList.remove(id);
			return true;
		}
		return false;
	}

	public Member addMember(String name, String address, String phone, String creditCardNumber, Calendar expiration){
		Member member = new Member(name, address, phone, creditCardNumber, expiration);
		memberList.add(member);
		return member;
	}
	
	public boolean removeMember(String id){
			//remove member
		return true;
	}

	public List<Member> listMembers() {
		return memberList.getList();
	}

	public List<Client> listClients() {
		return clientList.getList();
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
	public boolean removeCreditCard(String creditCardNumber) {

		for (Member member : memberList.getList()) {

			if (member.removeCreditCard(creditCardNumber)) {
				return true;
			}
		}
		return false;
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
