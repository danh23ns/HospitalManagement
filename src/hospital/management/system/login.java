package hospital.management.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class login extends JFrame implements ActionListener {
    JTextField textField = new JTextField() ;
    JPasswordField passwordField = new JPasswordField();
    JButton button = new JButton();
    JButton button1 = new JButton();
    login(){

        JLabel name = new JLabel("User name");
        name.setBounds(40, 20, 100, 30);
        name.setFont(new Font("Tahoma", Font.BOLD, 16));
        name.setForeground(Color.black);
        add(name);

        JLabel pass = new JLabel("Password");
        pass.setBounds(40, 70, 100, 30);
        pass.setFont(new Font("Tahoma", Font.BOLD, 16));
        pass.setForeground(Color.black);
        add(pass);

        textField.setBounds(150, 20, 150, 30);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setBackground(new Color(255, 179, 0));
        add(textField);

        passwordField.setBounds(150, 70, 150, 30);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        passwordField.setBackground(new Color(255,179, 0));
        add(passwordField);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image image = imageIcon.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(320, -30, 400, 300);
        add(label);

        button = new JButton("Login");
        button.setBounds(40, 140, 120, 30);
        button.setFont(new Font("serif", Font.BOLD, 15));
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.addActionListener(this);
        add(button);

        button1 = new JButton("Cancel");
        button1.setBounds(180, 140, 120, 30);
        button1.setFont(new Font("serif", Font.BOLD, 15));
        button1.setBackground(Color.black);
        button1.setForeground(Color.white);
        button1.addActionListener(this);
        add(button1);

        getContentPane().setBackground(new Color(109, 164, 170));
        setSize(750,300);
        setLayout(null);
        setLocation(400, 270);
        setVisible(true);

    }
    public static void main(String[] args){
        new login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== button){
            try {
                conn c = new conn();
                String user = textField.getText();
                String password = passwordField.getText();

                String q = "select * from login where name = '"+user+"' and PW = '"+password+"' ";
                ResultSet resultSet = c.statement.executeQuery(q);

                if(resultSet.next()){
                    new Reception();
                    setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid");
                }

            }catch (Exception E){
                E.printStackTrace();
            }
        }else {
            System.exit(10);
        }
    }
}
