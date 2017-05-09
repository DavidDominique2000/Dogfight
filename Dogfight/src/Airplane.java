import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Airplane extends JPanel{

	private Image plane;
	private boolean isP1;
	private int x, y;
	private int speed;
	//private int bombs = 3;
	
	
	public Airplane(Image plane, boolean isP1){
		this.plane = plane;
		plane = plane.getScaledInstance(100, 50, 100);
		this.isP1 = isP1;
		if(isP1 == true){
			x = 0;
			y = 100;
		}
		else{
			x = 1000;//Change this to match size of window
			y = 300;
		}
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(plane, x, y, this);
	}
}
