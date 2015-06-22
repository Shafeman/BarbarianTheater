package theater;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.*;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;


public class Theater implements Serializable {
	private static Theater singletonTheater;
	private static int CC_NOT_FOUND = 0;
	private static int TOO_FEW_CARDS = 1;
	private static int SUCCESS = 2;
	private ClientsList clientList;
	private MemberList memberList;
	//private static Theater singletonTheater = Theater.instance();
	private String name;
	private int seatCapacity;

	private Theater() {
		clientList = ClientsList.clientListInstance();
		memberList = MemberList.memberListInstance();
	}

	public static Theater instance() {
		if(singletonTheater == null){
			singletonTheater = new Theater();
		}
		return singletonTheater;
	}

	public static Theater retrieve() {

		try {
			FileInputStream file = new FileInputStream("TheaterData");
			ObjectInputStream input = new ObjectInputStream(file);
			input.readObject();
			//MemberIdServer.retrieve(input);
			return singletonTheater;
		} catch (IOException ioe) {
			//ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			//cnfe.printStackTrace();
			return null;
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSeatCapacity(int seatCapacity) {
		this.seatCapacity = seatCapacity;
	}
	
	public Client addClient(String name, String address, String phoneNumber){
		Client client = new Client(name, address, phoneNumber);
		clientList.add(client);
		return client;
	}

	public boolean removeClient(String id){
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
		Member memberToRemove = memberList.search(id);

		if (memberToRemove != null) {

			memberList.remove(id);
			return true;
		}
		return false;
	}

	public List<Member> listMembers() {
		return memberList.getList();
	}

	public Member searchMember(String ID) {
		return memberList.search(ID);
	}
	
	public Client searchClient(String ID) {
		return clientList.search(ID);
	}

	public List<Client> listClients() {
		return clientList.getList();
	}
	
	public CreditCard addCreditCard(String id, String creditCardNumber, Calendar expiration){
		CreditCard card = new CreditCard(creditCardNumber, expiration);
		return card;
	}

//	public Iterator<Show> listShows(){
//		return clients.iterator().next().getShows(); //obviously REALLY BAD code. Thankfully just temporary.
//	}

	/**
	 * Searches all members for a creditCard then removes it
	 *
	 * @param creditCardNumber
	 * @return
	 */
	public int removeCreditCard(String creditCardNumber) {
		int failReason = CC_NOT_FOUND;
		for (Member member : memberList.getList()) {
			int result = member.removeCreditCard(creditCardNumber);
			if (result == SUCCESS) {
				return result;
			}
			else if (result == TOO_FEW_CARDS){ //if any user has the card, but only one
				failReason = TOO_FEW_CARDS;
			}
		}
		return failReason;
	}
	
	public boolean checkCreditCardInCorrectFormat(String creditCardNumber){
		return CreditCard.isCreditCardInCorrectFormat(creditCardNumber);
	}
	
	public boolean isCreditCardDuplicate(String creditCardNumber) {

        for (Member member : this.listMembers()) {
            for (CreditCard customerCreditCard : member.getCreditCards()) {

                String cardNumberOnFile = customerCreditCard.getCreditCardNumber();
                if(creditCardNumber.equals(cardNumberOnFile)){
                    return true;
                }
            }
        }
        return false;
    }

	public List<Show> listShows(){
		
		List<Show> shows = new ArrayList<Show>();

		
		for(Client client : clientList.getList()) {
			if(client.hasShow())
			shows.addAll(client.getShows());
			
		}
		
		
		
		return shows;
	}
	
	/**
	 * A method to add a show to a valid client, also checks to see if 
	 * the Dates passed are valid.
	 * @param clientID
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @return Show object: if the dates are not valid the Show object will be null
	 */
	public Show addShow(String clientID, String name, Calendar startDate, Calendar endDate){
		
		Show show = null;		
		
		Client client = searchClient(clientID);
		
		for(Client checkClient : clientList.getList())
			if(checkClient.hasDate(startDate, endDate)) {
				return show;
			}else{
				
				show = new Show(name, startDate, endDate);
				client.addShow(show);
			}
		
		return show;
	}

	public boolean save() {

		try {
			FileOutputStream file = new FileOutputStream("TheaterData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(singletonTheater);
			//output.writeObject(MemberIdServer.instance());
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(singletonTheater);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	private Object readResolve() {
		return singletonTheater;
	}
	
	private void readObject(ObjectInputStream input) {

		try {
			input.defaultReadObject();
			if (singletonTheater == null) {
				singletonTheater = (Theater) input.readObject();
			} else {

				input.readObject();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
