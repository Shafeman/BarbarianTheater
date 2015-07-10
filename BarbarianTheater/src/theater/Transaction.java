/**
 * Barbarians: Douglas Brian Shaffer, Nathan Kangas, Johnathan Franco
 * Transaction creates a link between a member, a ticket, and a show, and stores
 * the credit card used to pay for the ticket.
 */
package theater;

import java.io.Serializable;

@SuppressWarnings("serial")
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
	}
	
	/**
	 * Returns the show
	 * @return
	 */
	public Show getShow() {
		return show;
	}

	/**
	 * Returns the member
	 * @return
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * Returns the ticket
	 * @return
	 */
	public Ticket getTicket() {
		return ticket;
	}

	/**
	 * Returns the credit card
	 * @return
	 */
	public CreditCard getCreditCard() {
		return creditCard;
	}
	
	

}
