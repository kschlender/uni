/*
 ============================================================================
 Name        : Speicherverwaltung.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include "speicherverwaltung.h"

#define heapsize 16384
#define dead 0xdeaddead

char memPool[heapsize];
struct MemBlock *freeMem;

int main(void) {

	initHeap();
	initHeap();
	printBlock((struct MemBlock*)memPool);
	printf("Adresse: %p \n", memPool);
	printf("Absolute Adresse: %p \n",rel_to_Abs(7));
	printf("Relative Adresse: %d \n",abs_to_Rel(0x404107));
	printFreeBlocks();
	return EXIT_SUCCESS;
}

void initHeap(void) {
	static int control;
	if(control<1) {

		freeMem= (struct MemBlock*) memPool;
		freeMem->size=sizeof(memPool);
		freeMem->next=0;

		/**((int *)memPool)=7;*/

	} else {
		printf("Bereits initialisiert \n");
	}
	control++;
}
int rel_to_Abs(int rel) {
	/*printf("Absolute Adresse: %p \n",(memPool)+rel);*/
	return (memPool)+rel;
}
int abs_to_Rel(int abs) {
	/*printf("Relative Adresse: %d \n",abs-((int)memPool));*/
	return abs-((int)memPool);
}
void printBlock(struct MemBlock *p) {
	if(p!= NULL) {
		printf("Startadresse der Verwaltungsstruktur: Absolut: %p Relativ: %d\n",p,abs_to_Rel(p));
		printf("Größe der Verwaltungsstruktur: %d  \n",sizeof(p));
		printf("Startadresse des Nutzbaren Speichers: Absolut: %p Relativ: %d\n", (p)+1,abs_to_Rel((p)+1));
		printf("Größe des nutzbaren Speichers: %d \n", p->size-(sizeof(p)));
		printf("Gesamtgröße: %d \n", p->size);
		printf("Wert des next Pointers: %p \n", p->next);
		printf("Belegt:  ");
		if(p->next==dead) {
			printf("ja \n");
		} else {
			printf("nein \n");
		}
	} else {
		printf("Ungültiger Pointer");
	}
}

void printFreeBlocks(void) {
	struct MemBlock *temp=freeMem;

	while(temp != NULL) {
		printBlock(temp);
		if(temp->next != NULL) {
			temp=temp->next;
		} else {
			temp=NULL;
		}
	}
}


