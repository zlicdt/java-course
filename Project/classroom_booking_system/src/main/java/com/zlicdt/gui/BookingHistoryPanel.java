package com.zlicdt.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.util.ArrayList;
// import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zlicdt.Main;
import com.zlicdt.db.DatabaseManager;

public class BookingHistoryPanel extends JPanel {
    private Frame parentFrame;
    private JTable bookingsTable;
    private JScrollPane scrollPane;
    private DatabaseManager dbManager;
    
    public BookingHistoryPanel(Frame parentFrame) {
        this.parentFrame = parentFrame;
        this.dbManager = DatabaseManager.getInstance();
        setLayout(new BorderLayout());
        
        // Create header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("My Bookings", JLabel.CENTER);
        titleLabel.setFont(new Font("", Font.BOLD, 24));
        
        JButton backButton = new JButton("Back to Calendar");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.showPanel("main");
            }
        });
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(backButton);
        
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(buttonPanel, BorderLayout.WEST);
        
        // Create table to display bookings
        String[] columnNames = {"Date", "Time", "Room", "Status"};
        Object[][] data = {}; // Will be populated with actual booking data
        
        bookingsTable = new JTable(data, columnNames);
        bookingsTable.setFillsViewportHeight(true);
        
        scrollPane = new JScrollPane(bookingsTable);
        
        // Add empty panel at the bottom for future use (e.g., cancel booking button)
        JPanel actionPanel = new JPanel();
        JButton cancelButton = new JButton("Cancel Selected Booking");
        cancelButton.setEnabled(false); // Initially disabled
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bookingsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int bookingId = (Integer) bookingsTable.getModel().getValueAt(selectedRow, 4);
                    String date = (String) bookingsTable.getModel().getValueAt(selectedRow, 0);
                    String room = (String) bookingsTable.getModel().getValueAt(selectedRow, 2);
                    
                    // Confirm cancellation
                    int option = JOptionPane.showConfirmDialog(
                        BookingHistoryPanel.this,
                        "Are you sure you want to cancel your booking for " + date + " in " + room + "?",
                        "Confirm Cancellation",
                        JOptionPane.YES_NO_OPTION
                    );
                    
                    if (option == JOptionPane.YES_OPTION) {
                        try {
                            // Use DatabaseManager to delete the booking
                            boolean success = dbManager.deleteBooking(bookingId);
                            
                            if (success) {
                                JOptionPane.showMessageDialog(
                                    BookingHistoryPanel.this,
                                    "Booking successfully cancelled.",
                                    "Cancellation Successful",
                                    JOptionPane.INFORMATION_MESSAGE
                                );
                                
                                // Refresh the bookings table
                                refreshBookings();
                            } else {
                                JOptionPane.showMessageDialog(
                                    BookingHistoryPanel.this,
                                    "Failed to cancel booking. Please try again.",
                                    "Cancellation Failed",
                                    JOptionPane.ERROR_MESSAGE
                                );
                            }
                        } catch (SQLException ex) {
                            System.out.println("Database error: " + ex.getMessage());
                            JOptionPane.showMessageDialog(
                                BookingHistoryPanel.this,
                                "Error cancelling booking: " + ex.getMessage(),
                                "Database Error",
                                JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                }
            }
        });
        
        actionPanel.add(cancelButton);
        
        // Add components to the panel
        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);
    }
    
    // Method to refresh booking data from database
    public void refreshBookings() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            // Use DatabaseManager to get user bookings
            rs = dbManager.getUserBookings(Main.currentUser);
            
            // Prepare data for table model
            java.util.List<Object[]> bookingsList = new java.util.ArrayList<>();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String room = rs.getString("room");
                
                // Add booking data to list - status is set to "Confirmed" for all bookings
                Object[] bookingData = {date, time, room, "Confirmed", id};
                bookingsList.add(bookingData);
            }
            
            // Convert list to array for table model
            Object[][] bookingsArray = new Object[bookingsList.size()][5];
            for (int i = 0; i < bookingsList.size(); i++) {
                bookingsArray[i] = bookingsList.get(i);
            }
            
            // Update table model with new data
            bookingsTable.setModel(new javax.swing.table.DefaultTableModel(
                bookingsArray,
                new String[] {"Date", "Time", "Room", "Status", "ID"}
            ));
            
            // Hide the ID column as it's only needed for internal operations
            if (bookingsTable.getColumnCount() > 4) {
                bookingsTable.getColumnModel().getColumn(4).setMinWidth(0);
                bookingsTable.getColumnModel().getColumn(4).setMaxWidth(0);
                bookingsTable.getColumnModel().getColumn(4).setWidth(0);
            }
            
            // Enable cancel button if there are bookings
            JButton cancelButton = (JButton) ((JPanel) getComponent(2)).getComponent(0);
            cancelButton.setEnabled(bookingsTable.getRowCount() > 0);
            
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                                         "Error loading bookings: " + e.getMessage(), 
                                         "Database Error", 
                                         JOptionPane.ERROR_MESSAGE);
        } finally {
            // Ensure database connection closure
            if (rs != null) {
                try {
                    Statement resultStmt = rs.getStatement();
                    Connection resultConn = null;
                    if (resultStmt != null) {
                        resultConn = resultStmt.getConnection();
                    }
                    DatabaseManager.closeResources(resultConn, resultStmt, rs);
                } catch (SQLException ex) {
                    System.out.println("Error closing resources: " + ex.getMessage());
                }
            }
        }
    }
    
    // Method called when the panel is displayed
    public void updateDisplay() {
        refreshBookings();
    }
}