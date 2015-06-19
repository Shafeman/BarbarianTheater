package theater;
import java.util.Calendar;
import java.util.Date;


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
	
}
