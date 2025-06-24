package courier.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class History extends JFrame implements ActionListener
{
    JTable table;
    Choice ccourno;
    JButton searchb, printb, backb;
    
    History()
    {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel search= new JLabel("Search Courier Request by Courier number");
        search.setBounds(20,20,150,20);
        add(search);
        
        ccourno= new Choice();
        ccourno.setBounds(180,20,150,20);
        add(ccourno);
        
        table= new JTable();
        
        try 
        {
            Connect c=new Connect();
            ResultSet rs= c.s.executeQuery("select * from courier");
            while(rs.next())
            {
                ccourno.add(rs.getString("courno"));
            }
        }
        
        catch (Exception e)
                {
                    e.printStackTrace();
                }
        
        try 
        {
            Connect c= new Connect();
            ResultSet rs= c.s.executeQuery("select * from courier");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
            while(rs.next())
            {
                ccourno.add(rs.getString("courno"));  
            }
        }
        
        catch (Exception e)
                {
                    e.printStackTrace();
                }
        
        JScrollPane jsp= new JScrollPane(table);
        jsp.setBounds(0,100,900,600);
        add(jsp);
        
        searchb = new JButton("Search");
        searchb.setBounds(20,70,80,20);
        searchb.addActionListener(this);
        add(searchb);
        
        printb = new JButton("Print");
        printb.setBounds(120,70,80,20);
        printb.addActionListener(this);
        add(printb);
        
        backb = new JButton("Back");
        backb.setBounds(220,70,80,20);
        backb.addActionListener(this);
        add(backb);
                    
        setSize(900,700);
        setLocation(300,100);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
      if(ae.getSource()== searchb)  
      {
          String query= "select * from courier where courno = '"+ccourno.getSelectedItem()+"'";
          try
          {
              Connect conn = new Connect();
              ResultSet rs= conn.s.executeQuery(query);
              table.setModel(DbUtils.resultSetToTableModel(rs));
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
      }
      
      else if(ae.getSource()== printb)
      {
          try
          {
              table.print();
          }
          catch(Exception e)
                  {
                    e.printStackTrace();
                  }
          
      }
      
      else
      {
          setVisible(false);
          new Home();
      }
      
    }
    
    public static void main(String [] args)
    {
        new History();
    }
}
