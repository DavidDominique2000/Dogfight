import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dogfight extends JPanel implements KeyListener{

	private static Airplane plane1, plane2;
	private static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public static Dogfight panel;

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;

	public static final Rectangle ground = new Rectangle(0, HEIGHT - 100, WIDTH, 100); 
	private static Image background = new ImageIcon("background.png").getImage();

	private static boolean w = false, a = false, s = false, d = false, space = false;
	private static boolean up = false, down = false, left = false, right = false, slash = false;
	private static final int planeRotation = 8;

	public static Menu menu;
	public static boolean started = false;


	public Dogfight(){
		//Instantiating airplanes, later will add a selection to this
		plane1 = new Airplane(new ImageIcon("spitfire.png").getImage(), true);
		plane2 = new Airplane(new ImageIcon("zero.png").getImage(), false);
	}

	public static void main(String[] args){
		started = false;
		background = background.getScaledInstance(WIDTH, HEIGHT, 100);
		JFrame frame = new JFrame("Dogfight!");
		frame.setBounds(200, 100, 1000, 700);
		panel = new Dogfight();
		frame.getContentPane().add(panel);
		panel.setBackground(Color.WHITE);
		frame.addKeyListener(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

		//To give time for stuff to load
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		menu = new Menu();
		menu.countdown();

		started = true;
		while(started == true){
			
			//Airplane movement
			if(w == true){
				plane1.rotate(planeRotation);
			}
			if(a == true){
				plane1.changeSpeed(-1);
			}
			if(s == true){
				plane1.rotate(-planeRotation);
			}
			if(d == true){
				plane1.changeSpeed(1);
			}
			if(up == true){
				plane2.rotate(planeRotation);
			}
			if(down == true){
				plane2.rotate(-planeRotation);
			}
			if(left == true){
				plane2.changeSpeed(-1);
			}
			if(right == true){
				plane2.changeSpeed(1);
			}
			if(space == true){
				plane1.shoot();
			}
			if(slash == true){
				plane2.shoot();
			}
			//Move bullets, happens before planes move so they don't run into their own bullets
			for(int i = 0; i < bullets.size(); i++){
				bullets.get(i).move();
			}
			
			plane1.move();
			plane2.move();
			

			//Delay between loops
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}





	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if(code == KeyEvent.VK_W){
			if(s != true){
				w = true;
			}
		}
		if(code == KeyEvent.VK_A){
			if(d != true){
				a = true;
			}
		}
		if(code == KeyEvent.VK_S){
			if(w != true){
				s = true;
			}
		}
		if(code == KeyEvent.VK_D){
			if(a != true){
				d = true;
			}
		}
		if(code == KeyEvent.VK_SPACE){
			space = true;
		}
		if(code == KeyEvent.VK_UP){
			if(down != true){
				up = true;
			}
		}
		if(code == KeyEvent.VK_DOWN){
			if(up != true){
				down = true;
			}
		}
		if(code == KeyEvent.VK_LEFT){
			if(right != true){
				left = true;
			}
		}
		if(code == KeyEvent.VK_RIGHT){
			if(left != true){
				right = true;
			}
		}
		if(code == KeyEvent.VK_SLASH){
			slash = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
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
		if(code == KeyEvent.VK_UP){
			up = false;
		}
		if(code == KeyEvent.VK_DOWN){
			down = false;
		}
		if(code == KeyEvent.VK_LEFT){
			left = false;
		}
		if(code == KeyEvent.VK_RIGHT){
			right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//nada
	}

	
	public static void addBullet(Bullet bullet){
		bullets.add(bullet);
	}

	public void paintComponent(Graphics g){
		g.drawImage(background, 0, 0, this);

		//plane1
		plane1.draw(g, this);

		//plane2
		plane2.draw(g, this);

		if(started == true){ //If the game has been started
			for(int i = 0; i < bullets.size(); i++){
				bullets.get(i).paint(g);
			}

		}
		else{
			if(Menu.counting == true){
				g.setColor(Color.RED);
				g.setFont(new Font("Serif", Font.BOLD, 50));
				g.drawString(Menu.countdown + "", WIDTH/2 - Menu.xMinus, HEIGHT/2 - 20);
			}
		}
	}
}
