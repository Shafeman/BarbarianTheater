/**
 * Barbarians: Douglas Brian Shaffer, Nathan Kangas, Johnathan Franco
 * The Show class represents a show that is being put on by a client.
 * Each show has a name, start date, end date, price, and soldTickets. Once a name is set,
 * it cannot be changed, but dates can be changed through setters. 
 */
package theater;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;


@SuppressWarnings("serial")
public class Show implements Matchable<Show>, Serializable {
	private String name;
	private Calendar startDate;
	private Calendar endDate;
	private BigDecimal price;
	

	private int soldTickets;
	
	/**
	 * Show Constructor. Creates a Show.
	 * @param name
	 * @param startDate
	 * @param endDate
	 */
	public Show(String name, Calendar startDate, Calendar endDate, BigDecimal price){
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.soldTickets = 0;
	}
	
	/**
	 * getName returns the Show name.
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * setName takes a String and assigns it to this Show's name.
	 * @param String name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getStartDate returns the Show's start date.
	 * @return
	 */
	public Calendar getStartDate() {
		return startDate;
	}
	
	/**
	 * getEndDate returns the Show's end date
	 * @return
	 */
	public Calendar getEndDate() {
		return endDate;
	}
	
	/**
	 * @return the soldTickets
	 */
	public int getSoldTickets() {
		return soldTickets;
	}

	public void indicateSell(){
		this.soldTickets++;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	

	/**
	 * toString returns the Show name, start date, and end date as a String.
	 * @return
	 */
	@Override
	public String toString() {
		
		String firstDate = this.dateToString(startDate);
		String lastDate = this.dateToString(endDate);
		String str = "";
		
		str += name;
		str += ":  " + firstDate + " - " + lastDate;
		str += " Price: $" + price;			
		
		return str;
		
	}

	@Override
	public boolean matches(Show key) {
		if (this.getName().equals(key.getName())){
			if (this.dateToString(startDate).equals(key.dateToString(key.getStartDate()))){
				if (this.dateToString(endDate).equals(key.dateToString(key.getEndDate()))){
					return true;
				}
			}
		}
		return false;
	}
	
	private String dateToString(Calendar date){
		int day = date.get(Calendar.DAY_OF_MONTH);
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		
		return month + "/" + day + "/" + year;
	}

}
