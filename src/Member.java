import java.util.ArrayList;
import java.util.Date;


public class Member extends TheaterPatron{
	private ArrayList<CreditCard> creditCards;
	
	public Member(String name, String address, String phoneNumber, String creditCardNumber, Date expiration){
		super(name, address, phoneNumber);
		creditCards.add(new CreditCard(creditCardNumber, expiration));
	}
	
	public CreditCard addCreditCard(String creditCardNumber, Date expiration){
		CreditCard card = new CreditCard(creditCardNumber, expiration);
		creditCards.add(card);
		return card;
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
}
