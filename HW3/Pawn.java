
public class Pawn extends ChessPiece {

	public Pawn(int r, int c, char p) {
		super(r, c, p);
	}

	public boolean canAttack(ChessPiece c) {
		for (int i = -1; i <= 1; i += 2) {
			if (row + i == c.row && col - 1 == c.col 
					&& (Character.isUpperCase(c.color) != Character.isUpperCase(color))
						&& Character.isUpperCase(color) == true) {
				return true;
			}
			if (row + i == c.row && col + 1 == c.col
				&& (Character.isUpperCase(c.color) != Character.isUpperCase(color))
					&& Character.isUpperCase(color) == false) {
				return true;
			}
		}
		return false;
	}

	public boolean canMove (int row, int col) {
		if (Character.isUpperCase(color) && this.col  - 1 == col) {
			return true;
		} else {
			if (Character.isUpperCase(color) == false && this.col + 1 == col)
				return true;
		}
		for (int i = -1; i <= 1; i += 2) {
			if (this.row + i == row && this.col - 1 == col 
						&& Character.isUpperCase(color) == true) {
				return true;
			}
			if (this.row + i == row && this.col + 1 == col
					&& Character.isUpperCase(color) == false) {
				return true;
			}
		}
		return false;
	}
	
	public int[][] getPath (int row, int col) {
		return new int[9][9];
	}
}
