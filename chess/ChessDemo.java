package chess;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

// Modules used :
//import chess.ChessGUI;
//import chess.ChessMouseEvent;
//import chess.ChessGraphicTool;
//import chess.LayerManagement;


public class ChessDemo {

	private static int positions(int x){
		System.out.println(x);
		return x*80+40;
	}
	
    public static void main(String[] args) {
    	
        // Initialization
        int width = 840;
        int height = 840;
        LayerManagement mgrLayers = new LayerManagement();
        mgrLayers.setPreferredSize(new Dimension(width, height));
        ChessGraphicTool chessGraphicTool = new ChessGraphicTool();
        String imagePath = new String("./images/");
		
        ChessGUI.showOnFrame(mgrLayers, "Comment réussir les échecs");
        ChessMouseEvent chessMouseEvent = ChessGUI.getChessMouseEvent();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BufferedImage emptyLayer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // ===============
        // YOUR CODE HERE - HERE AFTER PLEASE FIND  BASIC MATERIAL FOR THE PLAY
        // ===============

        // create a black background		
        BufferedImage foreground = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        mgrLayers.addLayer(foreground);
        java.awt.Graphics2D foregroundGC = (Graphics2D) foreground.getGraphics();
        foregroundGC.setRenderingHints(rh);
        foregroundGC.setColor(java.awt.Color.BLACK);
        foregroundGC.fill3DRect(0, 0, width, height, true);

        //drawing the chess board
        BufferedImage chessBoard = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        mgrLayers.addLayer(chessBoard);
        java.awt.Graphics2D chessBoardGC = (Graphics2D) chessBoard.getGraphics();
        chessBoardGC.setRenderingHints(rh);

        boolean pair_impair = false; // vérifie si la case est paire ou impaire pour savoir comment la colorier
        int line = 0; // vérifie sur quelle ligne on est pour le décalage des couleurs
        for (int y = 100; y < 740; y += 80) { // lines
            for (int x = 100; x < 740; x += 80) { // columns
                // chessBoardGC.draw3DRect(x,y,80,80,true); // xcenter, ycenter, width=80 , height=80
                if (pair_impair) {
                    pair_impair = !pair_impair;
                    chessBoardGC.setColor(java.awt.Color.WHITE); // remplis la couleur du rectangle dessiné
                } else {
                    pair_impair = !pair_impair;
                    chessBoardGC.setColor(java.awt.Color.BLACK); // remplis la couleur du rectangle dessiné
                }
                line++;
                if (line == 8) {
                    pair_impair = !pair_impair;
                    line = 0;
                }
                chessBoardGC.fillRect(x, y, 80, 80);
            }
        }

        // create a builder layer for the chess piece
        // and load two images pawns.
        int xorigin = 510; // size of piece ~ 40 -> 500+(60-40)/2
        int yorigin = 210; // size of the piece ~ 40 -> 200+(60-40)/2

        // create a layer with the image file
        BufferedImage whitePawnImage = ChessGraphicTool.load(imagePath + "pawn-white.png");
        if (whitePawnImage == null) {
            System.out.println("Error image not found : " + imagePath + "pawn-white.png");
        }

        // create a dedicated layer and copy the pawn image at the right coordinates 
        BufferedImage whitePawnLayer = chessGraphicTool.createImage(whitePawnImage,
                width, height, xorigin, yorigin);

        // create a layer with the image file
        BufferedImage blackPawnImage = ChessGraphicTool.load(imagePath + "pawn-black.png");
        if (blackPawnImage == null) {
            System.out.println("Error image not found : " + imagePath + "pawn-black.png");
        }
        
        // create a dedicated layer and copy the new pawn image at the right coordinates 
        xorigin = 510;
        yorigin = 330;
        BufferedImage blackPawnLayer = chessGraphicTool.createImage(blackPawnImage,
                width, height, xorigin, yorigin);

//	    // add the new layers
//	    mgrLayers.addLayer(whitePawnLayer);
//	    mgrLayers.addLayer(blackPawnLayer);

        // adding pieces to its list
        ArrayList<String> chessPiecesBlack = new ArrayList<String>();
        ArrayList<String> chessPiecesWhite = new ArrayList<String>();

        chessPiecesBlack.add(new String("rook-black.png"));
        chessPiecesBlack.add(new String("knight-black.png"));
        chessPiecesBlack.add(new String("bishop-black.png"));
        chessPiecesBlack.add(new String("queen-black.png"));
        chessPiecesBlack.add(new String("king-black.png"));
        chessPiecesBlack.add(new String("bishop-black.png"));
        chessPiecesBlack.add(new String("knight-black.png"));
        chessPiecesBlack.add(new String("rook-black.png"));

        chessPiecesWhite.add(new String("rook-white.png"));
        chessPiecesWhite.add(new String("knight-white.png"));
        chessPiecesWhite.add(new String("bishop-white.png"));
        chessPiecesWhite.add(new String("queen-white.png"));
        chessPiecesWhite.add(new String("king-white.png"));
        chessPiecesWhite.add(new String("bishop-white.png"));
        chessPiecesWhite.add(new String("knight-white.png"));
        chessPiecesWhite.add(new String("rook-white.png"));

        // adding pawns to its list
        ArrayList<String> chessPawnsBlack = new ArrayList<String>();
        ArrayList<String> chessPawnsWhite = new ArrayList<String>();

        for (int i = 8; i > 0; i--) {
            chessPawnsBlack.add(new String("pawn-black.png"));
        }

        for (int i = 8; i > 0; i--) {
            chessPawnsWhite.add(new String("pawn-white.png"));
        }

        int posx, posy;
        
        // display pieces for black player
        BufferedImage builderbpi = null;
        posx = posy = 1;
		for (java.lang.String pieceId : chessPiecesBlack) { 
			  builderbpi = ChessGraphicTool.load(imagePath + pieceId);
			  BufferedImage piece = chessGraphicTool.createImage(builderbpi, width, height, positions(posx), positions(posy)); 
			  mgrLayers.addLayer(piece); posx++;}
		  
		// display pawns for black player 
		BufferedImage builderbpa = null; 
	    posx = 1;
	    posy = 2;
	    for (java.lang.String pieceId : chessPawnsBlack) {
			builderbpa = ChessGraphicTool.load(imagePath + pieceId); 
			BufferedImage piece = chessGraphicTool.createImage(builderbpa, width, height, positions(posx), positions(posy));
			mgrLayers.addLayer(piece); posx++;}
		  
		  
		// display pieces for white player
		BufferedImage builderwpi = null; 
	    posx = 1;
	    posy = 7;
	    for (java.lang.String pieceId : chessPiecesWhite) { 
	    	builderwpi = ChessGraphicTool.load(imagePath + pieceId); 
			BufferedImage piece = chessGraphicTool.createImage(builderwpi, width, height, positions(posx), positions(posy));
			mgrLayers.addLayer(piece); posx++;}
		  
		// display pawns for white player 
		BufferedImage builderwpa = null; 
	    posx = 1;
	    posy = 8;
	    for (java.lang.String pieceId : chessPawnsWhite) { 
	    	builderwpa = ChessGraphicTool.load(imagePath + pieceId); 
			BufferedImage piece = chessGraphicTool.createImage(builderwpa, width, height, positions(posx), positions(posy));
			mgrLayers.addLayer(piece); posx++;}
		 

        // display all the layers
        mgrLayers.repaint();

        // -- main loop waiting drag and drop user events
        int counter = 0;
        while (true) {
            // waiting in millisecond
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (chessMouseEvent.drag() == true) {
                int x = chessMouseEvent.getX();
                int y = chessMouseEvent.getY();
                System.out.println("CLICK x=" + x + " y=" + y);

                // -------
                // We have to find the right chess piece selected
                // from the coordinates.
                // -------

                // Example for moving the blackPawn piece
                // clear and copy a new ones
                blackPawnLayer.setData(emptyLayer.getRaster()); // clear
                java.awt.Graphics2D gcb = (Graphics2D) blackPawnLayer.getGraphics(); // get graphic context
                gcb.drawImage(blackPawnImage, x - 20, y - 60, null); // copy the piece in the blackPawn graphic context at the right place

                // Example with the computer 
                if (counter == 200000000) {
                    System.out.println("Computer player - please wait - analyze is running (5s)");
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Computer player - Done !");
                    whitePawnLayer.setData(emptyLayer.getRaster());
                    java.awt.Graphics2D gcw = (Graphics2D) whitePawnLayer.getGraphics();
                    gcw.drawImage(whitePawnImage, 510, 210 + 2 * 60, null);

                    mgrLayers.repaint();
                    counter = 0;
                } else counter += 1;

                // refresh the display with the changes
                mgrLayers.repaint();
            }
        }
    }


}
