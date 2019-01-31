#ifndef _QUEUE_H_INCLUDE_
#define _QUEUE_H_INCLUDE_

#include <stdio.h>

typedef struct Queue* QueueObj;

QueueObj newQueue(void);

int enqueue(QueueObj q, int k);

int dequeue(QueueObj q);

void printQueue(FILE*, QueueObj);

#endif