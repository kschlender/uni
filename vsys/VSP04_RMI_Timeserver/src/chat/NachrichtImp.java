package chat;

import java.rmi.*;
import java.rmi.server.*;
import java.io.Serializable;

//public class NachrichtImp extends UnicastRemoteObject implements Nachricht, Serializable
public class NachrichtImp implements Nachricht, Serializable {

    private NachrichtTyp typ;
    private String nachricht, sender, empfaenger;
    private String password;
    private boolean gelesen;

    public NachrichtImp(String s, String e, String n, NachrichtTyp t, String p) {
        this.nachricht = n;
        this.sender = s;
        if (t == NachrichtTyp.Privat) {
            this.empfaenger = e;
        } else {
            this.empfaenger = "";
        }
        this.typ = t;
        this.password = p;
        this.gelesen = false;
    }

    public String nachricht() {
        return this.nachricht;
    }

    public String sender() {
        return this.sender;
    }

    public String empfaenger() {
        return this.empfaenger;
    }

    public NachrichtTyp typ() {
        return this.typ;
    }

    public boolean istPasswort(String p) {
        boolean gleich;

        if (gelesen) {
            return false;
        } else {
            gelesen = true;
            gleich = password.equals(p);
            password = "";
            return gleich;
        }
    }
}
