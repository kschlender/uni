import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class ChatProxy extends UnicastRemoteObject implements IChatProxy
{
	private static final long serialVersionUID = 1L;
	ChatServer chatServer;
    String nickname;
    IClientProxy clientProxy;
    
    public ChatProxy() throws RemoteException{}
    public ChatProxy(ChatServer server, String nickname, IClientProxy clientProxy) throws RemoteException
    {
        this.chatServer = server;
        this.nickname = nickname;
        this.clientProxy = clientProxy;
    }
    public void sendMessage(String message){chatServer.postMessage(message, this);}
    public IClientProxy getClientProxy(){return clientProxy;}
    public String getNickname(){return nickname;}
}