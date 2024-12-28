package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Update_Patient extends JFrame {
    private JButton update;
    Update_Patient(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5, 940, 490);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(500, 60, 300, 300);
        panel.add(label);

        JLabel label1 = new JLabel("Update Patient");
        label1.setBounds(124, 11, 262, 25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 20));
        label1.setForeground(Color.white);
        panel.add(label1);

        JLabel label2 = new JLabel("Name: ");
        label2.setBounds(25, 88, 100, 14);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label2.setForeground(Color.white);
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(248, 85, 100, 25);
        panel.add(choice);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from patient_info");
            while (resultSet.next()){
                choice.add(resultSet.getString("Name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number: ");
        label3.setBounds(25, 129, 260, 14);
        label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label3.setForeground(Color.white);
        panel.add(label3);

        JTextField textFieldR = new JTextField();
        textFieldR.setBounds(248, 129, 140, 20);
        panel.add(textFieldR);

        JLabel label4 = new JLabel("In-Time: ");
        label4.setBounds(25, 174, 100, 14);
        label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label4.setForeground(Color.white);
        panel.add(label4);

        JTextField textFieldInTime = new JTextField();
        textFieldInTime.setBounds(248, 174, 140, 20);
        panel.add(textFieldInTime);

        JLabel label5 = new JLabel("Amount Paid (USD): ");
        label5.setBounds(25, 216, 260, 14);
        label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label5.setForeground(Color.white);
        panel.add(label5);

        JTextField textFieldAmount = new JTextField();
        textFieldAmount.setBounds(248, 216, 140, 20);
        panel.add(textFieldAmount);


        JLabel label6 = new JLabel("Disease:");
        label6.setBounds(25,261,260,14);
        label6.setFont(new Font("Tahoma",Font.PLAIN,14));
        label6.setForeground(Color.white);
        panel.add(label6);

        JTextField textFieldD = new JTextField();
        textFieldD.setBounds(248,261,140,20);
        panel.add(textFieldD);

        JLabel label7 = new JLabel("Phone:");
        label7.setBounds(25,310,260,14);
        label7.setFont(new Font("Tahoma",Font.PLAIN,14));
        label7.setForeground(Color.white);
        panel.add(label7);

        JTextField textFieldP = new JTextField();
        textFieldP.setBounds(248,310,140,20);
        panel.add(textFieldP);

        JLabel label8 = new JLabel("Gender:");
        label8.setBounds(25, 360, 260, 14);
        label8.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label8.setForeground(Color.white);
        panel.add(label8);


//        JLabel label9 = new JLabel("New Name:");
//        label9.setBounds(25, 400, 260, 14);
//        label9.setFont(new Font("Tahoma", Font.PLAIN, 14));
//        label9.setForeground(Color.white);
//        panel.add(label9);
//

//        JTextField textFieldNewName = new JTextField();
//        textFieldNewName.setBounds(248, 400, 140, 20);
//        panel.add(textFieldNewName);

        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female"});
        genderComboBox.setBounds(248, 360, 140, 20);
        panel.add(genderComboBox);


        JButton check = new JButton("CHECK");
        check.setBounds(281,420,89,23);
        check.setBackground(Color.black);
        check.setForeground(Color.white);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = choice.getSelectedItem();
                String q = "select * from Patient_Info where Name = '"+id+"'";
                boolean dataFound = false;
                try{
                    conn c = new conn();
                    ResultSet resultSet = c.statement.executeQuery(q);
                    while (resultSet.next()){
                        //String newName = textFieldNewName.getText();
                        textFieldR.setText(resultSet.getString("Room_Number"));
                        textFieldInTime.setText(resultSet.getString("Time"));
                        textFieldAmount.setText(resultSet.getString("Deposite"));
                        textFieldD.setText(resultSet.getString("Patient_Disease"));
                        textFieldP.setText(resultSet.getString("Number"));
                        genderComboBox.setSelectedItem(resultSet.getString("Gender"));
                        dataFound = true;
                    }

                    if (dataFound) {
                        update.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "No data found for the selected patient.");
                    }


                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        update = new JButton("UPDATE");
        update.setBounds(56, 420, 89, 23);
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        update.setEnabled(false);
        panel.add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn();
                    String q = choice.getSelectedItem();
                    String room = textFieldR.getText();
                    String time = textFieldInTime.getText();
                    String amount = textFieldAmount.getText();
                    String disease = textFieldD.getText();
                    String phone = textFieldP.getText();
                    String gender = (String) genderComboBox.getSelectedItem();
                    c.statement.executeUpdate("update patient_info set Room_Number = '"+room+"', Time = '"+time+"', Deposite = '"+amount+"' , Patient_Disease = '"+disease+"' , Number = '"+phone+"' , Gender = '"+gender+"' where name = '"+q+"' ");
                    JOptionPane.showMessageDialog(null,"Updated Successfully");
                    setVisible(false);
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton back = new JButton("BACK");
        back.setBounds(168,420,89,23);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(950, 500);
        setLayout(null);
        setLocation(400, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Update_Patient();
    }
}
