import java.util.*;
import java.io.*;

public class NQueens {

	static int size;
	static int qrows[];
	static int qcols[];
	static int queenRow;
	static int queenCol;

	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("Usage: Java - jar Nqueens.jar <input file> <output file>");
			System.exit(1);
		}
		Scanner in = new Scanner (new File(args[0]));
		PrintWriter out = new PrintWriter(new File(args[1]));
		
		while(in.hasNextLine()) {
			//trim every single line of the input
			String line = in.nextLine().trim() + " ";
			//Takes the size of the first input of every line
			int firstSpace = line.indexOf(" ");
			size = Integer.parseInt(line.substring(0, firstSpace));
			int secondSpace = line.indexOf(" ", firstSpace + 1);
			int thirdSpace = line.indexOf(" ", secondSpace + 1);
			//puts the values of the 
			queenRow = Integer.parseInt(line.substring(firstSpace + 1,secondSpace));
			queenCol = Integer.parseInt(line.substring(secondSpace + 1,thirdSpace));
			qrows = new int[size];
			qcols = new int[size];
			qrows[queenRow - 1] = queenRow;
			qcols[queenRow - 1] = queenCol;
			
			if (recursionQueens(size)) {
				out.println(printQueens() + " ");
			}
			else {
				out.println("No solution");
			}
		}
		in.close();
		out.close();
//		size = 6;
//		qrows = new int[size];
//		qcols = new int[size];
//		queenRow = 6;
//		queenCol = 6;
//		qrows[queenRow - 1] = queenRow;
//		qcols[queenRow - 1] = queenCol;
//		System.out.println(printQueens());
//		System.out.println(recursionQueens(size));
//		System.out.println(printQueens());
	}

	

	/*
	 * checking if the board is safe of queens
	 */
	public static boolean safe() {
		// runs though 2 for loops and checks if the rows and cols of a queen will
		// match the row and cols of another. you cannot have the same queen on
		// the same row or col.
		for (int i = 0; i < qcols.length; i++) {
			for (int j = 0; j < qrows.length; j++) {
				if (qrows[i] == qrows[j] && i != j && qrows[i] != 0) {
					return false;
				}
				if (qcols[j] == qcols[i] && i != j && qcols[i] != 0) {
					return false;
				}
			}
		}

		for (int i = 0; i < qcols.length; i++) {
			for (int p = i + 1; p < qcols.length; p++) {
				int slowRows = qrows[i];
				int fastRows = qrows[p];
				int slowCols = qcols[i];
				int fastCols = qcols[p];
				if (Math.abs(fastRows - slowRows) == Math.abs(fastCols - slowCols) && slowRows != 0 &&
						fastRows!= 0 && slowCols != 0 && fastCols != 0) {
					return false;
				}
			}
		}

		return true;
	}

	/*
	 * uses recursion to place queens
	 */

	public static boolean recursionQueens(int n) {
		// the col is equal to the size, than it would return true
		// because it would be last element
		if (n == 0)
			return true;
		// recursively places queens on the board
		for (int row = 1; row <= size; row++) {
			qrows[n - 1] = n;
			qcols[n - 1] = row;
			qrows[queenRow - 1] = queenRow;
	 		qcols[queenRow - 1] = queenCol;
			if (safe()) {
				// see if a specific row,col is safe
				if (recursionQueens(n - 1))
					return true;
				
			}
			qrows[n - 1] = 0;
			qcols[n - 1] = 0;
			qrows[queenRow - 1] = queenRow;
			qrows[queenRow - 1] = queenCol;
		}

		return false;
	}

	// prints out every queen on the board
	public static String printQueens() {
		String queensPos = "";
		for (int i = 0; i < size; i++) {
			queensPos += " " + qrows[i] + " " + qcols[i];
		}
		queensPos = queensPos.trim();
		// returns every row-col that has a queen
		return queensPos;
	}

}
