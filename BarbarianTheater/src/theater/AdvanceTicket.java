/**
 * Barbarians: Douglas Brian Shaffer, Nathan Kangas, Johnathan Franco
 * AdvanceTicket extends Ticket and sets the ticket price to 70% of
 * the regular ticket price.
 */
package theater;

import java.math.BigDecimal;
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
		
		BigDecimal discountedPrice = ticketPrice.multiply(advanceDiscount).setScale(2, BigDecimal.ROUND_HALF_UP);			
	
		return discountedPrice;
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
		str += "$" + ticketPrice + " ";
		str += "Advance";
		
		return str;
	}

}
