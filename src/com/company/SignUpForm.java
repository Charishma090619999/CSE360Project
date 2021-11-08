package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpForm implements ActionListener {
    private static JPanel panel;
    private static JFrame frame;
    private static JLabel FirstNameLabel;
    private static JTextField FirstName;
    private static JLabel LastNameLabel;
    private static JTextField LastName;
    private static JLabel userLabel;
    private static JTextField userTextField;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JLabel message;
    public static void main(String args[])
    {
        panel=new JPanel();
        frame= new JFrame();
        frame.setSize(200,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        FirstNameLabel=new JLabel("First Name");
        FirstNameLabel.setBounds(10,20,80,25);
        panel.add(FirstNameLabel);
        FirstName=new JTextField();
        FirstName.setBounds(100,20,165,25);
        panel.add(FirstName);
        LastNameLabel=new JLabel("Last Name");
        LastNameLabel.setBounds(10,50,80,25);
        panel.add(LastNameLabel);
        LastName=new JTextField();
        LastName.setBounds(100,50,165,25);
        panel.add(LastName);
        userLabel=new JLabel("UserName");
        userLabel.setBounds(10,80,80,25);
        panel.add(userLabel);
        userTextField=new JTextField();
        userTextField.setBounds(100,80,165,25);
        panel.add(userTextField);
        passwordLabel=new JLabel("Password");
        passwordLabel.setBounds(10,110,80,25);
        panel.add(passwordLabel);
        passwordText=new JPasswordField();
        passwordText.setBounds(100,110,165,25);
        panel.add(passwordText);
        JButton button=new JButton("SignUp");
        button.setBounds(10,140,80,25);
        button.addActionListener(new SignUpForm());
        panel.add(button);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String firstname= FirstNameLabel.getText();
        String lastname= LastNameLabel.getText();
        String username= userTextField.getText();
        String password= passwordText.getText();
        SignUp sign=new SignUp();
        String result=sign.signup(firstname,lastname,username,password);
        System.out.println(result);


    }
}
