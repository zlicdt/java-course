/*
 * Main class to launch the Classroom Booking System application.
 * zlicdt@2025
 * For COMP2013 - OOP - Project
 */
package main;
import db.DatabaseManager;
import db.sqliteAction;
import exceptions.DatabaseConnectionException;
import exceptions.DatabaseQueryException;
import gui.Frame;
import javax.swing.*;
import java.awt.Dimension;

public class Main {
    public static String currentUser;
    public static boolean isAdmin = false;
    // can define rooms and times here
    public static String[] rooms = {"Room 114", "Room 514", "Room 1919", "Room 810"};
    public final static String[] timeSlots = {"08:00 - 09:00", "09:00 - 10:00", "10:00 - 11:00", 
                                         "11:00 - 12:00", "12:00 - 13:00", "13:00 - 14:00", 
                                         "14:00 - 15:00", "15:00 - 16:00", "16:00 - 17:00", "17:00 - 18:00"};
    public static void main(String[] args) {
        // Load SQLite JDBC driver
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load JDBC driver: " + e.getMessage());
            System.exit(1);
        }
        
        sqliteAction.init();
        
        // Check admin user after initialization
        // Only proceed if admin exists or was successfully created
        if (checkAdminUser()) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    // Create and set up the main frame
                    Frame frame = new Frame();
                    frame.setVisible(true);
                }
            });
        } else {
            // Application will exit if admin user was not created
            System.out.println("Exiting: Admin user is required.");
        }
    }
    
    /**
     * Check if admin user exists, create if not.
     * Application will not proceed without an admin user.
     * @return true if admin exists or was successfully created, false otherwise
     */
    private static boolean checkAdminUser() {
        DatabaseManager dbManager = DatabaseManager.getInstance();
        try {
            if (!dbManager.adminExists()) {
                // Admin user doesn't exist, inform user that admin creation is required
                String message = "Admin account (username: admin) does not exist.";
                JOptionPane.showMessageDialog(null, message, "Admin Required", JOptionPane.INFORMATION_MESSAGE);
                
                // User must create admin account
                boolean validPassword = false;
                
                while (!validPassword) {
                    // Create password input panel with confirmation
                    JPanel passwordPanel = new JPanel();
                    passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
                    
                    JLabel passwordLabel = new JLabel("Enter admin password (min 6 characters):");
                    JPasswordField passwordField = new JPasswordField(20);
                    passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, passwordField.getPreferredSize().height));
                    
                    JLabel confirmLabel = new JLabel("Confirm password:");
                    JPasswordField confirmField = new JPasswordField(20);
                    confirmField.setMaximumSize(new Dimension(Integer.MAX_VALUE, confirmField.getPreferredSize().height));
                    
                    passwordPanel.add(passwordLabel);
                    passwordPanel.add(passwordField);
                    passwordPanel.add(confirmLabel);
                    passwordPanel.add(confirmField);
                    
                    int result = JOptionPane.showConfirmDialog(null, passwordPanel, 
                            "Set Admin Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    
                    // User cancelled operation
                    if (result != JOptionPane.OK_OPTION) {
                        JOptionPane.showMessageDialog(null, 
                                "Admin account creation is mandatory. The application will now exit.", 
                                "Admin Required", JOptionPane.WARNING_MESSAGE);
                        System.exit(0); // Exit application if user refuses to create admin
                        return false;
                    }
                    
                    String password = new String(passwordField.getPassword());
                    String confirm = new String(confirmField.getPassword());
                    
                    // Validate password
                    if (password.length() < 6) {
                        JOptionPane.showMessageDialog(null, "Password must be at least 6 characters long", 
                                "Password Too Short", JOptionPane.WARNING_MESSAGE);
                    } else if (!password.equals(confirm)) {
                        JOptionPane.showMessageDialog(null, "Passwords do not match", 
                                "Password Mismatch", JOptionPane.WARNING_MESSAGE);
                    } else {
                        // Create admin user
                        if (dbManager.createAdminUser(password)) {
                            JOptionPane.showMessageDialog(null, "Admin account created successfully. Username: admin", 
                                    "Account Created", JOptionPane.INFORMATION_MESSAGE);
                            validPassword = true;
                            return true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to create admin account. Please try again.", 
                                    "Creation Failed", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                return false; // Should never reach here due to System.exit, but needed for compilation
            }
            return true; // Admin already exists
        } catch (DatabaseConnectionException e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to database: " + e.getMessage(), 
                    "Database Connection Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Exit on database error
            return false; // Will not reach here but needed for compilation
        } catch (DatabaseQueryException e) {
            JOptionPane.showMessageDialog(null, "Database query error: " + e.getMessage(), 
                    "Database Operation Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Exit on database error
            return false; // Will not reach here but needed for compilation
        }
    }
}