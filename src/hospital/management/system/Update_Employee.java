package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Update_Employee extends JFrame {
    private JButton update;
    private JButton delete;
    Update_Employee(){
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

        JLabel label1 = new JLabel("Update Employee");
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
            ResultSet resultSet = c.statement.executeQuery("select * from employee");
            while (resultSet.next()){
                choice.add(resultSet.getString("Name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Age: ");
        label3.setBounds(25, 129, 260, 14);
        label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label3.setForeground(Color.white);
        panel.add(label3);

        JTextField textFieldA = new JTextField();
        textFieldA.setBounds(248, 129, 140, 20);
        panel.add(textFieldA);

        JLabel label4 = new JLabel("Phone Number: ");
        label4.setBounds(25, 174, 260, 14);
        label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label4.setForeground(Color.white);
        panel.add(label4);

        JTextField textFieldP = new JTextField();
        textFieldP.setBounds(248, 174, 140, 20);
        panel.add(textFieldP);

        JLabel label5 = new JLabel("Email: ");
        label5.setBounds(25, 216, 260, 14);
        label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label5.setForeground(Color.white);
        panel.add(label5);

        JTextField textFieldE= new JTextField();
        textFieldE.setBounds(248, 216, 140, 20);
        panel.add(textFieldE);


        JLabel label6 = new JLabel("Salary (USD): ");
        label6.setBounds(25,261,260,14);
        label6.setFont(new Font("Tahoma",Font.PLAIN,14));
        label6.setForeground(Color.white);
        panel.add(label6);

        JTextField textFieldS = new JTextField();
        textFieldS.setBounds(248,261,140,20);
        panel.add(textFieldS);

        JButton check = new JButton("CHECK");
        check.setBounds(250,350,89,23);
        check.setBackground(Color.black);
        check.setForeground(Color.white);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = choice.getSelectedItem();
                String q = "select * from employee where Name = '"+id+"'";
                boolean dataFound = false;
                try{
                    conn c = new conn();
                    ResultSet resultSet = c.statement.executeQuery(q);
                    while (resultSet.next()){
                        //String newName = textFieldNewName.getText();
                        textFieldA.setText(resultSet.getString("Age"));
                        textFieldP.setText(resultSet.getString("Phone_Number"));
                        textFieldE.setText(resultSet.getString("email"));
                        textFieldS.setText(resultSet.getString("salary"));
                        dataFound = true;
                    }

                    if (dataFound) {
                        update.setEnabled(true);
                        delete.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "No data found for the selected employee.");
                    }


                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        update = new JButton("UPDATE");
        update.setBounds(25, 350, 89, 23);
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
                    String age = textFieldA.getText();
                    String number = textFieldP.getText();
                    String email = textFieldE.getText();
                    String salary = textFieldS.getText();

                    c.statement.executeUpdate("update employee set Age = '"+age+"', Phone_Number = '"+number+"', email = '"+email+"' , salary = '"+salary+"' where Name = '"+q+"' ");
                    JOptionPane.showMessageDialog(null,"Updated Successfully");
                    setVisible(false);
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton back = new JButton("BACK");
        back.setBounds(140,350,89,23);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        delete = new JButton("DELETE");
        delete.setBounds(360,350,89,23);
        delete.setBackground(Color.black);
        delete.setForeground(Color.white);
        delete.setEnabled(false);
        panel.add(delete);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c = new conn();
                try {
                    c.statement.executeUpdate("delete from employee where Name = '"+choice.getSelectedItem()+"' ");
                    JOptionPane.showMessageDialog(null, "Delete Successfully");
                    setVisible(false);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        setUndecorated(true);
        setSize(950, 500);
        setLayout(null);
        setLocation(400, 250);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Update_Employee();
    }
}
