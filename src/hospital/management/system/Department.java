package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

//Phong KHOA cua benh vien
public class Department extends JFrame {
    Department(){

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 690, 490);
        panel.setLayout(null);
        panel.setBackground(new Color(90, 156, 163));
        add(panel);

        JTable table = new JTable();
        table.setBounds(0, 40, 700, 350);
        table.setBackground(new Color(90, 156, 163));
        table.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(table);

        try {
            conn c = new conn();
            String q = "select * from Department";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel label = new JLabel("Department");
        label.setBounds(10, 11, 105, 20);
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label);

        JLabel label1 = new JLabel("Phone Number");
        label1.setBounds(350, 11, 150, 20);
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label1);

        JButton button = new JButton("Back");
        button.setBounds(250, 410, 100, 20);
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
        setSize(700, 500);
        setLayout(null);
        setLocation(350, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Department();
    }
}
