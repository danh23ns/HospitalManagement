package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class SearchPatient extends JFrame {
    JTable table;
    JTextField textField;
    SearchPatient(){
        JPanel panel = new JPanel();
        panel.setBounds(5,5, 890, 590);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        JLabel For = new JLabel("Search For Patient");
        For.setBounds(350, 11, 186, 31);
        For.setForeground(Color.white);
        For.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(For);

        JLabel status = new JLabel("Name: ");
        status.setBounds(200, 70, 80, 20);
        status.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(status);

        textField = new JTextField();
        textField.setBounds(270, 70, 350, 20);
        panel.add(textField);

        table = new JTable();
        table.setBounds(0, 160, 890, 210);
        table.setBackground(new Color(90, 156, 163));
        table.setForeground(Color.white);
        panel.add(table);

        try {
            conn c = new conn();
            String q = "select * from patient_info";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel label = new JLabel("ID");
        label.setBounds(0, 140, 100, 14);
        label.setForeground(Color.white);
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label);

        JLabel label1 = new JLabel("Phone");
        label1.setBounds(115, 140, 100, 14);
        label1.setForeground(Color.white);
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label1);

        JLabel label2 = new JLabel("Name");
        label2.setBounds(225, 140, 100, 14);
        label2.setForeground(Color.white);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label2);

        JLabel label3 = new JLabel("Gender");
        label3.setBounds(335, 140, 100, 14);
        label3.setForeground(Color.white);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label3);

        JLabel label5 = new JLabel("Disease");
        label5.setBounds(445, 140, 100, 14);
        label5.setForeground(Color.white);
        label5.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label5);

        JLabel label6 = new JLabel("Room");
        label6.setBounds(555, 140, 100, 14);
        label6.setForeground(Color.white);
        label6.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label6);

        JLabel label7 = new JLabel("Time");
        label7.setBounds(665, 140, 100, 14);
        label7.setForeground(Color.white);
        label7.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label7);

        JLabel label8 = new JLabel("Deposit");
        label8.setBounds(780, 140, 100, 14);
        label8.setForeground(Color.white);
        label8.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label8);

        JButton search = new JButton("Search");
        search.setBounds(300, 510, 120, 30);
        search.setBackground(Color.black);
        search.setForeground(Color.white);
        panel.add(search);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String q = "select * from patient_info where Name LIKE '%" + textField.getText() + "%'";
                try {
                    conn c = new conn();
                    ResultSet resultSet = c.statement.executeQuery(q);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        });

        JButton button = new JButton("BACK");
        button.setBounds(480, 510, 120, 30);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(900, 600);
        setLayout(null);
        setLocation(300, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SearchPatient();
    }
}
