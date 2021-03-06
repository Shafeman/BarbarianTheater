/**
 * Barbarians: Douglas Brian Shaffer, Nathan Kangas, Johnathan Franco
 * This class handles ticket sales for the Theater.
 */
package theater;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SuppressWarnings("serial")
public class BoxOffice implements Serializable{

	private List<Transaction> transactions = new ArrayList<Transaction>();
	private static BoxOffice singletonBoxOffice;
	
	/**
	 * constructor
	 */
	private BoxOffice(){
	}
	
	/**
	 * Instance ensures the BoxOffice's singleton property.
	 * @return
	 */
	public static BoxOffice instance(){
		if (singletonBoxOffice == null){
			singletonBoxOffice = new BoxOffice();
		}
		return singletonBoxOffice;
	}
	
	/**
	 * Sells a ticket, creates a ticket and a transaction.
	 * @param member: holds a ticket
	 * @param show : holds the price for a ticket
	 * @param card : used for the purchase
	 * @param ticketType : type of ticket to be sold
	 * @param showDate : date the ticket is good for
	 * @return price : of the ticket after any discounts
	 */
	public Ticket sellTicket(Member member, Show show, CreditCard card, int ticketType, Calendar showDate){
		
		Ticket ticket = createTicket(showDate, show, ticketType);
		Transaction transaction = new Transaction(show, member, ticket, card);
		transactions.add(transaction);		
		return ticket;
	}

	/**
	 * A ticket object is created here
	 * @param showDate : Date the ticket is good for
	 * @param show : To get base price of the show
	 * @param ticketType : To sell the appropriate ticket type
	 * @return Ticket
	 */
	public Ticket createTicket(Calendar showDate,Show show, int ticketType){
		
		Ticket ticket = null;
		
		switch(ticketType) {
		case Theater.REGULAR_TICKET:
			ticket = new RegularTicket(showDate, show.getPrice());
			break;
		case Theater.ADVANCE_TICKET:
			ticket = new AdvanceTicket(showDate, show.getPrice());
			break;
		case Theater.STUDENT_ADVANCE_TICKET:
			ticket = new StudentAdvanceTicket(showDate, show.getPrice());
			break;
		}
		
		return ticket;
	}
	
	/**
	 * Traverses through the list of transactions to find
	 * tickets with a certain date.
	 * @param date to look for
	 * @return List of tickets for a certain date
	 */
	public List<Ticket> getTickets(Calendar date){
		
		List<Ticket> ticketList = new ArrayList<Ticket>();
		
		for(Transaction ticket: transactions )
			if(ticket.getTicket().getDateOfShow().equals(date))
				ticketList.add(ticket.getTicket());
		
		return ticketList;
	}
	
	/**
	 * Gets the count of tickets sold for a certain date by looking at the Transactions list.
	 * @param date
	 * @return
	 */
	public int getTicketCountForDate(Calendar date){
		List<Ticket> tickets = getTickets(date);
		int ticketCount = tickets.size();
		return ticketCount;
	}
}
