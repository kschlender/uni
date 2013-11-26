/*
 * Java interface of Chat server.
 * It's like the Java interface of Time server of VL03.
 */
package chatprogramm;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Nico
 */
public interface ChatServer extends Remote {

    public ChatSession createSession(String nickname, ClientHandle handle)
            throws RemoteException;
}