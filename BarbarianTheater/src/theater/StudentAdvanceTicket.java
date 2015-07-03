package theater;

import java.math.BigDecimal;
import java.util.Calendar;

@SuppressWarnings("serial")
public class StudentAdvanceTicket extends Ticket {	
	
	
	public StudentAdvanceTicket(Calendar date, double price) {
		super(date, price);
		serialNumber = this.generateSerialNumber();
		ticketPrice = this.studentAdvanceDiscount();
		
	}
	
	/**
	 * This will discount the ticket price for student advance tickets.
	 * Which cost 50% of an advanced ticket.
	 * @return double that is rounded. (0.00)
	 */
	public double studentAdvanceDiscount() {		
		BigDecimal discountedPrice1 = new BigDecimal(
				(ticketPrice * advanceDiscount),theRounding);
		BigDecimal discountedPrice = new BigDecimal(
			(discountedPrice1.doubleValue() * studentDiscount), theRounding);
		
		return discountedPrice.doubleValue();
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
		str += "$" + this.ticketPrice + " ";
		str += "Student Advance";
		str += " *Must show valid student id*";
		
		return str;
	}


}
