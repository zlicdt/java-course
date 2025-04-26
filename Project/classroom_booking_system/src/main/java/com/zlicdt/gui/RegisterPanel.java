package com.zlicdt.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import com.zlicdt.db.DatabaseManager;
import com.zlicdt.exceptions.DatabaseConnectionException;

public class RegisterPanel extends BasePanel {
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;
    private JButton registerButton;
    private JButton backToLoginButton;
    
    public RegisterPanel(Frame parentFrame) {
        super(parentFrame);
    }
    
    @Override
    protected void initializePanel() {
        setLayout(new BorderLayout());
        
        // Create registration form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);
        
        JLabel confirmPasswordLabel = new JLabel("Password Confirm:");
        confirmPasswordField = new JPasswordField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(confirmPasswordLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(confirmPasswordField, gbc);
        
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);
        
        // Button panel
        JPanel buttonPanel = createButtonPanel(FlowLayout.CENTER);
        registerButton = new JButton("Register");
        backToLoginButton = new JButton("Back to Login");
        
        buttonPanel.add(registerButton);
        buttonPanel.add(backToLoginButton);
        
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Confirm registration
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String email = emailField.getText();
                
                if (validateRegistration(username, password, confirmPassword, email)) {
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
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Create title using base class method
        JLabel titleLabel = createTitleLabel("User Registration");
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        mainPanel.setBorder(BorderFactory.createEmptyBorder(60, 100, 60, 100));
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private boolean isEmailTaken(String email) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean taken = false;
        
        try {
            DatabaseManager dbManager = DatabaseManager.getInstance();
            conn = dbManager.getConnection();
            String sql = "SELECT COUNT(*) FROM accounts WHERE email = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            taken = count > 0; // Email is taken if count is greater than 0
        } catch (DatabaseConnectionException e) {
            System.out.println("数据库连接错误: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                    "无法连接到数据库: " + e.getMessage(), 
                    "连接错误", 
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            System.out.println("SQL查询错误: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                    "检查邮箱是否已注册时发生错误: " + e.getMessage(), 
                    "数据库错误", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            DatabaseManager.closeResources(conn, pstmt, rs);
        }
        return taken;
    }
    
    private boolean isUsernameTaken(String username) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean taken = false;
        
        try {
            DatabaseManager dbManager = DatabaseManager.getInstance();
            conn = dbManager.getConnection();
            String sql = "SELECT COUNT(*) FROM accounts WHERE username = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            taken = count > 0; // Username is taken if count is greater than 0
        } catch (DatabaseConnectionException e) {
            System.out.println("数据库连接错误: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                    "无法连接到数据库: " + e.getMessage(), 
                    "连接错误", 
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            System.out.println("SQL查询错误: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                    "检查用户名是否已存在时发生错误: " + e.getMessage(), 
                    "数据库错误", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            DatabaseManager.closeResources(conn, pstmt, rs);
        }
        return taken;
    }

    private boolean validateRegistration(String username, String password, String confirmPassword, String email) {
        // Basic validation
        if (username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (isUsernameTaken(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists.", "Validation Error", JOptionPane.ERROR_MESSAGE);
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

        if (isEmailTaken(email)) {
            JOptionPane.showMessageDialog(this, "Email already registered.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Write into database
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            DatabaseManager dbManager = DatabaseManager.getInstance();
            conn = dbManager.getConnection();
            String sql = "INSERT INTO accounts (username, password, email) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
            success = true; // Registration successful
        } catch (DatabaseConnectionException e) {
            System.out.println("数据库连接错误: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                    "无法连接到数据库: " + e.getMessage(), 
                    "连接错误", 
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            System.out.println("注册用户时发生SQL错误: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                    "创建用户账户时发生错误: " + e.getMessage(), 
                    "注册失败", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            DatabaseManager.closeResources(conn, pstmt, null);
        }
        return success;
    }
}