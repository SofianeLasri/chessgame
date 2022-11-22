package chess;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public class ChessGraphicTool {
	
	
	  /**
     * load an image from disk
     * @param imagePath is the path+name of the file to load
     * @return a BufferedImage or null on error
     */
    static public BufferedImage load(final String imagePath) {
    	File file = null;
		System.out.println("imagePath:" + imagePath);
    	try {
			URL fileUrl = ChessGraphicTool.class.getResource(imagePath);
    		file = new File(fileUrl.getPath());
    		if (!file.exists()) return null;		// image not found
    		return ImageIO.read(file);
    	}
    	catch (FileNotFoundException e) { e.printStackTrace(); }
    	catch (IOException e)           { e.printStackTrace(); }
    	return null;	// error
    }
    
    
    // --

    /**
     * default constructor
     */
	public ChessGraphicTool() {

	}


	/**
	 * Create a BufferedImage of size[widht,height] and copy the input image
	 * in the new BufferedImage at position (x,y)
	 * @param image is an input image to copy
	 * @param width is the size of the new BuffereImage
	 * @param height is the size of the new BufferedImage
	 * @param x is the coordinate (left corner) where the input image will be stored
	 * @param y is the coordinate (left corner) where the input image will be stored
	 * @return new Buffered Image containing the input image or null on error
	 */
	public BufferedImage createImage(BufferedImage image,int width,int height, int x, int y) {
		BufferedImage newImage = new BufferedImage(
				width, height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.drawImage(image, x, y, null);
 //		g.dispose();
		return newImage;
	}
	
	
}