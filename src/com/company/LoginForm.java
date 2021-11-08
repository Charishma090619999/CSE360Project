package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm implements ActionListener {
    private static JPanel panel;
    private static JFrame frame;
    private static JLabel userLabel;
    private static JTextField usertextfield;
    private static JLabel passwordLabel;
    private static JPasswordField passwordtext;
    private static JLabel message;
    public static void main(String args[])
    {
        panel=new JPanel();
        frame= new JFrame();
        frame.setSize(100,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        userLabel=new JLabel("UserName");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);
        usertextfield=new JTextField();
        usertextfield.setBounds(100,20,165,25);
        panel.add(usertextfield);
        passwordLabel=new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);
        passwordtext=new JPasswordField();
        passwordtext.setBounds(100,50,165,25);
        panel.add(passwordtext);
        JButton button=new JButton("Login");
        button.setBounds(10,80,80,25);
        button.addActionListener(new LoginForm());
        panel.add(button);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username= usertextfield.getText();
        String password= passwordtext.getText();
        Login log=new Login();
        String result=log.login(username,password);
        System.out.println(result);


    }
}
