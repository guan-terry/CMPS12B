JAVASRC    = chessboard.java bishop.java king.java knight.java queen.java rook.java Chesspiece.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = chessboard
CLASSES    = chessboard.class bishop.class king.class knight.class queen.class rook.class Chesspiece.class
JARFILE    = chessboard.jar

 all: $(JARFILE)

 $(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest

 $(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

 clean:
	rm $(CLASSES) $(JARFILE)