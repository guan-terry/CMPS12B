import java.io.*;
import java.util.*;

public class ChessBoard {
	ChessPiece head;

	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("Usage: Java - jar ChessMoves.jar <input file> <output file>");
			System.exit(1);
		}
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new File(args[1]));

		while (in.hasNextLine()) {
			String line = in.nextLine();
			ChessBoard cb = new ChessBoard();
			while (!(line.substring(0, 1).equals(":"))) {
				cb.createChessPiece(line.substring(0, 5));
				line = line.substring(5).trim();
			}
			line = line.substring(1).trim();
			// checks if color alternate works
			char color;
			char lastColor = 'b';
			while (!(line.isEmpty())) {
				int chessRow = Integer.parseInt(line.substring(0, 1));
				int chessCol = Integer.parseInt(line.substring(2, 3));
				ChessPiece movingCP = cb.getChessPiece(chessRow, chessCol);
				line = line.substring(3).trim();
				int moveRow = Integer.parseInt(line.substring(0, 1));
				int moveCol = Integer.parseInt(line.substring(2, 3));
				line = line.substring(3).trim();
				if (cb.getChessPiece(chessRow, chessCol) == null) {
					out.println("" + chessRow + " " + chessCol + " " + moveRow + " " + moveCol + " illegal");
					break;
				}
				if (Character.isUpperCase(cb.getChessPiece(chessRow, chessCol).color)) {
					color = 'b';
				} else {
					color = 'w';
				}

				if (lastColor == color) {
					out.println("" + chessRow + " " + chessCol + " " + moveRow + " " + moveCol + " illegal");
					break;
				}

				// checks if the chesspiece is allowed to move to the specific
				// row col in the chessboard
				if (cb.moveAvaliable(moveRow, moveCol, movingCP)) {
					int[][] path = movingCP.getPath(moveRow, moveCol);
					if (cb.positions(moveRow,moveCol)) {
						ChessPiece del = cb.getChessPiece(moveRow, moveCol);
						if (Character.toLowerCase(del.color) == 'k') {
							out.println("" + chessRow + " " + chessCol + " " + moveRow + " " + moveCol + " illegal");
							break;
						}
						cb.delete(del);
					}
					// if you are able to move, then check if anything is blocking
					// that piece
					ChessPiece cp = cb.delete(movingCP);
					// change the moving piece to the new position
					// but we have to save row/col incase piece doesn't work
					cp.row = moveRow;
					cp.col = moveCol;
					cb.insert(cp);
					// check if the path has any other pieces.
					if (cb.isLegalPath(path)) {
						ChessPiece king = cb.getKing(color);
						if (cb.kingAttack(king)) {
							out.println("" + chessRow + " " + chessCol + " " + moveRow + " " + moveCol + " illegal");
							break;
						}

					} else {
						out.println("" + chessRow + " " + chessCol + " " + moveRow + " " + moveCol + " illegal");
						// check path method
						break;
					}
				} else {
					out.println("" + chessRow + " " + chessCol + " " + moveRow + " " + moveCol + " illegal");
					break;
				}
				lastColor = color;
				line = line.trim();
				if (line.isEmpty()) {
					out.println("legal");
				}
			}
		}
		in.close();
		out.close();
	}

	public boolean kingAttack(ChessPiece king) {
		int kingRow = king.row;
		int kingCol = king.col;
		ChessPiece current = head;
		while (current != null) {
			int path[][] = current.getPath(kingRow, kingCol);
			if (!(current.equals(king)) && current.canAttack(king) && isLegalPath(path)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	public ChessPiece getKing(char color) {
		ChessPiece current = head;
		if (color == 'b') {
			while (current != null) {
				if (current.color == 'K') {
					return current;
				}
				current = current.next;
			}
		}
		while (current != null) {
			if (current.color == 'k') {
				return current;
			}
			current = current.next;
		}
		return current;
	}

	public boolean isLegalPath(int[][] path) {
		for (int i = 0; i < 9; i++) {
			for (int p = 0; p < 9; p++) {
				if (path[i][p] == 1) {
					if (positions(i, p)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public ChessPiece delete(ChessPiece delCP) {
		ChessPiece prev = null;
		ChessPiece curr = head;
		if (curr == null) {
			return null;
		}
		while (!(curr.equals(delCP)) && curr != null) {
			prev = curr;
			curr = curr.next;
		}
		if (prev == null)
			head = head.next;
		else
			prev.next = curr.next;
		return curr;
	}

	public void insert(ChessPiece c) {
		ChessPiece latest = c;
		latest.next = head;
		head = latest;
	}

	public boolean moveAvaliable(int row, int col, ChessPiece c) {
		ChessPiece current = head;
		// piece moves according to rules
		if (!(c.canMove(row, col))) {
			return false;
		}
		while (current != null) {
			// Check if
			if (Character.isUpperCase(c.color) == Character.isUpperCase(current.color) && row == current.row
					&& col == current.col) {
				return false;
			}
			current = current.next;
		}
		return true;
	}

	// goes through the linked list and checks if it is attacking another piece
	public boolean attacks(ChessPiece c) {
		ChessPiece current = head;
		// goes through all the chesspieces and compares it to c if it is attacking
		// another
		// chesspiece, if it is return true, if not return false.
		while (current != null) {
			if (c.canAttack(current) && !current.equals(c)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	// adds the chess piece to the linked list
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
				insert(new Pawn(row, col, 'P'));
				break;
			case 'p':
				insert(new Pawn(row, col, 'p'));
				break;
			}
			s = s.substring(5).trim();
		}
	}

	// goes through all the chess pieces and checks if there is
	// a chess piece at that position
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

	// gets the chesspiece in the given row and col
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

	// checks if there is 2 chess pieces in the same row - col and if they are the
	// same color
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
