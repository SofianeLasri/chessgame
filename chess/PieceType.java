package chess;

public class PieceType {
	String type; // 0 = pawn, 1 = tower, 2 = knight, 3 = fool, 4 = queen, 5 = king

	public String getType() {
		return type;
	}
	
	public PieceType(String type) {
		super();
		this.type = type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public int[] PossibleMove() {
		int[] moves = {0,0};
		switch(this.type) {
			case "pawn":
				moves[0] = 0;
				moves[1] = 1;
				break;
			case "tower":
				moves[0] = 0;
				moves[1] = 1;
				break;
			case "knight":
				moves[0] = 0;
				moves[1] = 1;
				break;
			case "fool":
				moves[0] = 0;
				moves[1] = 1;
				break;
			case "queen":
				moves[0] = 0;
				moves[1] = 1;
				break;
			case "king":
				moves[0] = 0;
				moves[1] = 1;
				break;
		}
		return moves;
	}
}
