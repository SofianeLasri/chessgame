package chess;

import java.util.ArrayList;
import java.util.List;

/**
 * The chess player
 */
public class Player {
    /**
     * The color of the player
     */
    private final String color;

    /**
     * Get the pieces of the player
     * @return List of pieces
     */
    public List<Piece> getPieces() {
        return pieces;
    }

    /**
     * Set the pieces of the player
     * @param pieces List of pieces
     */
    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    /**
     * The pieces of the player
     */
    private List<Piece> pieces = new ArrayList<>();

    /**
     * Get the color of the player
     * @return the color of the player
     */
    public String getColor() {
        return color;
    }

    /**
     * Get the position of the player
     * @return the position of the player
     */
    private static int positions(int x) {
        return x * 80 + 40;
    }

    /**
     * Create a player
     * @param color the color of the player
     */
    public Player(String color) {
        this.color = color;
        createPieces();
    }

    /**
     * Create the pieces of the player
     */
    private void createPieces() {
        int posx, posy;

        if (color.equals("black")) {
            posx = posy = 1;
        } else {
            posx = 1;
            posy = 8;
        }

        for (String pieceName : Game.piecesList) {
            Piece p = new Piece(pieceName + "-" + color + ".png", color);
            pieces.add(p);

            ChessDemo.table.placePiece(posx, posy, p);

            posx++;
            if (posx == 9) {
                if (color.equals("black")) {
                    posy++;
                } else {
                    posy--;
                }
                posx = 1;
            }
        }
    }
}
