package theater;

import java.util.Calendar;

@SuppressWarnings("serial")
public class AdvanceTicket extends Ticket{
	
	
	public AdvanceTicket(Member member, Calendar date, double price) {
		super(/*member,*/ date, price);
		this.advanceDiscount();
		
	}
	
	/**
	 * Generate a unique serial number for a advanced ticket
	 * Appends and "A" at the end of the SN to notify this.
	 */
	public String generateSerialNumber() {
		String serialNumber = super.generateSerialNumber();		
		
		serialNumber += "A";		
		
		return serialNumber;
	}

}
