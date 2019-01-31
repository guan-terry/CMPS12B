
public class Queens {
	int col;
	int row;
	
	public Queens(int r, int c) {
		col = c;
		row = r;
	}
	// if 2 queens in the same row then return true
	// true return = SAFE
	public boolean isSafe(Queens q) {
		return !((Math.abs(this.col - q.col) == Math.abs(this.row - q.row)) 
				|| (q.col == this.col) || (q.row == this.row));
	}
}
