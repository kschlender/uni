#include <stdio.h>
#include <math.h>
#define STEP 10
#define MAX 360
#define MIN 0
int main()
{
	short x=MIN;
	float y=0;
	short ende=0;
	printf("WHILE-Schleife...\n");
	while(ende!=1)
	{
		y = sin(x); /*Radiant (Bogenmaß)*/
		if(x==MAX) ende=1;
		printf("%3d Grad => Sinus-Funktionswert: %6.3f\n",x,y);
		x=x+STEP;
	}
	printf("\nFOR-Schleife...\n");

	for(x=0;x < MAX;x=x+STEP)
	{
		y = sin(x); /*Radiant (Bogenmaß)*/
		printf("%3d Grad => Sinus-Funktionswert: %6.3f\n",x,y);
	}
	return 0;
}