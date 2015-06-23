package theater;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Nathan on 6/22/2015.
 */
public class TheaterPatronIdServer implements Serializable {

    private int idCounter;
    private static TheaterPatronIdServer singletonTheaterPatronIdServer;

    private TheaterPatronIdServer() {

    }

    public static TheaterPatronIdServer theaterPatronIdServerInstance() {

        if (singletonTheaterPatronIdServer == null) {
            singletonTheaterPatronIdServer = new TheaterPatronIdServer();
        }
        return singletonTheaterPatronIdServer;
    }

    public int getNewIdCounter() {

        return idCounter++;
    }

    private void writeObject(java.io.ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(singletonTheaterPatronIdServer);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private Object readResolve() {
        return singletonTheaterPatronIdServer;
    }

    private void readObject(java.io.ObjectInputStream input) {
        try {
            if (singletonTheaterPatronIdServer != null) {

                input.readObject();
                return;
            } else {
                input.defaultReadObject();
                if (singletonTheaterPatronIdServer == null) {
                    singletonTheaterPatronIdServer = (TheaterPatronIdServer) input.readObject();
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
