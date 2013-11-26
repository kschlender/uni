/** Interface fur die Implementierung eines ChatClients. 
 *  Änderungen gegen�ber Version 1.0:
 *  1) ChatClient statt Client
 *  2) Methode {@link #nachricht(Nachricht)}
 *  3) Package chat
 *
 *  @version 1.2
 *  @author Schlender, Korkin
 *  @date 24.11.2013
 */

package chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote
{
    /** Dient nur dazu festzustellen, ob der Client noch
     *  remote erreichbar ist.
     */
    public void lebtNoch() throws RemoteException;

    /** Wird vom {@link ChatServer} aufgerufen, um bei diesem 
     *  Client eine Nachricht, die an alle Teilnehmer
     *  ging, anzuzeigen.
     *  @param n die anzuzeigende {@link Nachricht}, enth�lt
     *           Informationen zu Sender und Typ
     */
    public void nachricht(Nachricht n) throws RemoteException;

    /** Benachrichtigt diesen Teilnehmer �ber die Anmeldung eines
     *  neuen Teilnehmers.
     *  @param s der Name des neuen Teilnehmers
     */
    public void neuerTeilnehmer(String s) throws RemoteException;

    /** Benachrichtigt den Teilnehmer �ber die Abmeldung eines
     *  Teilnehmers.
     *  @param s der Name des abgemeldeten Teilnehmers
     */
    public void teilnehmerAbgemeldet(String s) throws RemoteException;

    /** Benachrichtigt den Teilnehmer dar�ber, dass ein anderer
     *  Teilnehmers vor�bergehend nicht erreichbar ist.
     *  @param s der Name des nicht erreichbaren Teilnehmers
     */
    public void teilnehmerInaktiv(String s) throws RemoteException;

    /** Benachrichtigt den Teilnehmer dar�ber, dass ein anderer
     *  Teilnehmer wieder erreichbar ist.
     *  @param s der Name des wieder erreichbaren Teilnehmers
     */
    public void teilnehmerAktiv(String s) throws RemoteException;
}
