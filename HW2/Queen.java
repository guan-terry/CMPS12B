
public class Queen extends ChessPiece{

	public Queen (int r, int c, char p) {
		super(r,c,p);
	}

	public boolean canAttack(ChessPiece c) {
		return ((Math.abs(c.col - col) == Math.abs(c.row - row) || 
				(col == c.col || row == c.row))
				&& Character.isUpperCase(c.color) != Character.isUpperCase(color));
	}
	
}