
package travel.tourism;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Main extends JFrame implements Runnable{
    Thread thread;
    Main() {

        setSize(380,375);
        setLocation(600,250);
        setUndecorated(true);
        setShape(new Ellipse2D.Double(-1,-1,380,375));
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/splash.png"));
        Image i2 = i1.getImage().getScaledInstance(400,400,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        setVisible(true);
        thread = new Thread(this);
        thread.start();


    }

    @Override
    public void run() {
        try {
            thread.sleep(2500);

            setVisible(false);
            new Login();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] var0) {
        new Main();
    }


}
