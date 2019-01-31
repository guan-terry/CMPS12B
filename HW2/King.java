
public class King extends ChessPiece {

	public King(int r, int c, char p) {
		super(r, c, p);
	}

	public boolean canAttack(ChessPiece k) {
		for (int i = -1; i <= 1; i++) {
			for (int p = -1; p <= 1; p++) {
				if (row +i == k.row && col + p == k.col 
						&& Character.isUpperCase(k.color) != Character.isUpperCase(color))
					return true;
			}
		}
		return false;
	}

}