/**
 * Barbarians: Douglas Brian Shaffer, Nathan Kangas, Johnathan Franco
 * The Member class acts as a customer in the Theater. The class extends
 * Theater Patron and maintains a list of Credit Cards that belong to the
 * Member. It has methods for adding and removing credit cards and generating
 * a credit card list.
 */
package theater;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@SuppressWarnings("serial")
public class Member extends TheaterPatron implements Matchable<String>, Serializable {

	private static int CC_NOT_HERE = 0;
	private static int TOO_FEW_CARDS = 1;
	private static int SUCCESS = 2;
	private List<CreditCard> creditCards = new ArrayList<CreditCard>();
	private List<Ticket> ticketsBought = new ArrayList<Ticket>();
	
	/**
	 * Member constructor
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @param creditCardNumber
	 * @param expiration
	 */
	public Member(String name, String address, String phoneNumber, String creditCardNumber, Calendar expiration){
		super(name, address, phoneNumber);
		creditCards = new ArrayList<CreditCard>();
		creditCards.add(new CreditCard(creditCardNumber, expiration));
	}
	
	/**
	 * The tickets bought by a member are stored here
	 * @param ticket
	 */
	public void addTicket(Ticket ticket) {
		ticketsBought.add(ticket);
	}
	
	/**
	 * addCreditCard adds a credit card to the credit card list.
	 * @param creditCardNumber
	 * @param expiration
	 * @return
	 */
	public CreditCard addCreditCard(String creditCardNumber, Calendar expiration){
		CreditCard card = new CreditCard(creditCardNumber, expiration);
		creditCards.add(card);
		return card;
	}

	/**
	 * getCreditCards returns a List of credit cards.
	 * @return
	 */
	public List<CreditCard> getCreditCards() {
		return creditCards;
	}
		
	/**
	 * Searches the list of creditCards the Member currently holds for 
	 * the creditCardNumber provided, and removes it. The method will only
	 * remove a credit card if the member has more than one card on file.
	 * A member always needs at least one card on file.
	 * @param creditCardNumber
	 * @return true : if found and removed 
	 * 	false: if not found or Member only has one creditCard	
	 */
	public int removeCreditCard(String creditCardNumber){
		for(int i = 0; i < creditCards.size(); i++){
			if(creditCards.get(i).getCreditCardNumber().equals(creditCardNumber)){
				if(creditCards.size() > 1){ 
					creditCards.remove(i);
					return SUCCESS;
				}
				else{
					return TOO_FEW_CARDS;
				}
			}			
		}
		return CC_NOT_HERE;		
	}

	/**
	 * Generates a string of credit cards on file for use in the toString method.
	 * @return
	 */
	private String printCreditCardList() {

		String creditCardList = "";
		for (CreditCard creditCard : creditCards) {

			creditCardList += creditCard.toString() + "\n";
		}

		return creditCardList;
	}

	/**
	 * returns a String of the Member's name, address, phone number and credit cards on file.
	 */
	@Override
	public String toString() {
		return super.toString() + "\n" + printCreditCardList();
	}
	
	/**
	 * Checks whether an item's key matches the given key. 
	 * @param key - the key value
	 * @return true iff the item's key matches the given key
	 */
	@Override
	public boolean matches(String key) {
		return this.getID().equals(key);
	}
}
