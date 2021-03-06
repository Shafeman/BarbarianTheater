/**
 * Barbarians: Douglas Brian Shaffer, Nathan Kangas, Johnathan Franco
 * The TheaterPatron is an abstract class for all patrons the Theater
 * might do business with. It has the name, address, unique id, and phone
 * number, as well as a singleton TheaterPatronIdServer used for generating
 * unique IDs. Methods include getters/setters and a generateID method.
 */
package theater;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class TheaterPatron implements Serializable {

	private TheaterPatronIdServer singletonTheaterPatronIdServer;
	private String id;
	private String name;
	private String address;
	private String phoneNumber;
	
	/**
	 * TheaterPatron constructor
	 * @param name
	 * @param address
	 * @param phoneNumber
	 */
	public TheaterPatron(String name, String address, String phoneNumber){
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		singletonTheaterPatronIdServer = TheaterPatronIdServer.theaterPatronIdServerInstance();
		this.id = generateID();
	}

	/**
	 * getID returns the ID.
	 * @return
	 */
	public String getID(){
		return id;
	}
	
	/**
	 * getName returns the name.
	 * @return
	 */
	public String getName(){
		return name;
	}

	/**
	 * setName assigns a passed string to this TheaterPatron's name 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getAddress returns the address.
	 * @return
	 */
	public String getAddress(){
		return address;
	}
	
	/**
	 * setAddress assigns the passed string to this TheaterPatron's address
	 * @param address
	 */
	public void setAddress(String address){
		this.address = address;
	}

	/**
	 * getPhoneNumber returns the phone number
	 * @return
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * setPhoneNumber assigns the passed string to this TheaterPatron's phone number
	 * @param address
	 */
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * generateID forms a unique ID from the first six characters
	 * of the TheaterPatron's name (or all characters if less than six)
	 * and a unique ID number generated by the TheaterPatronIdServer.
	 * @return
	 */
	public String generateID(){
		String id;
		int nameLength = this.name.length();
		if (nameLength > 6){
			nameLength = 6;
		}
		id = this.name.substring(0, nameLength) + singletonTheaterPatronIdServer.getNewIdCounter();
		return id;
	}

	/**
	 * toString returns the TheaterPatron's name, address, and phone number.
	 */
	@Override
	public String toString() {
		return ("ID: " + id + "\nName: " + name + "\nAddress: " + address + "\nPhone number: " +
				phoneNumber);
	}
}
