/**
 * Barbarians: Douglas Brian Shaffer, Nathan Kangas, Johnathan Franco
 * This class extends TheaterPatron and acts as the Theater members that
 * put on plays. The class has a balance, as well as the other variables
 * in TheaterPatron (name, address, phone number, unique id). It also
 * holds an ArrayList of Shows.
 * The class is responsible for managing the client's balance and
 * adding plays, searching the Client's plays for a certain date, and
 * returning an iterator for traversing the array of Shows...
 */
package theater;

import java.io.Serializable;
import java.util.*;


@SuppressWarnings("serial")
public class Client extends TheaterPatron implements Serializable {
	private double balance;
	private List<Show> shows;

/**
 * Client Constructor. Sets balance to 0.00 and generates a unique ID through TheaterPerson
 * @param name - the client's name
 * @param address - the client's address
 * @param phoneNumber - the client's phone number
 */
	public Client(String name, String address, String phoneNumber) {
		super(name, address, phoneNumber);
		this.balance = 0.00;
		shows = new ArrayList<Show>();
	}

	/**
	 * getBalance returns the Client's balance
	 * @return
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * setBalance takes a double and assigns it to this Client's balance
	 * @param balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * getShows returns a List of shows
	 * @return
	 */
	public List<Show> getShows() {
		return shows;
	}
	
	/**
	 * addShow adds a Show to the list of Shows.
	 * @param show
	 */
	public void addShow(Show show){
		shows.add(show); 
	}

	/**
	 * hasDate loops through the ArrayList of shows and returns
	 * true if a date falls between a shows start and end dates.
	 * @param date - the date being searched for
	 * @return
	 */
	public boolean hasDate(Calendar startDate, Calendar endDate){
		boolean hasDate = false;
		
		for(int i = 0; i < shows.size(); i++){
			Calendar testStartDate = shows.get(i).getStartDate();
			Calendar testEndDate = shows.get(i).getEndDate();
			
			if((startDate.after(testStartDate) && startDate.before(testEndDate))
				      || (endDate.after(testStartDate) && endDate.before(testEndDate))
				      || (startDate.before(testStartDate) && endDate.after(testEndDate))
				      || (startDate.equals(testStartDate) || (endDate.equals(testEndDate))))
				hasDate = true;
		}	
		return hasDate;
	} 
	
	/**
	 * hasShow returns true if the Client has any shows scheduled or false otherwise.
	 * @return
	 */
	public boolean hasShow() {
		boolean hasShows = false;
		
		if(shows != null)
			hasShows = true;
		
		return hasShows;
	}

	/**
	 * toString returns the Client's name, address, phone number, and balance.
	 */
	@Override
	public String toString() {
		return super.toString() + "\nBalance: " + balance;
	}
}
