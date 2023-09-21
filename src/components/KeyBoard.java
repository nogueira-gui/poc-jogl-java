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
        float dx = 0.0f;
        float dy = 0.0f;
        System.out.println("Key pressed: " + e.getKeyCode());
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);
        if(e.getKeyChar() == 'x')
            cena.rotacionarX();
        if(e.getKeyChar() == 'z')
            cena.rotacionarZ();
        if(e.getKeyChar() == 'y')
            cena.rotacionarY();
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            dy += 0.05f; // Mover para cima
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            dy -= 0.05f; // Mover para baixo
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            dx -= 0.05f; // Mover para a esquerda
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            dx += 0.05f; // Mover para a direita
        }

        cena.transladar(dx, dy, 0.0f);

        if (e.getKeyChar() == 's' || e.getKeyChar() == 't' || e.getKeyChar() == 'c' || e.getKeyChar() == 'l')
            cena.showAction(e.getKeyChar());
    }
    @Override
    public void keyReleased(KeyEvent e) { }

}