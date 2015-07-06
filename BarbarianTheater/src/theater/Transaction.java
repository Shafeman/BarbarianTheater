package theater;

import java.io.Serializable;


public class Transaction implements Serializable {
	
	private Show show;
	private Member member;
	private Ticket ticket;
	private CreditCard creditCard;
	
	public Transaction(Show show, Member member, Ticket ticket, CreditCard creditCard) {
		this.show = show;
		this.member = member;
		this.ticket = ticket;
		this.creditCard = creditCard;
		//Maybe a date of transaction?
	}
	
	
	public Show getShow() {
		return show;
	}

	public Member getMember() {
		return member;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}
	
	

}
