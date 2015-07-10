/**
 * Barbarians: Douglas Brian Shaffer, Nathan Kangas, Johnathan Franco
 * Theater acts as a facade between UserInterface and all other classes.
 * UI calls methods from Theater after user enters commands.
 * Theater is a singleton class and holds a singleton ClientsList and
 * a singleton MemberList.
 */
package theater;

import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;


@SuppressWarnings("serial")
public class Theater implements Serializable {
	private static Theater singletonTheater;
	public static final int NOT_FOUND = 0;
	public static final int TOO_FEW_CARDS = 1;
	public static final int SUCCESS = 2;
	public static final int HAS_SHOWS = 3;
	public static final int REGULAR_TICKET = 4;
	public static final int ADVANCE_TICKET = 5;
	public static final int STUDENT_ADVANCE_TICKET = 6;
	 
	private ClientsList clientList;
	private MemberList memberList;
	private String name;
	private int seatCapacity;
	private BoxOffice boxOffice;

	/**
	 * private Theater constructor
	 * @param name
	 * @param seatCapacity
	 */
	private Theater(String name, Integer seatCapacity) {
		this.name = name;
		this.seatCapacity = seatCapacity;
		clientList = ClientsList.clientListInstance();
		memberList = MemberList.memberListInstance();
		boxOffice = BoxOffice.instance();
	}

	/**
	 * instance creates a singleton Theater if one does not exist or
	 * returns the current theater if it does exist.
	 * @param name
	 * @param capacity
	 * @return
	 */
	public static Theater instance(String name, Integer capacity)
	{
		if(singletonTheater == null){
			singletonTheater = new Theater(name, capacity);
		}
		return singletonTheater;
	}

	/**
	 *setName sets this Theater's name 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * setCapacity sets this Theater's seating capacity.
	 * @param seatCapacity
	 */
	public void setSeatCapacity(int seatCapacity) {
		this.seatCapacity = seatCapacity;
	}
	
	/**
	 * addClient adds a Client to ClientsList
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @return
	 */
	public Client addClient(String name, String address, String phoneNumber){
		Client client = new Client(name, address, phoneNumber);
		clientList.add(client);
		return client;
	}

	/**
	 * removeClient asks ClientList to search for a client ID passed as a parameter.
	 * If the client does not have any shows scheduled for the current or future days,
	 * the system removes the client if found. An integer is returned based on the results.
	 * NOT_FOUND = 0;
	 * SUCCESS = 2;
	 * HAS_SHOWS = 3;
	 * @param id
	 * @return
	 */
	public int removeClient(String id){
		Client clientToRemove = clientList.search(id);
		Calendar today = Calendar.getInstance();
		String todayDate = (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.DAY_OF_MONTH) +
				"/" + today.get(Calendar.YEAR);
		DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
		try {
			today.setTime(dateFormat.parse(todayDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (clientToRemove == null) {
			return NOT_FOUND;
		}
		else if(clientToRemove != null && clientToRemove.hasShow()) {
			List<Show> clientsShows = clientToRemove.getShows();
			for (Show show: clientsShows){
				if(today.before(show.getEndDate())) {
					return HAS_SHOWS;
				}
				else if(today.equals(show.getEndDate())){
					return HAS_SHOWS;
				}
			}			
		}
		else {
			clientList.remove(id);
		}
		return SUCCESS;		
	}

	/**
	 * addMember adds a Member to MemberList
	 * @param name
	 * @param address
	 * @param phone
	 * @param creditCardNumber
	 * @param expiration
	 * @return
	 */
	public Member addMember(String name, String address, String phone, String creditCardNumber, Calendar expiration){
		Member member = new Member(name, address, phone, creditCardNumber, expiration);
		memberList.add(member);
		return member;
	}

	/**
	 * removeMember asks removeMember to search for a Member by ID and removes
	 * the member if found, returning true if the member is removed and
	 * false otherwise. 
	 * @param id
	 * @return
	 */
	public boolean removeMember(String id){
		Member memberToRemove = memberList.search(id);
		if (memberToRemove != null) {
			memberList.remove(id);
			return true;
		}
		return false;
	}

	/**
	 * listMembers retrieves a list of Members from memberList
	 * @return
	 */
	public List<Member> listMembers() {
		return memberList.getList();
	}

	/**
	 * searchMember returns a Member if the member is found
	 * by ID in Member List
	 * @param ID
	 * @return
	 */
	public Member searchMember(String ID) {
		return memberList.search(ID);
	}
	
	/**
	 * searchClient returns a Client if the client is found
	 * by ID in Client List
	 * @param ID
	 * @return
	 */
	public Client searchClient(String ID) {
		return clientList.search(ID);
	}

	/**
	 * listClients retrieves a list of Clients from ClientsList
	 * @return
	 */
	public List<Client> listClients() {
		return clientList.getList();
	}
	
	/**
	 * addCreditCard adds a credit card to a Member for a given Member ID
	 * @param id
	 * @param creditCardNumber
	 * @param expiration
	 * @return
	 */
	public CreditCard addCreditCard(String id, String creditCardNumber, Calendar expiration){
		CreditCard card = new CreditCard(creditCardNumber, expiration);
		return card;
	}

	/**
	 * Searches all members for a creditCard then removes it	 *
	 * @param creditCardNumber
	 * @return
	 */
	public int removeCreditCard(String creditCardNumber) {
		int failReason = NOT_FOUND;
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
	
	/**
	 * checkCreditCardInCorrectFormat passes a credit card number to
	 * CreditCard's static method to verify if a credit card number is
	 * in a valid format. It returns true if the format is valid.
	 * @param creditCardNumber
	 * @return
	 */
	public boolean checkCreditCardInCorrectFormat(String creditCardNumber){
		return CreditCard.isCreditCardInCorrectFormat(creditCardNumber);
	}
	
	/**
	 * isCreditCardDuplicate returns true if a credit card number has
	 * already been assigned to a member. It returns false if the credit card
	 * is not already assigned to a member.
	 * @param creditCardNumber
	 * @return
	 */
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

	/**
	 * listShows returns a list of Shows from all Clients.
	 * @return
	 */
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
	public Show addShow(Client client, String name, Calendar startDate, Calendar endDate, BigDecimal price){		
		Show show = null;				
		Client clientToAdd = client;
		
		for(Client checkClient : clientList.getList()){
			if(checkClient.hasDate(startDate, endDate)) {
				return show;
			}
		}
		show = new Show(name, startDate, endDate, price);
		clientToAdd.addShow(show);
		return show;
	}
	
	/**
	 * getShow finds a show for a particular date and returns it
	 * @param date
	 * @return
	 */
	public Show getShow(Calendar date){
		for(Client client: clientList.getList()){
			for(Show show: client.getShows()){
				Calendar testStartDate = show.getStartDate();
				Calendar testEndDate = show.getEndDate();
				if((date.equals(testStartDate) || date.equals(testEndDate) || 
						(date.after(testStartDate) && date.before(testEndDate)))){
					return show;
				}
			}
		}
		return null;
	}
	
	/**
	 * getCreditCard finds a credit card for a particular member and returns it
	 * @param member
	 * @param id
	 * @return
	 */
	public CreditCard getCreditCard(Member member, String id){
		for(CreditCard card: member.getCreditCards()){
			if (card.getCreditCardNumber().equals(id)){
				return card;
			}
		}
		return null;
	}

	/**
	 * Takes a Show, Member, CreditCard, Date and ticket type and calls BoxOffice to sell a ticket.
	 * Updates the client's balance based on the balance of the ticket sold.
	 * @param show
	 * @param member
	 * @param creditCard
	 * @param ticketType
	 * @param showDate
	 * @return
	 */
	public Ticket sellTicket(Show show, Member member, CreditCard creditCard,	int ticketType, Calendar showDate) {
		Client clientToBill = getClientByShow(show);
		Ticket ticket = boxOffice.sellTicket(member, show, creditCard, ticketType, showDate);
		member.addTicket(ticket);
		clientToBill.addPrice((ticket.getTicketPrice().divide(new BigDecimal(2))));		
		return ticket;
		
	}
	
	/**
	 * Returns a Client that holds the passed Show.
	 * @param show
	 * @return
	 */
	public Client getClientByShow(Show show){
		for(Client client: clientList.getList()){
			for(Show clientShow: client.getShows()){
				if (clientShow.matches(show)){
					return client;
				}
			}
		}
		return null;
	}
	
	/**
	 * Returns a list of tickets for a specific date
	 * @param date
	 * @return
	 */
	public List<Ticket> listTickets(Calendar date) {
		return boxOffice.getTickets(date);
	}

	/**
	 * save is used to save the system to a file
	 * @return
	 */
	public boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("TheaterData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(singletonTheater);
			output.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	/**
	 * writeObject writes the Theater to an output stream
	 * @param output
	 */
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(singletonTheater);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	/**
	 * retrieve loads the Theater and all other information from a file.
	 * @return
	 */
	public static Theater retrieve() {
		try {
			FileInputStream file = new FileInputStream("TheaterData");
			ObjectInputStream input = new ObjectInputStream(file);
			input.readObject();
			input.close();
			return singletonTheater;
		} catch (IOException ioe) {
			return null;
		} catch (ClassNotFoundException cnfe) {
			return null;
		}
	}
	
	/**
	 * readResolve returns a singleton instance of Theater
	 * @return
	 */
	private Object readResolve() {
		return singletonTheater;
	}
	
	/**
	 * readObject reads the Theater Objects from an input stream
	 * @param input
	 */
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

	/**
	 * This toString method will return a 
	 * string of the Name of the theater
	 * and the seating capacity
	 */
	@Override
	public String toString() {
		String str = "";
		
		str += "Theater Name:" + name + "\nSeating Capacity:" + seatCapacity;
		
		return str;
	}

	/**
	 * Returns a client based on the received client ID
	 * @param clientId
	 * @return
	 */
	public Client getClient(String clientId) {
		Client clientToPay = clientList.search(clientId);
		return clientToPay;
	}
	
	/**
	 * Updates the client's balance by the amount given.
	 * @param client
	 * @param amount
	 */
	public void payClient(Client client, BigDecimal amount){
		client.getPaid(amount);
	}

	/**
	 * Returns the seating capacity of the theater
	 * @return
	 */
	public int getCapacity() {
		return this.seatCapacity;
	}

	/**
	 * Returns the number of tickets sold for a show on a given date.
	 * @param date
	 * @return
	 */
	public int getTicketCount(Calendar date) {
		return boxOffice.getTicketCountForDate(date);
	}


}
