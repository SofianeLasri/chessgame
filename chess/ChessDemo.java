package chess;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

// Modules used :
//import chess.ChessGUI;
//import chess.ChessMouseEvent;
//import chess.ChessGraphicTool;
//import chess.LayerManagement;


public class ChessDemo {

	

	public static void main(String[] args) {

		// Initialization
		int width = 840;
		int height = 840;
		LayerManagement mgrLayers = new LayerManagement();
		mgrLayers.setPreferredSize(new Dimension(width,height));
		ChessGraphicTool chessGraphicTool  = new ChessGraphicTool(); 
		String imagePath = new String("Z:/PRISM1/acsi/chessGame/chessStudent/chess/images/");
		
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
		foregroundGC.fill3DRect(0,0,width,height,true);
		
		//drawing the chess board
		BufferedImage chessBoard = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		mgrLayers.addLayer(chessBoard);
		java.awt.Graphics2D chessBoardGC = (Graphics2D) chessBoard.getGraphics();
		chessBoardGC.setRenderingHints(rh);
		chessBoardGC.setColor(java.awt.Color.WHITE); // color of the chess board
		for (int y = 100; y < 740; y += 80) { // lines
			for (int x = 100; x < 740; x += 80) { // columns
				chessBoardGC.draw3DRect(x,y,80,80,true); // xcenter, ycenter, width=80 , height=80
			}
		}
		
		// create a builder layer for the chess piece
		// and load two images pawns.
	    int xorigin = 510; // size of piece ~ 40 -> 500+(60-40)/2
	    int yorigin = 210; // size of the piece ~ 40 -> 200+(60-40)/2
	
	    // create a layer with the image file
	    BufferedImage whitePawnImage = ChessGraphicTool.load(imagePath+"pawn-white.png");
	    if (whitePawnImage == null) {
	    	System.out.println("Error image not found : "+imagePath+"pawn-white.png");
	    }

	    // create a dedicated layer and copy the pawn image at the right coordinates 
		BufferedImage whitePawnLayer = chessGraphicTool.createImage(whitePawnImage , 
				width, height, xorigin, yorigin);	
		
		// create a layer with the image file
		BufferedImage blackPawnImage = ChessGraphicTool.load(imagePath+"pawn-black.png");
	    if (blackPawnImage == null) {
	    	System.out.println("Error image not found : "+imagePath+"pawn-black.png");
	    }
	    // create a dedicated layer and copy the new pawn image at the right coordinates 
	    xorigin = 510;
	    yorigin = 330;
	    BufferedImage blackPawnLayer = chessGraphicTool.createImage(blackPawnImage , 
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
	    
	    // display pieces for black player
	    int bpi = 0;
	    BufferedImage builderbpi = null;
	    for (java.lang.String pieceId : chessPiecesBlack) {
	    	bpi += 80;
	    	builderbpi = ChessGraphicTool.load(imagePath+pieceId);
	    	BufferedImage piece = chessGraphicTool.createImage(builderbpi , 
					width, height, 40+bpi,120);	
			mgrLayers.addLayer(piece);
	    }
	    // display pawns for black player
	    int bpa = 0;
	    BufferedImage builderbpa = null;
	    for (java.lang.String pieceId : chessPawnsBlack) {
	    	bpa += 80;
	    	builderbpa = ChessGraphicTool.load(imagePath+pieceId);
	    	BufferedImage piece = chessGraphicTool.createImage(builderbpa , 
					width, height, 40+bpa,200);	
			mgrLayers.addLayer(piece);
	    }
	    
	    // display pieces for white player
	    int wpi = 0;
	    BufferedImage builderwpi = null;
	    for (java.lang.String pieceId : chessPiecesWhite) {
	    	wpi += 80;
	    	builderwpi = ChessGraphicTool.load(imagePath+pieceId);
	    	BufferedImage piece = chessGraphicTool.createImage(builderwpi , 
					width, height, 40+wpi,680);	
			mgrLayers.addLayer(piece);
	    }
	    // display pawns for white player
	    int wpa = 0;
	    BufferedImage builderwpa = null;
	    for (java.lang.String pieceId : chessPawnsWhite) {
	    	wpa += 80;
	    	builderwpa = ChessGraphicTool.load(imagePath+pieceId);
	    	BufferedImage piece = chessGraphicTool.createImage(builderwpa , 
					width, height, 40+wpa,600);	
			mgrLayers.addLayer(piece);
	    }
	    
	    // display all the layers
		mgrLayers.repaint();
		
		// -- main loop waiting drag and drop user events
		int counter = 0;
		while (true) {
			// waiting in millisecond
			try { Thread.sleep(5); } catch (Exception e) { e.printStackTrace(); } 
			if (chessMouseEvent.drag() == true) {
				int x = chessMouseEvent.getX();
				int y = chessMouseEvent.getY();
				System.out.println("CLICK x="+x+" y="+y);
				
				// -------
				// We have to find the right chess piece selected
				// from the coordinates.
				// -------
				
				// Example for moving the blackPawn piece
				// clear and copy a new ones
				blackPawnLayer.setData(emptyLayer.getRaster()); // clear
				java.awt.Graphics2D gcb = (Graphics2D) blackPawnLayer.getGraphics(); // get graphic context
				gcb.drawImage(blackPawnImage , x-20, y-60, null); // copy the piece in the blackPawn graphic context at the right place
						
				// Example with the computer 
				if (counter == 200000000) {
					System.out.println("Computer player - please wait - analyze is running (5s)");
					try { Thread.sleep(5000); } catch (Exception e) { e.printStackTrace(); }
					System.out.println("Computer player - Done !");
					whitePawnLayer.setData(emptyLayer.getRaster());
					java.awt.Graphics2D gcw = (Graphics2D) whitePawnLayer.getGraphics(); 
					gcw.drawImage(whitePawnImage , 510, 210+2*60, null); 
					
					mgrLayers.repaint();					
					counter = 0;
				}
				else counter += 1;
				
				// refresh the display with the changes
				mgrLayers.repaint();
			}
		}
	}
		

}
