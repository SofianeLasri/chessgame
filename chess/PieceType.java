package chess;

import java.util.ArrayList;

/**
 * The chess piece type.
 * This class contains all the possible moves for each piece.
 */
public class PieceType {
    String type;

    /**
     * The score of the piece
     */
    final int score;

    /**
     * Get the type of the piece
     * @return the type of the piece
     */
    public String getType() {
        return type;
    }

    /**
     * Create a piece type
     * @param type the type of the piece
     * @param score the score of the piece
     */
    public PieceType(String type, int score) {
        super();
        this.type = type;
        this.score = score;
    }

    /**
     * Set the type of the piece
     * @param type the type of the piece
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the possible moves for the piece
     * @return List of possible moves
     */
    public ArrayList<int[]> PossibleMove() {
        ArrayList<int[]> moves = new ArrayList<int[]>(); //0 = x; 1 = y;
        System.out.println(this.type);
        switch (this.type) {
            case "pawn" -> {
                moves.add(new int[]{0, 1});
                moves.add(new int[]{0, 2});
                moves.add(new int[]{1, 1});
                moves.add(new int[]{-1, 1});
            }
            case "rook" -> {
                for (int i = 1; i < 8; i++) {
                    moves.add(new int[]{0, i});
                    moves.add(new int[]{i, 0});
                    moves.add(new int[]{-i, 0});
                    moves.add(new int[]{0, -i});
                }
            }
            case "bishop" -> {
                for (int i = 1; i < 8; i++) {
                    moves.add(new int[]{i, i});
                    moves.add(new int[]{i, -i});
                    moves.add(new int[]{-i, i});
                    moves.add(new int[]{-i, -i});
                }
            }
            case "knight" -> {
                moves.add(new int[]{1, 2});
                moves.add(new int[]{2, 1});
                moves.add(new int[]{-1, -2});
                moves.add(new int[]{-2, -1});
                moves.add(new int[]{-2, 1});
                moves.add(new int[]{-1, 2});
                moves.add(new int[]{2, -1});
                moves.add(new int[]{1, -2});
            }
            case "queen" -> {
                for (int i = 1; i < 8; i++) {
                    moves.add(new int[]{i, i});
                    moves.add(new int[]{i, -i});
                    moves.add(new int[]{-i, -i});
                    moves.add(new int[]{-i, i});
                    moves.add(new int[]{0, i});
                    moves.add(new int[]{0, -i});
                    moves.add(new int[]{-i, 0});
                    moves.add(new int[]{i, 0});
                }
            }
            case "king" -> {
                moves.add(new int[]{0, 1});
                moves.add(new int[]{1, 1});
                moves.add(new int[]{1, 0});
                moves.add(new int[]{0, -1});
                moves.add(new int[]{1, -1});
                moves.add(new int[]{-1, -1});
                moves.add(new int[]{-1, 0});
                moves.add(new int[]{-1, 1});
            }
        }
        return moves;
    }
}
