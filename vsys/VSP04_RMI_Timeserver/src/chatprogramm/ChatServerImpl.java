/*
 * Implementation of ChatServer like TimeServer from Lession V03.
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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.List;
import java.util.ArrayList;
import kaufhaus.ShopServerImpl;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {

    List sessions = new ArrayList();

    public ChatServerImpl() throws RemoteException {
    }

    public ChatSession createSession(String nickname, ClientHandle handle)
            throws RemoteException {
        System.out.println("create session: " + nickname);
        ChatSession s = new ChatSessionImpl(this, nickname, handle);
        sessions.add(s);
        return s;
    }

    public void postMessage(String message, ChatSessionImpl s) {
        ChatSessionImpl tmp;
        for (int i = 0; i < sessions.size(); i++) {
            tmp = (ChatSessionImpl) sessions.get(i);
            try {
                tmp.getClientHandle().receiveMessage(s.getNickname(), message);
            } catch (RemoteException ex) {
                System.out.println("unabled to contact client " + s.getNickname());
                System.out.println("removing.");
                removeSession(tmp);
                i--; // Da nun alle Clients in Liste einen Platz nach unten rutschen ...
            }
        }
    }

    public void removeSession(ChatSession session) {
        sessions.remove(session);
    }

    public static void main(String args[]) {
        try {
            // Man kann entweder eine lokale Registry erzeugen ...
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            // ... oder verwendet die Standard-RMI Registry auf dem lokalen Host 
            // unter Port 1099. In diesem Fall müssen aber Policies konfiguriert
            // werden. Siehe timer.policy fuer Hinweise.
            Registry registry = LocateRegistry.getRegistry();
            Naming.rebind("chat-server", new ChatServerImpl());
            System.out.println("ChatServer registered as 'chat-server' ...");
        } catch (Exception ex) {
            System.err.println("Exception during server Fregistration (port = " + Registry.REGISTRY_PORT + ")");
            ex.printStackTrace();
        }
    }
}