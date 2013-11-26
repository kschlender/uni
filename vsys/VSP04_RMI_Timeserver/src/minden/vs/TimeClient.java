/*
 * Client for TimeServer.
 */
package minden.vs;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.registry.LocateRegistry;
import java.util.*;

public class TimeClient {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry();
            TimeServer timer = (TimeServer) registry.lookup("TimeServer");
            long tm = timer.getTime(); 
            System.out.println("TimeServer: " + tm + " (" + new Date (tm) + ")");
        } catch (AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
