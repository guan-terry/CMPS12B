
public class Rook extends ChessPiece{

	public Rook (int r, int c, char p) {
		super(r,c,p);
	}
	
	public boolean canAttack(ChessPiece c) {
		return (col == c.col || row == c.row) 
				&& Character.isUpperCase(c.color) != Character.isUpperCase(color);
	}
}
