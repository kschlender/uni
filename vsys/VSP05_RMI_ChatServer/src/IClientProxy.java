import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IClientProxy extends Remote
{
	public void receiveMessage(String username, String message) throws RemoteException;
}