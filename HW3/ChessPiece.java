
abstract public class ChessPiece {
	int row;
	int col;
	char color;
	ChessPiece next;
	
	public ChessPiece (int r, int c, char p) {
		col = c;
		row = r;
		color = p;
		next = null;
	}
	
	public abstract boolean canAttack(ChessPiece c);
	
	public abstract boolean canMove(int row, int col);
	
	public abstract int[][] getPath(int row, int col);
}
