package chess;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * This class is used to manage the mouse event
 */
public class ChessMouseEvent implements MouseListener, MouseMotionListener {

    private boolean drag = false;
    private boolean clicked = false;
    private int x = 0;
    private int y = 0;

    /**
     * default constructor
     */
    public ChessMouseEvent() {

    }

    /**
     * Get the last x location of the mouse pointer
     *
     * @return Position X du pointeur
     */
    public int getX() {
        return this.x;
    }

    /**
     * Get the last y location of the mouse pointer
     *
     * @return Position Y du pointeur
     */
    public int getY() {
        return this.y;
    }

    /**
     * Tell us if the user has clicked
     *
     * @return true on click otherwise false
     */
    public boolean drag() {
        boolean status = drag;
        this.drag = false;
        return status;
    }

    /**
     * Tell us if the user has clicked
     * @return true on click otherwise false
     */
    public boolean isClicked() {
        boolean status = clicked;
        this.clicked = false;
        return status;
    }

    // ============================ Listener implementation =========================


    /**
     * Invoked when the mouse button has been clicked (pressed and released) on a component.
     * @param mouse the event to be processed
     */
    public void mouseClicked(MouseEvent mouse) {
        clicked = true;
        x = mouse.getX();
        y = mouse.getY();
    }


    /**
     * implements MouseMotionListener
     * @param mouse the event to be processed
     */
    public void mouseMoved(MouseEvent mouse) {
    }

    /**
     * NOT USED - Called when the mouse enters in a component
     * @param mouse the event to be processed
     */
    public void mouseEntered(MouseEvent mouse) {
    }

    /**
     * NOT USED - Invoked when the mouse exits a component.
     * @param mouse the event to be processed
     */
    public void mouseExited(MouseEvent mouse) {
    }

    /**
     * Invoked when a mouse button has been released on a component.
     * @param mouse the event to be processed
     */
    public void mouseReleased(MouseEvent mouse) {
        this.drag = false;
    }

    /**
     * NOT USED - Invoked when a mouse button has been pressed on a component.
     * @param mouse the event to be processed
     */
    public void mousePressed(MouseEvent mouse) {
    }

    /**
     * Implements MouseMotionListener
     * @param mouse the event to be processed
     */
    public void mouseDragged(MouseEvent mouse) {
        x = mouse.getX();
        y = mouse.getY();
        drag = true;
    }
}
