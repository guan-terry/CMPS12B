
public class Knight extends ChessPiece{
	
	public Knight(int r, int c, char p) {
		super(r, c, p);
	}

	public boolean canAttack(ChessPiece c) {
		for (int i = -1; i <= 1;i+=2) {
			for (int p = -1 ; p <= 1; p+=2) {
				if (c.col == col + i * 2 && c.row == row + p 
						&& Character.isUpperCase(c.color) != Character.isUpperCase(color)) {
					return true;
				}
				if (c.row == row + i * 2 && c.col == col + p 
						&& Character.isUpperCase(c.color) != Character.isUpperCase(color)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean canMove(int row, int col) {
		for (int i = -1; i <= 1;i+=2) {
			for (int p = -1 ; p <= 1; p+=2) {
				if (this.col == col + i * 2 && this.row == row + p ) {
					return true;
				}
				if (this.row == row + i * 2 && this.col == col + p ) {
					return true;
				}
			}
		}		
		return false;
	}
	
	public int[][] getPath(int row, int col) {
		return new int[9][9];
	}
}
