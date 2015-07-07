package theater;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;

@SuppressWarnings("serial")
public class RegularTicket extends Ticket {
	

	public RegularTicket(Calendar date, BigDecimal price) {
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
		str += "$" + ticketPrice.round(new MathContext(3)) + " ";
		str += "Regular";
		
		return str;
	}
	



}
