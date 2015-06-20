package theater;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
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
	
	public static boolean isCreditCardInCorrectFormat(String creditCardNumber) { //clean this up with reg-ex

	        if (creditCardNumber.length() == 19) {

	            for (int i = 0; i < creditCardNumber.length(); i++) {

	                if (i == 4 || i == 9 || i == 14) {

	                    if (!(creditCardNumber.charAt(i) == '-')) {
	                        return false;
	                    }
	                } else {
	                    if (!(Character.isDigit(creditCardNumber.charAt(i)))) {
	                        return false;
	                    }
	                }
	            }
	        } else {
	            return false;
	        }
	        return true;
	    }

	public static boolean isProperExpirationDateFormat(String expirationDate) {

        if (expirationDate.length() == 5) {

            for (int i = 0; i < expirationDate.length(); i++) {

                if (i == 2) {

                    if (!(expirationDate.charAt(i) == '/')) {
                        return false;
                    }
                } else {
                    if (!(Character.isDigit(expirationDate.charAt(i)))) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {

		//Calendar calendar = Calendar.getInstance();
		//calendar.add(expirationDate.DATE, 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");

		return "Credit card number: " + creditCardNumber + "\nExpiration date: " +
				simpleDateFormat.format(expirationDate.getTime());
	}
}
