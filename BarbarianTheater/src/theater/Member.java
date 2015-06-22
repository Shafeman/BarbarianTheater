package theater;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Member extends TheaterPatron implements Serializable {
public class Member extends TheaterPatron{
	private static int CC_NOT_HERE = 0;
	private static int TOO_FEW_CARDS = 1;
	private static int SUCCESS = 2;
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
