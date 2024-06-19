package travel.tourism;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ForgotPassword extends JFrame implements ActionListener {

	JButton jbback, jbcreate;
    JTextField tfusername, tfname, tfans;
    JPasswordField jpassword;
    JComboBox<String> ques;

    ForgotPassword() {
        setSize(900, 450);
        setLocation(350, 200);
        setLayout(null);

        getContentPane().setBackground(Color.white);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(20, 20, 600, 370);
        p1.setBackground(new Color(85, 173, 248));
        add(p1);

        JLabel jlusername = new JLabel("Username");
        jlusername.setBounds(50, 50, 150, 30);
        jlusername.setFont(new Font("San_serif", Font.BOLD, 17));
        p1.add(jlusername);
        tfusername = new JTextField();
        tfusername.setBounds(210, 50, 220, 30);
        tfusername.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfusername);

        JLabel jlname = new JLabel("Name");
        jlname.setBounds(50, 100, 150, 30);
        jlname.setFont(new Font("San_serif", Font.BOLD, 17));
        p1.add(jlname);
        tfname = new JTextField();
        tfname.setBounds(210, 100, 220, 30);
        tfname.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfname);

        JLabel jlpassword = new JLabel("Password");
        jlpassword.setBounds(50, 150, 150, 30);
        jlpassword.setFont(new Font("San_serif", Font.BOLD, 17));
        p1.add(jlpassword);
        jpassword = new JPasswordField();
        jpassword.setBounds(210, 150, 220, 30);
        jpassword.setBorder(null);
        p1.add(jpassword);

        JLabel jlques = new JLabel("Security Question");
        jlques.setBounds(50, 200, 150, 30);
        jlques.setFont(new Font("San_serif", Font.BOLD, 17));
        p1.add(jlques);
        String items[] = {"What is your mother's name?", "What is your first pet's name?", "What is your favorite color?"};
        ques = new JComboBox<>(items);
        ques.setBounds(210, 200, 220, 30);
        ques.setPreferredSize(new Dimension(200, 25));
        p1.add(ques);

        JLabel jlans = new JLabel("Answer");
        jlans.setBounds(50, 250, 150, 30);
        jlans.setFont(new Font("San_serif", Font.BOLD, 17));
        p1.add(jlans);
        tfans = new JTextField();
        tfans.setBounds(210, 250, 220, 30);
        tfans.setBorder(null);
        p1.add(tfans);

        jbcreate = new JButton("Create");
        jbcreate.setBounds(100, 320, 120, 30);
        jbcreate.setForeground(new Color(131, 193, 233));
        jbcreate.setBackground(Color.white);
        jbcreate.setFont(new Font("San_serif", Font.BOLD, 17));
        jbcreate.setBorder(null);
        p1.add(jbcreate);
        jbcreate.addActionListener(this);

        jbback = new JButton("Back");
        jbback.setBounds(300, 320, 120, 30);
        jbback.setForeground(new Color(131, 193, 233));
        jbback.setBackground(Color.white);
        jbback.setFont(new Font("San_serif", Font.BOLD, 17));
        jbback.setBorder(null);
        p1.add(jbback);
        jbback.addActionListener(this);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/forgotpassword.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(660, 100, 200, 200);
        add(image);

        setVisible(true); // Make the window visible
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            connect con = new connect();

            if (ae.getSource() == jbcreate) {
                String username = tfusername.getText();
                String name = tfname.getText();
                String password = new String(jpassword.getPassword()); // Use getPassword() for JPasswordField
                String security = (String) ques.getSelectedItem();
                String ans = tfans.getText();

                PreparedStatement ps = con.conn.prepareStatement("INSERT INTO signup VALUES (?, ?, ?, ?, ?);");

                ps.setString(1, username);
                ps.setString(2, name);
                ps.setString(3, password);
                ps.setString(4, security);
                ps.setString(5, ans);

                int executeUpdate = ps.executeUpdate();

                if (executeUpdate != 0) {
                    JOptionPane.showMessageDialog(null, "Account created Successfully");
                    setVisible(false);
                    new Login();
                } else {
                    JOptionPane.showMessageDialog(null, "Error");
                }

            } else if (ae.getSource() == jbback) {
                setVisible(false);
                new Login();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ForgotPassword();
    }
}
