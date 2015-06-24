/**
 * Barbarians: Douglas Brian Shaffer, Nathan Kangas, Johnathan Franco 
 * Code borrowed from Library example.
 *  @author Brahma Dathan and Sarnath Ramnath
 *  @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.
 * 
 * This class generates and increments an ID counter, and allows it to be saved and
 * loaded appropriately.
 */
package theater;

import java.io.IOException;
import java.io.Serializable;


@SuppressWarnings("serial")
public class TheaterPatronIdServer implements Serializable {

    private int idCounter;
    private static TheaterPatronIdServer singletonTheaterPatronIdServer;

    /**
     * private Constructor
     */
    private TheaterPatronIdServer() {

    }

    /**
     * theaterPatronIdServerInstance creates a TheaterPatronIdServer
     * unless one already exists, thus maintaining the class's
     * singleton property.
     * @return
     */
    public static TheaterPatronIdServer theaterPatronIdServerInstance() {

        if (singletonTheaterPatronIdServer == null) {
            singletonTheaterPatronIdServer = new TheaterPatronIdServer();
        }
        return singletonTheaterPatronIdServer;
    }

    /**
     * getNewIdCounter returns and then post-increments the idCounter.
     * @return
     */
    public int getNewIdCounter() {

        return idCounter++;
    }
	/**
	 * writeOjbect is used to write the the TheaterPatronIdServer to an output stream.
	 * @param output
	 */
    private void writeObject(java.io.ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(singletonTheaterPatronIdServer);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * readResolve returns the singleton TheaterPatronIdServer;
     * @return
     */
    private Object readResolve() {
        return singletonTheaterPatronIdServer;
    }

    /**
     * readObject is used when loading the TheaterPatronIdServer. It takes an 
     * ObjectInputStream and ensures the singleton property is upheld if the stream 
     * has a TheaterPatronIdServer.
     * @param input
     */
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
