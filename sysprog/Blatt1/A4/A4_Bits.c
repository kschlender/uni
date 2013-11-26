#include <stdio.h>
int main()
#define MASK1 255 /*1111 1111*/
#define MASK2 247 /*1111 0111*/
{
	unsigned char zahl=247; /*1111 0111*/
	unsigned char masked=(zahl & MASK1);
	
	printf("Prueffe Bits...\n");
	if(masked == MASK1) printf("Alle Bits gesetzt! => %d\n",masked); 
	else printf("Nicht alle Bits gesetzt! => %d\n",masked);
	
	printf("Setze Bits...\n");
	masked=(zahl | MASK1);
	printf("Bits gesetzt! => %d\n",masked);
	
	printf("Loesche Bits...\n");
	masked=(zahl & MASK2);
	printf("Bits geloescht! => %d\n",masked);
	
	return 0;
}