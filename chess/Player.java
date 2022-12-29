package chess;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static chess.ChessDemo.chessGraphicTool;

public class Player {
    private final String color;

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    private List<Piece> pieces = new ArrayList<>();

    public String getColor() {
        return color;
    }

    private static int positions(int x) {
        return x * 80 + 40;
    }

    public Player(String color) {
        super();
        this.color = color;
        createPieces();
    }

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
            BufferedImage piece = chessGraphicTool.createImage(p.getImage(), ChessDemo.windowWidth, ChessDemo.windowHeight, positions(posx), positions(posy));
            ChessDemo.mgrLayers.addLayer(piece);
            System.out.println(p.getType().getType());
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
