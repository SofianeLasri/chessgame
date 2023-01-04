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
                ChessDemo.table.highLightCell(selectedPiece.getPosX(), selectedPiece.getPosY(), Color.YELLOW);
                getMoves(selectedPiece);
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

    public void getMoves(Piece piece){
        ArrayList<int[]> array = piece.getType().PossibleMove();
        for(int[] move : array){
            int movex = piece.getPosX() + move[0];
            int movey = piece.getPosY() + move[1];
            if(ChessDemo.table.getPieceAtCellCoordinates(movex, movey) == null){
                if(/*&&*/ movex > 0 && movex <= 8 && movey > 0 && movey <= 8){
                    System.out.println(movex + " " + movey);
                    ChessDemo.table.highLightCell(movex, movey, Color.GREEN);
                }else{
                    System.out.println("Pas possible de faire : " + movex + ", " + movey);
                }
            }else{
                System.out.println("Les coordonnées : " + movex +", " + movey);
            }
        }
    }
}
