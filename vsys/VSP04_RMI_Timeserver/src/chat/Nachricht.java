/**
 * Modelliert Nachrichten, welche zwischen {@link ChatClient} und
 * {@link ChatServer} verschickt werden.
 *
 *  @version 1.2
 *  @author Schlender, Korkin
 *  @date 24.11.2013
 */
package chat;

public interface Nachricht {

    /**
     * Liefert den Typ der Nachricht.
     *  {
     *
     * @see NachrichtTyp}
     */
    public NachrichtTyp typ();

    /**
     * Liefert den Namen des Senders dieser Nachricht.
     */
    public String sender();

    /**
     * Liefert den Namen des Empf�ngers dieser Nachricht. Das Verhalten ist
     * unspezifiziert, falls der {@link NachrichtTyp} nicht
     * {@link NachrichtTyp.Privat} ist.
     */
    public String empfaenger();

    /**
     * Liefert den Inhalt der Nachricht.
     */
    public String nachricht();

    /**
     * Diese Methode soll vom {@link ChatServer} benutzt werden, um zu
     * �berpr�fen, ob die Nachricht wirklich von dem {@link ChatClient} stammt,
     * dessen Name als Sender in dieser Nachricht angegeben ist. Dazu muss der
     * {@link ChatClient}, der diese Nachricht verschickt, sie �ber diese
     * Methode mit seinem Passwort authentifizeren.
     *
     * @param password das vermutliche Passwort
     * @return true nur bei dem ersten Aufruf der Methode und bei
     * �bereinstimmung zwischen dem Parameter und dem richtigen Passwort
     */
    public boolean istPasswort(String password);
}
