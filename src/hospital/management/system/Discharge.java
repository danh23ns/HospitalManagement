package hospital.management.system;

import com.mysql.cj.jdbc.result.ResultSetImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Discharge extends JFrame {
    private JButton discharge;
    Discharge(){
        JPanel panel = new JPanel();
        panel.setBounds(5,5, 790, 390);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("Check-Out");
        label.setBounds(100, 20, 150, 20);
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setForeground(Color.white);
        panel.add(label);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/money-removebg-preview.png"));
        Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setBounds(550, 100, 200, 200);
        panel.add(imageLabel);


        JLabel label1 = new JLabel("Customer Name");
        label1.setBounds(30, 80, 150, 20);
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        label1.setForeground(Color.white);
        panel.add(label1);

        Choice choice = new Choice();
        choice.setBounds(200, 80, 150, 25);
        panel.add(choice);

        try {
            conn c = new conn();
            String q = "select * from patient_info";
            ResultSet resultSet = c.statement.executeQuery(q);
            while (resultSet.next()){
                choice.add(resultSet.getString("Name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel label2 = new JLabel("Room Number");
        label2.setBounds(30, 130, 150, 20);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        label2.setForeground(Color.white);
        panel.add(label2);

        JLabel RNo = new JLabel();
        RNo.setBounds(200, 130, 150, 20);
        RNo.setFont(new Font("Tahoma", Font.BOLD, 14));
        RNo.setForeground(Color.white);
        panel.add(RNo);

        JLabel label3 = new JLabel("In Time");
        label3.setBounds(30, 180, 250, 20);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        label3.setForeground(Color.white);
        panel.add(label3);

        JLabel INTIME = new JLabel();
        INTIME.setBounds(200, 180, 250, 20);
        INTIME.setFont(new Font("Tahoma", Font.BOLD, 14));
        INTIME.setForeground(Color.white);
        panel.add(INTIME);

        JLabel label4 = new JLabel("Out Time");
        label4.setBounds(30, 230, 250, 20);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        label4.setForeground(Color.white);
        panel.add(label4);

        Date date = new Date();

        JLabel OUTTIME = new JLabel(""+date);
        OUTTIME.setBounds(200, 230, 250, 20);
        OUTTIME.setFont(new Font("Tahoma", Font.BOLD, 14));
        OUTTIME.setForeground(Color.white);
        panel.add(OUTTIME);

        discharge = new JButton("Discharge");
        discharge.setBounds(30, 300, 120, 30);
        discharge.setBackground(Color.black);
        discharge.setForeground(Color.white);
        discharge.setEnabled(false);
        panel.add(discharge);
        discharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c = new conn();
                try {
                    c.statement.executeUpdate("delete from patient_info where Name = '"+choice.getSelectedItem()+"' ");
                    c.statement.executeUpdate("update Room set Availability = 'Available' where room_no = '"+RNo.getText()+"' ");
                    JOptionPane.showMessageDialog(null, "Done");
                    setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton Check = new JButton("Check");
        Check.setBounds(170, 300, 120, 30);
        Check.setBackground(Color.black);
        Check.setForeground(Color.white);
        panel.add(Check);
        Check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c = new conn();
                boolean found = false;
                try {
                    ResultSet resultSet = c.statement.executeQuery("select * from patient_info where Name = '"+choice.getSelectedItem()+"' ");
                    while (resultSet.next()){
                        RNo.setText(resultSet.getString("Room_Number"));
                        INTIME.setText(resultSet.getString("Time"));
                        found = true;
                    }

                    if (found) {
                        discharge.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "No data found for the selected patient.");
                    }

                } catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton Back= new JButton("Back");
        Back.setBounds(300, 300, 120, 30);
        Back.setBackground(Color.black);
        Back.setForeground(Color.white);
        panel.add(Back);
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(800, 400);
        setLayout(null);
        setLocation(400, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Discharge();
    }
}
