import java.awt.Color;
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
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public static Dogfight panel = new Dogfight();

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;

	public static final Rectangle ground = new Rectangle(0, HEIGHT - 100, WIDTH, 100); 
	private static Image background = new ImageIcon("background.png").getImage();

	private boolean w = false, a = false, s = false, d = false, space = false;
	private boolean up = false, down = false, left = false, right = false, shift = false;


	public static void main(String[] args){
		setup();
	}

	@Override
	public void keyPressed(KeyEvent e) {
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
	public void keyTyped(KeyEvent e) {
		//nada
	}

	//Creates the window
	public static void setup(){

		//Setting up frame, panel, and listener
		background = background.getScaledInstance(WIDTH, HEIGHT, 100);
		JFrame frame = new JFrame("Dogfight!");
		frame.setBounds(200, 100, 1000, 700);
		frame.getContentPane().add(panel);
		panel.setBackground(Color.WHITE);
		frame.addKeyListener(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

		//Instantiating airplanes, later will add a selection to this
		plane1 = new Airplane(new ImageIcon("spitfire.png").getImage(), true);
		plane2 = new Airplane(new ImageIcon("zero.png").getImage(), false);
		panel.add(plane1);
		panel.add(plane2);
	}

	public void paintComponent(Graphics g){
		g.drawImage(background, 0, 0, this);
		plane1.paint(g);
		plane2.paint(g);
		for(int i = 0; i < bullets.size(); i++){
			bullets.get(i).paint(g);
		}
	}
}
