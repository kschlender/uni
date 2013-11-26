/*
 * speicherverwaltung.h
 *
 *  Created on: 09.11.2013
 *      Author: Schlender,Korkin
 */

#ifndef SPEICHERVERWALTUNG_H_
#define SPEICHERVERWALTUNG_H_
#define heapsize 16384
#define dead 0xdeaddead

struct MemBlock {
	size_t size;
	struct MemBlock *next;
};

void initHeap(void);
int rel_to_Abs(int);
int abs_to_Rel(int);
void printBlock(struct MemBlock *p);
void printFreeBlocks(void);

#endif /* SPEICHERVERWALTUNG_H_ */
