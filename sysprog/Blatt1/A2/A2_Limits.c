#include <stdio.h>
int main()
{
	unsigned int limit=0;
	unsigned int old_limit=0;
	int erreicht=0;
	printf("Berechne Limits...\n");
	printf("Max nach Abzug von 1: %u\n",limit-1);
	while(erreicht!=1)
	{
		old_limit=limit;
		limit=limit+1;
		if(limit < old_limit) erreicht=1;
	}
	
	printf("Max: %u\n",old_limit);
	printf("Min: %u\n",limit);
	
	
	return 0;
}