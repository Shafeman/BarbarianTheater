package theater;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@SuppressWarnings("serial")
public abstract class Ticket implements Serializable{
	
	private TheaterPatronIdServer singletonTheaterPatronIdServer;
	protected String serialNumber;	
	private Calendar dateOfShow;
	protected BigDecimal ticketPrice;	
	protected static BigDecimal advanceDiscount = new BigDecimal(0.70);
	protected static BigDecimal studentDiscount = new BigDecimal(0.50);
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	public Ticket(Calendar date, BigDecimal price) {
		this.dateOfShow = date;
		this.ticketPrice = price;
		singletonTheaterPatronIdServer = TheaterPatronIdServer.theaterPatronIdServerInstance();
	}

	/**
	 * Returns the serial number assigned to the Ticket
	 * @return
	 */
	public String getSerialNumber() {
		return serialNumber;
	}	

	/**
	 * Returns the date a show the ticket is for.
	 * @return
	 */
	public Calendar getDateOfShow() {
		return dateOfShow;
	}

	/**
	 * Returns the price of the ticket
	 * @return
	 */
	public BigDecimal getTicketPrice() {
		return ticketPrice;
	}
	
	/**
	 * Generate a unique serial number for a Ticket.
	 * @return
	 */
	public String generateSerialNumber() {
		String serialNumber = "";
		
		if((this.dateOfShow.get(Calendar.MONTH) < 10) 
				&& (this.dateOfShow.get(Calendar.DAY_OF_MONTH) < 10)) {		
			serialNumber += "0" + (this.dateOfShow.get(Calendar.MONTH) + 1);
			serialNumber += "0" + this.dateOfShow.get(Calendar.DAY_OF_MONTH);
			serialNumber += (this.dateOfShow.get(Calendar.YEAR)) % 100;
			serialNumber += "0";
		}else	{		
			serialNumber += (this.dateOfShow.get(Calendar.MONTH) + 1);
			serialNumber += this.dateOfShow.get(Calendar.DAY_OF_MONTH);
			serialNumber += (this.dateOfShow.get(Calendar.YEAR)) % 100;
			serialNumber += "0";
		}
		
		//unique number to the system
		serialNumber += singletonTheaterPatronIdServer.getNewIdCounter();
		
		return serialNumber;
	}	
	
	/**
	 * Returns a description of the ticket
	 */
	@Override
	public String toString() {
		String str = "";		
		
		str += dateFormat.format(dateOfShow.getTime()) + " ";
		
		return str;
	}
	
	
}