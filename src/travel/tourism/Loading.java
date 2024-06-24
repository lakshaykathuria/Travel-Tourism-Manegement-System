package travel.tourism;

import javax.swing.*;
import java.awt.*;

public class Loading extends JFrame implements Runnable {
    JProgressBar bar;
    Thread th;

    Loading(String username) {
        th = new Thread(this);

        setBounds(500, 200, 650, 400);
        setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);
        setResizable(false);


        JLabel head = new JLabel("Kathuria's ");
        head.setFont(new Font("SansSerif", Font.BOLD, 30));
        head.setBounds(275, 40, 300, 30);
        head.setForeground(Color.BLUE);
        add(head);

        JLabel head1 = new JLabel("Travel and Tourism");
        head1.setFont(new Font("SansSerif", Font.BOLD, 30));
        head1.setBounds(200, 80, 300, 30);
        head1.setForeground(Color.BLUE);
        add(head1);

        bar = new JProgressBar();
        bar.setBounds(180, 200, 300, 25);
        bar.setStringPainted(true);
        add(bar);
        th.start();

        JLabel loading = new JLabel("Loading Please Wait.....");
        loading.setBounds(200, 250, 200, 20);
        add(loading);

        JLabel welcome = new JLabel("Welcome " + username);  // Set the username in the welcome message
        welcome.setBounds(10, 350, 300, 15);
        add(welcome);
    }

    public void run() {
        try {
            for (int i = 0; i < 200; i++) {
                int m = bar.getMaximum();
                int v = bar.getValue();
                if (v < m) {
                    bar.setValue(bar.getValue() + 1);
                } else {
                    i = 201;
                    setVisible(false);
//                    new Home(username).setVisible(true);
                }
                Thread.sleep(50);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String username = "defaultUser";  // Default username for testing
        Loading l = new Loading(username);
        l.setVisible(true);
    }
}
