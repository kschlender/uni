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

public interface ShopServer extends Remote {

    public Cart createCart() throws RemoteException;
}