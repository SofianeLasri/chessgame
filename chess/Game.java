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

    public void playerClicked(int x, int y) {
        ChessDemo.table.clearHighLights();

        // Faudrait faire le système de tour par tour pour savoir qui a cliqué
        Piece oldSelectedPiece = selectedPiece;

        selectedPiece = ChessDemo.table.getPieceAtCoordinate(x, y);
        if (oldSelectedPiece == null) {
            // On avait pas de pièce précédemment cliquée
            if (selectedPiece != null) {
                ChessDemo.table.highLightCell(selectedPiece.getPosX(), selectedPiece.getPosY(), Color.GREEN);
            } else {
                System.out.println("Ceci n'est pas une pièce.");
            }
        } else {
            if(oldSelectedPiece != selectedPiece){
                // On va déplacer la pièce actuelle
                int[] cellCordinates = ChessDemo.table.getCellAtCoordinates(x, y);
                if (cellCordinates != null) {
                    ChessDemo.table.movePiece(cellCordinates[0], cellCordinates[1], oldSelectedPiece);
                    selectedPiece = null;
                } else {
                    System.out.println("Le clique était en dehors de la table de jeu.");
                }
            }else{
                // On la déselectionne
                System.out.println("OLD:" + oldSelectedPiece.getPosX()+";"+oldSelectedPiece.getPosY()+" - NEW:"+selectedPiece.getPosX()+";"+selectedPiece.getPosY());
                selectedPiece = null;
            }
        }
    }
}
