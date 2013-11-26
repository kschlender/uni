/** Interface für die Implementierung eines ChatServers. 
 *  Änderungen gegenüber Version 1.0:
 *  1) ChatServer statt Server
 *  2) Methoden anAlleSenden, anEinenSenden ersetzt durch {@link nachrichtSenden}
 *  3) Methode anmelden(Client,String) ersetzt durch {@link anmelden(ChatClient,String,String)}
 *  4) Methode abmelden(String) ersetzt durch {@link abmelden(String,String)}
 *
 *  @version 1.1
 *  @author ProgPrak-Team
 *  @date 10.11.2004
 */

package chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote
{
    /** Wird von einem Client aufgerufen, um sich beim Chat
     *  anzumelden.
     *  @param c der anzumeldende {@link ChatClient}
     *  @param name ein Name, über den der Client identifiziert wird
     *  @param password ein Passwort, welches später zur Authentifizierung benutzt wird
     *  @return liefert true, falls Anmeldung erfolgreich war
     */
    public boolean anmelden(ChatClient c, String name, String password) throws RemoteException;

    /** Wird von einem Client aufgerufen, um sich beim Chat
     *  abzumelden.
     *  @param name der Name des abzumeldenden Clients
     *  @param password das Passwort, welches bei der Anmeldung angegeben wurde
     *  @return false falls der Name bereits vergeben ist
     */
    public void abmelden(String name, String password) throws RemoteException;

    /** Veranlasst den Server, eine Nachricht zu verschicken.
     *  @param n die {@link Nachricht}; diese beinhaltet den Typ, Sender,
     *            möglicherweise den Empfänger und einen Authetifikationsmechanismus
     *  @return false falls die Nachricht nicht zugestellt werden konnte, z.B.
     *            wenn der Empfänger unbekannt ist
     */
    public boolean nachrichtSenden(Nachricht n) throws RemoteException;

}
