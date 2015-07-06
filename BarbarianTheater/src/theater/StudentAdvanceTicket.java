package theater;

import java.util.Calendar;

@SuppressWarnings("serial")
public class StudentAdvanceTicket extends Ticket {	
	
	
	public StudentAdvanceTicket(Calendar date, Integer price) {
		super(date, price);
		serialNumber = this.generateSerialNumber();
		ticketPrice = this.studentAdvanceDiscount();
		
	}
	
	/**
	 * This will discount the ticket price for student advance tickets.
	 * Which cost 50% of an advanced ticket.
	 * @return Integer that is in "Cents"
	 */
	public Integer studentAdvanceDiscount() {		
		Integer firstDiscount = (int) (ticketPrice * advanceDiscount);
		Integer secondDiscount = (int) (firstDiscount * studentDiscount);
		
		return secondDiscount;
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
	
	/**
	 * Returns a description of the ticket
	 */
	@Override
	public String toString() {
		String str = "";
		
		str += super.toString();
		str += " " + serialNumber + " ";
		str += "$" + UserInterface.displayPrice(this.ticketPrice) + " ";
		str += "Student Advance";
		str += " *Must show valid student id*";
		
		return str;
	}
}
