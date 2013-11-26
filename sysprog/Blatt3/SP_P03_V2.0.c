#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define text_length 250
/*############################################################################*/
/*Aufgabe 1: Strukturen und Datentypen*/

/*############################################################################*/
typedef struct task {
    int start;
    int end;
    char description[text_length];
} Task;

typedef struct tasknode {
    Task *task;
    struct tasknode *prev;
    struct tasknode *next;
} Tasknode;
void showall(Tasknode *ToDoList[]);
/*############################################################################*/
/*Aufgabe 2: Aufzählungen*/
/*############################################################################*/
char * day[7] = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};

typedef enum {
    Montag, Dienstag, Mittwoch, Donnerstag, Freitag, Samstag, Sonntag
} Day;
/*############################################################################*/
/*Aufgabe 3: Dynamische Speicherverwaltung*/
/*############################################################################*/
void deleteDayList(Tasknode*);
void deleteTask(Tasknode*);
/*############################################################################*/
/*Aufgabe 4: Listen durchlaufen*/
/*############################################################################*/
int insertTask(Tasknode**, Day, Task*);
/*############################################################################*/
/*Sonstiges*/
/*############################################################################*/
Tasknode* createNode(Task *task, Tasknode *prev, Tasknode *next);
Task* createTask(int start, int end, char description[]);
/*############################################################################*/
/*# MAIN                                                                     #*/

/*############################################################################*/
int main(void) {
    int i = 0;
    Tasknode * ToDoList[7];
    /*ToDoList initialisieren mit NULL, damit keine zufaelligen werte drin stehen*/
    for (i = 0; i < 7; i++) {
        ToDoList[i] = NULL;
    }
    /*Werte eintragen*/
    Task *test = createTask(0, 10, "Insert"); /*Neuer Tag*/
    insertTask(ToDoList, Sonntag, test);
    Task *test2 = createTask(2, 3, "Insert2"); /*Ueberschneidung*/
    insertTask(ToDoList, Sonntag, test2);
    Task *test3 = createTask(13, 24, "Insert3"); /*NEXT*/
    insertTask(ToDoList, Sonntag, test3);
    Task *test4 = createTask(11, 13, "Insert4"); /*NODE*/
    insertTask(ToDoList, Sonntag, test4);
    Task *test5 = createTask(8, 14, "Zum Loeschen 1"); /*Neuer Tag*/
    insertTask(ToDoList, Montag, test5);
    Task *test6 = createTask(5, 8, "Zum Loeschen 2"); /*PREV*/
    insertTask(ToDoList, Montag, test6);
    /*Alles Anzeigen*/
    showall(ToDoList);
    /*Werte Loeschen*/
    deleteDayList(ToDoList[Montag]->next);
    ToDoList[Montag] = NULL;
    /*Alles Anzeigen*/
    showall(ToDoList);
    /*Programm ervolgreich beendet*/
    return EXIT_SUCCESS;
}
/*############################################################################*/
/*Implementierungen*/

/*############################################################################*/
void showall(Tasknode *ToDoList[]) {
    printf("\nShowall\n=======\n");
    Tasknode *pos;
    Tasknode *first;
    Tasknode *prev;
    int i = 0;
    for (i = 0; i < 7; i++) {
        printf("%s\n----------------\n", day[i]);
        pos = ToDoList[i];
        while (pos != NULL) {
            printf("   Adresse: %p Start: %2d Ende: %2d Beschreibung: %s \n",
                    pos, pos->task->start, pos->task->end, pos->task->description);
            pos = pos->next;
        }
    }
}

Tasknode* createNode(Task *task, Tasknode *prev, Tasknode *next) {
    Tasknode *node = (Tasknode *) malloc(sizeof (Tasknode));
    if (node == NULL) {
        printf("Speicherallozierung fehlgeschlagen \n");
    } else {
        node->task = task;
        node->prev = prev;
        node->next = next;
    }
    return node;
}

Task* createTask(int startp, int endp, char descriptionp[]) {
    Task *task = (Task *) malloc(sizeof (Task));
    if (!task) {
        printf("Speicherallozierung fehlgeschlagen \n");
    } else {
        task->start = startp;
        task->end = endp;
        strcpy(task->description, descriptionp);
    }
    return task;
}

void deleteDayList(Tasknode *tasknode) {
    printf("\ndeleteDayList\n=============\n");
    /*Wenn tasknodepointer bereits gleich NULL tue nichts...*/
    if (tasknode == NULL) {
    } else {
        /*...sonst setze Vorgaenger gleich NULL*/
        Tasknode *prev = NULL;
        /*Frage ob prev ungleich NULL, dann setze *prev neu gleich prev*/
        if (tasknode->prev != NULL) {
            prev = tasknode->prev;
        }
        Tasknode *first = tasknode;
        Tasknode *temp;
        Tasknode *last;
        /*Hangel dich durch bis zum ersten Element solange prev nicht NULL
         erster zeigt auf prev*/
        while (prev != NULL) {
            first = prev;
            prev = prev->prev;
        }
        /*Jetzt sind wir beim ersten Task und iterieren solange tmp(nachfolger) nicht NULL,
         so zeigt letzter auf temp(nachfolger) und temp wird zum weiteren nachfolger
         Dies geht solange bis wir beim letzten angekommen sind.*/
        last = first;
        temp = first->next;
        while (temp != NULL) {
            last = temp;
            temp = temp->next;
        }
        /*Hier zeigt temp (erst NULL) dann auf vorgaenger der durch die iteration 
         einen Wert haben sollte und pruefen solange temp nicht NULL,
         loesche(den letzten, der der aktuelle ist) und wir geben dann noch aus...
         ...die Adresse und zur Kontrolle die letzte Adresse.*/
        temp = last->prev;
        while (temp != NULL) {
            deleteTask(last);
            /*
            printf("Adresse\t\t: %p \n", temp);
            printf("Adresselast\t: %p \n", last);
             */
            last = temp;
            temp = temp->prev;
            /*
            printf("Adresse\t\t: %p \n", temp);
            printf("Adresselast\t: %p \n", last);
             */
        }
        /*Hier loeschen wir noch den letzten task Letzen task prev und next auf null, mit pointer*/
        deleteTask(last);
        /*und setzen unsere Pointer auf NULL*/
        last = NULL;
        temp = NULL;
        prev = NULL;
        first = NULL;
        tasknode = NULL;
    }
}

void deleteTask(Tasknode *tasknode) {
    printf("Adressegeloescht: %p \n", tasknode);
    /*Hier wird der Heap vom Task freigeraeumt*/
    free(tasknode->task);
    /*Hier fragen wir ob der vorgaenger existiert und loeschen von ihm den Nachfolger*/
    if (tasknode->prev != NULL) {
        tasknode->prev->next = NULL;
    }
    /*Dann setzen wir den node gleich NULL und entfernen ihn aus dem Heap*/
    tasknode = NULL;
    free(tasknode);
}

/*Tasks sollen sortiert eingefügt werden und keine überlappung und Stunden mit Werten von 0 bis 24*/
int insertTask(Tasknode **ToDoList, Day tag, Task *task) {
    printf("\ninsertTask\n===========");
    Tasknode *node = ToDoList[tag];
    Tasknode *prev;
    Tasknode *first;
    Tasknode *pos;
    int eingefuegt = 0;
    /*if((task->end)==24) task->end=0;
        /*if((task->start)==24)task->start=0;*/
    if ((task->end) > 24 || (task->end) < 0 || (task->start) > 24 || (task->start) < 0) {
        printf("Die Zeit geht bei mir nur von 0 bis 24!\n");
        return 3;
    }

    if ((task->end)<(task->start)) {
        printf("Die Endzeit darf nicht kleiner als die Startzeit sein!\n");
        return 2;
    }

    if (node == NULL) {
        ToDoList[tag] = createNode(task, NULL, NULL);
        printf("%s Node erstellt und Task eingetragen\n", day[tag]);
        return 0;
    }

    first = node;
    if ((node->prev) != NULL) {
        prev = node->prev;
    } else {
        prev = NULL;
    }

    while (prev != NULL) {
        first = prev;
        prev = prev->prev;
    }
    pos = first;

    while ((!eingefuegt) && (pos != NULL)) {
        if (pos->next != NULL) {
            if (((pos->task->end) <= (task->start)) && ((task->end) <= (pos->next->task->start))) {
                printf("NODE\n");
                pos->next = createNode(task, pos, pos->next);
                eingefuegt = 1;
            }
        } else if (((pos->task->end) <= (task->start))) {
            printf("NEXT\n");
            pos->next = createNode(task, pos, NULL);
            eingefuegt = 1;
        } else if (((pos->task->start) >= (task->end))) {
            printf("PREV\n");
            pos->prev = createNode(task, NULL, pos);
            if ((pos->prev->prev) == NULL) {
                ToDoList[tag] = pos->prev;
            }
            eingefuegt = 1;
        } else {
            printf("Keine Ueberschneidung erlaubt!\n");
            return 1;
        }
        pos = pos->next;
    }
    return 0;
}
