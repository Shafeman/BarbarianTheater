

public abstract class TheaterPatron {
	private String id;
	private String name;
	private String address;
	private String phoneNumber;
	private static int counter = 0;
	
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
	
	public String getAddress(){
		return address;
	}
	
	public String getPhoneNumber(){
		return phoneNumber;
	}
	
	public void setName(String name){
		this.name = name;		
	}
	
	public void setAddress(String address){
		this.address = address;
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
	
}
