 #include<stdio.h>
 #include<ctype.h>
 #include<stdlib.h>
 #include<string.h>

 void extract_chars(char* s, char* a, char* d, char* p, char* w) {
	 int i;
	 char c;
	 for (i = 0; i < strlen(s) ;i++) {
		c = s[i];
		if (isdigit(c)) {
			d[strlen(d)] = c; 
		} else if (isalpha(c)) {
			a[strlen(a)] = c;
		} else if (isspace(c)) {
			w[strlen(w)] = c;
		} else {
			p[strlen(p)] = c;
		}
	}
	d[strlen(d) + 1] = '\0';
	a[strlen(a) + 1] = '\0';
	w[strlen(w) + 1] = '\0';
	p[strlen(p) + 1] = '\0';
 }
 
 int main(int argc, char* argv[]){
	FILE* in;
	FILE* out;
	char line [256];
	
	if ( argc != 3) {
		printf("Usage: %s <input file> <output file> \n", argv[0]);
		exit(EXIT_FAILURE);
	}

   in = fopen(argv[1], "r");
   if( in==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   out = fopen(argv[2], "w");
   if( out==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }
   int i = 0;
   while( fgets (line, sizeof(line), in) != NULL) {
		int aSize, dSize, pSize, wSize;
		i++;
		char *a,*d,*p,*w;
		a = (char*)calloc(strlen(line), sizeof(char));
		d = (char*)calloc(strlen(line), sizeof(char));
		p = (char*)calloc(strlen(line), sizeof(char));
		w = (char*)calloc(strlen(line), sizeof(char));
		extract_chars(line,a,d,p,w);
		fprintf(out, "line %d contains:\n", i);
		aSize = strlen(a);
		dSize = strlen(d);
		pSize = strlen(p);
		wSize = strlen(w);
		if (aSize == 1) {
			fprintf(out, "%d alphabetic character: %s\n", aSize, a);
		} else {
			fprintf(out, "%d alphabetic characters: %s\n", aSize, a);
		}
		if (dSize == 1) {
			fprintf(out, "%d numeric character: %s\n", dSize, d);
		} else {
			fprintf(out, "%d numeric characters: %s\n", dSize, d);
		}
		if (pSize == 1) {
			fprintf(out, "%d punctuation character: %s\n", pSize, p);
		} else {
			fprintf(out, "%d punctuation characters: %s\n", pSize, p);
		}
		if (wSize == 1) {
			fprintf(out, "%d whitespace character: %s\n", wSize, w);	
		} else {
			fprintf(out, "%d whitespace characters: %s\n", wSize, w);
		}
		free(a);
		free(d);
		free(p);
		free(w);
		a = NULL;
		d = NULL;
		p = NULL;
		w = NULL;
   }
   fclose(in);
   fclose(out);
   return EXIT_SUCCESS;
 }
