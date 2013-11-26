#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define desc_length 200

enum Day {
    Montag, Dienstag, Mittwoch, Donnerstag, Freitag, Samstag, Sonntag
};

/*Aufgabe 1: Strukturen und Datentypen*/
typedef struct task {
    int start;
    int end;
    char description[desc_length];
} Task;

/*Aufgabe 2: AufzÃƒÂ¤hlungen*/
typedef struct tasknode {
    Task *taskptr;
    struct tasknode *nachfolger;
    struct tasknode *vorgaenger;
} Tasknode;


void showall(Tasknode *ToDoList[]);

Tasknode* createNode(Task *taskptr, Tasknode *prevptr, Tasknode *nextptr);

Task* createTask(int start, int end, char description[]);

void deleteDayList(Tasknode*);
void deleteTask(Tasknode*);
int insertTask(Tasknode*, Task*);

int main(void) {
    int i = 0;
    Tasknode * ToDoList[7];
    Tasknode *start = createNode(createTask(0, 1, "Hallo"), NULL, NULL);
    Tasknode *five = createNode(createTask(0, 1, "Hallo"), NULL, NULL);
    start->nachfolger = createNode(createTask(1, 2, "Test"), start, NULL);
    five->nachfolger = createNode(createTask(2, 3, "Test2"), five, NULL);
    five->nachfolger->nachfolger = createNode(createTask(2, 3, "Test3"), five->nachfolger, NULL);

    for (i = 0; i < 7; i++) {
        ToDoList[i] = NULL;
        ToDoList[i]->nachfolger = NULL;
        ToDoList[i]->vorgaenger = NULL;
        ToDoList[i]->taskptr = NULL;
    }

    ToDoList[Montag] = start;
    ToDoList[Samstag] = five;

    printf("Adresse: %p \n", ToDoList[0]);

    showall(ToDoList);
    deleteDayList(ToDoList[Samstag]->nachfolger);
    deleteDayList(ToDoList[Sonntag]->nachfolger);
    ToDoList[Samstag] = NULL;

    showall(ToDoList);
    printf("Adresse: %p \n", ToDoList[Samstag]);
    Task *test = createTask(2, 3, "Insert");
    /*insertTask(ToDoList[Sonntag], test);*/
    return EXIT_SUCCESS;
}

void showall(Tasknode *ToDoList[]) {
    Tasknode *nachfolger;
    int i = 0;
    for (i = 0; i < 7; i++) {
        printf("Tag: %d \n", (i + 1));
        /*printf("\t Adresse: %p \n", ToDoList[i]);*/
        nachfolger = ToDoList[i];
        while (nachfolger != NULL) {
            printf("\t Adresse: %p \n", nachfolger);
            printf("\t Start: %d Ende: %d Beschreibung: %s \n",
                    nachfolger->taskptr->start, nachfolger->taskptr->end, nachfolger->taskptr->description);
            nachfolger = nachfolger->nachfolger;
        }
    }
}

Tasknode* createNode(Task *taskptr, Tasknode *prevptr, Tasknode *nextptr) {
    Tasknode *node = (Tasknode *) malloc(sizeof (Tasknode));
    if (node == NULL) {
        /*Speicher konnte nicht reserviert werden*/
        printf("Speicherallozierung fehlgeschlagen \n");
    } else {
        node->taskptr = taskptr;
        node->vorgaenger = prevptr;
        node->nachfolger = nextptr;
    }
    return node;
}

Task* createTask(int startp, int endp, char descriptionp[]) {
    Task *task = (Task *) malloc(sizeof (Task));
    if (!task) {
        /*Speicher konnte nicht reserviert werden*/
        printf("Speicherallozierung fehlgeschlagen \n");
    } else {
        task->start = startp;
        task->end = endp;
        strcpy(task->description, descriptionp);
    }
    return task;
}

/*Aufgabe 3: Dynamische Speicherverwaltung*/
void deleteDayList(Tasknode *tasknodeptr) {
    /*Wenn tasknodepointer bereits gleich NULL tue nichts...*/
    if (tasknodeptr == NULL) {

    } else {
        /*...sonst setze VorgÃƒÂ¤nger gleich NULL*/
        Tasknode *vorgaenger = NULL;
        /*Frage ob VorgÃƒÂ¤nger ungleich NULL, dann setze *vorgaenger neu gleich VorgÃƒÂ¤nger*/
        if (tasknodeptr->vorgaenger != NULL) {
            Tasknode *vorgaenger = tasknodeptr->vorgaenger;
        }
        Tasknode *erster = tasknodeptr;
        Tasknode *temp;
        Tasknode *letzter;
        /*Hangel dich durch bis zum ersten Element solange vorgaenger nicht NULL
         erster zeigt auf vorgaenger*/
        while (vorgaenger != NULL) {
            erster = vorgaenger;
            vorgaenger = vorgaenger->vorgaenger;
        }
        /*Jetzt sind wir beim ersten Task und iterieren solange tmp(nachfolger) nicht NULL,
         so zeigt letzter auf temp(nachfolger) und temp wird zum weiteren nachfolger
         Dies geht solange bis wir beim letzten angekommen sind.*/
        letzter = erster;
        temp = erster->nachfolger;
        while (temp != NULL) {
            letzter = temp;
            temp = temp->nachfolger;
        }
        /*Hier zeigt temp (erst NULL) dann auf vorgaenger der durch die iteration 
         einen Wert haben sollte und prÃƒÂ¼fen solange temp nicht NULL,
         lÃƒÂ¶sche(den letzten, der der aktuelle ist) und wir geben dann noch aus...
         ...die Adresse und zur Kontrolle die letzte Adresse.*/
        temp = letzter->vorgaenger;
        while (temp != NULL) {
            deleteTask(letzter);
            printf("Adresse: %p \n", temp);
            printf("Adresselast: %p \n", letzter);
            letzter = temp;
            temp = temp->vorgaenger;
            printf("Adresse: %p \n", temp);
            printf("Adresselast: %p \n", letzter);
        }
        /*Hier lÃƒÂ¶schen wir noch den letzten task*/
        deleteTask(letzter);
        /*und setzen unsere Pointer auf NULL*/
        letzter = NULL;
        temp = NULL;
        vorgaenger = NULL;
        erster = NULL;
        tasknodeptr = NULL;
    }
}

void deleteTask(Tasknode *node) {
    printf("AdressegelÃƒÂ¶scht: %p \n", node);
    /*Hier wird der Heap vom Task freigerÃƒÂ¤umt*/
    free(node->taskptr);
    /*Hier fragen wir ob der vorgÃƒÂ¤nger existiert und lÃƒÂ¶schen von ihm den Nachfolger*/
    if (node->vorgaenger != NULL) {
        node->vorgaenger->nachfolger = NULL;
    }
    /*Dann setzen wir den node gleich NULL und entfernen ihn aus dem Heap*/
    node = NULL;
    free(node);
}

/*Die Funktion bekommt ein Knoten und einen task und fÃƒÂ¼gt ihn ein*/
int insertTask(Tasknode *node, Task *task) {
    Tasknode *vorgaenger = node->vorgaenger;
    Tasknode *erster = node;
    Tasknode *temp;
    /*0 ist false*/
    int eingefuegt = 0;
    int ende = 0;
    while (vorgaenger != NULL) {
        erster = vorgaenger;
        vorgaenger = vorgaenger->vorgaenger;
    }
    /*temp zeigt auf ersten und auch vorgaenger*/
    temp = erster;
    /*solange nichts eingefuegt oder nicht am ende ... 0=false, mache...*/
    while (!eingefuegt || !ende) {
        /*wenn temp nicht NULL,also schon belegt, tue nichts...*/
        if (temp != NULL) {

            /*sonst setze wenn temp==NULL, ende=1=true und gib info aus.*/
        } else {
            ende = 1;
            printf("Das Ende der Liste wurde erreicht");
        }
        /*temp springt immer weiter bis er am Ende der Kette ist.*/
        temp = temp->nachfolger;
    }
    return 0;
}