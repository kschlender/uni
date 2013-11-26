import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class ClientProxy extends UnicastRemoteObject implements IClientProxy
{
	private static final long serialVersionUID = 1L;
	ChatClient client;

    public ClientProxy(ChatClient client) throws RemoteException
    {
        this.client = client;
    }
    public ClientProxy() throws RemoteException{}
    public void receiveMessage(String nickname, String message)
    {
        client.receiveMessage(nickname, message);
    }
    public ChatClient getChatClient()
    {
    	return client;
    }
}