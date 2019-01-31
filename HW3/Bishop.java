
public class Bishop extends ChessPiece {

	public Bishop(int r, int c, char p) {
		super(r, c, p);
	}

	public boolean canAttack(ChessPiece c) {
		return Math.abs(c.col - col) == Math.abs(c.row - row)
				&& Character.isUpperCase(c.color) != Character.isUpperCase(color);
	}

	public boolean canMove(int row, int col) {
		if (Math.abs(row - this.row) == Math.abs(col - this.col)) {
			return true;
		}
		return false;
	}

	public int[][] getPath(int row, int col) {
		int[][] path = new int[9][9];
		if (row > this.row && col > this.col) {
			// lower right
			for (int i = this.row, p = this.col; i < row && p < col; i++, p++) {
				if (i != this.row && p != this.col)
					path[i][p] = 1;
			}
		} else if (row < this.row && col > this.col) {
			// upper right
			for (int i = this.row, p = this.col; i > row && p < col; i--, p++) {
				if (i != this.row && p != this.col)
					path[i][p] = 1;
			}
		} else if (row < this.row && col < this.col) {
			// upper left
			for (int i = this.row, p = this.col; i > row && p > col; i--, p--) {
				if (i != this.row && p != this.col)
					path[i][p] = 1;
			}
		} else if (row > this.row && col < this.col) {
			// lower left
			for (int i = this.row, p = this.col; i < row && p > col; i++, p--) {
				if (i != this.row && p != this.col)
					path[i][p] = 1;
			}
		}
		return path;
	}
}
