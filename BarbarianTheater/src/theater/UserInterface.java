package theater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    private void addClient() {

        String name = getToken("Enter client name");
        String address = getToken("Enter client address");
        String phoneNumber = getToken("Enter client phone number");
        Client client;
        client = theater.addClient(name, address, phoneNumber);

        if (client != null) {
            System.out.println("Client " + name + " was added");
        }
    }

    private void removeClient() {

        String clientID = getToken("Enter client ID to be removed");
        if (theater.removeClient(clientID)) {
            System.out.println("Client was removed");
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

        String clientID = getToken("Enter client ID to be removed");
        if (theater.removeMember(clientID)) {
            System.out.println("Member was removed");
        } else {
            System.out.println("Member ID entered didn't match any in the system");
        }
    }

    private void addCreditCard() {


    }

    private void removeCreditCard() {


    }

    private void listMember() {

        for (Member member : theater.listMembers()) {

            System.out.println(member.toString());
        }
    }

    private void addShow() {


    }

    private void listShows() {


    }

    private void saveData() {


    }

    private void retrieveData() {


    }

    public Calendar getCreditCardExpirationDate(String prompt) {

        do {
            try {
                System.out.println(prompt);
                String line = reader.readLine();

                if (isProperExpirationDateFormat(line)) {

                    DateFormat dateFormat = new SimpleDateFormat("MM/yy");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dateFormat.parse(line));

                    return calendar;
                }

            } catch (IOException ioe) {
                System.exit(0);
            } catch (ParseException pe) {
                System.out.println("Please enter the expiration date in this format mm/yy");
            }
        } while (true);
    }

    private String getCreditCardNumber(String prompt) {

        while (true) {

            System.out.println(prompt);

            try {
                String creditCardNumber = reader.readLine();

                if (isCreditCardInCorrectFormat(creditCardNumber)) {

                    if (!isCreditCardDuplicate(creditCardNumber)) {

                        return creditCardNumber;
                    }
                }
            } catch (IOException ioe) {
                System.out.println("bad credit card input");
            }
        }
    }

    private boolean isCreditCardInCorrectFormat(String creditCardNumber) {

        if (creditCardNumber.length() == 19) {

            for (int i = 0; i < creditCardNumber.length(); i++) {

                if (i == 4 || i == 9 || i == 14) {

                    if (!(creditCardNumber.charAt(i) == '-')) {
                        return false;
                    }
                } else {
                    if (!(Character.isDigit(creditCardNumber.charAt(i)))) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private boolean isCreditCardDuplicate(String creditCardNumber) {

        String delims = "-";
        StringTokenizer creditCardToValidate = new StringTokenizer(creditCardNumber, delims);
        //TODO try catch block upcast
        //Theater theater = (Theater) lastState;

        for (Member member : theater.listMembers()) {
            for (CreditCard customerCreditCard : member.getCreditCards()) {

                String cardNumberOnFile = customerCreditCard.getCreditCardNumber();
                StringTokenizer cardOnFile = new StringTokenizer(cardNumberOnFile, delims);
                int StringTokensTheSame = 0;

                while (creditCardToValidate.hasMoreElements()) {

                    if (creditCardToValidate.nextToken().equals(cardOnFile.nextToken())) {
                        StringTokensTheSame++;
                    }
                }
                if (StringTokensTheSame == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isProperExpirationDateFormat(String expirationDate) {

        if (expirationDate.length() == 5) {

            for (int i = 0; i < expirationDate.length(); i++) {

                if (i == 2) {

                    if (!(expirationDate.charAt(i) == '/')) {
                        return false;
                    }
                } else {
                    if (!(Character.isDigit(expirationDate.charAt(i)))) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
