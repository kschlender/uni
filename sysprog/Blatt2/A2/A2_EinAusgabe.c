#include <stdio.h>

void reihe(int l)
{
	int i;
	char c[l];
	for(i=0;i<l;i++)
	{
		printf("*");
	}
	printf("\n");
}
void linksPyramide(int l)
{
	int i,j;
	char c[l];
	for(i=0;i<l;i++)
	{
		c[i]='*';
		
		for(j=0;j<=i;j++)
		{
			printf("%c",c[j]);
		}
		printf("\n");
	}
}

void pyramide(int l)
{
	int i,j,m=l/2;
	char c[l];
	printf("Mitte: %d\n",m);
	/*Array mit Leerzeichen ueberschreiben*/
	for(i=0;i<l;i++)
	{
		c[i]=' ';
	}
	
	for(i=0;i<=m;i++)
	{
		if(i==0)
		{
			c[m]='*';
		}
		else
		{
			c[m+i]='*';
			c[m-i]='*';
		}
		
		for(j=0;j<l;j++)
		{
			printf("%c",c[j]);
		}
		printf("\n");
	}
}

int main()
{
	unsigned int length=1, x=0;
		
	while(!x && length<75 && length>0)
	{
		x=0;
		printf("Laenge: ");
		x = scanf("%ud", &length);	
	}
	reihe(length);
	printf("\n");
	linksPyramide(length);
	printf("\n");
	pyramide(length);
	
	return 0;
}
