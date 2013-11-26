/*
 * Java interface of Chat session.
 * It's like the Java interface of Time server of VL03.
 */
package chatprogramm;

/**
 *
 * @author Nico
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatSession extends Remote {

    public void sendMessage(String message) throws RemoteException;
}