FLAGS	= -std=c99 -Wall
SOURCES = queueClient.c queue.c
OBJECTS = queueClient.o queue.o
HEADERS = queue.h
EXEBIN  = queueClient

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)
	
clean: 
	rm -f $(EXEBIN) $(OBJECTS)
