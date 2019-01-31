
public class Rook extends ChessPiece {

	public Rook(int r, int c, char p) {
		super(r, c, p);
	}

	public boolean canAttack(ChessPiece c) {
		return (col == c.col || row == c.row) && Character.isUpperCase(c.color) != Character.isUpperCase(color);
	}

	public boolean canMove(int row, int col) {
		if ((this.col == col || this.row == row)) {
			return true;
		}
		return false;
	}

	public int[][] getPath(int row, int col) {
		int[][] path = new int[9][9];
		if (col == this.col) {
			if (row > this.row) {
				for (int i = this.row; i < row; i++) {
					if (i != this.row)
						path[i][col] = 1;
				}
			} else {
				for (int i = this.row; i > row; i--) {
					if (i != this.row)
						path[i][col] = 1;
				}
			}
		} else {
			if (col > this.col) {
				for (int i = this.col; i < col; i++) {
					if (i != this.col)
						path[row][i] = 1;
				}
			} else {
				for (int i = this.col; i > col; i--) {
					if (i != this.col)
						path[row][i] = 1;
				}
			}
		}
		return path;
	}
}
