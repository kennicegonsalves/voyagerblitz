package courier.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener
{
    JTextField tfuser;
    JPasswordField passwordField;
    JButton showPasswordButton;

    Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel user = new JLabel("USERNAME:");
        user.setBounds(40, 20, 100, 30);
        add(user);

        tfuser = new JTextField();
        tfuser.setBounds(150, 20, 150, 30);
        add(tfuser);

        JLabel password = new JLabel("Password:");
        password.setBounds(40, 60, 100, 30);
        add(password);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 60, 150, 30);
        add(passwordField);

        showPasswordButton = new JButton("Show");
        showPasswordButton.setBounds(310, 60, 80, 30);
        showPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPasswordButton.getText().equals("Show")) {
                    showPasswordButton.setText("Hide");
                    passwordField.setEchoChar((char) 0);
                } else {
                    showPasswordButton.setText("Show");
                    passwordField.setEchoChar('*');
                }
            }
        });
        add(showPasswordButton);

        JButton login = new JButton("LOGIN");
        login.setBounds(200, 100, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.setFont(new Font("serif", Font.PLAIN, 15));
        login.addActionListener(this);
        add(login);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 250, 200);
        add(image);

        setSize(600, 200);
        setLocation(450, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String username = tfuser.getText();
            String password = new String(passwordField.getPassword());

            Connect c = new Connect();
            String query = "select * from login where username= '" + username + "' and password = '" + password + "'";

            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "LOGIN SUCCESSFUL!");
                setVisible(false);
                new Home();
            } else {
                JOptionPane.showMessageDialog(null, "*INVALID USERNAME OR PASSWORD*");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
