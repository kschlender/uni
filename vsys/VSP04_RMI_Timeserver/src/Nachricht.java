/** Modelliert Nachrichten, welche zwischen {@link ChatClient} und
 *  {@link ChatServer} verschickt werden.
 *
 *  @version 1.0
 *  @author ProgPrak-Team
 */

package chat;

public interface Nachricht
{
    /** Liefert den Typ der Nachricht.
     *  {@see NachrichtTyp}
     */
    public NachrichtTyp typ();

    /** Liefert den Namen des Senders dieser Nachricht. */
    public String sender();

    /** Liefert den Namen des Empfängers dieser Nachricht.
     *  Das Verhalten ist unspezifiziert, falls der {@link NachrichtTyp} 
     *  nicht {@link NachrichtTyp.Privat} ist.
     */
    public String empfaenger();
    
    /** Liefert den Inhalt der Nachricht. */
    public String nachricht();

    /** Diese Methode soll vom {@link ChatServer} benutzt werden, um
     *  zu überprüfen, ob die Nachricht wirklich von dem {@link ChatClient}
     *  stammt, dessen Name als Sender in dieser Nachricht angegeben ist.
     *  Dazu muss der {@link ChatClient}, der diese Nachricht verschickt, 
     *  sie über diese Methode mit seinem Passwort authentifizeren. 
     *
     *  @param password das vermutliche Passwort
     *  @return true nur bei dem ersten Aufruf der Methode und bei
     *               Übereinstimmung zwischen dem Parameter und dem 
     *               richtigen Passwort
     */
    public boolean istPasswort(String password);
}
