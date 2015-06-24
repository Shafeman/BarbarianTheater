/**
 * Barbarians: Douglas Brian Shaffer, Nathan Kangas, Johnathan Franco
 * This class represents a Credit Card object. It holds the
 * credit card number as a string and a Calendar expiration date.
 * It has getters, a setter for the expiration date (the number should
 * not be changed after creation), and a method for validating the number
 * is in a correct credit card format. The toString() returns the number
 * and expiration date.
 */
package theater;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@SuppressWarnings("serial")
public class CreditCard implements Serializable {
	private String creditCardNumber;
	private Calendar expirationDate;
	
	/**
	 * Credit Card constructor.
	 * @param creditCardNumber
	 * @param expiration
	 */
	public CreditCard(String creditCardNumber, Calendar expiration){
		this.creditCardNumber = creditCardNumber;
		this.expirationDate = expiration;
	}
	
	/**
	 * getCreditCardNumber returns the credit card number.
	 * @return
	 */
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	/**
	 * getExpirationDate returns the expiration date.
	 * @return
	 */
	public Calendar getExpirationDate() {
		return expirationDate;
	}
	
	/**
	 * setExpiration Date takes a Calendar date and assigns it to
	 * this CreditCard's expiration date.
	 * @param expirationDate
	 */
	public void setExpirationDate(Calendar expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	/**
	 * isCreditCardInCorrectFormat uses a regular expression to confirm
	 * that a String credit card number is in a valid credit card format.
	 * It matches Visa, MasterCard, American Express, Diner's Club, Discover,
	 * and JCB cards. The regular expression was enhanced by the discussion at
	 * http://stackoverflow.com/questions/9315647/regex-credit-card-number-tests
	 * @param creditCardNumber
	 * @return
	 */
	public static boolean isCreditCardInCorrectFormat(String creditCardNumber) {

		String allCreditCardPatterns = "(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|"
				+ "6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|"
				+ "3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})";
		/*
		 * allCreditCardPatterns matches Visa, MasterCard, American Express, Diners Club, Discover, and JCB cards. 
		 * Regular expression taken from http://stackoverflow.com/questions/9315647/regex-credit-card-number-tests
		*/
		
		if(creditCardNumber.matches(allCreditCardPatterns)){
			return true;
		}
		else{
			return false;
		}
    }

	/**
	 * toString returns the credit card number and expiration date with identifiers.
	 */
	@Override
	public String toString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");

		return "Credit card number: " + creditCardNumber + "\nExpiration date: " +
				simpleDateFormat.format(expirationDate.getTime());
	}
}
