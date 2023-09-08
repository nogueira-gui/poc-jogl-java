package components;

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;

public class Mouse implements MouseListener {
    Scene scene;

    boolean leftButtonPressed;
    boolean rightButtonPressed;
    boolean scrollButtonPressed;

    public Mouse(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isButtonDown(MouseEvent.BUTTON1)) {
            setLeftButtonPressed(true);
            System.out.println("PressIn LeftButton.");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            setLeftButtonPressed(false);
            System.out.println("PressOut LeftButton.");
        }
    }


    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isLeftButtonPressed())
            scene.objectIsPressed(e.getX(), e.getY());
    }

    @Override
    public void mouseWheelMoved(MouseEvent e) {
        // Movimento da roda do mouse
    }

    public boolean isLeftButtonPressed() {
        return leftButtonPressed;
    }

    public void setLeftButtonPressed(boolean leftButtonPressed) {
        this.leftButtonPressed = leftButtonPressed;
    }

    public boolean isRightButtonPressed() {
        return rightButtonPressed;
    }

    public void setRightButtonPressed(boolean rightButtonPressed) {
        this.rightButtonPressed = rightButtonPressed;
    }

    public boolean isScrollButtonPressed() {
        return scrollButtonPressed;
    }

    public void setScrollButtonPressed(boolean scrollButtonPressed) {
        this.scrollButtonPressed = scrollButtonPressed;
    }
}
