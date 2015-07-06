package theater;

import java.util.Calendar;

@SuppressWarnings("serial")
public class RegularTicket extends Ticket {
	

	public RegularTicket(Calendar date, Integer price) {
		super(date, price);
		serialNumber = this.generateSerialNumber();
		
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
		str += " " + serialNumber + " ";
		str += "$" + UserInterface.displayPrice(this.ticketPrice) + " ";
		str += "Regular";
		
		return str;
	}
	



}
