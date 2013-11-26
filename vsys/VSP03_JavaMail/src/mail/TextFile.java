package mail;

import java.io.*;

public class TextFile
{
	String openInhalt()
	{
		try
		{
			BufferedReader br=new BufferedReader(new FileReader(new File("inhalt.txt")));
			try
			{
				boolean hasLine=false;
				String line=null;
				String text="";
				do
				{
					line=br.readLine();
					if(line==null)
					{
						hasLine=false;
					}
					else
					{
						hasLine=true;
						text+=(line+"\n");
					} 
					
				}
				while(hasLine);
				return text;
			}
			catch (IOException e) {System.out.println("FEHLER: Konnte \"inhalt.txt\" nicht Lesen!"); return null;}
			
		}
		catch (FileNotFoundException e) {System.out.println("FEHLER: Konnte \"inhalt.txt\" nicht Finden!");return null;}
	}
	String openEmpfaenger()
	{
		try
		{
			BufferedReader br=new BufferedReader(new FileReader(new File("empfaenger.txt")));
			try
			{
				String line=null;
				line=br.readLine();
				if(line!=null) return line;
				else return null;
			}
			catch (IOException e) {System.out.println("FEHLER: Konnte \"empfaenger.txt\" nicht Lesen!"); return null;}
		}
		catch (FileNotFoundException e) {System.out.println("FEHLER: Konnte \"empfaenger.txt\" nicht Finden!");return null;}
	}
	String openPasswort()
	{
		try
		{
			BufferedReader br=new BufferedReader(new FileReader(new File("p.txt")));
			try
			{
				String line=null;
				line=br.readLine();
				if(line!=null) return line;
				else return null;
			}
			catch (IOException e) {System.out.println("FEHLER: Konnte \"p.txt\" nicht Lesen!"); return null;}
		}
		catch (FileNotFoundException e) {System.out.println("FEHLER: Konnte \"p.txt\" nicht Finden!");return null;}
	}
}
