/*
 * Implementation of Client handle.
 * Its like the Implementation of TimeServer in V03
 */
package chatprogramm;

/**
 *
 * @author Nico
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;

public class ClientHandleImpl extends UnicastRemoteObject implements ClientHandle {

    ChatClient client;

    public ClientHandleImpl(ChatClient client) throws RemoteException {
        this.client = client;
    }

    public ClientHandleImpl() throws RemoteException {
    }

    public void receiveMessage(String nickname, String message) {
        client.receiveMessage(nickname, message);
    }
}