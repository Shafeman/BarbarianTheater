package theater;

import java.util.Calendar;

public class AdvanceTicket extends Ticket{

	
	//Not sure if this would be the correct way of setting the prices
	public AdvanceTicket(Member member, Calendar date, double price) {
		super(/*member,*/ date, price * advanceDiscount);
		
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
