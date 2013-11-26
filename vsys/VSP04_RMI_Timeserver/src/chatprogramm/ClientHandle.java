/*
 * Java interface of Client handle.
 * It's like the Java interface of Time server of VL03.
 */
package chatprogramm;

/**
 *
 * @author Nico
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientHandle extends Remote {

    public void receiveMessage(String nickname, String message) throws RemoteException;
}