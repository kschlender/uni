/*
 * Java interface of Time server.
 */
package minden.vs;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TimeServer extends Remote {

    public long getTime() throws RemoteException;
}
