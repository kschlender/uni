package chat;

public class Teilnehmer {

    private ChatClient client;
    private String name,password;
    private boolean active;
    
    public Teilnehmer(ChatClient c, String n, String p) {

	this.client = c;
	this.name = n;
	this.password = p;
	this.active = true;
    }

    public String name() {
	return name;
    }

    public boolean istAktiv() {
	return active;
    }

    public void reaktivieren() {
	this.active=true;
    }

    public void deaktivieren() {
	this.active=false;
    }

    public ChatClient client() {
	return client;
    }

    public void setzeClient(ChatClient c) {
	this.client=c;
    }

    public boolean equals(Teilnehmer t)
    {
	return this.name.equals(t.name());
    }
    
    public String passwort()
    {
	return password;
    }
}
