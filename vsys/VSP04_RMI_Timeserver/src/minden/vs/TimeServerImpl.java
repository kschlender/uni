/*
 * Implementation of TimeServer.
 */
package minden.vs;

import java.rmi.RemoteException;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;

public class TimeServerImpl extends UnicastRemoteObject implements TimeServer {
    TimeServerImpl() throws RemoteException {
        // Default ctor overloaded to catch RemoteException
        System.out.println ("TimeServerImpl created...");
    }

    public long getTime() throws RemoteException {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        try {
            // Man kann entweder eine lokale Registry erzeugen ...
            LocateRegistry.createRegistry (Registry.REGISTRY_PORT);
            // ... oder verwendet die Standard-RMI Registry auf dem lokalen Host 
            // unter Port 1099. In diesem Fall müssen aber Policies konfiguriert
            // werden. Siehe timer.policy fuer Hinweise.
            Registry registry = LocateRegistry.getRegistry();
            Naming.rebind ("TimeServer", new TimeServerImpl());
            System.out.println ("TimeServerImpl registered as 'TimeServer' ...");
        } catch (Exception ex) {
            System.err.println ("Exception during server Fregistration (port = " + Registry.REGISTRY_PORT + ")");
            ex.printStackTrace();
        }
    }
}
