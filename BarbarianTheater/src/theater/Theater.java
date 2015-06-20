package theater;

import java.io.ObjectInputStream;
import java.util.Calendar;
import java.util.List;


public class Theater {
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
		if (isClientIDRemoved(id)) { //why use a separate method here?

			return true;
		}
		else{
			return false;
		}
	}

	private boolean isClientIDRemoved(String id) {

		Client clientToRemove = clientList.search(id);

		if (clientToRemove != null && clientToRemove.getShows().size() == 0) {

			clientList.remove(id); //should this pass the client instead?
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

		if (isMemberIDRemoved(id)) { //why use a separate method here

			return true;
		} else {
			return false;
		}
	}

	private boolean isMemberIDRemoved(String id) {

		Member memberToRemove = memberList.search(id);

		if (memberToRemove != null) {

			memberList.remove(id);
			return true;
		}
		return false;  //should this pass the member instead?
	}

	public List<Member> listMembers() {
		return memberList.getList();
	}

	public Member searchMember(String ID) {
		return memberList.search(ID);
	}

	public List<Client> listClients() {
		return clientList.getList();
	}
	
	public CreditCard addCreditCard(String id, String creditCardNumber, Calendar expiration){
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
