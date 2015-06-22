package theater;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MemberList implements Serializable {
	private static MemberList singletonMemberList;
	//private static MemberList singletonMemberList = MemberList.memberListInstance();
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
		return false;
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
		return null;
	}

	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(singletonMemberList);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private Object readResolve() {
		return singletonMemberList;
	}

	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (singletonMemberList != null) {

				input.readObject();
				return;
			} else {
				input.defaultReadObject();
				if (singletonMemberList == null) {
					singletonMemberList = (MemberList) input.readObject();
				} else {
					input.readObject();
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
}
