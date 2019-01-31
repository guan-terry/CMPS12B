import java.io.*;
import java.util.*;

public class ChessBoard {
	ChessPiece head;

	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("Usage: Java - jar chessboard.jar <input file> <output file>");
			System.exit(1);
		}
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new File(args[1]));

		while (in.hasNextLine()) {
			String line = in.nextLine();
			//sets the row/col of the given String input
			int pieceRow = Integer.parseInt(line.substring(0, 1));
			int pieceCol = Integer.parseInt(line.substring(2, 3));
			String piecePosition = line.substring(4).trim();
			ChessBoard cb = new ChessBoard();
			//creates all the chess pieces after the : in the String
			cb.createChessPiece(piecePosition);
			//checks if there are 2 chess pieces in one square
			if (cb.twoChessPieces()) {
				out.println("Invalid");
				continue;
			}
			//checks if there is a specific chessPiece in the given square
			if (cb.positions(pieceRow, pieceCol)) {
				ChessPiece cp = cb.getChessPiece(pieceRow, pieceCol);
				// file print out type of Chesspiece with what color
				out.print(cp.color);
				//checks if that specific chesspiece is attacking another chesspiece
				//if it does print out y, if not print n
				if (cb.attacks(cp)) {
					out.println(" y");
				} else {
					out.println(" n");
				}
			//if there is not chessPIece then it will print '-'
			} else {
				out.println("-");
			}

		}
		in.close();
		out.close();
	}

	public void insert(ChessPiece c) {
		ChessPiece latest = c;
		//System.out.println("c.row = " + c.row);
		//System.out.println("c.col = " + c.col);
		latest.next = head;
		head = latest;
	}
	//goes through the linked list and checks if it is attacking another piece
	public boolean attacks(ChessPiece c) {
		ChessPiece current = head;
		//goes through all the chesspieces and compares it to c if it is attacking another
		//chesspiece, if it is return true, if not return false.
		while (current != null) {
			//System.out.println(c.canAttack(current));
			if (c.canAttack(current) && !current.equals(c)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	//adds the chess piece to the linked list
	public void createChessPiece(String s) {
		for (int i = 0; i < s.length();) {
			char cp = s.charAt(0);
			int row = Character.getNumericValue(s.charAt(2));
			int col = Character.getNumericValue(s.charAt(4));
			switch (cp) {
			case 'n':
				insert(new Knight(row, col, 'n'));
				break;
			case 'N':
				insert(new Knight(row, col, 'N'));
				break;
			case 'k':
				insert(new King(row, col, 'k'));
				break;
			case 'K':
				insert(new King(row, col, 'K'));
				break;
			case 'q':
				insert(new Queen(row, col, 'q'));
				break;
			case 'Q':
				insert(new Queen(row, col, 'Q'));
				break;
			case 'r':
				insert(new Rook(row, col, 'r'));
				break;
			case 'R':
				insert(new Rook(row, col, 'R'));
				break;
			case 'b':
				insert(new Bishop(row, col, 'b'));
				break;
			case 'B':
				insert(new Bishop(row, col, 'B'));
				break;
			case 'P':
				insert(new Pawn(row,col,'P'));
				break;
			case 'p':
				insert(new Pawn(row,col,'p'));
				break;
			}
			s = s.substring(5).trim();
		}
	}
	//goes through all the chess pieces and checks if there is 
	//a chess piece at that position
	public boolean positions(int row, int col) {
		ChessPiece current = head;
		int count = 0;
		while (current != null) {
			if (current.col == col && current.row == row) {
				count++;
			}
			current = current.next;
		}
		return count == 1;
	}
	//gets the chesspiece in the given row and col
	public ChessPiece getChessPiece(int row, int col) {
		ChessPiece current = head;
		ChessPiece rn = null;
		while (current != null) {
			if (current.col == col && current.row == row)
				rn = current;
			current = current.next;
		}
		return rn;
	}
	//checks if there is 2 chess pieces in the same row - col and if they are the same color
	public boolean twoChessPieces() {
		for (ChessPiece current = head; current != null; current = current.next) {
			for (ChessPiece traverse = current.next; traverse != null; traverse = traverse.next) {
				if (traverse.row == current.row && traverse.col == current.col) {
					return true;
				}
			}
		}
		return false;
	}

}
