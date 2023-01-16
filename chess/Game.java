package chess;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Game {
    public static boolean isfinished = false;
    private String currentPlayer = "white";
    private static Player p1, p2;
    private Piece selectedPiece = null;

    private ArrayList<int[]> moves = new ArrayList<>();

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
        if(!isfinished) {
            ChessDemo.table.clearHighLights();

            // Faudrait faire le système de tour par tour pour savoir qui a cliqué
            Piece oldSelectedPiece = selectedPiece;

            selectedPiece = ChessDemo.table.getPieceAtScreenCoordinates(x, y);
                if (oldSelectedPiece == null) {
                    // On avait pas de pièce précédemment cliquée
                    if (selectedPiece != null) {
                        if(selectedPiece.getColor().equals(currentPlayer)) {
                            ChessDemo.table.highLightCell(selectedPiece.getPosX(), selectedPiece.getPosY(), Color.YELLOW);
                            moves = getMovesPossible(selectedPiece);
                        }
                    } else {
                        System.out.println("Ceci n'est pas une pièce.");
                    }
                } else {
                    if (oldSelectedPiece != selectedPiece) {
                        // On va déplacer la pièce actuelle
                        int[] cellCordinates = ChessDemo.table.getCellAtScreenCoordinates(x, y);
                        if (cellCordinates != null) {
                            for (int[] move : moves) {
                                if (move[0] - 1 == cellCordinates[0] && move[1] - 1 == cellCordinates[1]) {
                                    ChessDemo.table.movePiece(cellCordinates[0], cellCordinates[1], oldSelectedPiece);
                                    selectedPiece = null;
                                    if (currentPlayer.equals("white")) {
                                        currentPlayer = "black";
                                    } else {
                                        currentPlayer = "white";
                                    }
                                }
                            }
                        } else {
                            System.out.println("Le clique était en dehors de la table de jeu.");
                        }
                    } else {
                        // On la déselectionne
                        System.out.println("OLD:" + oldSelectedPiece.getPosX() + ";" + oldSelectedPiece.getPosY() + " - NEW:" + selectedPiece.getPosX() + ";" + selectedPiece.getPosY());
                        selectedPiece = null;
                    }
            }
        }
    }

    public ArrayList<int[]> getMovesPossible(Piece p) {
        ArrayList<int[]> array_returned = new ArrayList<>();

        ArrayList<int[]> array = p.getType().PossibleMove();

        //En gros, ici on gère les 8 axes du tableau
        //On veut faire en sorte que le système retienne à partir de quelle distance un move n'est plus possible
        ArrayList<int[]> forbiddenAxes = new ArrayList<>();
        for (int[] move : array) {
            if (p.getColor() == "white") {
                move[1] = -move[1];
            } //Pour que les blancs aillent vers le haut
            int this_move[] = new int[2];
            int movex = p.getPosX() + move[0];
            this_move[0] = movex;
            int movey = p.getPosY() + move[1];
            this_move[1] = movey;
            if (/*&&*/ movex > 0 && movex <= 8 && movey > 0 && movey <= 8) {
                Piece adverse = ChessDemo.table.getPieceAtCellCoordinates(movex, movey);
                if (ChessDemo.table.isNotOnForbiddenAxis(forbiddenAxes, move)) {
                    if (adverse == null) {
                        if(p.getType().getType().equals("pawn")){
                            if(abs(move[0]) != abs(move[1])) {
                                if (abs(move[1]) == 2) {
                                    if ((p.getPosY() == 7 && p.getColor() == "white") || (p.getPosY() == 2 && p.getColor() == "black")) {
                                        ChessDemo.table.highLightCell(movex, movey, Color.GREEN);
                                        array_returned.add(this_move);
                                    }
                                } else {
                                    ChessDemo.table.highLightCell(movex, movey, Color.GREEN);
                                    array_returned.add(this_move);
                                }
                            }
                        }else {
                            ChessDemo.table.highLightCell(movex, movey, Color.GREEN);
                            array_returned.add(this_move);
                        }
                    } else {
                        if (adverse.getColor() != p.getColor()) {
                            if(p.getType().getType().equals("pawn")){
                                if(abs(move[0]) == abs(move[1])){
                                    ChessDemo.table.highLightCell(movex, movey, Color.RED);
                                    array_returned.add(this_move);
                                }
                            }else{
                                ChessDemo.table.highLightCell(movex, movey, Color.RED);
                                array_returned.add(this_move);
                            }
                        }
                        int[] distance = ChessDemo.table.getDistanceBetweenPieces(p, adverse);
                        forbiddenAxes.add(distance);
                    }
                }
            }
        }
        return array_returned;
    }
}
