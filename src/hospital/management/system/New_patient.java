package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class New_patient extends JFrame implements ActionListener {
    JComboBox comboBox; // lua chon //

    JTextField textNumber, textName, textDisease, textDeposite;

    JRadioButton r1, r2;

    Choice c1;

    JLabel date;

    JButton button, button1;

    New_patient(){
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 840, 540);
        panel.setBackground(new Color(98, 156, 163));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/patient.png"));
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(550, 150, 200, 200);
        panel.add(label);

        JLabel labeltitle = new JLabel("New Patient Information");
        labeltitle.setBounds(118, 11, 260, 53);
        labeltitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(labeltitle);

        JLabel labelID = new JLabel("Idenity: ");
        labelID.setBounds(35, 76, 260, 14);
        labelID.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelID.setForeground(Color.white);
        panel.add(labelID);

        comboBox = new JComboBox(new String[] {"Idenityfine Card","Driving License","Visa"});
        comboBox.setBounds(271, 73, 150, 20);
        comboBox.setBackground(new Color(3, 45, 48));
        comboBox.setForeground(Color.white);
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(comboBox);

        JLabel labelnumber = new JLabel("Number: ");
        labelnumber.setBounds(35, 111, 260, 14);
        labelnumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelnumber.setForeground(Color.white);
        panel.add(labelnumber);

        textNumber = new JTextField();
        textNumber.setBounds(271, 111, 150, 20);
        panel.add(textNumber);

        JLabel labelName = new JLabel("Name: ");
        labelName.setBounds(35, 151, 260, 14);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelName.setForeground(Color.white);
        panel.add(labelName);

        textName = new JTextField();
        textName.setBounds(271, 151, 150, 20);
        panel.add(textName);

        JLabel labelGender = new JLabel("Gender: ");
        labelGender.setBounds(35, 191, 260, 14);
        labelGender.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelGender.setForeground(Color.white);
        panel.add(labelGender);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Tahoma", Font.BOLD, 14));
        r1.setForeground(Color.white);
        r1.setBackground(new Color(109, 164, 170));
        r1.setBounds(271, 191, 80, 15);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Tahoma", Font.BOLD, 14));
        r2.setForeground(Color.white);
        r2.setBackground(new Color(109, 164, 170));
        r2.setBounds(350, 191, 80, 15);
        panel.add(r2);

        JLabel labelDisease = new JLabel("Disease ");
        labelDisease.setBounds(35, 231, 260, 14);
        labelDisease.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelDisease.setForeground(Color.white);
        panel.add(labelDisease);

        textDisease = new JTextField();
        textDisease.setBounds(271, 231, 150, 20);
        panel.add(textDisease);

        JLabel labelRoom = new JLabel("Room: ");
        labelRoom.setBounds(35, 274, 200, 14);
        labelRoom.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelRoom.setForeground(Color.white);
        panel.add(labelRoom);

        c1 = new Choice();
        try{
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from Room");
            while(resultSet.next()){
                c1.add(resultSet.getString("room_no"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        c1.setBounds(271, 274, 150, 20);
        c1.setFont(new Font("Tahoma", Font.BOLD, 14));
        c1.setForeground(Color.WHITE);
        c1.setBackground(new Color(3, 45, 48));
        panel.add(c1);

        JLabel labelDate = new JLabel("Time: ");
        labelDate.setBounds(35, 316, 260, 14);
        labelDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelDate.setForeground(Color.white);
        panel.add(labelDate);

        Date date1 = new Date();

        date = new JLabel(""+date1);
        date.setBounds(271, 316, 250, 14);
        date.setForeground(Color.white);
        date.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(date);

        JLabel labelDeposite = new JLabel("Diposite: ");
        labelDeposite.setBounds(35, 359, 260, 17);
        labelDeposite.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelDeposite.setForeground(Color.white);
        panel.add(labelDeposite);

        textDeposite = new JTextField();
        textDeposite.setBounds(271, 359, 150, 20);
        panel.add(textDeposite);

        button = new JButton("Add");
        button.setBounds(100, 430, 120, 30);
        button.setForeground(Color.white);
        button.setBackground(Color.black);
        button.addActionListener(this);
        panel.add(button);

        button1 = new JButton("Back");
        button1.setBounds(260, 430, 120, 30);
        button1.setForeground(Color.white);
        button1.setBackground(Color.black);
        button1.addActionListener(this);
        panel.add(button1);

        setUndecorated(true);
        setSize(850, 550);
        setLayout(null);
        setLocation(300, 250);
        setVisible(true);
    }

    public static void main(String[] args){
        new New_patient();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            conn c = new conn();
            String radioBTN = null;
            if(r1.isSelected()){
                radioBTN = "Male";
            }else if(r2.isSelected()) {
                radioBTN = "Female";
            }
            String s1 = (String) comboBox.getSelectedItem();
            String s2 = textNumber.getText().trim();
            String s3 = textName.getText().trim(); //bo khoang tra
            String s4 = radioBTN;
            String s5 = textDisease.getText().trim();
            String s6 = c1.getSelectedItem().trim();
            String s7 = date.getText();
            String s8 = textDeposite.getText().trim();

            try {

                if (s3.isEmpty() || s2.isEmpty() || s5.isEmpty() || s8.isEmpty() || s4.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please add all information");
                    return; // Dừng tiến trình nếu không nhập du
                }

                // Kiểm tra trạng thái phòng
                String checkRoomQuery = "SELECT Availability FROM Room WHERE room_no = ?";
                PreparedStatement checkStmt = c.connection.prepareStatement(checkRoomQuery);
                checkStmt.setString(1, s6);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    String roomStatus = rs.getString("Availability");
                    if (roomStatus.equals("Occupied")) {
                        JOptionPane.showMessageDialog(null, "Room was use. Please use another room.");
                        return;
                    } else if (roomStatus.equals("Under Maintenance")) {
                        JOptionPane.showMessageDialog(null, "Room is under maintanece. Cannot use.");
                        return;
                    }
                }

                String q = "INSERT INTO patient_info (ID, Number, Name, Gender, Patient_Disease, Room_Number, Time, Deposite) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = c.connection.prepareStatement(q);
                pstmt.setString(1, s1);
                pstmt.setString(2, s2);
                pstmt.setString(3, s3);
                pstmt.setString(4, s4);
                pstmt.setString(5, s5);
                pstmt.setString(6, s6);
                pstmt.setString(7, s7);
                pstmt.setString(8, s8);
                pstmt.executeUpdate();

                String q1 = "UPDATE Room SET Availability = 'Occupied' WHERE room_no = ?";
                PreparedStatement pstmt1 = c.connection.prepareStatement(q1);
                pstmt1.setString(1, s6);
                pstmt1.executeUpdate();

                JOptionPane.showMessageDialog(null, "Added Successfully");
                setVisible(false);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Error: " + exception.getMessage());
            }
        }else {
            setVisible(false);
        }
    }
}
