package theater;

import java.util.Calendar;

public class AdvanceTicket extends Ticket{

	
	//Not sure if this would be the correct way of setting the prices
	public AdvanceTicket(Member member, Calendar date, double price) {
		super(/*member,*/ date, price * advanceDiscount);
		
	}

}
