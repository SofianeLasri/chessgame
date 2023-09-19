package chess;


import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import java.util.ArrayList;

/**
 * The layer management
 */
public class LayerManagement extends JPanel {

    /**
     * The layers
     */
	final ArrayList<BufferedImage> layers;

    /**
     * Get the layers
     * @return the layers
     */
    public ArrayList<BufferedImage> getLayers() {
        return layers;
    }

    /**
     * Create a layer management
     */
	public LayerManagement() {
        layers = new ArrayList<BufferedImage>();
    }

    /**
     * Add a layer
     * @param layer the layer to add
     */
    public void addLayer(BufferedImage layer) {
        layers.add(layer);
    }

    /**
     * Remove a layer
     * @param layer the layer to remove
     */
    public void removeLayer(BufferedImage layer) {
    	for(java.awt.image.BufferedImage buf : layers) { 
    		if (buf == layer) {
    			System.out.println("REMOVED");
    			layers.remove(layer);
    		}
    	}
    }

    /**
     * Paint the component
     * @param g the graphic
     */
    @Override
    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
        for(java.awt.image.BufferedImage buf : layers) { 
        	//render all layers
            g.drawImage(buf, 0, 0, buf.getWidth(), buf.getHeight(), null);
        }
    }
}
