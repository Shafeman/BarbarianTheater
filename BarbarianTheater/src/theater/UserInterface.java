package theater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        String phone = getToken("Enter client phone number");
        Client client;
        client = theater.addClient(name, address, phone);

        if (client != null) {
            System.out.println("Client " + name + " was added");
        }
        //TODO check client add for success
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


    }

    private void removeMember() {


    }

    private void addCreditCard() {


    }

    private void removeCreditCard() {


    }

    private void listMember() {


    }

    private void addShow() {


    }

    private void listShows() {


    }

    private void saveData() {


    }

    private void retrieveData() {


    }
}
