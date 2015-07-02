package theater;

import java.util.Calendar;

@SuppressWarnings("serial")
public class RegularTicket extends Ticket {
	

	public RegularTicket(Member member, Calendar date, double price) {
		super(date, price);
		this.generateSerialNumber();
		
	}
	
	/**
	 * Generate a unique serial number for a Regular ticket
	 * Appends and "R" at the end of the SN to notify this.
	 */
	public String generateSerialNumber() {
		String serialNumber = super.generateSerialNumber();		
		
		serialNumber += "R";		
		
		return serialNumber;
	}
	
	/**
	 * Returns a description of the ticket
	 */
	@Override
	public String toString() {
		String str = "";
		
		str += super.toString();
		str += "$" + this.ticketPrice + " ";
		str += "Regular";
		
		return str;
	}
	



}
