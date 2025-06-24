package courier.management.system;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Payment extends JFrame {

    public Payment() {
        setLayout(null);
        setBounds(300, 150, 800, 600); 

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/gpay.png"));
        Image i8 = i7.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l4 = new JLabel(i9);
        l4.setBounds(0, 100, 800, 400);
        add(l4);

        JButton payButton = new JButton("Pay");
        payButton.setBackground(Color.DARK_GRAY);
        payButton.setForeground(Color.WHITE);
        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new Gpay();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        int buttonWidth = 80;
        int centerX = (getWidth() - buttonWidth) / 2;
        payButton.setBounds(centerX, 20, buttonWidth, 40);
        add(payButton);

        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                new Request();
            }
        });
        
        backButton.setBounds(centerX, 70, buttonWidth, 40);
        add(backButton);

        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Payment().setVisible(true);
    }
}
