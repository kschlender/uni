package chat;

import java.rmi.*;
import java.rmi.server.*;
import java.util.Vector;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.net.MalformedURLException;

public class ChatServerImp extends UnicastRemoteObject implements ChatServer
{
    public static void main(String[] args) {
        java.util.logging.Logger logger =
                java.util.logging.Logger.getLogger("sun.rmi.transport.tcp");
        logger.setLevel(java.util.logging.Level.FINEST);
        try {
            java.util.logging.Handler fh =
                    new java.util.logging.FileHandler("chatLog.xml");
            java.util.logging.Logger.getLogger("").addHandler(fh);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(logger);

        int port = 1099;

        if (args.length == 1) {
            if (args[0].compareTo("-?") == 0 || args[0].compareTo("/?") == 0) {
                System.out.println("Argumente: [port]");
                System.exit(0);
            }

            port = Integer.parseInt(args[0]);
        }
        System.out.println("Starte den ChatServer auf Port " + port + "...");

        try {
            // Man kann entweder eine lokale Registry erzeugen ...
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            // ... oder verwendet die Standard-RMI Registry auf dem lokalen Host 
            // unter Port 1099. In diesem Fall müssen aber Policies konfiguriert
            // werden. Siehe timer.policy fuer Hinweise.
            Registry registry = LocateRegistry.getRegistry();
            ChatServerImp server = new ChatServerImp();
            Naming.rebind("//localhost:" + port+ "/ChatServer", server);
            System.out.println("ChatServer registered as 'chat-server' ...");
            
        } catch (Exception ex) {
            System.out.println("Serverfehler: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
    
    private Vector<Buffer> buffers;

    public ChatServerImp() throws RemoteException
    {
	super();

	buffers = new Vector<Buffer>();
    }

    public boolean anmelden(ChatClient c, String name, String password) throws RemoteException
    {
	int i=0;
	boolean gefunden=false;
	Buffer b;

	System.err.println("Anmeldung von " + name + " eingegangen.");

	while (i<buffers.size() && (gefunden==false)) {
	    b = buffers.get(i);
	    if (b.getTeilnehmer().name().equals(name) && b.getTeilnehmer().passwort().equals(password)) {
		if (!b.getTeilnehmer().istAktiv()) {
		    b.getTeilnehmer().reaktivieren();
		    b.getTeilnehmer().setzeClient(c);
		    anAlleSenden("ChatServer", b.getTeilnehmer().name() + " ist wieder da.");
		    b.senden();
		} else {
		    b.senden();
		}
		gefunden=true;
	    }
	    i++;
	}

	if (!gefunden) {
	    buffers.add(new Buffer(c,name,password));
	    anAlleSenden("ChatServer", name + " betritt den Chat");
	}

	System.err.println(name + " hat sich angemeldet.");
	return true;
    }

    public void abmelden(String name, String password) throws RemoteException
    {
	int i=0;
	Buffer b;

	while (i<buffers.size()) {
	    b = buffers.get(i);
	    if (b.getTeilnehmer().name().equals(name) && b.getTeilnehmer().passwort().equals(password)) {
		buffers.remove(i);
		anAlleSenden("ChatServer", name + " verl�sst den Chat.");
		break;
	    }
	    i++;
	}

	System.err.println(name + " hat sich abgemeldet.");
    }

    public boolean nachrichtSenden(Nachricht n) throws RemoteException
    {
	int i=0;
        Buffer b;
	String name,p,empfaenger;
	boolean gefunden = false;

	while (i<buffers.size()) {
	    b = buffers.get(i);
	    name = b.getTeilnehmer().name();
	    if (n.sender().equals(name)) {
		gefunden = true;
		break;
	    }
	    i++;
	}
	b = buffers.get(i);

	if (!gefunden) {
	    System.err.println("Sender " + n.sender() + " nicht gefunden.");
	    return false;
	}

	if (!n.istPasswort(b.getTeilnehmer().passwort())) {
	    System.err.println("Passw�rter stimmen nicht �berein.");
	    return false;
	}

	NachrichtTyp t = n.typ();
	gefunden = false;

	if (t==NachrichtTyp.Allgemein || t==NachrichtTyp.Server) {
	    for (Buffer bf : buffers) {
		bf.add(n);
		System.err.println("Nachricht von " + n.sender() + " eingetragen bei " + bf.getTeilnehmer().name());
		bf.senden();
	    }
	} else if (t==NachrichtTyp.Privat) {
	    for (Buffer bf : buffers) {
		if (bf.getTeilnehmer().name().equals(n.empfaenger())) {
		    bf.add(n);
		    System.err.println("Nachricht von " + n.sender() + " eingetragen bei " + bf.getTeilnehmer().name());
		    bf.senden();
		    gefunden = true;
		    break;
		}
	    }
	    if (!gefunden) {
		return false;
	    }
	} else {
	    System.err.println("Unbekannter Nachrichttyp.");
	    return false;
	}

	return true;
    }

    public void anAlleSenden(String sender, String nachricht) throws java.rmi.RemoteException
    {
	/*	int i=0;
	Buffer b;
	String empfaenger;

	while (i<buffers.size()) {
	    b=buffers.get(i);
	    empfaenger=b.getTeilnehmer().name();
	    b.add(new NachrichtImp(sender,empfaenger,nachricht,NachrichtTyp.Allgemein));
	    System.err.println("Nachricht von " + sender + " eingetragen bei " + empfaenger);
	    b.senden();
	    i++;
	    }*/
    }

    public boolean anEinenSenden(String sender, String empfaenger, String nachricht) throws java.rmi.RemoteException
    {
	/*	int i=0;
	Buffer b;
	boolean gefunden=false;

	while (i<buffers.size()) {
	    b=buffers.get(i);
	    if (b.getTeilnehmer().name().equals(empfaenger) || b.getTeilnehmer().name().equals(sender)) {
		b.add(new NachrichtImp(sender,empfaenger,nachricht,NachrichtTyp.Privat));
		System.err.println("Private Nachricht von " + sender + " eingetragen bei " + empfaenger);
		b.senden();
		gefunden=true;
	    }
	    i++;
	    }*/
	return false;
    }


}


class Buffer extends Vector<Nachricht>
{
    private Teilnehmer teilnehmer;

    public Teilnehmer getTeilnehmer() {
	return teilnehmer;
    }

    public Buffer(ChatClient client,String name,String password) {
	super();

	this.teilnehmer = new Teilnehmer(client, name, password);
    }

    public void senden() {
	while (!this.isEmpty() && this.getTeilnehmer().istAktiv()) {
	    try {
		teilnehmer.client().nachricht(this.get(0));
		this.remove(0);
	    } catch (RemoteException e) {
		System.err.println(teilnehmer.name() + " ist im Moment nicht erreichbar.");
		teilnehmer.deaktivieren();
		break;
	    }
	}
    }
}
