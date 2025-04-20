package com.zlicdt.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JLabel titleLabel;
    
    public BookingHistoryPanel(Frame parentFrame) {
        this.parentFrame = parentFrame;
        this.dbManager = DatabaseManager.getInstance();
        setLayout(new BorderLayout());
        
        // Create header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        titleLabel = new JLabel("My Bookings", JLabel.CENTER);
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
        
        // 创建表格列名
        String[] columnNames;
        if (Main.isAdmin) {
            // Admin用户表格显示预订用户名
            columnNames = new String[]{"Date", "Time", "Room", "Status", "User"};
        } else {
            columnNames = new String[]{"Date", "Time", "Room", "Status"};
        }
        
        Object[][] data = {}; // Will be populated with actual booking data
        
        // 创建一个不可编辑的表格模型
        bookingsTable = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 使所有单元格不可编辑
            }
        };
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
                    int bookingId = (Integer) bookingsTable.getModel().getValueAt(selectedRow, 
                        Main.isAdmin ? 5 : 4); // ID列的索引
                    String date = (String) bookingsTable.getModel().getValueAt(selectedRow, 0);
                    String room = (String) bookingsTable.getModel().getValueAt(selectedRow, 2);
                    String username = Main.isAdmin ? 
                        (String) bookingsTable.getModel().getValueAt(selectedRow, 4) : Main.currentUser;
                    
                    // Confirm cancellation
                    String message = "Are you sure you want to cancel " + 
                        (Main.isAdmin ? username + "'s " : "your ") + 
                        "booking for " + date + " in " + room + "?";
                    
                    int option = JOptionPane.showConfirmDialog(
                        BookingHistoryPanel.this,
                        message,
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
        ResultSet rs = null;
        
        try {
            // 根据用户类型获取预订信息
            if (Main.isAdmin) {
                // 管理员可以查看所有预订
                rs = dbManager.getAllBookings();
                titleLabel.setText("All Bookings (Admin View)");
            } else {
                // 普通用户只能查看自己的预订
                rs = dbManager.getUserBookings(Main.currentUser);
                titleLabel.setText("My Bookings");
            }
            
            // Prepare data for table model
            java.util.List<Object[]> bookingsList = new java.util.ArrayList<>();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String room = rs.getString("room");
                String name = rs.getString("name");
                
                // Add booking data to list
                if (Main.isAdmin) {
                    // 管理员查看包含用户名
                    Object[] bookingData = {date, time, room, "Confirmed", name, id};
                    bookingsList.add(bookingData);
                } else {
                    // 普通用户视图
                    Object[] bookingData = {date, time, room, "Confirmed", id};
                    bookingsList.add(bookingData);
                }
            }
            
            // Convert list to array for table model
            Object[][] bookingsArray = new Object[bookingsList.size()][Main.isAdmin ? 6 : 5];
            for (int i = 0; i < bookingsList.size(); i++) {
                bookingsArray[i] = bookingsList.get(i);
            }
            
            // 更新表格模型
            if (Main.isAdmin) {
                bookingsTable.setModel(new javax.swing.table.DefaultTableModel(
                    bookingsArray,
                    new String[] {"Date", "Time", "Room", "Status", "User", "ID"}
                ) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
            } else {
                bookingsTable.setModel(new javax.swing.table.DefaultTableModel(
                    bookingsArray,
                    new String[] {"Date", "Time", "Room", "Status", "ID"}
                ) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
            }
            
            // 隐藏ID列
            int idColumnIndex = Main.isAdmin ? 5 : 4;
            if (bookingsTable.getColumnCount() > idColumnIndex) {
                bookingsTable.getColumnModel().getColumn(idColumnIndex).setMinWidth(0);
                bookingsTable.getColumnModel().getColumn(idColumnIndex).setMaxWidth(0);
                bookingsTable.getColumnModel().getColumn(idColumnIndex).setWidth(0);
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