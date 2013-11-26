package mail;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import Prog1Tools.IOTools;

public class RunMail {

    public static void aufgabe2AundC() {
        TextFile text = new TextFile();
        EMail mail = new EMail();
        mail.setPasswort(text.openPasswort());
        mail.setEmpfaenger(text.openEmpfaenger());
        mail.setInhalt(text.openInhalt());
        mail.sendMail();
    }

    public static void aufgabe2B() {
        Datum datum;
        int last_send_Month = -1;
        while (true) {
            datum = new Datum();
            if (last_send_Month == datum.getTodayMonth() + 1) {
            } else if (datum.bezahlZeit()) {
                TextFile text = new TextFile();
                EMail mail = new EMail();
                mail.setEmpfaenger(text.openEmpfaenger());
                mail.setInhalt("Bezahlen nicht vergessen!!!");
                System.out.println("SEND! am 1." + (datum.getTodayMonth() + 1));
                //mail.sendMail();
                last_send_Month = datum.getTodayMonth() + 1;
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welche Funktion möchten Sie durchführen?");
        int input = IOTools.readInteger("Bitte gebe die Zahlen={0,1} für die jeweilige Aufgabe 2AundC{0} /oder/ 2B{1} ein => (0/oder/1)");
        if (input==0) {
        RunMail.aufgabe2AundC();
        }else{
        RunMail.aufgabe2B();
        }
        
    }
}
