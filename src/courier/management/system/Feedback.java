package courier.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Feedback extends Frame implements ActionListener {
    
    private Connect databaseConnection = new Connect();
    
    Label lscourno, lsgender, lsfeedback, lsOrderReceived;
    Choice orderReceivedChoice;
    CheckboxGroup gender;
    Checkbox male, female, other;
    Choice courierNumberDropdown; 
    Button submit, backButton;
    TextField tffeedback;
    TextArea display_details;

    Feedback() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        int frameWidth = 500;
        int frameHeight = 430;

        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        setLocation(x, y);

        lscourno = new Label("Courier No: ");
        lsgender = new Label("Gender: ");
        lsfeedback = new Label("Feedback: ");
        lsOrderReceived = new Label("Order Received On Time?");

        orderReceivedChoice = new Choice();
        orderReceivedChoice.add("Yes");
        orderReceivedChoice.add("No");

        gender = new CheckboxGroup();
        male = new Checkbox("Male", gender, false);
        female = new Checkbox("Female", gender, false);
        other = new Checkbox("Other", gender, false);

        courierNumberDropdown = new Choice();
        loadCourierNumbers(); 
        
        tffeedback = new TextField();
        display_details = new TextArea("", 2, 100, TextArea.SCROLLBARS_NONE);

        submit = new Button("Submit");
        backButton = new Button("Back");

        lscourno.setBounds(10, 30, 100, 20);
        courierNumberDropdown.setBounds(120, 30, 150, 20);
        lsgender.setBounds(10, 60, 100, 20);
        male.setBounds(120, 60, 50, 20);
        female.setBounds(180, 60, 60, 20);
        other.setBounds(240, 60, 60, 20);
        lsOrderReceived.setBounds(10, 90, 150, 20);
        orderReceivedChoice.setBounds(180, 90, 70, 20);
        lsfeedback.setBounds(10, 120, 70, 20);
        tffeedback.setBounds(90, 120, 360, 70);
        display_details.setBounds(10, 200, 480, 120);

        submit.setBounds(10, 330, 220, 30);
        backButton.setBounds(240, 330, 220, 30);

        add(lscourno);
        add(courierNumberDropdown);
        add(lsgender);
        add(male);
        add(female);
        add(other);
        add(lsOrderReceived);
        add(orderReceivedChoice);
        add(lsfeedback);
        add(tffeedback);
        add(display_details);
        add(submit);
        add(backButton);

        submit.addActionListener(this);
        backButton.addActionListener(this);

        setTitle("FEEDBACK");
        setSize(500, 430);
        setLayout(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        databaseConnection = new Connect();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String orderReceived = orderReceivedChoice.getSelectedItem();
            String feedback = tffeedback.getText();
            String courno = courierNumberDropdown.getSelectedItem();
            String genderSelected = getSelectedGender();

            if (feedback.trim().isEmpty()) {
                showMessageDialog("Feedback cannot be blank.");
                return;
            }

            try {
                String insertSQL = "INSERT INTO feedback (courier_no, order_received, gender, feedback) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = databaseConnection.c.prepareStatement(insertSQL);

                preparedStatement.setString(1, courno);
                preparedStatement.setString(2, orderReceived);
                preparedStatement.setString(3, genderSelected);
                preparedStatement.setString(4, feedback);

                preparedStatement.executeUpdate();
                preparedStatement.close();

                String feedbackDetails = " ***** Courier Management System Feedback ***** \n"
                        + "Order No. :" + courno + "\n Order Received On Time? : " + orderReceived
                        + "\n Gender : " + genderSelected + "\n Feedback : " + feedback;
                display_details.setText(feedbackDetails);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            new Home();
            dispose();
        }
    }

    private void loadCourierNumbers() {
        try {
            Statement statement = databaseConnection.c.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT courno FROM courier");

            while (resultSet.next()) {
                String courno = resultSet.getString("courno");
                courierNumberDropdown.add(courno);
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private String getSelectedGender() {
        if (male.getState()) {
            return male.getLabel();
        } else if (female.getState()) {
            return female.getLabel();
        } else if (other.getState()) {
            return other.getLabel();
        }
        return "N/A";
    }

    private void showMessageDialog(String message) {
        Dialog errorDialog = new Dialog(this, "Error", true);
        errorDialog.setLayout(new FlowLayout());
        Label errorMessage = new Label(message);
        Button okButton = new Button("OK");
        errorDialog.add(errorMessage);
        errorDialog.add(okButton);
        errorDialog.setSize(300, 100);
        errorDialog.setLocationRelativeTo(this);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                errorDialog.setVisible(false);
            }
        });
        errorDialog.setVisible(true);
    }

    public static void main(String[] args) {
        new Feedback();
    }
}
