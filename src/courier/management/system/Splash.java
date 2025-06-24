package courier.management.system;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Splash extends JFrame implements ActionListener
{
    JButton clickhere1, clickhere2;
    
    Splash()
    {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("VOYAGER BLITZ");
        heading.setBounds(270,30,1200,60);
        heading.setFont(new Font("serif",Font.BOLD,60));
        heading.setForeground(Color.WHITE);
        add(heading);
        
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/front.jpg"));
        Image i2=i1.getImage().getScaledInstance(1100,700,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image= new JLabel(i3);
        image.setBounds(0,0,1100,700);
        add(image);
        
        clickhere1= new JButton("SIGN UP");
        clickhere1.setBounds(150,550,300,70);
        clickhere1.setBackground(Color.DARK_GRAY);
        clickhere1.setForeground(Color.WHITE);
        clickhere1.setFont(new Font("serif",Font.PLAIN,20));
        clickhere1.addActionListener(this);
        image.add(clickhere1);
        
        clickhere2= new JButton("LOG IN");
        clickhere2.setBounds(600,550,300,70);
        clickhere2.setBackground(Color.DARK_GRAY);
        clickhere2.setForeground(Color.WHITE);
        clickhere2.setFont(new Font("serif",Font.PLAIN,20));
        clickhere2.addActionListener(this);
        image.add(clickhere2);
        
        setSize(1100,700);
        setLocation(200,50);
        setVisible(true);
        
        while(true)
        {
            heading.setVisible(false);
            try
            {
                Thread.sleep(500);
            }
            catch(Exception e)
            {
                
            }
            
            heading.setVisible(true);
            try
            {
                Thread.sleep(500);
            }
            catch(Exception e)
            {
                
            }
        }
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource()==clickhere1)
        {
        setVisible(false);
        new Signup();
        }
        
        else if(ae.getSource()==clickhere2)
        {
            setVisible(false);
            new Login();
        }
        
    }
    public static void main(String [] args)
    {
        new Splash();
    }
}
