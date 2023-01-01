package chess;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static chess.ChessDemo.chessGraphicTool;

public class Game {
    private static Player p1, p2;
    private Piece selectedPiece = null;

    public static final List<String> piecesList = new ArrayList<>() {
        {
            add("rook");
            add("knight");
            add("bishop");
            add("queen");
            add("king");
            add("bishop");
            add("knight");
            add("rook");
            add("pawn");
            add("pawn");
            add("pawn");
            add("pawn");
            add("pawn");
            add("pawn");
            add("pawn");
            add("pawn");
        }
    };

    public Game() {
        p1 = new Player("black");
        p2 = new Player("white");
    }

    public void playerClicked(int x, int y){
        ChessDemo.table.clearHighLights();
        // Faudrait faire le système de tour par tour pour savoir qui a cliqué
        Piece selectedPiece = ChessDemo.table.getPieceAtCoordinate(x, y);

        if(selectedPiece != null){
            ChessDemo.table.highLightCell(selectedPiece.getPosX(), selectedPiece.getPosY(), Color.GREEN);
        } else {
            System.out.println("Le clique était en dehors de la table de jeu.");
        }
    }
}
