/** Wir benutzt, um verschiedene Arten von Nachrichten zu modellieren. 
 *  Die Namen sind selbsterklärend.
 *  @version 1.0
 *  @author ProgPrak-Team
 */

package chat;

public enum NachrichtTyp implements java.io.Serializable
{
    Allgemein, 
    Privat, 
    Server
}
