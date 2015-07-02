/**
 * Barbarians: Douglas Brian Shaffer, Nathan Kangas, Johnathan Franco
 * The Show class represents a show that is being put on by a client.
 * Each show has a name, start date, end date, price, and soldTickets. Once a name is set,
 * it cannot be changed, but dates can be changed through setters. 
 */
package theater;

import java.io.Serializable;
import java.util.Calendar;


@SuppressWarnings("serial")
public class Show implements Serializable {
	private String name;
	private Calendar startDate;
	private Calendar endDate;
	private double price;
	

	private int soldTickets;
	
	/**
	 * Show Constructor. Creates a Show.
	 * @param name
	 * @param startDate
	 * @param endDate
	 */
	public Show(String name, Calendar startDate, Calendar endDate, double price){
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
	
	public double getPrice() {
		return price;
	}

	/**
	 * toString returns the Show name, start date, and end date as a String.
	 * @return
	 */
	@Override
	public String toString() {
		
		int startDay = startDate.get(Calendar.DAY_OF_MONTH);
		int startMonth = startDate.get(Calendar.MONTH) + 1;
		int startYear = startDate.get(Calendar.YEAR);
		int endDay = endDate.get(Calendar.DAY_OF_MONTH);
		int endMonth = endDate.get(Calendar.MONTH) + 1;
		int endYear = endDate.get(Calendar.YEAR);
		
		String str = "";
		
		str += name;
		str += ":  " + startMonth + "/" + startDay + "/" + startYear + " -";
		str += " " + endMonth + "/" + endDay + "/" + endYear;
		str += " Price: $" + price;
				
		
		return str;
		
	}

}
