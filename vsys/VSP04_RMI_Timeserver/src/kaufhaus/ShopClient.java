/*
 * Implementierung der Client-Anwendung zum testen getestet werden...
 * Nacheinander werden in diesem Beispiel die verschiedenen Methoden auf dem Server aufgerufen. 
 * Zunächst wird über den Namens-Dienst das zentrale ShopServer-Exemplar ermittelt. 
 * Von diesem Exemplar wird anschließend ein neuer Warenkorb abgerufen, dem drei Produkte hinzugefügt werden. 
 * Danach wird der Inhalt des Warenkorbs aufgelistet und schließlich durch Aufruf von buy() gekauft. 
 * Wenn man die Anwendung ausführt, kann man feststellen, dass die Ausgaben in CartImpl alle im Server-Prozess 
 * ausgegeben werden und der Client somit lediglich einen Verweis auf den im Server gespeicherten Warenkorb bekommt. 
 */
package kaufhaus;

/**
 *
 * @author Nico
 */
import java.rmi.Naming;

public class ShopClient {

    public static void main(String args[]) {
        try {
            ShopServer server = (ShopServer) Naming.lookup("shop-server");
            Cart cart = server.createCart();
            cart.addProduct("Coffee");
            cart.addProduct("Tea");
            cart.addProduct("Java");

            System.out.println("listing contents ...");
            String[] contents = cart.listContents();
            for (int i = 0; i < contents.length; i++) {
                System.out.println(" - " + contents[i]);
            }

            cart.buy("rsinger");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}