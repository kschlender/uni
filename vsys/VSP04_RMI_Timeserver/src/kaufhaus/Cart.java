/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaufhaus;

/**
 *
 * @author Nico
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cart extends Remote {

    public void addProduct(String name) throws RemoteException;

    public void removeProduct(String name) throws RemoteException;

    public String[] listContents() throws RemoteException;

    public void buy(String custID) throws RemoteException;
}