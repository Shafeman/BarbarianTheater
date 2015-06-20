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

	@Override
	public String toString() {

		//Calendar calendar = Calendar.getInstance();
		//calendar.add(expirationDate.DATE, 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");

		return "Credit card number: " + creditCardNumber + "\nExpiration date: " +
				simpleDateFormat.format(expirationDate.getTime());
	}
}
