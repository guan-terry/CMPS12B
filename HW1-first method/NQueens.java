import java.util.*;
import java.io.*;

public class NQueens {

	static int size;
	static int board[][];
	static int queenRow;
	static int queenCol;
	/*
	 * checking if the board is safe of queens
	 */
	public static boolean safe (int board[][], int row, int col) {
		//right
		for (int i = row; i < size ; i ++) {
			if (board[i][col] == 1) 
				return false;
		}
		//up
		for (int i = col; i < size; i++) {
			if(board[row][i] == 1) 
				return false;
		}
		//left
		for(int i = row; i >= 0; i--) {
			if (board[i][col] == 1)
				return false;
		}
		//bottom
		for (int i = col; i >= 0; i--) {
			if(board[row][i] == 1)
				return false;
		}
		//left up
		for (int i = row, p = col; i >= 0 && p >= 0 ; i--, p--) {
			if (board[i][p] == 1)
				return false;
		}
		//left bottom
		for (int i = row, p = col; i >= 0 && p < size; i--, p++) {
			if (board[i][p] == 1)
				return false;
		}
		//right up
		for (int i = row, p = col; i < size && p < size; i++, p++) {
			if (board[i][p] == 1)
				return false;
		}
		//right bottom
		for (int i = row, p = col; i < size && p >= 0; i++,p--) {
			if (board[i][p] == 1)
				return false;
		}
		
		return true;
	}
	
	/*
	 * uses recursion to place queens
	 */
	public static boolean recursionQueens (int board[][], int col) {
		//the col is equal to the size, than it would return true
		//because it would be last element
		if (col == size){
			return true;
		}
		//recursively places queens on the board
		for (int i = 0; i < size; i++) {
			//see if a specific row,col is safe
			if (safe(board,i,col) || board[i][col] == 1) {
				//if that row,col is safe or there is a queen there already than
				//that specific board piece will be a queen
				board[i][col] = 1;
				//recursion begins here, if you're able to fill up the entire board
				//then it would return true from the if (col == size) and in the end you
				//would return true finishing the recursion
				if (recursionQueens(board, col + 1)){
					return true;
				}
				//but if it fails the recursion then it will run this statement clearing the board
				//and go to the next for satement
				board[i][col] = 0;
				board[queenRow][queenCol] = 1;
			}
		}
		return false;
	}

	/*
	 * prints out every queen on the board
	 */
	public static String printQueens() {
		String queensPos = "";
		//loops through every row
		for (int row = 0; row < size; row++) {
			//loops through every col
			for (int col = 0; col < size; col++) {
				//if the position in that row-col has a queen it will add it to queensPos
				if (board[row][col] == 1) {
					//queenPos will be the statement returned at the end of the method
					queensPos+= "" + (row + 1) + " "+ (col + 1) + " ";
				}
			}
		}
		//returns every row-col that has a queen
		return queensPos;
	}
	
	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("Usage: java -jar NQueens.jar <input file> <output file>");
			System.exit(1);
		}
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new File(args[1]));
		//if the input-file still has a line left than it will make continue
		while(in.hasNextLine()) {
			//trim every single line of the input
			String line = in.nextLine().trim() + " ";
			//Takes the size of the first input of every line
			int firstSpace = line.indexOf(" ");
			size = Integer.parseInt(line.substring(0, firstSpace));
			int localBoard[][] = new int[size][size];
			//makes the size of the board
			board = localBoard;
			//finds the second and third space in order to use it to find the row and the col
			//of the space where the queen needs to be put
			int secondSpace = line.indexOf(" ", firstSpace + 1);
			int thirdSpace = line.indexOf(" ", secondSpace + 1);
			//puts the values of the 
			queenRow = Integer.parseInt(line.substring(firstSpace + 1,secondSpace)) - 1;
			queenCol = Integer.parseInt(line.substring(secondSpace + 1,thirdSpace)) - 1;
			//makes the entire board 0, 
			for(int row =0; row < size; row++) {
				for(int col =0; col < size; col++) {
					board[row][col] = 0;
				}
			}
			//the position of the 2 
			board[queenRow][queenCol] = 1;
			if (recursionQueens(board, 0)) {
				out.println(printQueens());
			}
			else {
				out.println("No solution");
			}
			
		}
		in.close();
		out.close();
		
	}
}
