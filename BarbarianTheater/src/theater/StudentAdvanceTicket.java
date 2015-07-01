package theater;

import java.util.Calendar;

public class StudentAdvanceTicket extends Ticket {
	
	
	//Not sure if this would be the correct way of setting the prices
	public StudentAdvanceTicket(Member member, Calendar date, double price) {
		super(/*member,*/ date, (price * advanceDiscount) * studentDiscount );
		
	}
	
	/**
	 * Generate a unique serial number for a Student advanced ticket
	 * Appends and "S" at the end of the SN to notify this.
	 */
	public String generateSerialNumber() {
		String serialNumber = super.generateSerialNumber();		
		
		serialNumber += "S";		
		
		return serialNumber;
	}

}
