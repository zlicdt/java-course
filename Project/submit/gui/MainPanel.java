package gui;

import javax.swing.*;

import main.Main;
import db.DatabaseManager;
import exceptions.DatabaseConnectionException;
import exceptions.DatabaseQueryException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainPanel extends BasePanel {
    
    // For rendering the user name
    private JLabel userLabel;
    // For calendar
    private JTabbedPane calendarTabs;
    private Calendar selectedDate;
    private DatabaseManager dbManager;

    public MainPanel(Frame parentFrame) {
        super(parentFrame);
    }
    
    @Override
    public void initializePanel() {
        this.dbManager = DatabaseManager.getInstance();
        // Initialize userLabel
        this.userLabel = new JLabel(Main.currentUser);
        
        setLayout(new BorderLayout());
        
        JLabel welcomeLabel = createTitleLabel("Classroom Booking System");
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
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        userPanel.add(userLabel);
        
        // Add button to view bookings
        JButton myBookingsButton = new JButton("My Bookings");
        myBookingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate to booking history page
                parentFrame.showPanel("bookingHistory");
            }
        });
        
        userPanel.add(myBookingsButton);
        
        topPanel.add(userPanel, BorderLayout.WEST);
        topPanel.add(logoutPanel, BorderLayout.EAST);
        
        // Create a content panel with calendar functionality
        JPanel contentPanel = new JPanel(new BorderLayout());
        
        // Initialize selected date to today
        selectedDate = Calendar.getInstance();
        
        // Create calendar panel with tabs for 3 months
        calendarTabs = new JTabbedPane();
        
        // Add three month tabs
        Calendar currentMonth = Calendar.getInstance();
        for (int i = 0; i < 3; i++) {
            JPanel monthPanel = createMonthPanel(currentMonth);
            
            // Format month name for tab
            SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
            String monthName = monthFormat.format(currentMonth.getTime());
            
            calendarTabs.addTab(monthName, monthPanel);
            
            // Move to next month
            currentMonth.add(Calendar.MONTH, 1);
        }
        
        // Add a selection panel to display and confirm selected date
        JPanel selectionPanel = new JPanel();
        JLabel selectedDateLabel = new JLabel("No date selected");
        JButton confirmButton = new JButton("Confirm Booking");
        confirmButton.setEnabled(false);
        
        selectionPanel.add(new JLabel("Selected Date: "));
        selectionPanel.add(selectedDateLabel);
        selectionPanel.add(confirmButton);
        
        // Add calendar and selection panel to content panel
        contentPanel.add(calendarTabs, BorderLayout.CENTER);
        contentPanel.add(selectionPanel, BorderLayout.SOUTH);
        
        // Add components to the main panel
        add(topPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        
        // Add action listener to confirm button
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedDate != null) {
                    // Create a dialog for room selection and time slot
                    JDialog bookingDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(MainPanel.this), "Complete Booking", true);
                    bookingDialog.setLayout(new BorderLayout());
                    bookingDialog.setSize(400, 300);
                    bookingDialog.setLocationRelativeTo(MainPanel.this);
                    
                    JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
                    formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                    
                    // Date display (non-editable)
                    JLabel dateLabel = new JLabel("Date:");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd (EEEE)");
                    JTextField dateField = new JTextField(sdf.format(selectedDate.getTime()));
                    dateField.setEditable(false);
                    
                    // Room selection
                    JLabel roomLabel = new JLabel("Room:");
                    // From main.Main.rooms
                    JComboBox<String> roomComboBox = new JComboBox<>(main.Main.rooms);
                    
                    // Time slot selection
                    JLabel timeLabel = new JLabel("Time Slot:");
                    // String[] timeSlots = {"09:00-11:00", "11:00-13:00", "13:00-15:00", "15:00-17:00", "17:00-19:00"};
                    JComboBox<String> timeComboBox = new JComboBox<>(main.Main.timeSlots);
                    
                    // User (non-editable)
                    JLabel userLabel = new JLabel("User:");
                    JTextField userField = new JTextField(Main.currentUser);
                    userField.setEditable(false);
                    
                    // Add components to form panel
                    formPanel.add(dateLabel);
                    formPanel.add(dateField);
                    formPanel.add(roomLabel);
                    formPanel.add(roomComboBox);
                    formPanel.add(timeLabel);
                    formPanel.add(timeComboBox);
                    formPanel.add(userLabel);
                    formPanel.add(userField);
                    
                    // Button panel
                    JPanel buttonPanel = createButtonPanel(FlowLayout.RIGHT);
                    JButton cancelButton = new JButton("Cancel");
                    JButton submitButton = new JButton("Submit Booking");
                    
                    cancelButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            bookingDialog.dispose();
                        }
                    });
                    
                    submitButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                // Get form values
                                String selectedRoom = (String) roomComboBox.getSelectedItem();
                                String selectedTime = (String) timeComboBox.getSelectedItem();
                                
                                // Format date for database
                                SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                String formattedDate = dbDateFormat.format(selectedDate.getTime());
                                
                                // Check if this room is already booked for this date and time using DatabaseManager
                                boolean bookingExists = dbManager.bookingExists(formattedDate, selectedTime, selectedRoom);
                                
                                if (bookingExists) {
                                    // Booking already exists
                                    JOptionPane.showMessageDialog(
                                        bookingDialog,
                                        "This room is already booked for the selected date and time.",
                                        "Booking Failed",
                                        JOptionPane.ERROR_MESSAGE
                                    );
                                    return;
                                }
                                
                                // Add the new booking using DatabaseManager
                                boolean success = dbManager.addBooking(Main.currentUser, formattedDate, selectedTime, selectedRoom);
                                
                                if (success) {
                                    JOptionPane.showMessageDialog(
                                        bookingDialog,
                                        "Booking successfully created for " + formattedDate + ".",
                                        "Booking Successful",
                                        JOptionPane.INFORMATION_MESSAGE
                                    );
                                    
                                    bookingDialog.dispose();
                                } else {
                                    JOptionPane.showMessageDialog(
                                        bookingDialog,
                                        "Failed to create booking. Please try again.",
                                        "Booking Failed",
                                        JOptionPane.ERROR_MESSAGE
                                    );
                                }
                                    
                            } catch (DatabaseConnectionException ex) {
                                JOptionPane.showMessageDialog(
                                    bookingDialog,
                                    "Database connection error: " + ex.getMessage(),
                                    "Failed to connect",
                                    JOptionPane.ERROR_MESSAGE
                                );
                                System.out.println("Database connection error: " + ex.getMessage());
                            } catch (DatabaseQueryException ex) {
                                JOptionPane.showMessageDialog(
                                    bookingDialog,
                                    "Database query error: " + ex.getMessage(),
                                    "Failed to booking",
                                    JOptionPane.ERROR_MESSAGE
                                );
                                System.out.println("Database query error: " + ex.getMessage());
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(
                                    bookingDialog,
                                    "Error: " + ex.getMessage(),
                                    "Failed to booking",
                                    JOptionPane.ERROR_MESSAGE
                                );
                                System.out.println("Unknown error: " + ex.getMessage());
                            }
                        }
                    });
                    
                    buttonPanel.add(cancelButton);
                    buttonPanel.add(submitButton);
                    
                    // Add components to dialog
                    bookingDialog.add(formPanel, BorderLayout.CENTER);
                    bookingDialog.add(buttonPanel, BorderLayout.SOUTH);
                    
                    // Show dialog
                    bookingDialog.setVisible(true);
                }
            }
        });
    }
    
    private JPanel createMonthPanel(Calendar month) {
        // Clone the calendar to avoid modifying the original
        Calendar cal = (Calendar) month.clone();
        
        // Set to first day of month
        cal.set(Calendar.DAY_OF_MONTH, 1);
        
        // Create panel with grid layout (7 columns for days of week)
        JPanel monthPanel = new JPanel(new BorderLayout());
        
        // Header panel for days of week
        JPanel headerPanel = new JPanel(new GridLayout(1, 7));
        String[] dayNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String dayName : dayNames) {
            JLabel dayLabel = new JLabel(dayName, JLabel.CENTER);
            headerPanel.add(dayLabel);
        }
        
        // Grid panel for days of month
        JPanel daysPanel = new JPanel(new GridLayout(0, 7));
        
        // Get the day of week for the first day of month (0 = Sunday, 1 = Monday, etc.)
        int firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        
        // Get the number of days in the month
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        // Add empty labels for days before the first day of month
        for (int i = 0; i < firstDayOfWeek; i++) {
            daysPanel.add(new JLabel(""));
        }
        
        // Add buttons for each day of the month
        Calendar today = Calendar.getInstance();
        for (int day = 1; day <= daysInMonth; day++) {
            final int dayNumber = day;
            JButton dayButton = new JButton(Integer.toString(day));
            
            // Set calendar to this specific day
            cal.set(Calendar.DAY_OF_MONTH, day);
            
            // Disable past dates
            if (cal.before(today)) {
                dayButton.setEnabled(false);
            }
            
            // Add action listener for day selection
            dayButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Set selected date
                    Calendar selected = (Calendar) cal.clone();
                    selected.set(Calendar.DAY_OF_MONTH, dayNumber);
                    selectedDate = selected;
                    
                    // Update UI to show selected date
                    updateSelectedDateDisplay();
                }
            });
            
            daysPanel.add(dayButton);
        }
        
        // Add headers and days to month panel
        monthPanel.add(headerPanel, BorderLayout.NORTH);
        monthPanel.add(daysPanel, BorderLayout.CENTER);
        
        return monthPanel;
    }
    
    private void updateSelectedDateDisplay() {
        if (selectedDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd EEEE");
            JLabel label = (JLabel) ((JPanel) calendarTabs.getParent().getComponent(1)).getComponent(1);
            label.setText(dateFormat.format(selectedDate.getTime()));
            
            // Enable confirmation button
            JButton button = (JButton) ((JPanel) calendarTabs.getParent().getComponent(1)).getComponent(2);
            button.setEnabled(true);
        }
    }
    
    @Override
    public void updateDisplay() {
        userLabel.setText(Main.currentUser);
        repaint();
    }
}