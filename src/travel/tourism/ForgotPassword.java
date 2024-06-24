package travel.tourism;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class ForgotPassword extends JFrame implements ActionListener {

	JButton jbback, jbsearch, jbretrieve;
    JTextField tfusername, tfname, tfans, tfpassword;
    JComboBox<String> ques;
    ResultSet resultSet = null;
    String getname,getpassword;

    ForgotPassword() {
        super.setTitle("Forgot Password");
        setSize(900, 450);
        setLocation(350, 200);
        setLayout(null);
        setResizable(false);


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

        jbsearch = new JButton("Search");
        jbsearch.setBounds(450, 50, 120, 30);
        jbsearch.setForeground(new Color(131, 193, 233));
        jbsearch.setBackground(Color.white);
        jbsearch.setFont(new Font("San_serif", Font.BOLD, 17));
        jbsearch.setBorder(null);
        p1.add(jbsearch);
        jbsearch.addActionListener(this);

        JLabel jlname = new JLabel("Name");
        jlname.setBounds(50, 100, 150, 30);
        jlname.setFont(new Font("San_serif", Font.BOLD, 17));
        p1.add(jlname);
        tfname = new JTextField();
        tfname.setBounds(210, 100, 220, 30);
        tfname.setBorder(BorderFactory.createEmptyBorder());
        tfname.setEditable(false);
        p1.add(tfname);

        JLabel jlques = new JLabel("Security Question");
        jlques.setBounds(50, 150, 150, 30);
        jlques.setFont(new Font("San_serif", Font.BOLD, 17));
        p1.add(jlques);
        String items[] = {"What is your mother's name?", "What is your first pet's name?", "What is your favorite color?"};
        ques = new JComboBox<>(items);
        ques.setBounds(210, 150, 220, 30);
        ques.setPreferredSize(new Dimension(200, 25));
        p1.add(ques);

        JLabel jlans = new JLabel("Answer");
        jlans.setBounds(50, 200, 150, 30);
        jlans.setFont(new Font("San_serif", Font.BOLD, 17));
        p1.add(jlans);
        tfans = new JTextField();
        tfans.setBounds(210, 200, 220, 30);
        tfans.setBorder(null);
        p1.add(tfans);

        jbretrieve = new JButton("Retrieve");
        jbretrieve.setBounds(450, 200, 120, 30);
        jbretrieve.setForeground(new Color(131, 193, 233));
        jbretrieve.setBackground(Color.white);
        jbretrieve.setFont(new Font("San_serif", Font.BOLD, 17));
        jbretrieve.setBorder(null);
        p1.add(jbretrieve);
        jbretrieve.addActionListener(this);

        JLabel jlpassword = new JLabel("Password");
        jlpassword.setBounds(50, 250, 150, 30);
        jlpassword.setFont(new Font("San_serif", Font.BOLD, 17));
        p1.add(jlpassword);
        tfpassword = new JTextField();
        tfpassword.setBounds(210, 250, 220, 30);
        tfpassword.setBorder(null);
        p1.add(tfpassword);

        jbback = new JButton("Back");
        jbback.setBounds(190, 320, 120, 30);
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
            Connect con = new Connect();

            if (ae.getSource() == jbsearch) {
                String username = tfusername.getText();

                String query = "SELECT NAME FROM signup WHERE username=?; ";
                PreparedStatement ps = con.connect.prepareStatement(query);
                ps.setString(1, username);
                System.out.println();
                resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    getname = resultSet.getString("NAME");
                    tfname.setText(getname);

                } else {
                    JOptionPane.showMessageDialog(null, "Error");
                }

            }
            else if (ae.getSource() == jbretrieve){
                String username = tfusername.getText();
                String name = tfname.getText();
                String security = (String)ques.getSelectedItem();
                String ans = tfans.getText();


                String query = "SELECT password FROM signup WHERE username=? AND name=? AND question=? AND answer=?";
                PreparedStatement ps = con.connect.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, name);
                ps.setString(3, security);
                ps.setString(4, ans);
                resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    getpassword = resultSet.getString("password");
                    tfpassword.setText(getpassword);

                } else {
                    JOptionPane.showMessageDialog(null, "Error");
                }


            }else if (ae.getSource() == jbback) {
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
