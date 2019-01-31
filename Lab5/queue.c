#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

#include "queue.h"

typedef struct Node* NodeObj;

typedef struct Node {
	int num;
	struct Node* next;
} Node;

struct Queue {
	NodeObj front;
	NodeObj rear;
} Queue;

NodeObj newNode(int i) {
	NodeObj n = malloc(sizeof(Node));
	n -> num = i;
	n ->next = NULL;
	return n;
}

void freeNode(NodeObj* fN) {
	//if (fN != NULL && *fN!= NULL) {
	free(*fN);
	*fN = NULL;
	//}
}

void freeQueue(QueueObj *q) {
	//if(q != NULL && *q!=NULL) {
	free(*q);
	*q = NULL;
	//}
}

QueueObj newQueue() {
	QueueObj q = malloc(sizeof(Queue));
	q->front = NULL;
	q->rear = NULL;
	return q;
}

int enqueue (QueueObj q, int i) {
	NodeObj newn = newNode(i);
	if (q->front == NULL && q->rear == NULL) {
		q->front = newn;
		q->rear = newn;
		return i;
	}
	q->front->next = newn;
	q->front = newn;
	return i;
}

int dequeue(QueueObj q) {
	NodeObj deq = q->rear;
	if (deq == NULL) {
		return 0;
	}
	int deqNum = deq->num;
	if (q->rear->next == NULL) {
		q->front = NULL;
		q->rear = NULL;
		freeNode(&deq);
		return deqNum;
	}
	q->rear = q->rear->next;
	freeNode(&deq);
	
	return deqNum;
}

void printQueue(FILE* out, QueueObj q) {
	for (NodeObj n = q->rear; n != NULL; n = n->next) {
		fprintf(out, "%d ", n->num);
	}
	fprintf(out, "\n");
}

