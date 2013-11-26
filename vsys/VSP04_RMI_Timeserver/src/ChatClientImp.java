package chat;

import java.rmi.*;
import java.rmi.server.*;
import java.net.MalformedURLException;
import java.util.Vector;

public class ChatClientImp extends UnicastRemoteObject implements ChatClient // extends java.rmi.UnicastRemoteObject
{

    private ConsoleReader console;
    private ChatServer server;
    private String name,password;
    private Vector<Teilnehmer> teilnehmer;

    private static String host="localhost",port="1099";

    public void neuerTeilnehmer(String s) throws RemoteException
    {
    }

    public void teilnehmerAbgemeldet(String s) throws RemoteException
    {
    }

    public void teilnehmerInaktiv(String s) throws RemoteException
    {
    }

    public void teilnehmerAktiv(String s) throws RemoteException
    {
    }

    public void lebtNoch() throws java.rmi.RemoteException
    {
    }

    public void nachricht(Nachricht n) throws java.rmi.RemoteException
    {
	NachrichtTyp t = n.typ();
	//	System.out.println("Tatütata: " + n.empfaenger() + " " + n.nachricht() + " " + n.typ.toString());
	//	System.out.println("Tatütata: " + n.empfaenger() + " " + n.nachricht());
	
	if (t.equals(NachrichtTyp.Allgemein)) {
	    System.out.println(n.sender() + ": " + n.nachricht());
	} else if (t.equals(NachrichtTyp.Privat)) {
	    System.out.println("*" + n.sender() + "*: " + n.nachricht());
	} else if (t.equals(NachrichtTyp.Server)) {
	    System.out.println("#" + n.sender() + "#: " + n.nachricht());
	} else {
	    System.out.println("Unidentifizierte Nachricht.");
	}
    }

    public ChatClientImp() throws java.rmi.RemoteException
    {
	/*
      	try {
	    UnicastRemoteObject.exportObject(this);
	} catch (RemoteException e) {
	    System.err.println("Client can nicht geunicastremoteobjected werden.");
	    }*/

	try {
	    server = (ChatServer) java.rmi.Naming.lookup("//"+host+":"+port+"/ChatServer");
	} catch (RemoteException e) {
	    System.err.println("Server nicht gefunden.");
	    System.exit(1);
	} catch (NotBoundException e) {
	    System.err.println("Chatserver nicht gefunden.");
	    System.exit(1);
	} catch (MalformedURLException e) {
	    System.err.println("Falsche URL.");
	    System.exit(1);
	    /*	} catch (AccessException e) {
	    System.err.println("Access denied.");
	    System.exit(1);*/
	}

	console = new ConsoleReader(System.in);

	try {
	    do {
		System.out.print("Name? ");
		name=console.readLine();
		System.out.print("Passwort? ");
		password=console.readLine();
	    } while (!server.anmelden(this,name,password)) ;
	} catch (java.rmi.RemoteException e) {
	    System.err.println("Server nicht erreichbar.");
	    System.exit(1);
	}

	chatSchleife();
    }

    private void chatSchleife() {
	String s="";
	while (!s.equals(":quit"))
	    {
		System.out.print("Eingabe: ");
		s=console.readLine();
		try {
		    server.nachrichtSenden(new NachrichtImp(name, "", s, NachrichtTyp.Allgemein, password));
		} catch (RemoteException e) {
		    System.err.println("Nachricht konnte nicht verschickt werden.");
		}
	    }

	try {
	    server.abmelden(name,password);
	} catch (RemoteException e) {
	    System.err.println("Abmeldung fehlgeschlagen.");
	    System.exit(1);
	}
	System.exit(0);
    }


    public static void main(String[] args) throws java.rmi.RemoteException
    {
	if (args.length>0 && args[0]!=null) {
	    host = args[0];
	}

	if (args.length>1 && args[1]!=null) {
	    port = args[1];
	}

	System.out.println(host + " " + port);

	new ChatClientImp();
    }
}
