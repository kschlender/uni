/**
 * Wir benutzt, um verschiedene Arten von Nachrichten zu modellieren. Die Namen
 * sind selbsterkl�rend.
 *
 *  @version 1.2
 *  @author Schlender, Korkin
 *  @date 24.11.2013
 */
package chat;

public enum NachrichtTyp implements java.io.Serializable {

    Allgemein,
    Privat,
    Server
}
