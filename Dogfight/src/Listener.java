import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener{

	
	private boolean w = false, a = false, s = false, d = false, space = false;
	private boolean up, down, left, right, shift;
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W){
			w = true;
		}
		if(code == KeyEvent.VK_A){
			a = true;
		}
		if(code == KeyEvent.VK_S){
			s = true;
		}
		if(code == KeyEvent.VK_D){
			d = true;
		}
		if(code == KeyEvent.VK_SPACE){
			space = true;
		}
		if(code == KeyEvent.VK_KP_UP){
			up = true;
		}
		if(code == KeyEvent.VK_KP_DOWN){
			down = true;
		}
		if(code == KeyEvent.VK_KP_LEFT){
			left = true;
		}
		if(code == KeyEvent.VK_KP_RIGHT){
			right = true;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W){
			w = false;
		}
		if(code == KeyEvent.VK_A){
			a = false;
		}
		if(code == KeyEvent.VK_S){
			s = false;
		}
		if(code == KeyEvent.VK_D){
			d = false;
		}
		if(code == KeyEvent.VK_SPACE){
			space = false;
		}
		if(code == KeyEvent.VK_KP_UP){
			up = false;
		}
		if(code == KeyEvent.VK_KP_DOWN){
			down = false;
		}
		if(code == KeyEvent.VK_KP_LEFT){
			left = false;
		}
		if(code == KeyEvent.VK_KP_RIGHT){
			right = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
