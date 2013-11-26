import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IChatProxy extends Remote
{
	public void sendMessage(String message) throws RemoteException;
}