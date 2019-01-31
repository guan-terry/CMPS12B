import java.util.*;
import java.io.*;

public class NQueens {

	public static Stack<Queens> recurQueenStack(Queens[] i, Stack<Queens> s, int size) {
		int count = 1;
		for (int arrayInfo = 0; arrayInfo < i.length; arrayInfo++) {
			if (i[arrayInfo] != null) {
//				System.out.println("pushed from array: " + i[arrayInfo].row + "," + i[arrayInfo].col);
				s.push(i[arrayInfo]);
			}
		}
		// runs for the amount of size there is
		for (int Row = 1; Row <= size; Row++) {
			int m = 0;
			// System.out.println("size of stack is :" + s.size());
			for (int arrayInfo = 0; arrayInfo < i.length; arrayInfo++) {
				if (i[arrayInfo] != null && i[arrayInfo].row == Row) {
//					 System.out.println("skipped row: " + Row);
					m++;
					continue;
				}
			}
			if (m == 1) {
				continue;
			}
			// this makes sure that they push the Queen if there is a Queen in that
			// specific row
			// initially runs the full time, and if it's safe everytime
			// than it will run on the size
			int Col;
			for (Col = count; Col <= size; Col++) {
				s.push((new Queens(Row, Col)));
				if (isSafe(s) || Col == size) {
//					System.out.println("pushed: " + Row + Col);
					break;
				}
				s.pop();
			}
			
			if (isSafe(s) && s.size() == size) {
				break;
			}
			
			if (isSafe(s)) {
				count = 1;
				continue;
			}

			if (Col == size) {
				Queens printing = s.pop();
//				System.out.println("popped col == size at: " + printing.row + printing.col);
				Queens temp = s.pop();
//				System.out.println("popped temp at: " + temp.row + temp.col);
				while (temp.col == size && temp.row != size && !s.isEmpty()) {
					temp = s.pop();
//					System.out.println("popped temp in while loop at: " + temp.row + temp.col);
				}
				count = temp.col + 1;
				Row = temp.row - 1;
				continue;
			}
		}
		return s;
	}

	// return true if it is safe and false if it is not safe
	public static boolean isSafe(Stack<Queens> s) {
		for (int i = 0; i < s.size(); i++) {
			for (int j = 0; j < s.size(); j++) {
				if (!(s.elementAt(i).isSafe(s.elementAt(j)))) {
					// System.out.println("returned false on isSafe on :" + s.elementAt(i).row +
					// s.elementAt(i).col
					// + "and :" + s.elementAt(j).row + s.elementAt(j).col);
					if (!s.elementAt(i).equals(s.elementAt(j))) {
						// System.out.println(!(s.elementAt(i).isSafe(s.elementAt(j))));
						return false;
					}
				}
			}
		}
		return true;
	}

	public static String printStack(Stack<Queens> s) {
		Queens[] qTemp = new Queens[s.size()];
		for (int i = 0; !s.isEmpty();i++) {
			qTemp[i] = s.pop();
		}
		
		for (int i = 0; i < qTemp.length; i++) {
			for (int p = 0; p < qTemp.length; p++) {
				if (qTemp[i].row > qTemp[p].row) {
					Queens tempStorage = qTemp[p];
					qTemp[p] = qTemp[i];
					qTemp[i] = tempStorage;
					
				}
			}
		}
		
		String prnt = "";
		for (int j = qTemp.length - 1; j >= 0;j--) {
			prnt += qTemp[j].row + " " + qTemp[j].col + " ";
		}
		prnt = prnt.trim();
		return prnt;
	}

	public static boolean legalStack(Stack<Queens> s, int size) {
		return s.size() != size;
	}

	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("Usage: Java - jar Nqueens.jar <input file> <output file>");
			System.exit(1);
		}
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new File(args[1]));
		Queens[] arr;

		while (in.hasNextLine()) {
			String line = in.nextLine().trim();
			// System.out.println("line is: " + line);
			int firstSpace = line.indexOf(" ");
			int size = Integer.parseInt(line.substring(0, firstSpace));
			arr = new Queens[size];
			line = line.substring(firstSpace).trim() + " ";
//			System.out.println(line);
			int row;
			int col;
			for (int i = 0; !line.isEmpty(); i++) {
				int rowSpace = line.indexOf(" ");
				int colSpace = line.indexOf(" ", rowSpace + 1);
				row = Integer.parseInt(line.substring(0, rowSpace));
				col = Integer.parseInt(line.substring(rowSpace + 1, colSpace));
				arr[i] = new Queens(row, col);
				line = line.substring(colSpace).trim() + " ";
				if (line.trim().isEmpty()) {
					break;
				}
			}
//			System.out.println(line);
			Stack<Queens> stck = new Stack<Queens>();
			stck = recurQueenStack(arr, stck, size);
//			System.out.println(stck.size());
//			System.out.println(size);
			if (legalStack(stck, size)) {
				out.println("No solution");
			} else {
				out.println(printStack(stck));
			}

		}
		in.close();
		out.close();
	}
}
