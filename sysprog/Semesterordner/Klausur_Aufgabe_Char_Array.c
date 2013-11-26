/*
 ============================================================================
 Name        : Klausur_Aufgabe_Char_Array.c
 Author      : Carsten Gips, Malte Flender
 Version     : 0.1
 Copyright   : GNU General Public License
 Description : Systemprogrammierung Vorlesung 6 am 14.11.2013 Char Array Beispiel
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

int main(void) {

	static char *a = "Huhn";
	char *b = (char*) malloc(100); sprintf(b, "Huhn");
	char *c = "Huhn";
	char d[] = "Huhn";
	char e[100] = "Huhn";

	printf("a1: %s\n", a);
	printf("b1: %s\n", b);
	printf("c1: %s\n", c);
	printf("d1: %s\n", d);
	printf("e1: %s\n\n", e);

//	a[3]= 'a';		/*Fehler: a ist static und lässt sich nicht veraendern */
	b[3] = 'b';
//	c[3] = 'c';		/*Fehler: c ist eine konstante */
	d[3] = 'd'; 	/*Nur syntactic sugar, wird aufgelöst wie bei e */
	*(e + 3) = 'e'; /*Genau das gleiche wie bei d */

	printf("a2: %s\n", a);
	printf("b2: %s\n", b);
	printf("c2: %s\n", c);
	printf("d2: %s\n", d);
	printf("e2: %s\n", e);

	return EXIT_SUCCESS;
}
