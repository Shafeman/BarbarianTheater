package theater;


public abstract class TheaterPatron {
	private static int counter = 0;
	private String id;
	private String name;
	private String address;
	private String phoneNumber;
	
	public TheaterPatron(String name, String address, String phoneNumber){
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.id = generateID();
		counter++;
	}
	
	public String getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	public String generateID(){
		String id;
		int nameLength = this.name.length();
		if (nameLength > 6){
			nameLength = 6;
		}
		id = this.name.substring(0, nameLength) + counter;
		return id;
	}

	@Override
	public String toString() {
		return ("ID: " + id + "\nName: " + name + "\nAddress: " + address + "\nPhone number: " +
				phoneNumber);
	}
	
	//add equals method that lets us call objects instead of ids?
}