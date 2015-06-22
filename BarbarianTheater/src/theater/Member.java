package theater;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Member extends TheaterPatron implements Serializable {
	private List<CreditCard> creditCards;
	
	public Member(String name, String address, String phoneNumber, String creditCardNumber, Calendar expiration){
		super(name, address, phoneNumber);
		creditCards = new ArrayList<CreditCard>();
		creditCards.add(new CreditCard(creditCardNumber, expiration));
	}
	
	public CreditCard addCreditCard(String creditCardNumber, Calendar expiration){
		CreditCard card = new CreditCard(creditCardNumber, expiration);
		creditCards.add(card);
		return card;
	}

	public List<CreditCard> getCreditCards() {
		return creditCards;
	}
		
	/**
	 * Searches the list of creditCards the Member currently holds for 
	 * the creditCardNumber provided, and removes it. 
	 * @param creditCardNumber
	 * @return true : if found and removed 
	 * 	false: if not found or Member only has one creditCard	
	 */
	public boolean removeCreditCard(String creditCardNumber){
		
		boolean removed = false;
		
		//Member has more than one card
		if(creditCards.size() > 1){ 
			for(int i = 0; i < creditCards.size(); i++){
				if(creditCards.get(i).getCreditCardNumber().equals(creditCardNumber)){
					creditCards.remove(i);
					removed = true;
					return removed;
				}				
			}			
		}
		return removed;		
	}

	private String printCreditCardList() {

		String creditCardList = "";
		for (CreditCard creditCard : creditCards) {

			creditCardList += creditCard.toString() + "\n";
		}

		return creditCardList;
	}

	@Override
	public String toString() {
		return super.toString() + "\n" + printCreditCardList();
	}
}
