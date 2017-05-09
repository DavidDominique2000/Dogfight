import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

public class Airplane extends JPanel{

	private Image plane;
	private boolean isP1 = true;
	private int speed = 6;
	private int x, y;
	private int angle, angleChange = 0;
	private int health;
	//private int bombs = 3;


	public Airplane(Image plane, boolean isP1){
		this.plane = plane;
		plane = plane.getScaledInstance(100, 50, 100);
		this.isP1 = isP1;
		if(isP1 == true){
			x = 0;
			y = 100;
			angle = 0;
		}
		else{
			x = 1000;//Change this to match size of window
			y = 300;
			angle = 180;
		}
		angleChange = 0;
	}

	public void changeSpeed(int change){
		if(speed + change <= 8 && speed + change > 2){
			speed += change;
		}
	}

	public void move(){
		while(angle > 360){
			angle -= 360;
		}
		while(angle < -360){
			angle += 360;
		}
		if(angle >= 0 && angle < 90){
			y += Math.sin(Math.toRadians(angle)) * speed;
			x += Math.cos(Math.toRadians(angle))  * speed;
		}
		else if(angle >= 90 && angle < 180){
			y -= Math.sin(Math.toRadians(angle)) * speed;
			x += Math.cos(Math.toRadians(angle))  * speed;
		}
		else if(angle >= 180 && angle < 270){
			y += Math.sin(Math.toRadians(angle)) * speed;
			x += Math.cos(Math.toRadians(angle))  * speed;
		}
		else if(angle >= 270 && angle < 360){
			y += Math.sin(Math.toRadians(angle)) * speed;
			x -= Math.cos(Math.toRadians(angle))  * speed;
		}
		repaint();
	}

	public void shoot(){
		Bullet bullet = new Bullet(x + 10, y + 20, angle);
		Dogfight.bullets.add(bullet);
	}

	public void setAngle(int angle){
		angleChange = this.angle - angle;
		this.angle = angle;
	}

	public void setSpeed(int speed){
		this.speed = speed;
	}

	public void rotate(int amount){
		angleChange = amount;
	}

	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		if(angleChange != 0){
			g2d.rotate(Math.toRadians(angleChange));
			angleChange = 0;
		}
		if(isP1 == false){
			g.drawImage(plane, -x, y, null);
		}
		else{
			g.drawImage(plane, x, y, null);
		}
	}
}
