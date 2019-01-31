
public class Bishop extends ChessPiece{

	public Bishop(int r, int c, char p) {
		super(r,c,p);
	}

	public boolean canAttack(ChessPiece c) {
		return Math.abs(c.col - col) == Math.abs(c.row - row) 
				&& Character.isUpperCase(c.color) != Character.isUpperCase(color);
	}
}
