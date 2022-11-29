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
        int[] moves = {0, 0, 0};
        switch (this.type) {
            case "pawn":
                moves[0] = 0;
                moves[1] = 2;
                moves[2] = 0;
                break;
            case "tower":
                moves[0] = 8;
                moves[1] = 8;
                moves[2] = 0;
                break;
            case "knight":
                moves[0] = 2;
                moves[1] = 1;
                moves[2] = 0;
                break;
            case "rook":
                moves[0] = 0;
                moves[1] = 0;
                moves[2] = 8;
                break;
            case "queen":
                moves[0] = 8;
                moves[1] = 8;
                moves[2] = 8;
                break;
            case "king":
                moves[0] = 1;
                moves[1] = 1;
                moves[2] = 1;
                break;
        }
        return moves;
    }
}
