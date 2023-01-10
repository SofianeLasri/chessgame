package chess;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static chess.ChessDemo.chessGraphicTool;
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

    public ChessTable(LayerManagement mgrLayers) {
        // On commence par tracer la table d'�checs

        chessBoard = new BufferedImage(ChessDemo.windowWidth, ChessDemo.windowHeight, BufferedImage.TYPE_INT_ARGB);
        mgrLayers.addLayer(chessBoard);
        chessBoardGC = (Graphics2D) chessBoard.getGraphics();
        chessBoardGC.setRenderingHints(ChessDemo.rh);

        cellsHighlight = new BufferedImage(ChessDemo.windowWidth, ChessDemo.windowHeight, BufferedImage.TYPE_INT_ARGB);
        mgrLayers.addLayer(cellsHighlight);
        cellsHighlightGC = (Graphics2D) cellsHighlight.getGraphics();
        cellsHighlightGC.setRenderingHints(ChessDemo.rh);


        // On calcule l'origine de la table d'�chec, bas� sur le nombre de cellules et de leur taille
        xOrigin = round((ChessDemo.windowWidth - (tableCellNumber * cellWidth)) / (float) 2);
        yOrigin = round((ChessDemo.windowHeight - (tableCellNumber * cellWidth)) / (float) 2);

        if (xOrigin < 0 || yOrigin < 0) {
            System.out.println("Error: The jFrame (window) is not wide enought!");
            System.out.println("Here is the minimal size: square" + (tableCellNumber * cellWidth) + "px");
        }

        pieceRows = new ArrayList<>();
        for (int x = 1; x <= tableCellNumber; x++) {
            // On en profite pour pre-remplir la liste des pi�ces
            ArrayList<Piece> rowColumns = new ArrayList<>();
            for (int y = 1; y <= tableCellNumber; y++) {
                if ((x + y) % 2 == 0) {
                    drawCell(x, y, Color.BLACK);
                } else {
                    drawCell(x, y, Color.WHITE);
                }
                rowColumns.add(null);
            }
            pieceRows.add(rowColumns);
        }

        highLightCell(3, 3, Color.ORANGE);
    }

    public void drawCell(int cellColumnNum, int cellRowNum, Color color) {
        int cellPosX = xOrigin + ((cellColumnNum - 1) * cellWidth);
        int cellPosY = yOrigin + ((cellRowNum - 1) * cellWidth);

        chessBoardGC.draw3DRect(cellPosX, cellPosY, cellWidth, cellWidth, true);
        chessBoardGC.setColor(color);
        chessBoardGC.fillRect(cellPosX, cellPosY, cellWidth, cellWidth);
    }

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

    public void clearHighLights() {
        cellsHighlight.setData(emptyLayer.getRaster());
    }

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

    public void movePiece(int cellColumnNum, int cellRowNum, Piece piece) {
        // On d�r�f�rence la pi�ce de sa position dans la liste des pi�ces
        pieceRows.get((piece.getPosY())).set((piece.getPosX()), null);
        // On lui donne sa nouvelle position
        piece.setPos(cellColumnNum + 1, cellRowNum + 1);

        // On check si on avait pas une pi�ce � ces coordonn�es
        Piece oldPiece = pieceRows.get(cellRowNum).get(cellColumnNum);
        if (oldPiece != null) {
            oldPiece.isNowDown();
            oldPiece.getLayeredImage().setData(emptyLayer.getRaster());
            oldPiece.setPos(-1, -1);
        }

        // On d�place l'image de la pi�ce
        piece.getLayeredImage().setData(emptyLayer.getRaster()); // On l'efface pour la redessiner juste en dessous

        int piecePosX = xOrigin + (cellColumnNum * cellWidth) + (cellWidth - Piece.imageSize) / 2;
        int piecePosY = yOrigin + (cellRowNum * cellWidth) + (cellWidth - Piece.imageSize) / 2;
        Graphics2D gcb = (Graphics2D) piece.getLayeredImage().getGraphics(); // get graphic context
        gcb.drawImage(piece.getLayeredImage(), piecePosX, piecePosY, null); // copy the piece in the blackPawn graphic context at the right place

        // Enfin, on r�f�rencie la nouvelle pi�ce dans la cellule souhait�e
        pieceRows.get((cellRowNum)).set((cellColumnNum), piece);
    }

    /**
     * R�cup�re la pi�ce aux coordonn�es du clic de la souris
     *
     * @param x Position x
     * @param y Position y
     * @return Pi�ce
     */
    public Piece getPieceAtScreenCoordinates(int x, int y) {
        // On va d�terminer de quelle cellule il s'agit
        int[] coordinates = getCellAtScreenCoordinates(x, y);

        if (coordinates != null) {
            return pieceRows.get(coordinates[1]).get(coordinates[0]);
        }
        return null;
    }

    /**
     * R�cup�re la pi�ce aux coordonn�es de la cellule.
     *
     * @param PieceRowNum Num�ro de la ligne de la cellule (1-8)
     * @param PieceColNum Num�ro de la colonne de la cellule (1-8)
     * @return Pi�ce
     */
    public Piece getPieceAtCellCoordinates(int PieceRowNum, int PieceColNum) {
        return pieceRows.get(PieceColNum - 1).get(PieceRowNum - 1);
    }

    /**
     * R�cup�re les positions de la cellule aux coordonn�es du clic de la souris
     *
     * @param x Position x
     * @param y Position y
     * @return Pi�ce
     */
    public int[] getCellAtScreenCoordinates(int x, int y) {

        // Ici on calcule la taille de la marge de gauche
        int calcWidth = (ChessDemo.windowWidth - (cellWidth * tableCellNumber)) / 2;
        if (x > calcWidth) {
            // On incr�mente la largeur pour chaque cellule
            for (int cellColumnNum = 0; cellColumnNum < tableCellNumber; cellColumnNum++) {
                calcWidth += cellWidth;

                // On a trouv� sa colonne
                if (x < calcWidth) {

                    // Pareil pour les lignes maintenant
                    int calcHeight = (ChessDemo.windowHeight - (cellWidth * tableCellNumber)) / 2 + ChessDemo.titleBarHeight;
                    if (y > calcHeight) {
                        // On incr�mente la largeur pour chaque cellule
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
}
