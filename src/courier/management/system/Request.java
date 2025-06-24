package courier.management.system;

import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class Request extends JFrame implements ActionListener {
    Random ran = new Random();
    int number = ran.nextInt(9999999);

    JTextField tfrece, tfradd, tfinst, tfcourno;
    JDateChooser dcdate;
    JComboBox cbtype;
    JLabel id;
    JButton pay, back;

    Request() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("COURIER DETAILS");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("san_serif", Font.BOLD, 25));
        add(heading);

        JLabel rece = new JLabel("Recipient Name:");
        rece.setBounds(50, 150, 150, 30);
        rece.setFont(new Font("serif", Font.PLAIN, 20));
        add(rece);

        tfrece = new JTextField();
        tfrece.setBounds(200, 150, 150, 30);
        add(tfrece);

        JLabel radd = new JLabel("Recipient's Add:");
        radd.setBounds(400, 150, 150, 30);
        radd.setFont(new Font("serif", Font.PLAIN, 20));
        add(radd);

        tfradd = new JTextField();
        tfradd.setBounds(600, 150, 150, 30);
        add(tfradd);

        JLabel type = new JLabel("Courier Type:");
        type.setBounds(50, 210, 150, 30);
        type.setFont(new Font("serif", Font.PLAIN, 20));
        add(type);

        String types[] = { " ", "Local Courier", "Global Courier", "Scheduled Courier", "Same-day Courier", "Special Courier" };
        cbtype = new JComboBox(types);
        cbtype.setBackground(Color.WHITE);
        cbtype.setBounds(200, 210, 150, 30);
        add(cbtype);

        JLabel note = new JLabel("(If Scheduled)");
        note.setBounds(400, 230, 150, 30);
        note.setFont(new Font("serif", Font.PLAIN, 15));
        add(note);

        JLabel date = new JLabel("Date of Delivery:");
        date.setBounds(400, 210, 150, 30);
        date.setFont(new Font("serif", Font.PLAIN, 20));
        add(date);

        dcdate = new JDateChooser();
        dcdate.setBounds(600, 210, 150, 30);
        add(dcdate);

        JLabel inst1 = new JLabel("Instructions:");
        inst1.setBounds(50, 270, 150, 30);
        inst1.setFont(new Font("serif", Font.PLAIN, 20));
        add(inst1);

        JLabel inst2 = new JLabel("(Optional)");
        inst2.setBounds(50, 290, 150, 30);
        inst2.setFont(new Font("serif", Font.PLAIN, 15));
        add(inst2);

        tfinst = new JTextField();
        tfinst.setBounds(200, 270, 150, 30);
        add(tfinst);

        JLabel rid = new JLabel("Request ID:");
        rid.setBounds(400, 270, 150, 30);
        rid.setFont(new Font("serif", Font.PLAIN, 20));
        add(rid);

        id = new JLabel(" " + number);
        id.setBounds(600, 270, 150, 30);
        id.setFont(new Font("serif", Font.PLAIN, 20));
        add(id);

        JLabel courno1 = new JLabel("Courier Number:");
        courno1.setBounds(50, 330, 150, 30);
        courno1.setFont(new Font("serif", Font.PLAIN, 20));
        add(courno1);

        tfcourno = new JTextField();
        tfcourno.setBounds(200, 330, 150, 30);
        add(tfcourno);

        JLabel courno2 = new JLabel("(Same as Request ID)");
        courno2.setBounds(50, 350, 150, 30);
        courno2.setFont(new Font("serif", Font.PLAIN, 15));
        add(courno2);

        pay = new JButton("PAYMENT");
        pay.setBounds(250, 430, 150, 40);
        pay.addActionListener(this);
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        add(pay);

        back = new JButton("BACK");
        back.setBounds(450, 430, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);

        setSize(900, 600);
        setLocation(300, 50);
        setVisible(true);
    }

public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == pay) {
        
        String rece=tfrece.getText();
        String radd=tfradd.getText();
        String type= (String) cbtype.getSelectedItem();
        String date= ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();
        String inst=tfinst.getText();
        String courno1=tfcourno.getText();
        
        if (!rece.matches("^[a-zA-Z ]+$")) {
                JOptionPane.showMessageDialog(this, "Receiver's Name must contain only alphabets.");
                return;
            }
            
            if (radd.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid address.");
                return;
            }

            if (!courno1.matches("^[0-9]{7}$")) {
                JOptionPane.showMessageDialog(this, "Please provide required courier number.");
                return;
            }
        
        try
        {
            Connect c=new Connect();
            String query="insert into courier values('"+rece+"','"+radd+"','"+type+"','"+date+"','"+inst+"','"+courno1+"')";
            c.s.executeUpdate(query);
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        JFrame paymentOptions = new JFrame("Payment Options");
        paymentOptions.setSize(400, 200);

        paymentOptions.setLocationRelativeTo(null);

        paymentOptions.setLayout(new FlowLayout());

        JCheckBox cashOnDelivery = new JCheckBox("Cash On Delivery");
        JCheckBox upiPayment = new JCheckBox("UPI Payment");

        JButton confirmButton = new JButton("Confirm");

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cashOnDelivery.isSelected()) {
                    
                    JOptionPane.showMessageDialog(null, "ORDER PLACED!");
                    paymentOptions.dispose();
                } else if (upiPayment.isSelected()) {
                    new Payment();
                    paymentOptions.dispose();
                }
            }
        });

        paymentOptions.add(cashOnDelivery);
        paymentOptions.add(upiPayment);
        paymentOptions.add(confirmButton);

        paymentOptions.setVisible(true);
    } else {
        setVisible(false);
        new Home();
    }
}

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new Request();
    });
}

}
