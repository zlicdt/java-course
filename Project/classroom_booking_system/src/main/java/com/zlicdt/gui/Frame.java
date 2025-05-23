package com.zlicdt.gui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    
    private CardLayout cardLayout;
    private JPanel containerPanel;
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private MainPanel mainPanel;
    private BookingHistoryPanel bookingHistoryPanel;
    
    public Frame() {
        setTitle("Classroom Booking System");
        setSize(960, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Initialize the CardLayout and container panel
        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout);
        
        // Create instances of the panels
        loginPanel = new LoginPanel(this);
        registerPanel = new RegisterPanel(this);
        mainPanel = new MainPanel(this);
        bookingHistoryPanel = new BookingHistoryPanel(this);
        
        // Add panels to the container
        containerPanel.add(loginPanel, "login");
        containerPanel.add(registerPanel, "register");
        containerPanel.add(mainPanel, "main");
        containerPanel.add(bookingHistoryPanel, "bookingHistory");
        
        // Set the initial panel to login
        cardLayout.show(containerPanel, "login");
        
        // Add the container panel to the frame
        add(containerPanel);
    }
    
    // Switch to the specified panel 
    public void showPanel(String panelName) {
        if (panelName.equals("main")) {
            mainPanel.updateDisplay();
        } else if (panelName.equals("bookingHistory")) {
            bookingHistoryPanel.updateDisplay();
        }
        
        cardLayout.show(containerPanel, panelName);
    }
    
    public void updateMainPanel() {
        mainPanel.updateDisplay();
    }
}