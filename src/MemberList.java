import java.util.ArrayList;
import java.util.Iterator;


public class MemberList<E> extends ArrayList<E>{
	private ArrayList<Member> members;
	private static MemberList<Member> singletonMemberList;
	
	private MemberList(){
		
	}
	
	public static MemberList<Member> memberListInstance(){
		if(singletonMemberList == null){
			singletonMemberList = new MemberList<Member>();
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
		return false;
	}
	
	public Iterator<Member> getList(){
		return members.iterator();
	}
	
	public Member search(String id){
		for(int i = 0; i < members.size(); i++){
			if(members.get(i).getID().equals(id)){
				return members.get(i);
			}				
		}
		return null;
	}
}
