package com.zlicdt;
/*
 * Main class to launch the Classroom Booking System application.
 * zlicdt@2025
 * For COMP2013 - OOP - Project
 */
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.zlicdt.gui.Frame;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FlatMacLightLaf.setup();
                // Create and set up the main frame
                Frame frame = new Frame();
                frame.setVisible(true);
            }
        });
    }
}