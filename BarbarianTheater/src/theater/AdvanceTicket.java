package theater;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;

@SuppressWarnings("serial")
public class AdvanceTicket extends Ticket{
	
	
	public AdvanceTicket(Calendar date, BigDecimal price) {
		super(date, price);
		serialNumber = this.generateSerialNumber();
		ticketPrice = this.advanceDiscount();
		
	}
	
	/**
	 * This will discount the tickets price for advance tickets.
	 * Which cost 70% of a Regular ticket
	 * @return Integer that represents "Cents"
	 */
	public BigDecimal advanceDiscount() {	
		
		BigDecimal discountedPrice = ticketPrice.multiply(advanceDiscount);			
	
		return discountedPrice.round(new MathContext(3));
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
	
	/**
	 * Returns a description of the ticket
	 */
	@Override
	public String toString() {
		String str = "";
		
		str += super.toString();
		str += " " + serialNumber + " ";
		str += "$" + this.ticketPrice.round(new MathContext(3)) + " ";
		str += "Advance";
		
		return str;
	}

}
