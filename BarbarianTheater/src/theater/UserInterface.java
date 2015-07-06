/**
 * Barbarians: Douglas Brian Shaffer, Nathan Kangas, Johnathan Franco
 *  @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnathn
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.
 * 
 * This class implements the user interface for the Theater project.
 * The commands are encoded as integers using a number of
 * static final variables. A number of utility methods exist to
 * make it easier to parse the input.
 */
package theater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

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
    private static final int SELL_REGULAR_TICKET = 13;
    private static final int SELL_ADVANCE_TICKET = 14;
    private static final int SELL_STUDENT_ADVANCE_TICKET = 15;
    private static final int PAY_CLIENT = 16;
    private static final int PRINT_ALL_TICKETS = 17;
    private static final int HELP = 18;
    private static UserInterface userInterface;
    private static Theater theater;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * Constructor. Prompts user if they want to load a file or creates a new theater
     */
    String name;
    Integer capacity;

    private UserInterface() {
        if (yesOrNo("Look for saved data and  use it?")) {
            retrieve();
        } else {
        	
        	 //If starting a new Theater, ask for name and capacity
            name = getToken("What will be the name of your theater?");
            capacity = getNumber("What is the MAX capacity of your theater?");

            boolean isDataRelatedCommand;
            int commandValue;
            do {
                help();
                isDataRelatedCommand = true;
                commandValue = getCommand();

                if (commandValue == HELP) {
                    isDataRelatedCommand = false;
                } else if (commandValue == EXIT) {

                    if (yesOrNo("Do you wish to save your file before exiting")) {
                        saveData();
                    }
                    System.exit(0);
                } else if (commandValue == RETRIEVE_DATA) {
                    retrieve();
                    commandValue = -1;
                }

            } while (!isDataRelatedCommand);

            theater = Theater.instance(name, capacity);

            switch (commandValue) {
                case ADD_CLIENT:
                    addClient();
                    break;
                case REMOVE_CLIENT:
                    removeClient();
                    break;
                case LIST_CLIENTS:
                    listClients();
                    break;
                case ADD_MEMBER:
                    addMemeber();
                    break;
                case REMOVE_MEMBER:
                    removeMember();
                    break;
                case ADD_CREDIT_CARD:
                    addCreditCard();
                    break;
                case REMOVE_CREDIT_CARD:
                    removeCreditCard();
                    break;
                case LIST_MEMBER:
                    listMember();
                    break;
                case ADD_SHOW:
                    addShow();
                    break;
                case LIST_SHOWS:
                    listShows();
                    break;
                case SAVE_DATA:
                    saveData();
                    break;
                case RETRIEVE_DATA:
                    retrieve();
                    break;
                case SELL_REGULAR_TICKET:
                	sellRegularTicket();
                	break;
                case SELL_ADVANCE_TICKET:
                	sellAdvanceTicket();
                	break;
                case SELL_STUDENT_ADVANCE_TICKET:
                	sellStudentAdvanceTicket();
                	break;
                case PAY_CLIENT:
                	payClient();
                	break;
                case PRINT_ALL_TICKETS:
                	printAllTickets();
                	break;
                case HELP:
                    help();
                    break;
            }
        }
    }

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static UserInterface instance() {
        if (userInterface == null) {
            return userInterface = new UserInterface();
        } else {
            return userInterface;
        }
    }

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {

        UserInterface.instance().process();
    }

    /**
     * Converts the string to a number
     * @param prompt the string for prompting
     * @return the integer corresponding to the string
     * 
     */
    public int getNumber(String prompt) {
      do {
        try {
          String item = getToken(prompt);
          Integer number = Integer.valueOf(item);
          return number.intValue();
        } catch (NumberFormatException nfe) {
          System.out.println("Please input a number ");
        }
      } while (true);
    }
    
    /**
     * Converts the string to a double
     * @param prompt the string for prompting
     * @return the double corresponding to the string
     */
    public Integer getPrice(String prompt) {
    	do {
    		try {
    			String item = getToken(prompt);
    			item = item.replaceAll("[.]","");    			
    			Integer price = Integer.valueOf(item);
    			return price;
    		} catch (NumberFormatException nfe) {
    			System.out.println("Please input a price: (0.00) ");
    		}    		
    	}while (true);
    }
    
    /**
     * Price is in "cents", this method converts price into 
     * a 0.00 format.
     * @param price
     * @return String representation of Cents into dollars and cents
     */
    public static String displayPrice(Integer price) {
    	String str = "";
    	
    	str += price / 100;
		str += "."; 
		if(price % 100 == 0) {
			str += "00";
		}
		else {
			str += price % 100;
		}	
		
		return str;   	
    	
    }
    
    /**
     * Queries for a yes or no and returns true for yes and false for no
     * 
     * @param prompt The string to be prepended to the yes/no prompt
     * @return true for yes and false for no
     * 
     */
    private boolean yesOrNo(String prompt) {
        String more = getToken(prompt + " (Y|y)[es] or anything else for no");
        if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
            return false;
        }
        return true;
    }

    /**
     * Displays the help screen
     * 
     */
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
        System.out.println(SELL_REGULAR_TICKET + " to sell a regular ticket");
        System.out.println(SELL_ADVANCE_TICKET + " to sell a advance ticket");
        System.out.println(SELL_STUDENT_ADVANCE_TICKET + " to sell a student advance ticket");
        System.out.println(PAY_CLIENT + " to pay a client");
        System.out.println(PRINT_ALL_TICKETS + " to list all tickets for a certian date");
        System.out.println(HELP + " for help");
    }

    /**
     * calls appropriate methods for user input
     */
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
                case RETRIEVE_DATA: retrieve();
                	break;
                case SELL_REGULAR_TICKET: sellRegularTicket();
                	break;
                case SELL_ADVANCE_TICKET: sellAdvanceTicket();
                	break;
                case SELL_STUDENT_ADVANCE_TICKET: sellStudentAdvanceTicket();
                	break;
                case PAY_CLIENT: payClient();
                	break;
                case PRINT_ALL_TICKETS: printAllTickets();
                	break;
                case HELP: help();
                    break;
            }
        }
        if (yesOrNo("Do you wish to save your file before exiting")) {

            saveData();
        }
    }

    /**
     * Prompts for a command from the keyboard
     * 
     * @return a valid command
     * 
     */
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

    /**
     * Gets a token after prompting
     * 
     * @param prompt - whatever the user wants as prompt
     * @return - the token from the keyboard
     * 
     */
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
    
    /**
     * Method to be called for adding a client.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for adding the client.
     *  
     */
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

    /**
     * Prompts user for a client id and Calls appropriate methods in Theater 
     * class to remove a client. Prints messages to user based on returned results.
     */
    private void removeClient() {
    	int result;
    	
    	do {
    	      String clientID = getToken("Enter client's id to be removed");
    	      result = theater.removeClient(clientID);
    	      switch(result){
    	        case Theater.NOT_FOUND:
    	          System.out.println("Client does not exist");
    	          break;
    	        case Theater.SUCCESS:
    	          System.out.println("Client " + clientID +  " was removed");
    	          break;
    	        case Theater.HAS_SHOWS:
    	          System.out.println("Client has future shows and could not be removed");
    	          break;
    	        default:
    	          System.out.println("An error has occurred");
    	      }
    	      if (!yesOrNo("Remove another client?")) {
    	        break;
    	      }
    	    } while (true);
    }

    /**
     * Lists clients in the theater.
     */
    private void listClients() {
        for (Client client : theater.listClients()) {
            System.out.println(client.toString());
        }

    }

    /**
     * Method to be called for adding a member.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for adding the member.
     *  
     */
    private void addMemeber() {
        String name = getToken("Enter member's name");
        String address = getToken("Enter  member's address");
        String phoneNumber = getToken("Enter  member's phone number");
        String creditCardNumber = getCreditCardNumber("Enter a valid credit card number\n" +
        		"This must follow the correct number format of a real credit card company"); 
        Calendar date = getCreditCardExpirationDate("Enter credit card expiration date in this format mm/yy");        
        Member member = theater.addMember(name, address, phoneNumber, creditCardNumber, date);

        if (member != null) {
            System.out.println("Member " + name + " " + member.getID() + " was added");
        }
        
    }

    /**
     * Prompts user for a member id and
     * Calls appropriate method in Theater to remove a member
     */
    private void removeMember() {
        String memberID = getToken("Enter member ID to be removed");
        if (theater.removeMember(memberID)) {
            System.out.println("Member " + memberID + " was removed");
        } else {
            System.out.println("Member ID entered didn't match any in the system");
        }
    }

    /**
     * Prompts user for a member id and, if member is found, asks user
     * for a valid credit card number and expiration date. 
     */
    private void addCreditCard() {
        String memberID = getToken("Enter member ID to add the credit card too");
        Member member = theater.searchMember(memberID);

        if (member != null) {
            String creditCardNumber = getCreditCardNumber("Enter a valid credit card number");
            Calendar expirationDate = getCreditCardExpirationDate("Enter credit card expiration date in this format mm/yy");
            member.addCreditCard(creditCardNumber, expirationDate);
        } else {
            System.out.println("Member " + memberID + " isn't in the system");
        }
    }

    /**
     * Asks user for a credit card number and removes it from the system if one is found.
     */
    private void removeCreditCard() {    	
    	String creditCardNumber = getToken("Enter a credit card number.");
    	int attempt = theater.removeCreditCard(creditCardNumber);
    	if (attempt == Theater.SUCCESS) {
    		System.out.println("Credit card " + creditCardNumber + " was removed.");
    	}
    	else if(attempt == Theater.TOO_FEW_CARDS){
    		System.out.println("Cannot delete a card from a member with only one credit card");
    	}
    	else{
    		System.out.println("Credit card " + creditCardNumber + " isn't in the system");
    	}

    }

    /**
     * Lists members in the system
     */
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
    		Integer price = (Integer) getPrice("Please enter the cost of a ticket");
    		
    		while(startDate.after(endDate)){
    			System.out.println("Please enter a start date that is before the end date");
    			startDate = getDate("Enter a start date: mm/dd/yy");
        		endDate = getDate("Enter a end date: mm/dd/yy");
    		}
    		
    		//Show will return null if dates are used 
    		show = theater.addShow(id, showTitle, startDate, endDate, price);    		
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

    /**
     * Method to be called for saving the Theater object.
     * Uses the appropriate Theater method for saving.
     *  
     */
    private void saveData() {

        if (theater.save()) {
            System.out.println("The theater data has been saved");
        } else {
            System.out.println("Saving failed");
        }
    }

    /**
     * Method to be called for retrieving saved data.
     * Uses the appropriate Theater method for retrieval.
     *  
     */
    private void retrieve() {
    	
    	if(theater != null && (!theater.listMembers().isEmpty() || !theater.listClients().isEmpty())){
    		System.out.println("The theater is already loaded.");
    	} else { 

    		try {
    			Theater tempLibrary = Theater.retrieve();
    			if (tempLibrary != null) {
    				System.out.println(" The Theater has been successfully retrieved from the file TheaterData \n");
    				theater = tempLibrary;
    			} else {
    				System.out.println("File doesnt exist creating new Theater");
    				String name = getToken("What will be the name of your theater?");
    	        	Integer capacity = getNumber("What is the MAX capacity of your theater?");    	
    	        	theater = Theater.instance(name,capacity);
    			}
    		} catch (Exception cnfe) {
    			cnfe.printStackTrace();
    		}
    	}
    }
    
    /**
     * sell a regular ticket by calling sellAnyTicket with the proper value
     */
    private void sellRegularTicket() {
    	sellAnyTicket(Theater.REGULAR_TICKET);  	
    }
    
    /**
     * sell an advanced ticket by calling sellAnyTicket with the proper value
     */
    private void sellAdvanceTicket() {
    	sellAnyTicket(Theater.ADVANCE_TICKET);
    }
    
    /**
     * sell an advanced student ticket by calling sellAnyTicket with the proper value
     */
    private void sellStudentAdvanceTicket()	{
    	sellAnyTicket(Theater.STUDENT_ADVANCE_TICKET);
    }
    
    /**
     * takes a ticket type and processes a ticket sell.
     * @param ticketType
     */
    private void sellAnyTicket(int ticketType){
    	Calendar showDate = getDate("Enter a show date: mm/dd/yy");
    	Show show = theater.getShow(showDate);
    	String creditCardNumber;
    	
    	if (show != null){
    		String memberID = getToken("Enter member ID");
    		Member member = theater.searchMember(memberID);
    		if (member != null) {
    			do{
    				creditCardNumber = getToken("Enter a valid credit card number");
    			} while(!theater.checkCreditCardInCorrectFormat(creditCardNumber));
    			CreditCard creditCard = theater.getCreditCard(member, creditCardNumber);
    			if(creditCard != null){
    				if(ticketType == Theater.REGULAR_TICKET) {
    					theater.sellTicket(show, member, creditCard, Theater.REGULAR_TICKET, showDate);
    				} 
    				if(ticketType == Theater.ADVANCE_TICKET) {
    					theater.sellTicket(show, member, creditCard, Theater.ADVANCE_TICKET, showDate);
    				}
    				if(ticketType == Theater.STUDENT_ADVANCE_TICKET) {
    					theater.sellTicket(show, member, creditCard, Theater.STUDENT_ADVANCE_TICKET, showDate);
    				}
    			}
    		} else {
    			System.out.println("Member " + memberID + " isn't in the system");
    		}
    	} else {
    		System.out.println("There is no show scheduled for " + dateFormat.format(showDate.getTime()));
    	}
    }
    
    private void payClient() {
    	
    }
    
    private void printAllTickets() {     	
    	
    	Calendar date = getDate("To view tickets for a certain date,\n"
    			+ "Enter a date: mm/dd/yy");
    	
    	for(Ticket ticket: theater.listTickets(date)) {
    		System.out.println(ticket.toString());
    	}
    	
       	
    }
    

    /**
     * Prompts the user for a correctly formatted credit card
     * expiration date
     * @param prompt
     * @return
     */
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

    /**
     * Prompts user for a credit card number and verifies it is a valid format
     * by calling the proper method in Theater.
     * @param prompt
     * @return
     */
    private String getCreditCardNumber(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                String creditCardNumber = reader.readLine();
                String nonDigitsPattern = "[^0-9]+";
        		creditCardNumber = creditCardNumber.replaceAll(nonDigitsPattern, "");
                if (theater.checkCreditCardInCorrectFormat(creditCardNumber)) {
                    if (!theater.isCreditCardDuplicate(creditCardNumber)) {
                        return creditCardNumber;
                    }
                    else{
                    	System.out.println("That credit card is already in the system.");
                    }
                }
            } catch (IOException ioe) {
                System.out.println("bad credit card input");
            }
        }
    }
}
