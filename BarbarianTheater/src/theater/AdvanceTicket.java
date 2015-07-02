package theater;

import java.math.BigDecimal;
import java.util.Calendar;

@SuppressWarnings("serial")
public class AdvanceTicket extends Ticket{
	
	
	public AdvanceTicket(Member member, Calendar date, double price) {
		super(date, price);
		this.generateSerialNumber();
		this.advanceDiscount();
		
	}
	
	/**
	 * This will discount the tickets price for advance tickets.
	 * Which cost 70% of a Regular ticket
	 * @return double that is rounded. (0.00)
	 */
	public double advanceDiscount() {	
		BigDecimal discountedPrice = new BigDecimal(
				(ticketPrice * advanceDiscount),theRounding);
		
		return discountedPrice.doubleValue();
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
