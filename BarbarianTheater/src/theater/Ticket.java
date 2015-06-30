package theater;

import java.io.Serializable;
import java.util.Calendar;

@SuppressWarnings("serial")
public abstract class Ticket implements Serializable{
	
	private String serialNumber;
	//private Member ticketHolder;
	private Calendar dateOfShow;
	private double ticketPrice;
	protected static double advanceDiscount = .70;
	protected static double studentDiscount = .50;
	
	public Ticket(/*Member member,*/ Calendar date, double price) {
		//this.ticketHolder = member;
		this.dateOfShow = date;
		this.ticketPrice = price;
		//Could use the same technique as TheaterPatron to generate an ID for a Ticket
		//Use the show title and the idServer
	}

	/**
	 * Returns the serial number assigned to the Ticket
	 * @return
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Returns the Member object who purchased the ticket.
	 * @return
	 */
	/*public Member getTicketHolder() {
		return ticketHolder;
	}*/

	/**
	 * Returns the date a show the ticket is for.
	 * @return
	 */
	public Calendar getDateOfShow() {
		return dateOfShow;
	}

	/**
	 * Returns the price of the ticket
	 * @return
	 */
	public double getTicketPrice() {
		return ticketPrice;
	}
	
	/**
	 * Generate a unique serial number for a Ticket.
	 * @return
	 */
	public String generateSerialNumber() {
		String serialNumber = "";
		
		if((this.dateOfShow.get(Calendar.MONTH) < 10) && (this.dateOfShow.get(Calendar.DAY_OF_MONTH) < 10)) {		
			serialNumber += "0" + this.dateOfShow.get(Calendar.MONTH);
			serialNumber += "0" + this.dateOfShow.get(Calendar.DAY_OF_MONTH);
			serialNumber += (this.dateOfShow.get(Calendar.YEAR)) % 100;
			serialNumber += "0";
		}else	{		
			serialNumber += this.dateOfShow.get(Calendar.MONTH);
			serialNumber += this.dateOfShow.get(Calendar.DAY_OF_MONTH);
			serialNumber += (this.dateOfShow.get(Calendar.YEAR)) % 100;
			serialNumber += "0";
		}	
		//Then add the Box office # and the seat #.
		return serialNumber;
	}
	
	/**
	 * Returns a description of the ticket
	 */
	@Override
	public String toString() {
		String str = "";
		
		return str;
	}
	
	
}