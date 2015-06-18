import java.util.Date;


public class CreditCard {
	private String creditCardNumber;
	private Date expirationDate;
	
	public CreditCard(String creditCardNumber, Date expiration){
		this.creditCardNumber = creditCardNumber;
		this.expirationDate = expiration;
	}
	
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
}
