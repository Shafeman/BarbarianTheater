package theater;

import java.util.Calendar;

@SuppressWarnings("serial")
public class StudentAdvanceTicket extends Ticket {	
	
	
	public StudentAdvanceTicket(Member member, Calendar date, double price) {
		super(/*member,*/ date, price);
		this.studentAdvanceDiscount();
		
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
