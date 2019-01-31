
public class Pawn extends ChessPiece {

	public Pawn(int r, int c, char p) {
		super(r, c, p);
	}

	public boolean canAttack(ChessPiece c) {
		for (int i = -1; i <= 1; i += 2) {
			if (row + i == c.row && col + 1 == c.col 
					&& (Character.isUpperCase(c.color) != Character.isUpperCase(color))
						&& Character.isUpperCase(c.color) == true) {
				return true;
			}
			if (row + i == c.row && col - 1 == c.col
				&& (Character.isUpperCase(c.color) != Character.isUpperCase(color))
					&& Character.isUpperCase(c.color) == false) {
				return true;
			}
		}
		return false;
	}

}
