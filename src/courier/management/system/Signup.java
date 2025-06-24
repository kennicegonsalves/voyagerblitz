package courier.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.*;

public class Signup extends JFrame implements ActionListener {
    
    JTextField tfuser, tfName, tfAddress, tfPhone, tfEmail;
    JPasswordField passwordField;
    JButton showPasswordButton;

    Signup() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel fname= new JLabel("Name:");
        fname.setBounds(40, 20, 100, 30);
        add(fname);

        tfName = new JTextField();
        tfName.setBounds(150, 20, 200, 30);
        add(tfName);

        JLabel username= new JLabel("Username:");
        username.setBounds(40, 60, 100, 30);
        add(username);

        tfuser = new JTextField();
        tfuser.setBounds(150, 60, 200, 30);
        add(tfuser);

        JLabel password= new JLabel("Password:");
        password.setBounds(40, 100, 100, 30);
        add(password);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 30);
        add(passwordField);

        showPasswordButton = new JButton("Show");
        showPasswordButton.setBounds(310, 100, 120, 30);
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

        JLabel addr= new JLabel("Address:");
        addr.setBounds(40, 140, 100, 30);
        add(addr);

        tfAddress = new JTextField();
        tfAddress.setBounds(150, 140, 200, 30);
        add(tfAddress);

        JLabel phone= new JLabel("Phone Number:");
        phone.setBounds(40, 180, 100, 30);
        add(phone);

        tfPhone = new JTextField();
        tfPhone.setBounds(150, 180, 200, 30);
        add(tfPhone);

        JLabel email= new JLabel("Email:");
        email.setBounds(40, 220, 100, 30);
        add(email);

        tfEmail = new JTextField();
        tfEmail.setBounds(150, 220, 200, 30);
        add(tfEmail);

        JButton signup = new JButton("SIGN UP");
        signup.setBounds(150, 260, 100, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.setFont(new Font("serif", Font.PLAIN, 15));
        signup.addActionListener(this);
        add(signup);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/signup.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(250, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 0, 300, 300);
        add(image);

        setSize(700, 350);
        setLocation(450, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String fname= tfName.getText();
            String username = tfuser.getText();
            String password = new String(passwordField.getPassword()); 
            String addr = tfAddress.getText();
            String phone = tfPhone.getText();
            String email = tfEmail.getText();

            if (addr.trim().isEmpty()) {
               JOptionPane.showMessageDialog(null, "Please enter a valid address.");
               return;
            }

            
           
            if (!fname.matches("^[a-zA-Z ]+$")) {
                JOptionPane.showMessageDialog(null, "Name must contain only alphabets.");
                return;
            }

            
            if (!phone.matches("^[0-9]{10}$")) {
                JOptionPane.showMessageDialog(null, "Phone number must contain 10 digits.");
                return;
            }

            
            if (!email.contains("@")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid email.");
                return;
                
            }

            
            if (username.length() < 8 || username.matches("^[0-9]+$")) {
               JOptionPane.showMessageDialog(null, "Username must be at least 8 characters long and not contain only numbers.");
               return;
            }


            
            if (password.length() < 8) {
               JOptionPane.showMessageDialog(null, "Password is weak. It must be at least 8 characters long.");
            } else if (!Pattern.compile("[A-Z]").matcher(password).find()) {
               JOptionPane.showMessageDialog(null, "Password is weak. It must contain at least one capital letter.");
            } else if (!Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]+").matcher(password).find()) {
              JOptionPane.showMessageDialog(null, "Password is weak. It must contain at least one special character.");
            } 
            
            else {

            Connect c = new Connect();

            String query = "select * from login where username='" + username + "'";
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Username already exists.");
            } else {
                String insert = "insert into login values('" + username + "','" + password + "','" + fname + "','" + addr + "','" + phone + "','" + email + "')";
                c.s.executeUpdate(insert);
                
                JOptionPane.showMessageDialog(null, "Signed up!");
                new Login();
                setVisible(false);
            }
            
}
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        new Signup();
    }
}
