package theater;
import java.util.ArrayList;
import java.util.List;


public class MemberList {
	private static MemberList singletonMemberList;
	private List<Member> members;

	private MemberList() {

		members = new ArrayList<Member>();
	}

	public static MemberList memberListInstance() {
		if(singletonMemberList == null){
			singletonMemberList = new MemberList();
		}
		return singletonMemberList;
	}
	
	public boolean add(Member member){
		return members.add(member);
	}
	
	public boolean remove(String id){
		for(int i = 0; i < members.size(); i++){
			if(members.get(i).getID().equals(id)){
				members.remove(i);
				return true;
			}				
		}
		return false; //same as CientList. If we make Member equals() method use ID, we can just compare Members instead of Strings.
	}

	public List<Member> getList() {
		return members;
	}
	
	public Member search(String id){
		for(int i = 0; i < members.size(); i++){
			if(members.get(i).getID().equals(id)){
				return members.get(i);
			}				
		}
		return null;  //search for Member rather than ID?
	}
}
