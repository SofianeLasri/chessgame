package chess;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.round;

public class ChessTable {
    private final int tableCellNumber = 8;
    private final int tableCellWidth = 80;
    private int xOrigin;
    private int yOrigin;
    private LayerManagement chessGameLayers;
    private BufferedImage chessBoard;
    private Graphics2D chessBoardGC;
    private BufferedImage cellsHighlight;

    private static int positions(int x) {
        return x * 80 + 40;
    }

    public ChessTable(LayerManagement mgrLayers){
        chessGameLayers = mgrLayers;

        chessBoard = new BufferedImage(ChessDemo.windowWidth, ChessDemo.windowHeight, BufferedImage.TYPE_INT_ARGB);
        mgrLayers.addLayer(chessBoard);
        chessBoardGC = (Graphics2D) chessBoard.getGraphics();
        chessBoardGC.setRenderingHints(ChessDemo.rh);

        // On calcule l'origine de la table d'échec, basé sur le nombre de cellules et de leur taille
        xOrigin = round((ChessDemo.windowWidth - (tableCellNumber * tableCellWidth)) / 2);
        yOrigin = round((ChessDemo.windowHeight - (tableCellNumber * tableCellWidth)) / 2);

        if(xOrigin < 0 || yOrigin < 0){
            System.out.println("Error: The jFrame (window) is not wide enought!");
            System.out.println("Here is the minimal size: square" + (tableCellNumber * tableCellWidth) + "px");
        }

        for (int x=0; x<tableCellNumber; x++){
            for(int y=0; y<tableCellNumber; y++){
                if((x+y) % 2 == 0){
                    drawCell(x, y, Color.BLACK);
                }else{
                    drawCell(x, y, Color.WHITE);
                }
            }
        }

        /*boolean pair_impair = false; // v?rifie si la case est paire ou impaire pour savoir comment la colorier
        int line = 0; // v?rifie sur quelle ligne on est pour le d?calage des couleurs
        for (int y = 100; y < 740; y += 80) { // lines
            for (int x = 100; x < 740; x += 80) { // columns
                // chessBoardGC.draw3DRect(x,y,80,80,true); // xcenter, ycenter, windowWidth=80 , windowHeight=80
                if (pair_impair) {
                    pair_impair = false;
                    chessBoardGC.setColor(java.awt.Color.WHITE); // remplis la couleur du rectangle dessin?
                } else {
                    pair_impair = true;
                    chessBoardGC.setColor(java.awt.Color.BLACK); // remplis la couleur du rectangle dessin?
                }
                line++;
                if (line == 8) {
                    pair_impair = !pair_impair;
                    line = 0;
                }
                chessBoardGC.fillRect(x, y, 80, 80);
            }
        }*/
    }

    public void drawCell(int cellColumnNum, int cellRowNum, Color color){
        int cellPosX = xOrigin + (cellColumnNum * tableCellWidth);
        int cellPosY = yOrigin + (cellRowNum * tableCellWidth);
        chessBoardGC.draw3DRect(cellPosX,cellPosY,tableCellWidth,tableCellWidth,true);
        chessBoardGC.setColor(color);
        chessBoardGC.fillRect(cellPosX,cellPosY,tableCellWidth,tableCellWidth);
    }
}
