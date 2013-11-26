/**
 * Interface f�r die Implementierung eines ChatServers. �nderungen gegen�ber
 * Version 1.0: 1) ChatServer statt Server 2) Methoden anAlleSenden,
 * anEinenSenden ersetzt durch {@link nachrichtSenden} 3) Methode
 * anmelden(Client,String) ersetzt durch
 * {@link anmelden(ChatClient,String,String)} 4) Methode abmelden(String)
 * ersetzt durch {@link abmelden(String,String)}
 *
 * @version 1.2
 * @author Schlender, Korkin
 * @date 24.11.2013
 */
package chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote {

    /**
     * Wird von einem Client aufgerufen, um sich beim Chat anzumelden.
     *
     * @param c der anzumeldende {@link ChatClient}
     * @param name ein Name, �ber den der Client identifiziert wird
     * @param password ein Passwort, welches sp�ter zur Authentifizierung
     * benutzt wird
     * @return liefert true, falls Anmeldung erfolgreich war
     */
    public boolean anmelden(ChatClient c, String name, String password) throws RemoteException;

    /**
     * Wird von einem Client aufgerufen, um sich beim Chat abzumelden.
     *
     * @param name der Name des abzumeldenden Clients
     * @param password das Passwort, welches bei der Anmeldung angegeben wurde
     * @return false falls der Name bereits vergeben ist
     */
    public void abmelden(String name, String password) throws RemoteException;

    /**
     * Veranlasst den Server, eine Nachricht zu verschicken.
     *
     * @param n die {@link Nachricht}; diese beinhaltet den Typ, Sender,
     * m�glicherweise den Empf�nger und einen Authetifikationsmechanismus
     * @return false falls die Nachricht nicht zugestellt werden konnte, z.B.
     * wenn der Empf�nger unbekannt ist
     */
    public boolean nachrichtSenden(Nachricht n) throws RemoteException;
}
