package travel.tourism;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JFrame {

    Login(){
        setSize(800,400);
        setLocation(400,200);
        setLayout(null);
        getContentPane().setBackground(new Color(211, 211, 211));
        
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(0,0,350,400);
        p1.setBackground(new Color(131,193,233));
        add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(75,100,200,200);
        p1.add(image);

        JLabel jlusername = new JLabel("Username");
        jlusername.setBounds(400,50,100,30);
        jlusername.setFont(new Font("San_serif",Font.PLAIN , 20));
        add(jlusername);
        JTextField tfusername = new JTextField();
        tfusername.setBounds(400,85,300,30);
        tfusername.setBorder(BorderFactory.createEmptyBorder());
        add(tfusername);

        JLabel jlpassword = new JLabel("Password");
        jlpassword.setBounds(400,120,100,30);
        jlpassword.setFont(new Font("San_serif",Font.PLAIN , 20));
        add(jlpassword);
        JTextField tfpassword = new JTextField();
        tfpassword.setBounds(400,155,300,30);
        tfpassword.setBorder(null);
        add(tfpassword);

        JButton jblogin = new JButton("Login");
        jblogin.setBounds(435,220,100,30);
        jblogin.setBackground(new Color(131,193,233));
        jblogin.setForeground(Color.white);
        jblogin.setBorder(null);
        add(jblogin);

        JButton jbsignup = new JButton("Sign Up");
        jbsignup.setBounds(565,220,100,30);
        jbsignup.setBackground(new Color(131,193,233));
        jbsignup.setForeground(Color.white);
        jbsignup.setBorder(null);
        add(jbsignup);
        
        JButton jbforgot = new JButton("Forgot Password");
        jbforgot.setBounds(490,260,120,30);
        jbforgot.setBackground(new Color(131,193,233));
        jbforgot.setForeground(Color.white);
        jbforgot.setBorder(null);
        add(jbforgot);

        setVisible(true);   

    }
    public static void main(String[] args) {
        new Login();
    }
}
