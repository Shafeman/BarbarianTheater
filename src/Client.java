import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Barbarians: Douglas Brian Shaffer, Nathan Kangas, Johnathan Franco
 * This class extends TheaterPatron and acts as the Theater members that
 * put on plays. The class has a balance, as well as the other variables
 * in TheaterPatron (name, address, phone number, unique id). It also
 * holds an ArrayList of Shows.
 * The class is responsible for managing the client's balance and
 * adding plays, searching the Client's plays for a certain date, and
 * returning an iterator for traversing the array of Shows. 
 */
public class Client extends TheaterPatron{
	private double balance;
	private ArrayList<Show> shows;

/**
 * Client Constructor. Sets balance to 0.00 and generates a unique ID through TheaterPerson
 * @param name - the client's name
 * @param address - the client's address
 * @param phoneNumber - the client's phone number
 */
	public Client(String name, String address, String phoneNumber) {
		super(name, address, phoneNumber);
		this.balance = 0.00;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public Iterator<Show> getShows(){
		return shows.iterator();
	}
	
	public void addShow(Show show){
		shows.add(show); 
		/*
		 * The addShow method in Theater should be the method that searches all of the clients and does the date
		 * checking. When it's sure there are no date overlaps, it can call the client.addshow method.
		 */
	}

/**
 * hasDate loops through the ArrayList of shows and returns
 * true if a date falls between a shows start and end dates.
 * @param date - the date being searched for
 * @return
 */
	public boolean hasDate(Date date){
		for(int i = 0; i < shows.size(); i++){
			if(shows.get(i).getStartDate().before(date) && shows.get(i).getStartDate().after(date) ){
				return true;
			}				
		}
		return false;
	}
	
}
