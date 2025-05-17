package gui;

import javax.swing.*;

import main.Main;
import db.DatabaseManager;
import exceptions.DatabaseConnectionException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;

public class LoginPanel extends BasePanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton goToRegisterButton;
    
    public LoginPanel(Frame parentFrame) {
        super(parentFrame);
    }
    
    @Override
    public void initializePanel() {
        setLayout(new BorderLayout());
        
        // Create login form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Username input
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);
        
        // Password input
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);
        
        // Button panel
        JPanel buttonPanel = createButtonPanel(FlowLayout.CENTER);
        loginButton = new JButton("Login");
        goToRegisterButton = new JButton("Register");
        
        buttonPanel.add(loginButton);
        buttonPanel.add(goToRegisterButton);
        
        // Add action listeners for buttons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                if (validateLogin(username, password)) {
                    Main.currentUser = username; // Store the current user
                    System.out.println("Current logged user: " + Main.currentUser);
                    JOptionPane.showMessageDialog(LoginPanel.this, "Successfully logged in", "Login success", JOptionPane.INFORMATION_MESSAGE);
                    parentFrame.showPanel("main");
                    parentFrame.updateMainPanel(); // Update the main panel display
                } else {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Wrong username or password", "Login failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        goToRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.showPanel("register");
            }
        });
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Add system title using the method provided by the base class
        JLabel titleLabel = createTitleLabel("Classroom Booking System");
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        mainPanel.setBorder(BorderFactory.createEmptyBorder(80, 100, 80, 100));
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private boolean validateLogin(String username, String password) {
        // Connect to database
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean loginSuccess = false;
        
        try {
            // Get connect from DatabaseManager
            DatabaseManager dbManager = DatabaseManager.getInstance();
            conn = dbManager.getConnection();
            String sql = "SELECT * FROM accounts WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                loginSuccess = true; // Match
                
                // Check if user is admin
                if (username.equals("admin")) {
                    Main.isAdmin = true;
                } else {
                    Main.isAdmin = false;
                }
            }
        } catch (DatabaseConnectionException e) {
            System.out.println("Database connection error: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                    "Unable to connect to database: " + e.getMessage(), 
                    "Connection Error", 
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            System.out.println("SQL query error: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                    "Failed to validation: " + e.getMessage(), 
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Ensure database resources are closed
            DatabaseManager.closeResources(conn, pstmt, rs);
        }
        // If there is no match records, return false
        return loginSuccess;
    }
}
