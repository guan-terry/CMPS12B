JAVASRC    = ChessBoard.java Bishop.java King.java Knight.java Queen.java Rook.java ChessPiece.java Pawn.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = ChessBoard
CLASSES    = ChessBoard.class Bishop.class King.class Knight.class Queen.class Rook.class ChessPiece.class Pawn.class
JARFILE    = ChessBoard.jar

 all: $(JARFILE)

 $(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest

 $(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

 clean:
	rm $(CLASSES) $(JARFILE)