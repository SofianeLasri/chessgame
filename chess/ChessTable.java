package chess;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static chess.ChessDemo.chessGraphicTool;
import static java.lang.Math.round;

public class ChessTable {
    private final int tableCellNumber = 8;
    private final int cellWidth = 80;
    private final int cellHighlightStrokeWidth = round(cellWidth / 16);
    private int xOrigin;
    private int yOrigin;
    private LayerManagement chessGameLayers;
    private BufferedImage chessBoard;
    private Graphics2D chessBoardGC;
    private BufferedImage cellsHighlight;
    private Graphics2D cellsHighlightGC;
    private final BufferedImage emptyLayer = new BufferedImage(ChessDemo.windowWidth, ChessDemo.windowHeight, BufferedImage.TYPE_INT_ARGB);

    private ArrayList<ArrayList> pieceRows;

    private static int positions(int x) {
        return x * 80 + 40;
    }

    public ChessTable(LayerManagement mgrLayers) {
        // On commence par tracer la table d'échecs
        chessGameLayers = mgrLayers;

        chessBoard = new BufferedImage(ChessDemo.windowWidth, ChessDemo.windowHeight, BufferedImage.TYPE_INT_ARGB);
        mgrLayers.addLayer(chessBoard);
        chessBoardGC = (Graphics2D) chessBoard.getGraphics();
        chessBoardGC.setRenderingHints(ChessDemo.rh);

        cellsHighlight = new BufferedImage(ChessDemo.windowWidth, ChessDemo.windowHeight, BufferedImage.TYPE_INT_ARGB);
        mgrLayers.addLayer(cellsHighlight);
        cellsHighlightGC = (Graphics2D) cellsHighlight.getGraphics();
        cellsHighlightGC.setRenderingHints(ChessDemo.rh);

        // On calcule l'origine de la table d'échec, basé sur le nombre de cellules et de leur taille
        xOrigin = round((ChessDemo.windowWidth - (tableCellNumber * cellWidth)) / 2);
        yOrigin = round((ChessDemo.windowHeight - (tableCellNumber * cellWidth)) / 2);

        if (xOrigin < 0 || yOrigin < 0) {
            System.out.println("Error: The jFrame (window) is not wide enought!");
            System.out.println("Here is the minimal size: square" + (tableCellNumber * cellWidth) + "px");
        }

        pieceRows = new ArrayList();
        for (int x = 1; x <= tableCellNumber; x++) {
            // On en profite pour pre-remplir la liste des pièces
            ArrayList rowColumns = new ArrayList<Piece>();
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

        piece.setLayeredImage(
                chessGraphicTool.createImage(
                        piece.getImage(),
                        ChessDemo.windowWidth,
                        ChessDemo.windowHeight,
                        piecePosX,
                        piecePosY
                )
        );
        ChessDemo.mgrLayers.addLayer(piece.getLayeredImage());
        piece.setPos(cellColumnNum, cellRowNum);

        pieceRows.get((cellRowNum - 1)).set((cellColumnNum - 1), piece);
    }

    public Piece getPieceAtCoordinate(int x, int y) {
        // On va déterminer de quelle cellule il s'agit
        int cellColumnNum = -1, cellRowNum = -1;

        // Ici on calcule la taille de la marge de gauche
        int calcWidth = (ChessDemo.windowWidth - (cellWidth * tableCellNumber)) / 2;
        if (x > calcWidth) {
            // On incrémente la largeur pour chaque cellule
            for (int i = 0; i < tableCellNumber; i++) {
                calcWidth += cellWidth;

                // On a trouvé sa colonne
                if (x < calcWidth) {
                    cellColumnNum = i;
                    break;
                }
            }
        }
        if(cellColumnNum == -1){
            return null;
        }

        // Pareil pour les lignes maintenant
        int calcHeight = (ChessDemo.windowHeight - (cellWidth * tableCellNumber)) / 2 + ChessDemo.titleBarHeight;
        if (y > calcHeight) {
            // On incrémente la largeur pour chaque cellule
            for (int j = 0; j < tableCellNumber; j++) {
                calcHeight += cellWidth;

                if (y < calcHeight) {
                    cellRowNum = j;
                    break;
                }
            }
        }

        if(cellRowNum == -1){
            return null;
        }

        Piece piece = (Piece) pieceRows.get(cellRowNum).get(cellColumnNum);
        if(piece != null) {
            return piece;
        }
        return null;
    }
}
