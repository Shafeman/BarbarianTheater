/**
 * Barbarians: Douglas Brian Shaffer, Nathan Kangas, Johnathan Franco
 * This class holds a list of Members for the Theater. It maintains a list
 * and has methods for adding, removing, searching and listing members,
 * as well as saving and loading the list.
 */
package theater;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
public class MemberList implements Serializable {
	private static MemberList singletonMemberList;
	private List<Member> members;

    /**
	 * memberList Constructor, creates a singleton list as an ArrayList.
	 */
	private MemberList() {

		members = new ArrayList<Member>();
	}

	/**
	 * MemberListInstance is used to create a single MemberList if one does
	 * not exist, maintaining the MemberList's singleton property
	 * @return
	 */
	public static MemberList memberListInstance() {
		if(singletonMemberList == null){
			singletonMemberList = new MemberList();
		}
		return singletonMemberList;
	}
	
	/**
	 * add takes a Member and adds it to the list.
	 * @param Member
	 * @return
	 */
	public boolean add(Member member){
		return members.add(member);
	}
	
	/**
	 * remove takes a Member ID as a string and searches for it in the list.
	 * If the ID is found, that Member is removed, provided the Member has
	 * no future shows scheduled. remove returns true if the Member was removed
	 * and false otherwise.
	 * @param id
	 * @return
	 */
	public boolean remove(String id){
		for(int i = 0; i < members.size(); i++){
			if(members.get(i).getID().equals(id)){
				members.remove(i);
				return true;
			}				
		}
		return false;
	}

	/**
	 * getList provides a List of members.
	 * @return
	 */
	public List<Member> getList() {
		return members;
	}
	
	/**
	 * search takes a member ID as a string and returns that Member
	 * if found in the list. search returns null if the member isn't
	 * found in the list.
	 * @param id
	 * @return
	 */
	public Member search(String id){
		for(int i = 0; i < members.size(); i++){
			if(members.get(i).getID().equals(id)){
				return members.get(i);
			}				
		}
		return null;
	}
	
	/**
	 * writeOjbect is used to write the the MemberList to an output stream.
	 * @param output
	 */
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(singletonMemberList);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

    /**
     * readResolve returns the singleton MemberList
     * @return
     */
	private Object readResolve() {
		return singletonMemberList;
	}

    /**
     * readObject is used when loading the MemberList. It takes an ObjectInputStream
     * and ensures the singleton property is upheld if the stream has
     * a MemberList.
     * @param input
     */
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
