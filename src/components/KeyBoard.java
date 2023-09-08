package components;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

public class KeyBoard implements KeyListener {
    private Scene cena;

    public KeyBoard(Scene cena){
        this.cena = cena;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyCode());
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);

        if(e.getKeyChar() == 'x')
            cena.rotacionarX();
        if(e.getKeyChar() == 'z')
            cena.rotacionarZ();
        if(e.getKeyChar() == 'y')
            cena.rotacionarY();
    }

    @Override
    public void keyReleased(KeyEvent e) { }

}