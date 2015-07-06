package theater;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;

@SuppressWarnings("serial")
public class StudentAdvanceTicket extends Ticket {	
	
	
	public StudentAdvanceTicket(Calendar date, BigDecimal price) {
		super(date, price);
		serialNumber = this.generateSerialNumber();
		ticketPrice = this.studentAdvanceDiscount();
		
	}
	
	/**
	 * This will discount the ticket price for student advance tickets.
	 * Which cost 50% of an advanced ticket.
	 * @return Integer that is in "Cents"
	 */
	public BigDecimal studentAdvanceDiscount() {		
		BigDecimal discountedPrice = ticketPrice.multiply(advanceDiscount);
		discountedPrice = discountedPrice.multiply(studentDiscount);
		
		return discountedPrice.round(new MathContext(3));
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
		str += "$" + ticketPrice.round(new MathContext(3)) + " ";
		str += "Student Advance";
		str += " *Must show valid student id*";
		
		return str;
	}
}
