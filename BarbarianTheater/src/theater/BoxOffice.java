/**
 * Barbarians: Douglas Brian Shaffer, Nathan Kangas, Johnathan Franco
 * This class handles ticket sales for the Theater.
 */
package theater;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BoxOffice {

	private List<Transaction> transactions = new ArrayList<Transaction>();
	private int counter;
	private static BoxOffice singletonBoxOffice;
	
	private BoxOffice(){
		counter = 0;
	}
	
	public static BoxOffice instance(){
		if (singletonBoxOffice == null){
			singletonBoxOffice = new BoxOffice();
		}
		return singletonBoxOffice;
	}
	
	public double sellTicket(Member member, Show show, CreditCard card, int ticketType){
		//
		return 0.00;
	}
	
	public boolean checkSeats(Show show){
		return true;
	}
	
	public Ticket createTicket(Show show, int ticketType){
		return null;
	}
	
	public List<Ticket> getTickets(Calendar date){
		return null;
	}
}
