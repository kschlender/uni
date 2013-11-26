import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IChatServer extends Remote
{
	public IChatProxy subscribeUser (String nickname, IClientProxy clientProxy) throws RemoteException;
	public boolean unsubscribeUser (IClientProxy clientProxy) throws RemoteException;
}