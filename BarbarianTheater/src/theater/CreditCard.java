package theater;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CreditCard {
	private String creditCardNumber;
	private Calendar expirationDate;
	
	public CreditCard(String creditCardNumber, Calendar expiration){
		this.creditCardNumber = creditCardNumber;
		this.expirationDate = expiration;
	}
	
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public Calendar getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(Calendar expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public static boolean isCreditCardInCorrectFormat(String creditCardNumber) {

		String nonDigitsPattern = "[^0-9]+";
		creditCardNumber = creditCardNumber.replaceAll(nonDigitsPattern, "");
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


	@Override
	public String toString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");

		return "Credit card number: " + creditCardNumber + "\nExpiration date: " +
				simpleDateFormat.format(expirationDate.getTime());
	}
}
