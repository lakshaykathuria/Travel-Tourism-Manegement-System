package travel.tourism;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.ScatteringByteChannel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    
    JButton jblogin, jbsignup, jbforgot;
    JTextField tfusername;
    JPasswordField tfpassword;
    Connect con = new Connect();
    ResultSet resultSet = null;
    String name;


    Login() {
        setSize(800, 400);
        setLocation(400, 200);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(new Color(211, 211, 211));
        
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(0, 0, 350, 400);
        p1.setBackground(new Color(131, 193, 233));
        add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(75, 100, 200, 200);
        p1.add(image);

        JLabel jlusername = new JLabel("Username");
        jlusername.setBounds(400, 50, 100, 30);
        jlusername.setFont(new Font("San_serif", Font.PLAIN, 20));
        add(jlusername);
        
        tfusername = new JTextField();
        tfusername.setBounds(400, 85, 300, 30);
        tfusername.setBorder(BorderFactory.createEmptyBorder());
        add(tfusername);

        JLabel jlpassword = new JLabel("Password");
        jlpassword.setBounds(400, 120, 100, 30);
        jlpassword.setFont(new Font("San_serif", Font.PLAIN, 20));
        add(jlpassword);
        
        tfpassword = new JPasswordField();
        tfpassword.setBounds(400, 155, 300, 30);
        tfpassword.setBorder(BorderFactory.createEmptyBorder());
        add(tfpassword);

        jblogin = new JButton("Login");
        jblogin.setBounds(435, 220, 100, 30);
        jblogin.setBackground(new Color(131, 193, 233));
        jblogin.setForeground(Color.white);
        jblogin.setBorder(null);
        add(jblogin);
        jblogin.addActionListener(this);

        jbsignup = new JButton("Sign Up");
        jbsignup.setBounds(565, 220, 100, 30);
        jbsignup.setBackground(new Color(131, 193, 233));
        jbsignup.setForeground(Color.white);
        jbsignup.setBorder(null);
        add(jbsignup);
        jbsignup.addActionListener(this);

        jbforgot = new JButton("Forgot Password");
        jbforgot.setBounds(490, 260, 120, 30);
        jbforgot.setBackground(new Color(131, 193, 233));
        jbforgot.setForeground(Color.white);
        jbforgot.setBorder(null);
        add(jbforgot);
        jbforgot.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == jblogin) {
            try {
                String username = tfusername.getText();
                String password = new String(tfpassword.getPassword());

                String query = "SELECT * FROM signup WHERE username=? AND password=?";
                PreparedStatement ps = con.connect.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);

                resultSet = ps.executeQuery();
//                name= resultSet.getString("NAME");
//                System.out.println(name);
                if (resultSet.next()) {
                    System.out.println("Login successful");
                    name= resultSet.getString("NAME");
                    setVisible(false); // Hide login frame
                    SwingUtilities.invokeLater(() -> new Loading(name).setVisible(true));
                } else {
                    System.out.println("Invalid login credentials");
                    JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == jbsignup) {
            setVisible(false);
            new SignUp();
        } else if (ae.getSource() == jbforgot) {
            setVisible(false);
            new ForgotPassword();
        } 
    }

    public static void main(String[] args) {
        new Login();
    }
}
