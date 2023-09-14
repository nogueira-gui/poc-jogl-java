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
        System.out.println("Key pressed: " + e.getKeyCode() + e.getKeyChar());
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);

        if(e.getKeyChar() == 'x')
            cena.rotacionarX();
        if(e.getKeyChar() == 'z')
            cena.rotacionarZ();
        if(e.getKeyChar() == 'y')
            cena.rotacionarY();
        if(e.getKeyCode() == 150)
            cena.transladar(0.0f,0.05f,0.0f);
        if(e.getKeyCode() == 151)
            cena.transladar(0.05f,0.0f,0.0f);
        if(e.getKeyCode() == 149)
            cena.transladar(-0.05f,0.0f,0.0f);
        if(e.getKeyCode() == 152)
            cena.transladar(0.0f,-0.05f,0.0f);

    }

    @Override
    public void keyReleased(KeyEvent e) { }

}