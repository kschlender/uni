#include <stdio.h>
#include <stdlib.h>

void func1(int *x);
void func2(int **x);

int main(void)
{
	int *x = malloc(sizeof(int));
	*x = 12;

	printf("pointee in main: %d\n", *x);
	printf("pointer in main: %lu\n", (unsigned long) x);
	printf("addresse des pointers in main: %lu\n\n", (unsigned long) &x);

	/* function calls */
	func1(x);
	func2(&x);

	free(x);
	return 0;
}

void func1(int *x)
{
	printf("pointee in func1: %d\n", *x);
	printf("pointer in func1: %lu\n", (unsigned long) x);
	printf("addresse des pointers in func1: %lu\n\n", (unsigned long) &x);
}

void func2(int **x)
{
	printf("pointee->pointee in func2: %d\n", **x);
	printf("pointee in func2: %lu\n", (unsigned long) *x);
	printf("pointer in func2: %lu\n", (unsigned long) x);
	printf("addresse des pointers in func2: %lu\n", (unsigned long) &x);
}

/*
 * mögliche Ausgabe:
 *
 * A pointee in main: 12
 * B pointer in main: 31027216
 * C addresse des pointers in main: 140735752097000
 *
 * A pointee in func1: 12
 * B pointer in func1: 31027216
 * X addresse des pointers in func1: 140735752096968
 *
 * A pointee->pointee in func2: 12
 * B pointee in func2: 31027216
 * C pointer in func2: 140735752097000
 * X addresse des pointers in func2: 140735752096968
 *
 *
 * X ist nur identisch, weil die stack-Variablen aus func1 geleert sind
 * und die einzige lokale Variable in func2 der pointer ist.
 * Wir könnten hier je nach Code völlig unterschiedliche Addressen
 * bekommen.
 */
