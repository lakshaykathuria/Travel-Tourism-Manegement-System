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

public class SignUp extends JFrame implements ActionListener{

    JButton jbback,jbcreate;
    JTextField tfusername,tfname, tfans;
    JPasswordField jpassword;
    JComboBox<String> ques;


    SignUp(String title){
        super(title);
        setSize(900,450);
        setLocation(350,200);
        setLayout(null);
        
        getContentPane().setBackground(Color.white);
        
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(0,0,550,450);
        p1.setBackground(new Color(131,193,233));
        add(p1);
        
        JLabel jlusername = new JLabel("Username");
        jlusername.setBounds(70,50,150,30);
        jlusername.setFont(new Font("San_serif",Font.BOLD , 17));
        p1.add(jlusername);
        tfusername = new JTextField();
        tfusername.setBounds(230,50,220,30);
        tfusername.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfusername);
        
        JLabel jlname = new JLabel("Name");
        jlname.setBounds(70,100,150,30);
        jlname.setFont(new Font("San_serif",Font.BOLD , 17));
        p1.add(jlname);
        tfname = new JTextField();
        tfname.setBounds(230,100,220,30);
        tfname.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfname);

        JLabel jlpassword = new JLabel("Password");
        jlpassword.setBounds(70,150,150,30);
        jlpassword.setFont(new Font("San_serif",Font.BOLD , 17));
        p1.add(jlpassword);
        jpassword = new JPasswordField();
        jpassword.setBounds(230,150,220,30);
        jpassword.setBorder(null);
        p1.add(jpassword);
        
        JLabel jlques = new JLabel("Security Question");
        jlques.setBounds(70,200,150,30);
        jlques.setFont(new Font("San_serif",Font.BOLD , 17));
        p1.add(jlques);
        String items[] = {"What is your mother's name?", "What is your first pet's name?","What is your favorite color?"}; 
        ques = new JComboBox<>(items);
        ques.setBounds(230,200,220,30);
        ques.setPreferredSize(new Dimension(200, 25));
        p1.add(ques);
        
        JLabel jlans = new JLabel("Answer");
        jlans.setBounds(70,250,150,30);
        jlans.setFont(new Font("San_serif",Font.BOLD , 17));
        p1.add(jlans);
        tfans = new JTextField();
        tfans.setBounds(230,250,220,30);
        tfans.setBorder(null);
        p1.add(tfans);

        jbcreate = new JButton("Create");
        jbcreate.setBounds(100,320,120,30);
        jbcreate.setForeground(new Color(131,193,233));
        jbcreate.setBackground(Color.white);
        jbcreate.setFont(new Font("San_serif",Font.BOLD , 17));
        jbcreate.setBorder(null);
        p1.add(jbcreate);
        jbcreate.addActionListener(this);

        jbback = new JButton("Back");
        jbback.setBounds(300,320,120,30);
        jbback.setForeground(new Color(131,193,233));
        jbback.setBackground(Color.white);
        jbback.setFont(new Font("San_serif",Font.BOLD , 17));
        jbback.setBorder(null);
        p1.add(jbback);
        jbback.addActionListener(this);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/signup.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(620,100,200,200);
        add(image);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            connect con = new connect();

        if(ae.getSource()==jbcreate){
            String username = tfusername.getText();
            String name = tfname.getText();
            String password = jpassword.getText();
            String security = (String)ques.getSelectedItem();
            String ans = tfans.getText();

            PreparedStatement ps = con.conn.prepareStatement("INSERT INTO signup VALUES (?,?,?,?,?);" );

            ps.setString(1, username);
            ps.setString(2, name);
            ps.setString(3, password);
            ps.setString(4, security);
            ps.setString(5, ans);

       
            
            int executeUpdate = ps.executeUpdate();
            
            if(executeUpdate!=0)
            {
                JOptionPane.showMessageDialog(null, "Account created Successfully");
                setVisible(false);
                new Login();
            }
            else
            {
              JOptionPane.showConfirmDialog(null, "Error");
            }
            
        }

        else {
            setVisible(false);
            new Login();
        } 
        } catch (Exception e) {
            // TODO: handle exception
        
        }
        
    }

    public static void main(String[] args) {
        SignUp s = new SignUp("Sign Up");
        s.setVisible(true);   
    }
}
