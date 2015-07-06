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
	
	private BoxOffice(){
	}
	
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
	public double sellTicket(Member member, Show show, CreditCard card, int ticketType, Calendar showDate){
		
		Ticket ticket = createTicket(showDate, show, ticketType);
		Transaction transaction = new Transaction(show, member, ticket, card);
		
		transactions.add(transaction);
		member.addTicket(ticket);
		
		
		return show.getPrice();
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
	
	public List<Ticket> getTickets(Calendar date){
		return null;
	}
}
