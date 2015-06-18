import java.util.Date;


public class Show {
	private String name;
	private Date startDate;
	private Date endDate;
	
	public Show(String name, Date startDate, Date endDate){
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	

}
