package theater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Nathan on 6/19/2015.
 */

public class UserInterface {

    private static final int EXIT = 0;
    private static final int ADD_CLIENT = 1;
    private static final int REMOVE_CLIENT = 2;
    private static final int LIST_CLIENTS = 3;
    private static final int ADD_MEMBER = 4;
    private static final int REMOVE_MEMBER = 5;
    private static final int ADD_CREDIT_CARD = 6;
    private static final int REMOVE_CREDIT_CARD = 7;
    private static final int LIST_MEMBER = 8;
    private static final int ADD_SHOW = 9;
    private static final int LIST_SHOWS = 10;
    private static final int SAVE_DATA = 11;
    private static final int RETRIEVE_DATA = 12;
    private static final int HELP = 13;
    private static UserInterface userInterface;
    private static Theater theater;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private UserInterface() {
        if (yesOrNo("Look for saved data and  use it?")) {
            retrieveData();
        } else {
            theater = Theater.instance("Guthrie", 1441);
        }
    }

    public static UserInterface instance() {
        if (userInterface == null) {
            return userInterface = new UserInterface();
        } else {
            return userInterface;
        }
    }

    public static void main(String[] args) {

        UserInterface.instance().process();
    }

    private boolean yesOrNo(String prompt) {
        String more = getToken(prompt + " (Y|y)[es] or anything else for no");
        if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
            return false;
        }
        return true;
    }

    public void help() {
        System.out.println("Enter a number between 0 and 12 as explained below:");
        System.out.println(EXIT + " to Exit\n");
        System.out.println(ADD_CLIENT + " to add a client");
        System.out.println(REMOVE_CLIENT + " to remove a client");
        System.out.println(LIST_CLIENTS + " to list all clients");
        System.out.println(ADD_MEMBER + " to add member ");
        System.out.println(REMOVE_MEMBER + " to remove member ");
        System.out.println(ADD_CREDIT_CARD + " to add credit card");
        System.out.println(REMOVE_CREDIT_CARD + " to remove credit card");
        System.out.println(LIST_MEMBER + " to list member");
        System.out.println(ADD_SHOW + " to add show");
        System.out.println(LIST_SHOWS + " to list all shows");
        System.out.println(SAVE_DATA + " to save data");
        System.out.println(RETRIEVE_DATA + " to retrieve");
        System.out.println(HELP + " for help");
    }

    public void process() {
        int command;
        help();
        while ((command = getCommand()) != EXIT) {
            switch (command) {
                case ADD_CLIENT: addClient();
                    break;
                case REMOVE_CLIENT: removeClient();
                    break;
                case LIST_CLIENTS: listClients();
                    break;
                case ADD_MEMBER: addMemeber();
                    break;
                case REMOVE_MEMBER: removeMember();
                    break;
                case ADD_CREDIT_CARD: addCreditCard();
                    break;
                case REMOVE_CREDIT_CARD: removeCreditCard();
                    break;
                case LIST_MEMBER: listMember();
                    break;
                case ADD_SHOW: addShow();
                    break;
                case LIST_SHOWS: listShows();
                    break;
                case SAVE_DATA: saveData();
                    break;
                case RETRIEVE_DATA: retrieveData();
                    break;
                case HELP: help();
                    break;
            }
        }
    }

    public int getCommand() {
        do {
            try {
                int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
                if (value >= EXIT && value <= HELP) {
                    return value;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Enter a number");
            }
        } while (true);
    }

    public String getToken(String prompt) {
        do {
            try {
                System.out.println(prompt);
                String line = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
                if (tokenizer.hasMoreTokens()) {
                    return tokenizer.nextToken();
                }
            } catch (IOException ioe) {
                System.exit(0);
            }
        } while (true);
    }

    /**
     * Prompts for a date and gets a date object
     * @param prompt the prompt
     * @return the data as a Calendar object
     */
    public Calendar getDate(String prompt) {
      do {
        try {
          Calendar date = new GregorianCalendar();
          String item = getToken(prompt);
          DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
          dateFormat.setLenient(false);
          date.setTime(dateFormat.parse(item));
          return date;
        } catch (Exception fe) {
          System.out.println("Incorrect Format");
        }
      } while (true);
    }   
    
    private void addClient() {

        String name = getToken("Enter client name");
        String address = getToken("Enter client address");
        String phoneNumber = getToken("Enter client phone number");
        Client client;
        client = theater.addClient(name, address, phoneNumber);

        if (client != null) {
            System.out.println("Client " + name + " " + client.getID() + " was added");
        }
    }

    private void removeClient() {

        String clientID = getToken("Enter client ID to be removed");
        if (theater.removeClient(clientID)) {
            System.out.println("Client " + clientID +  " was removed");
        } else {
            System.out.println("Client ID entered didn't match any in the system");
        }
    }

    private void listClients() {

        for (Client client : theater.listClients()) {

            System.out.println(client.toString());
        }

    }

    private void addMemeber() {

        String name = getToken("Enter client name");
        String address = getToken("Enter client address");
        String phoneNumber = getToken("Enter client phone number");
        String creditCardNumber = getCreditCardNumber("Enter credit card number with dashes\n" +
                "Example 1111-2222-3333-4444"); 
        Calendar date = getCreditCardExpirationDate("Enter credit card expiration date in this format mm/yy");        
        Member member = theater.addMember(name, address, phoneNumber, creditCardNumber, date);

        if (member != null) {
            System.out.println("Member " + name + " was added");
        }
    }

    private void removeMember() {

        String memberID = getToken("Enter member ID to be removed");
        if (theater.removeMember(memberID)) {
            System.out.println("Member " + memberID + " was removed");
        } else {
            System.out.println("Member ID entered didn't match any in the system");
        }
    }

    private void addCreditCard() {

        String memberID = getToken("Enter member ID to add the credit card too");
        Member member = theater.searchMember(memberID);

        if (member != null) {
            String creditCardNumber = getCreditCardNumber("Enter credit card number with dashes\n" +
                    "Example 1111-2222-3333-4444");
            Calendar expirationDate = getCreditCardExpirationDate("Enter credit card expiration date in this format mm/yy");
            member.addCreditCard(creditCardNumber, expirationDate);
        } else {
            System.out.println("Member " + memberID + " isn't in the system");
        }
    }

    private void removeCreditCard() {


    }

    private void listMember() {

        for (Member member : theater.listMembers()) {

            System.out.println(member.toString());
        }
    }
    
    /**
     * Method to add a new show to a current client with no
     * conflicting dates.     * 
     */
    private void addShow() {
    	Show show;
    	do{
    	String id = getToken("Please enter a client ID");
    	Client client = theater.searchClient(id);
    	
    	if(client != null) {
    		String showTitle = getToken("What is the title of the show?");
    		Calendar startDate = getDate("Enter a start date: mm/dd/yy");
    		Calendar endDate = getDate("Enter a end date: mm/dd/yy");
    	
    		//Show will return null if date are used 
    		show = theater.addShow(id, showTitle, startDate, endDate);    		
    		if(show != null){
    	    		System.out.println(show);
    		}else{
    			 System.out.println("These date/s are already booked!");
    		}
    	
    	}else {
    		System.out.println("No such client exists!");
    	}    	
    		if (!yesOrNo("Add another show?")) {
            break;
    		}
    	}while(true);    	
    }	
    	
    /**
     * Method to list a currently running shows from
     * each client.
     */
    private void listShows() {
    	
    	System.out.println("************************************");
    	System.out.println("A list of current shows:\n");    	
    	
    	for(Show show : theater.listShows()) {
    		System.out.print(show.toString() + "\n");
    	}
    	System.out.println("************************************");
    }

    private void saveData() {


    }

    private void retrieveData() {


    }

    private Calendar getCreditCardExpirationDate(String prompt) {

        do {
            try {
                System.out.println(prompt);
                String line = reader.readLine();
                DateFormat dateFormat = new SimpleDateFormat("MM/yy");
                dateFormat.setLenient(false);
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(dateFormat.parse(line));
                if(calendar.after(Calendar.getInstance())){
                	return calendar;
                }
                else{
                	System.out.println("Expiration Date cannot be in the past.");
                }                	
            } catch (IOException ioe) {
                System.exit(0);
            } catch (ParseException pe) {
                System.out.println("Incorrect Format");
            }
        } while (true);
    }

    private String getCreditCardNumber(String prompt) {

        while (true) {

            System.out.println(prompt);

            try {
                String creditCardNumber = reader.readLine();

                if (theater.checkCreditCardInCorrectFormat(creditCardNumber)) {

                    if (!theater.isCreditCardDuplicate(creditCardNumber)) {

                        return creditCardNumber;
                    }
                }
            } catch (IOException ioe) {
                System.out.println("bad credit card input");
            }
        }
    }
}
