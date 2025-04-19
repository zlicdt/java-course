package com.zlicdt.gui;

import javax.swing.*;

import com.formdev.flatlaf.FlatClientProperties;
import com.zlicdt.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    
    private Frame parentFrame;
    // For rendering the user name
    JLabel userLabel = new JLabel(Main.currentUser);

    public MainPanel(Frame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        
        JLabel welcomeLabel = new JLabel("Classroom Booking System", JLabel.CENTER);
        // Create a welcome label
        welcomeLabel.setFont(new Font("",Font.BOLD, 24));
        userLabel.setFont(new Font("",Font.PLAIN, 16));
        userLabel.setBackground(Color.LIGHT_GRAY);
        // Add a logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(
                    MainPanel.this,
                    "Are you sure to logout?",
                    "Logout Confirmation",
                    JOptionPane.YES_NO_OPTION
                );
                
                if (option == JOptionPane.YES_OPTION) {
                    parentFrame.showPanel("login");
                }
            }
        });
        
        // Create a top panel with the welcome label and logout button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(welcomeLabel, BorderLayout.CENTER);
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(logoutButton);
        // Create a panel for user label with left padding
        topPanel.add(logoutPanel, BorderLayout.EAST);
        
        // Create a content panel for the main functionality
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(new JLabel("Renderer", JLabel.CENTER));
        // Add components to the main panel
        add(topPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }
    public void updateDisplay() {
        userLabel.setText(Main.currentUser);
        repaint();
    }
}