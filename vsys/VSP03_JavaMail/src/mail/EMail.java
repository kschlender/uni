package mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;

public class EMail {

    final String betreff = "JavaMail Test VS_P03";
    final String username = "klausschlender@gmail.com";
    private String passwort = "";
    private String empfaenger = "";
    private String inhalt = "";

    void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    void setEmpfaenger(String empfaenger) {
        this.empfaenger = empfaenger;
    }

    void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

    void sendMail() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); //autentifizierung
        props.put("mail.smtp.starttls.enable", "true"); //verschl�sselung
        props.put("mail.smtp.host", "smtp.gmail.com");//Die SMTP Server Adresse
        props.put("mail.smtp.port", "587");//Der Port f�r den SMTP Server
        /*
         props.put("mail.smtp.host", "smtp-relay.fh-bielefeld.de"); //Die SMTP Server Adresse. Postausgangsserver "smtp-relay.fh-bielefeld.de"
         props.put("mail.smtp.port", "465"); //Der Port f�r den SMTP Server. "993" beim Posteingangsserver und auf "465" beim Postausgangsserver
         */

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication()// Wird zum Authentifizieren genutzt
            {
                return new PasswordAuthentication(username, passwort);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));//Von welcher Adresse wird hier gesetzt.
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(empfaenger));//Zu welcher Ardresse wird hier gesetzt.
            message.setSubject(betreff);//Erstellt das Subjekt der Mail, Betreffzeile:
            message.setContent(inhalt, "text/html; charset=utf-8");//Erstellt den Inhalt der E-Mail
            Transport.send(message);//Her wird die EMail versendet
            System.out.println("Die E-Mail wurde versendet!");
        } catch (Exception e) {
            System.out.println("FEHLER: Die E-Mail wurde nicht versendet!\n");
            e.printStackTrace();
        }
    }
}
