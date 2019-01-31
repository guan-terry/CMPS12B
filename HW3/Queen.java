
public class Queen extends ChessPiece {

	public Queen(int r, int c, char p) {
		super(r, c, p);
	}

	public boolean canAttack(ChessPiece c) {
		return ((Math.abs(c.col - col) == Math.abs(c.row - row)
				|| (col == c.col || row == c.row)) && Character.isUpperCase(c.color) != Character.isUpperCase(color));
	}

	public boolean canMove(int row, int col) {
		if ((Math.abs(this.col - col) == Math.abs(this.row - row) || (col == this.col || row == this.row))) {
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
		} else if (col == this.col) {
			// row - right
			if (row > this.row) {
				for (int i = this.row; i < row; i++) {
					if (i != this.row)
						path[i][col] = 1;
				}
				// row - left
			} else {
				for (int i = this.row; i > row; i--) {
					if (i != this.row)
						path[i][col] = 1;
				}
			}
		} else {
			// col - up
			if (col > this.col) {
				for (int i = this.col; i < col; i++) {
					if (i != this.col)
						path[row][i] = 1;
				}
				// col - down
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