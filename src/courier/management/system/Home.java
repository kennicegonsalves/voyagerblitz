package courier.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Home extends JFrame implements ActionListener
{
    JButton request,history,feed;
    Home()
    {
        setLayout(null);
        
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image i2=i1.getImage().getScaledInstance(1100,700,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image= new JLabel(i3);
        image.setBounds(0,0,1100,700);
        add(image);
        
        JLabel heading=new JLabel("VOYAGER BLITZ");
        heading.setBounds(400,180,500,40);
        heading.setFont(new Font("serif",Font.BOLD,30));
        heading.setForeground(Color.WHITE);
        image.add(heading);
        
        request= new JButton("COURIER REQUEST");
        request.setBounds(400,240,250,40);
        request.addActionListener(this);
        image.add(request);
        
        history= new JButton("ORDER HISTORY");
        history.setBounds(400,300,250,40);
        history.addActionListener(this);
        image.add(history);
        
        feed= new JButton("FEEDBACK");
        feed.setBounds(400,360,250,40);
        feed.addActionListener(this);
        image.add(feed);
        
        setSize(1100,700);
        setLocation(200,60);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==request)
        {
            setVisible(false);
            new Request();
        }
        else if(ae.getSource()==feed)
        {
            setVisible(false);
            new Feedback();
        }
        else
        {
            setVisible(false);
            new History();
        }
    }
    
    public static void main(String[] args)
    {
        new Home();
    }
}
