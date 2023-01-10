package chess;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static chess.ChessDemo.chessGraphicTool;
import static java.lang.Math.abs;
import static java.lang.Math.round;

@SuppressWarnings("SuspiciousNameCombination")
public class ChessTable {
    private final int tableCellNumber = 8;
    private final int cellWidth = 80;
    private final int cellHighlightStrokeWidth = round(cellWidth / (float) 16);
    private final int xOrigin;
    private final int yOrigin;
    private final BufferedImage chessBoard;
    private final Graphics2D chessBoardGC;
    private final BufferedImage cellsHighlight;
    private final Graphics2D cellsHighlightGC;
    private final BufferedImage emptyLayer = new BufferedImage(ChessDemo.windowWidth, ChessDemo.windowHeight, BufferedImage.TYPE_INT_ARGB);

    private final ArrayList<ArrayList<Piece>> pieceRows;

    /**
     * Créé un tableau d'échec
     *
     * @param mgrLayers Variables des calques sur laquelle dessiner le tableau
     */
    public ChessTable(LayerManagement mgrLayers) {
        // On commence par tracer la table d'échecs

        chessBoard = new BufferedImage(ChessDemo.windowWidth, ChessDemo.windowHeight, BufferedImage.TYPE_INT_ARGB);
        mgrLayers.addLayer(chessBoard);
        chessBoardGC = (Graphics2D) chessBoard.getGraphics();
        chessBoardGC.setRenderingHints(ChessDemo.rh);

        cellsHighlight = new BufferedImage(ChessDemo.windowWidth, ChessDemo.windowHeight, BufferedImage.TYPE_INT_ARGB);
        mgrLayers.addLayer(cellsHighlight);
        cellsHighlightGC = (Graphics2D) cellsHighlight.getGraphics();
        cellsHighlightGC.setRenderingHints(ChessDemo.rh);


        // On calcule l'origine de la table d'échec, basé sur le nombre de cellules et de leur taille
        xOrigin = round((ChessDemo.windowWidth - (tableCellNumber * cellWidth)) / (float) 2);
        yOrigin = round((ChessDemo.windowHeight - (tableCellNumber * cellWidth)) / (float) 2);

        if (xOrigin < 0 || yOrigin < 0) {
            System.out.println("Error: The jFrame (window) is not wide enought!");
            System.out.println("Here is the minimal size: square" + (tableCellNumber * cellWidth) + "px");
        }

        pieceRows = new ArrayList<>();
        for (int x = 1; x <= tableCellNumber; x++) {
            // On en profite pour pre-remplir la liste des pièces
            ArrayList<Piece> rowColumns = new ArrayList<>();
            for (int y = 1; y <= tableCellNumber; y++) {
                if ((x + y) % 2 == 0) {
                    drawCell(x, y, Color.white);
                } else {
                    drawCell(x, y, Color.black);
                }
                rowColumns.add(null);
            }
            pieceRows.add(rowColumns);
        }
    }

    /**
     * Dessiner une cellule
     *
     * @param cellColumnNum Numéro de la colonne de la cellule
     * @param cellRowNum    Numéro de la ligne de la cellule
     * @param color         Couleur
     */
    public void drawCell(int cellColumnNum, int cellRowNum, Color color) {
        int cellPosX = xOrigin + ((cellColumnNum - 1) * cellWidth);
        int cellPosY = yOrigin + ((cellRowNum - 1) * cellWidth);

        chessBoardGC.draw3DRect(cellPosX, cellPosY, cellWidth, cellWidth, true);
        chessBoardGC.setColor(color);
        chessBoardGC.fillRect(cellPosX, cellPosY, cellWidth, cellWidth);
    }

    /**
     * Mettre en valeur une cellule du tableau d'échec
     *
     * @param cellColumnNum Numéro de la colonne de la cellule
     * @param cellRowNum    Numéro de la ligne de la cellule
     * @param color         Couleur
     */
    public void highLightCell(int cellColumnNum, int cellRowNum, Color color) {
        int cellPosX = xOrigin + ((cellColumnNum - 1) * cellWidth);
        int cellPosY = yOrigin + ((cellRowNum - 1) * cellWidth);

        cellsHighlightGC.setColor(color);
        cellsHighlightGC.setStroke(
                new BasicStroke(cellHighlightStrokeWidth)
        );
        cellsHighlightGC.drawRect(
                cellPosX + (cellHighlightStrokeWidth / 2),
                cellPosY + (cellHighlightStrokeWidth / 2),
                cellWidth - (cellHighlightStrokeWidth),
                cellWidth - (cellHighlightStrokeWidth)
        );
    }

    /**
     * Supprimer les mises en évidences de cellules
     */
    public void clearHighLights() {
        cellsHighlight.setData(emptyLayer.getRaster());
    }

    /**
     * Placer une pièce sur une cellule aux coordonées données
     *
     * @param cellColumnNum Numéro de la colonne de la cellule
     * @param cellRowNum    Numéro de la ligne de la cellule
     * @param piece         Pièce
     */
    public void placePiece(int cellColumnNum, int cellRowNum, Piece piece) {
        int piecePosX = xOrigin + ((cellColumnNum - 1) * cellWidth) + (cellWidth - Piece.imageSize) / 2;
        int piecePosY = yOrigin + ((cellRowNum - 1) * cellWidth) + (cellWidth - Piece.imageSize) / 2;

        BufferedImage tempImage = chessGraphicTool.createImage(
                piece.getImage(),
                ChessDemo.windowWidth,
                ChessDemo.windowHeight,
                piecePosX,
                piecePosY
        );
        ChessDemo.mgrLayers.addLayer(tempImage);
        piece.setLayeredImage(tempImage);
        piece.setPos(cellColumnNum, cellRowNum);

        pieceRows.get((cellRowNum - 1)).set((cellColumnNum - 1), piece);
    }

    /**
     * Déplacer une pièce sur les coordonnées données
     *
     * @param cellColumnNum Numéro de la colonne de destination
     * @param cellRowNum    Numéro de la ligne de destination
     * @param piece         Pièce à déplacer
     */
    public void movePiece(int cellColumnNum, int cellRowNum, Piece piece) {
        // On déréférence la pièce de sa position dans la liste des pièces
        pieceRows.get((piece.getPosY() - 1)).set((piece.getPosX() - 1), null);

        // On lui donne sa nouvelle position
        piece.setPos(cellColumnNum + 1, cellRowNum + 1);

        // On check si on avait pas une pièce à ces coordonnées
        Piece oldPiece = pieceRows.get(cellRowNum).get(cellColumnNum);
        if (oldPiece != null) {
            oldPiece.isNowDown();
            oldPiece.getLayeredImage().setData(emptyLayer.getRaster());
            oldPiece.setPos(-1, -1);
        }

        // On déplace l'image de la pièce
        piece.getLayeredImage().setData(emptyLayer.getRaster()); // On l'efface pour la redessiner juste en dessous

        int piecePosX = xOrigin + (cellColumnNum * cellWidth) + (cellWidth - Piece.imageSize) / 2;
        int piecePosY = yOrigin + (cellRowNum * cellWidth) + (cellWidth - Piece.imageSize) / 2;

        BufferedImage tempImage = chessGraphicTool.createImage(
                piece.getImage(),
                ChessDemo.windowWidth,
                ChessDemo.windowHeight,
                piecePosX,
                piecePosY
        );

        piece.getLayeredImage().setData(tempImage.getRaster());

        // Enfin, on référencie la nouvelle pièce dans la cellule souhaitée
        pieceRows.get((cellRowNum)).set((cellColumnNum), piece);
    }

    /**
     * Récupère la pièce aux coordonnées du clic de la souris
     *
     * @param x Position x
     * @param y Position y
     * @return Pièce
     */
    public Piece getPieceAtScreenCoordinates(int x, int y) {
        // On va déterminer de quelle cellule il s'agit
        int[] coordinates = getCellAtScreenCoordinates(x, y);

        if (coordinates != null) {
            return pieceRows.get(coordinates[1]).get(coordinates[0]);
        }
        return null;
    }

    /**
     * Récupère la pièce aux coordonnées de la cellule.
     *
     * @param PieceRowNum Numéro de la ligne de la cellule (1-8)
     * @param PieceColNum Numéro de la colonne de la cellule (1-8)
     * @return Pièce
     */
    public Piece getPieceAtCellCoordinates(int PieceRowNum, int PieceColNum) {
        return pieceRows.get(PieceColNum - 1).get(PieceRowNum - 1);
    }

    /**
     * Récupère les positions de la cellule aux coordonnées du clic de la souris
     *
     * @param x Position x
     * @param y Position y
     * @return Pièce
     */
    public int[] getCellAtScreenCoordinates(int x, int y) {

        // Ici on calcule la taille de la marge de gauche
        int calcWidth = (ChessDemo.windowWidth - (cellWidth * tableCellNumber)) / 2;
        if (x > calcWidth) {
            // On incrémente la largeur pour chaque cellule
            for (int cellColumnNum = 0; cellColumnNum < tableCellNumber; cellColumnNum++) {
                calcWidth += cellWidth;

                // On a trouvé sa colonne
                if (x < calcWidth) {

                    // Pareil pour les lignes maintenant
                    int calcHeight = (ChessDemo.windowHeight - (cellWidth * tableCellNumber)) / 2 + ChessDemo.titleBarHeight;
                    if (y > calcHeight) {
                        // On incrémente la largeur pour chaque cellule
                        for (int cellRowNum = 0; cellRowNum < tableCellNumber; cellRowNum++) {
                            calcHeight += cellWidth;

                            if (y < calcHeight) {
                                return new int[]{cellColumnNum, cellRowNum};
                            }
                        }
                    }
                    break;
                }
            }
        }
        return null;
    }

    /**
     * Permet de calculer la distance entre deux pièces
     *
     * @param p1 Pièce 1
     * @param p2 Pièce 2
     * @return Distance entre les deux pièces
     */
    public int[] getDistanceBetweenPieces(Piece p1, Piece p2) {
        int[] array = new int[2];
        array[0] = p2.getPosX() - p1.getPosX();
        array[1] = p2.getPosY() - p1.getPosY();
        System.out.println("On vient d'interdire x : " + array[0] + "\ty : " + array[1]);
        return array;
    }

    /**
     * Vérifie si on est sur un axe interdit
     *
     * @param forbidden les moves interdits déjà donnés
     * @param the_move  le move qu'on veut faire
     * @return si c'est possible ou non
     */
    public boolean isNotOnForbiddenAxis(ArrayList<int[]> forbidden, int[] the_move) {
        for (int[] move : forbidden) {
            int this_move[] = new int[2];
            this_move[0] = (move[0] == 0) ? 0 : move[0] / abs(move[0]);
            this_move[1] = (move[1] == 0) ? 0 : move[1] / abs(move[1]);
            for (int i = 1; i < 8; i++) {
                int[] nextMove = new int[2];
                nextMove[0] = this_move[0] * i + move[0];
                nextMove[1] = this_move[1] * i + move[1];
                if (the_move[0] == nextMove[0] && the_move[1] == nextMove[1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
