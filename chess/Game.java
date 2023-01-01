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

        // Faudrait faire le syst�me de tour par tour pour savoir qui a cliqu�
        Piece oldSelectedPiece = selectedPiece;

        selectedPiece = ChessDemo.table.getPieceAtCoordinate(x, y);
        if (oldSelectedPiece == null) {
            // On avait pas de pi�ce pr�c�demment cliqu�e
            if (selectedPiece != null) {
                ChessDemo.table.highLightCell(selectedPiece.getPosX(), selectedPiece.getPosY(), Color.GREEN);
            } else {
                System.out.println("Ceci n'est pas une pi�ce.");
            }
        } else {
            if(oldSelectedPiece != selectedPiece){
                // On va d�placer la pi�ce actuelle
                int[] cellCordinates = ChessDemo.table.getCellAtCoordinates(x, y);
                if (cellCordinates != null) {
                    ChessDemo.table.movePiece(cellCordinates[0], cellCordinates[1], oldSelectedPiece);
                    selectedPiece = null;
                } else {
                    System.out.println("Le clique �tait en dehors de la table de jeu.");
                }
            }else{
                // On la d�selectionne
                System.out.println("OLD:" + oldSelectedPiece.getPosX()+";"+oldSelectedPiece.getPosY()+" - NEW:"+selectedPiece.getPosX()+";"+selectedPiece.getPosY());
                selectedPiece = null;
            }
        }
    }
}
