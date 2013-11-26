#include <stdio.h>
#include <stdlib.h>
/*1. Aufgabe: Strukturen und Variablen #######################################*/
#define MEM_POOL_SIZE 16384
#define magic 0xdeaddead
#define statusArray 6
typedef struct MemBlock{size_t size; struct MemBlock *next;}memBlock;
char memPool[MEM_POOL_SIZE];
memBlock *freeMem = NULL;
/*2. Aufgabe: Initialisierung ################################################*/
void initHeap(void);
/*3. Aufgabe: Adressumrechnung ###############################################*/
int absoluteToRelative(int);
int relativeToAbsolute(int);
/*4. Aufgabe: Ausgabe der freien Blöcke ######################################*/
void printBlock(memBlock *);
void printFreeBlocks(void);
/*############################################################################*/
/*MAIN #######################################################################*/
/*############################################################################*/
int main(){
	initHeap();
	printf("Convert (absoluteToRelative): %d\n",absoluteToRelative(4248515));
	printf("Convert (relativeToAbsolute): %d\n",relativeToAbsolute(3));
	printFreeBlocks();
	return 0;
}
/*2. Aufgabe: Initialisierung ################################################*/
void initHeap(void){
	/*Nur wenn freeMem==NULL, d.h. nur beim ersten Mal*/
	if(!freeMem)
	{
		freeMem = (memBlock *) (memPool);
		freeMem->size = sizeof(memPool);
		freeMem->next = NULL;
	}
}
/*3. Aufgabe: Adressumrechnung ###############################################*/
int absoluteToRelative(int absolute){
	/*Wandelt von Absolut zu Relativ um*/
	if(absolute<0)return -1;
	return absolute-((int)memPool);
}
int relativeToAbsolute(int relative){
	/*Wandelt von Relativ zu Absolut um*/
	if(relative<0)return -1;
	return ((int)memPool)+relative;
}
/*4. Aufgabe: Ausgabe der freien Blöcke ######################################*/
void printBlock(memBlock *p){
	/*Gibt info zu einem Block*/
	if(p!= NULL)
	{
		printf("|%8d|%8d|%8d|%8d|%8d|%8d|%8d|%8d|",
		absoluteToRelative(p),p,sizeof(p),
		absoluteToRelative((p)+1),(p)+1,
		p->size-(sizeof(p)),p->size,p->next
		);
		if(p->next==magic) printf(" Used|\n");
		else printf(" Free|\n");
	}
	else printf("Ungültiger Pointer!\n");
}
void printFreeBlocks(void){
	/*Gibt alle Bloecke aus*/
	if(!freeMem){printf("Keine freien Bloecke!");}
	else
	{
		memBlock *mBlock = freeMem;
		printf("===============================================================================\n");
		printf("|StartRel|StartAbs|Mem_Size|Use_Rel.|Use_Abs.|Use_Size|  Size  |  Next  |State|\n");
		printf("|--------|--------|--------|--------|--------|--------|--------|--------|-----|\n");
		while(mBlock != NULL)
		{
			printBlock(mBlock);
			mBlock = mBlock->next;
		}
		printf("===============================================================================\n");
	}
}
