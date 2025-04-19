package com.zlicdt.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {
    
    private Frame parentFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;
    private JButton registerButton;
    private JButton backToLoginButton;
    
    public RegisterPanel(Frame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        
        // 创建注册表单面板
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // 用户名输入
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);
        
        // 密码输入
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);
        
        // 确认密码
        JLabel confirmPasswordLabel = new JLabel("Password Confirm:");
        confirmPasswordField = new JPasswordField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(confirmPasswordLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(confirmPasswordField, gbc);
        
        // 邮箱
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);
        
        // 按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        registerButton = new JButton("Register");
        backToLoginButton = new JButton("Back to Login");
        
        buttonPanel.add(registerButton);
        buttonPanel.add(backToLoginButton);
        
        // 添加事件监听
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 注册验证逻辑
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String email = emailField.getText();
                
                if (validateRegistration(username, password, confirmPassword, email)) {
                    // 这里应该将用户信息保存到数据库
                    JOptionPane.showMessageDialog(RegisterPanel.this, "Registration successful");
                    parentFrame.showPanel("login");
                }
            }
        });
        
        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.showPanel("login");
            }
        });
        
        // 主面板布局
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JLabel("User Register", JLabel.CENTER), BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // 添加边距
        mainPanel.setBorder(BorderFactory.createEmptyBorder(60, 100, 60, 100));
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    // 注册验证逻辑
    private boolean validateRegistration(String username, String password, String confirmPassword, String email) {
        if (username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "The passwords you entered twice do not match.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (email.trim().isEmpty() || !email.contains("@")) {
            JOptionPane.showMessageDialog(this, "Invalid email address.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // TODO: 写入数据库逻辑
        return true;
    }
}