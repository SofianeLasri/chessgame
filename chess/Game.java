package chess;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

        selectedPiece = ChessDemo.table.getPieceAtScreenCoordinates(x, y);
        if (oldSelectedPiece == null) {
            // On avait pas de pièce précédemment cliquée
            if (selectedPiece != null) {
                ChessDemo.table.highLightCell(selectedPiece.getPosX(), selectedPiece.getPosY(), Color.YELLOW);
                getMovesPossible(selectedPiece);
            } else {
                System.out.println("Ceci n'est pas une pièce.");
            }
        } else {
            if(oldSelectedPiece != selectedPiece){
                // On va déplacer la pièce actuelle
                int[] cellCordinates = ChessDemo.table.getCellAtScreenCoordinates(x, y);
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

    public void getMovesPossible(Piece p){
        ArrayList<int[]> array = p.getType().PossibleMove();

        //En gros, ici on gère les 8 axes du tableau
        //On veut faire en sorte que le système retienne à partir de quelle distance un move n'est plus possible
        ArrayList<int[]> forbiddenAxes = new ArrayList<>();
        for(int[] move : array){
            if(p.getColor() == "white"){move[1] = -move[1];} //Pour que les blancs aillent vers le haut
            int movex = p.getPosX() + move[0];
            int movey = p.getPosY() + move[1];
            if(/*&&*/ movex > 0 && movex <= 8 && movey > 0 && movey <= 8){
                Piece adverse = ChessDemo.table.getPieceAtCellCoordinates(movex, movey);
                if(adverse == null){
                    if(ChessDemo.table.isOnForbiddenAxis(forbiddenAxes, move)){
                        ChessDemo.table.highLightCell(movex, movey, Color.GREEN);
                    }
                }else{
                    int[] distance = ChessDemo.table.getDistanceBetweenPieces(p, adverse);
                    forbiddenAxes.add(distance);
                }
            }
        }
    }
}
