package courier.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gpay extends JFrame {
    Gpay() {
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int frameWidth = 600;
        int frameHeight = 650;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        setBounds(x, y, frameWidth, frameHeight);

        ImageIcon qrCodeImage = new ImageIcon(ClassLoader.getSystemResource("icons/qrcode.jpeg"));
        JLabel qrCodeLabel = new JLabel(qrCodeImage);

        int qrCodeSize = 535;
        int qrCodeX = (frameWidth - qrCodeSize) / 2;
        int qrCodeY = (frameHeight - qrCodeSize - 100) / 2;
        qrCodeLabel.setBounds(qrCodeX, qrCodeY, qrCodeSize, qrCodeSize);
        add(qrCodeLabel);

        JButton backButton = new JButton("Done!");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                JOptionPane.showMessageDialog(null, "ORDER PLACED!", "Order Status", JOptionPane.INFORMATION_MESSAGE);
                new Home();
                dispose(); 
            }
        });

        int buttonWidth = 80;
        int buttonHeight = 40;
        int buttonX = (frameWidth - buttonWidth) / 2;
        int buttonY = qrCodeY + qrCodeSize + 20;
        backButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        add(backButton);

        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Gpay();
    }
}
