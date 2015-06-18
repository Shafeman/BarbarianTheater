package theater;
import java.util.Calendar;
import java.util.Date;


public class Show {
	private String name;
	private Calendar startDate;
	private Calendar endDate;
	
	public Show(String name, Calendar startDate, Calendar endDate){
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
	
	public Calendar getStartDate() {
		return startDate;
	}
	
	public Calendar getEndDate() {
		return endDate;
	}
	

}
