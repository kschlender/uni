import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.List;
import java.util.ArrayList;

public class ChatServer extends UnicastRemoteObject implements IChatServer
{
	private static final long serialVersionUID = 1L;
	List<IChatProxy> chatProxys = new ArrayList<IChatProxy>();

    public ChatServer() throws RemoteException{}
    
    public static void main(String args[]) {
        try {
            // Man kann entweder eine lokale Registry erzeugen ...
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            // ... oder verwendet die Standard-RMI Registry auf dem lokalen Host 
            // unter Port 1099. In diesem Fall muessen aber Policies konfiguriert
            // werden. Siehe timer.policy fuer Hinweise.
            LocateRegistry.getRegistry();
            Naming.rebind("chat-server", new ChatServer());
            System.out.println("ChatServer registriert als 'chat-server' ...");
        }
        catch (Exception ex)
        {
            System.err.println("Exception during server Registration (port = " + Registry.REGISTRY_PORT + ")");
            ex.printStackTrace();
            return;
        }
    }
    public void postMessage(String message, ChatProxy s)
    {
        ChatProxy tmp;
        for (int i = 0; i < chatProxys.size(); i++)
        {
            tmp = (ChatProxy) chatProxys.get(i);
            try{tmp.getClientProxy().receiveMessage(s.getNickname(), message);}
            catch (RemoteException e){}// Fals nur eine Person im Chat ist und mit sich selbst schreibt.
        }
    }
	@Override
	public IChatProxy subscribeUser(String nickname, IClientProxy clientProxy) throws RemoteException
	{
		System.out.println("Nutzer \""+nickname+"\" Registrieren...");
        IChatProxy s = new ChatProxy(this, nickname, clientProxy);
        chatProxys.add(s);
        return s;
	}
	@Override
	public boolean unsubscribeUser(IClientProxy clientProxy) throws RemoteException
	{
		ChatProxy tmp;
        for (int i = 0; i < chatProxys.size(); i++)
        {
            tmp = (ChatProxy) chatProxys.get(i);
            if(tmp.getClientProxy().equals(clientProxy))
            {
            	System.out.println("Nutzer \""+tmp.getNickname()+"\" Abmelden...");
            }
        }
		return chatProxys.remove(clientProxy);
	}
}