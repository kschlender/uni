#include <stdio.h>

typedef struct angestellter
{
	unsigned int personalNummer;
	char name[20];
	char vorname[20];
	float gehalt;
	enum Bereich {MANAGEMENT, ENTWICKLUNG, PRODUKTION, MARKETING, VERTRIEB} abteilung; /*% Werte zuweisen*/
} Angestellter;

Angestellter feld[10];
float erhoeung[5]={1.3,1.1,1.01,1.02,1.01}; /*erhöhungen definieren*/
int feldLength=sizeof(feld)/sizeof(feld[0]);
int i;

float durchschnitsGelalt()
{
	float gesammt;
	i=0;
	while(feld[i].gehalt!=0)
	{
		gesammt+=feld[i].gehalt;
		i+=1;
	}
	return (gesammt/i);
}

void gehaltsErhoeung()
{
	i=0;
	while(feld[i].gehalt!=0)
	{
		feld[i].gehalt=feld[i].gehalt*erhoeung[feld[i].abteilung];
		i+=1;
	}
}

void ausgabe(int index)
{
	printf("|%5.5d|%-20.20s|%-20.20s|%5.2f|%3d|\n",
	feld[index].personalNummer,feld[index].name,feld[index].vorname,feld[index].gehalt,feld[index].abteilung);
}
void gesammtAusgabe()
{
	i=0;
	printf("|pNr. |      Nachname      |      Vorname       |Gehalt |Abt|\n");
	printf("=============================================================\n");
	while(feld[i].gehalt!=0)
	{
		ausgabe(i);
		i+=1;
	}
}

int main()
{
	/*Dateneingabe*/
	feld[0] = (Angestellter){12345,"Test","Erster",5234.56,MANAGEMENT};
	feld[1] = (Angestellter){12346,"Tester","Zweiter",4234.56,MARKETING};
	feld[2] = (Angestellter){12347,"Testerin","Dritte",3234.56,VERTRIEB};
	feld[3] = (Angestellter){12348,"Testomat","Vier",2234.56,PRODUKTION};
	
	/*Datenausgabe*/
	gesammtAusgabe();
	printf("\nDurchschnitsgehalt: %5.2f\n\n\n",durchschnitsGelalt());
	
	/*Gehaltserhoeung*/
	gehaltsErhoeung();
	
	/*Datenausgabe*/
	gesammtAusgabe();
	printf("\nDurchschnitsgehalt: %5.2f\n\n\n",durchschnitsGelalt());

	return 0;
}
