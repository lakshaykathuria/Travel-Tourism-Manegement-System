package travel.tourism;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Splash extends JFrame implements Runnable{
	Thread thread;  
	Splash(){
		
		setSize(380,375);
		setLocation(600, 250);
		setUndecorated(true);
		setShape(new java.awt.geom.Ellipse2D.Double(-1, -1, 380, 375));
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/splash.png"));
		Image i2 = i1.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);

		JLabel image = new JLabel(i3);
		add(image);
		
		setVisible(true);
		thread = new Thread(this);
		thread.start();
 
	}

	public void run(){
		try {
			thread.sleep(2500);

			setVisible(false);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void main(String[] args) {
		new Splash();
	}

}
