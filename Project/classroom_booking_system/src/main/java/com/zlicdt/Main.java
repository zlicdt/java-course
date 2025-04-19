package com.zlicdt;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.zlicdt.db.sqliteAction;
/*
 * Main class to launch the Classroom Booking System application.
 * zlicdt@2025
 * For COMP2013 - OOP - Project
 */
import com.zlicdt.gui.Frame;

public class Main {
    public static String currentUser;
    public static void main(String[] args) {
        sqliteAction.init();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FlatIntelliJLaf.setup();
                // Create and set up the main frame
                Frame frame = new Frame();
                frame.setVisible(true);
            }
        });
    }
}