/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaufhaus;

/**
 *
 * @author Nico
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ShopServerImpl extends UnicastRemoteObject implements ShopServer {

    public ShopServerImpl() throws RemoteException {
    }

    public Cart createCart() throws RemoteException {
        System.out.println("create cart");
        return new CartImpl();
    }

    public static void main(String args[]) {
        try {
            // Man kann entweder eine lokale Registry erzeugen ...
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            // ... oder verwendet die Standard-RMI Registry auf dem lokalen Host 
            // unter Port 1099. In diesem Fall müssen aber Policies konfiguriert
            // werden. Siehe timer.policy fuer Hinweise.
            Registry registry = LocateRegistry.getRegistry();
            Naming.rebind("shop-server", new ShopServerImpl());
            System.out.println("ShopServerImpl registered as 'shop-server' ...");
        } catch (Exception ex) {
            System.err.println("Exception during server Fregistration (port = " + Registry.REGISTRY_PORT + ")");
            ex.printStackTrace();
        }
    }
}