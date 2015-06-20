package theater;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;


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
	
	@Override
	public String toString() {
		
		int startDay = startDate.get(Calendar.DAY_OF_MONTH);
		int startMonth = startDate.get(Calendar.MONTH);
		int startYear = startDate.get(Calendar.YEAR);
		int endDay = endDate.get(Calendar.DAY_OF_MONTH);
		int endMonth = endDate.get(Calendar.MONTH);
		int endYear = endDate.get(Calendar.YEAR);
		
		String str = "";
		
		str += name;
		str += ":  " + startMonth + "/" + startDay + "/" + startYear + " -";
		str += " " + endMonth + "/" + endDay + "/" + endYear;
				
		
		return str;
		
	}

}
