#include <stdio.h>
#include "queue.h"
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

int main(int argc, char* argv[]) {
	FILE* in;
	FILE* out;
	char word[256];
	
	in = fopen(argv[1], "r");
	if (in == NULL) {
		exit(EXIT_FAILURE);
	}
	
	out = fopen(argv[2] , "w");
	if (out == NULL) {
		exit(EXIT_FAILURE);
	}
	
	QueueObj q = newQueue();
	while(fgets(word,sizeof(word), in) != NULL) {
		if(word[0] == 'e') {
			char len[strlen(word)];
			int m;
			for (int i = 2, p = 0; i < strlen(word); i++, p++) {
				len[p] = word[i];
			}
			m = atoi (len);
			fprintf(out, "enqueued %d\n", enqueue(q,m));
		} else if (word[0] == 'p') {
			printQueue(out,q);
		} else if (word[0] == 'd') {
			int number = dequeue(q);
			if (number == 0) {
				fprintf(out, "empty\n");
			} else {
				fprintf(out, "%d\n", number);
			}
		}
	}
	freeQueue(&q);
}