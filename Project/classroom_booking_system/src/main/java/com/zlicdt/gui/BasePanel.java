package com.zlicdt.gui;

import javax.swing.*;
import java.awt.*;

public abstract class BasePanel extends JPanel {
    
    protected Frame parentFrame;
    
    public BasePanel(Frame parentFrame) {
        this.parentFrame = parentFrame;
        initializePanel();
    }
    
    protected abstract void initializePanel();
    
    // Void but not abstract
    public void updateDisplay() {}
    
    /**
     * Overloaded method: Update display method that can receive parameters
     * @param data The data object to display
     */
    public void updateDisplay(Object data) {
        // Default implementation calls the no-parameter method
        updateDisplay();
    }
    
    /**
     * Create a title label
     */
    protected JLabel createTitleLabel(String title) {
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("", Font.BOLD, 24));
        return titleLabel;
    }
    
    /**
     * Overloaded method: Create a title label with custom font size
     */
    protected JLabel createTitleLabel(String title, int fontSize) {
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("", Font.BOLD, fontSize));
        return titleLabel;
    }
    
    /**
     * Overloaded method: Create a title label with custom font size and color
     */
    protected JLabel createTitleLabel(String title, int fontSize, Color color) {
        JLabel titleLabel = createTitleLabel(title, fontSize);
        titleLabel.setForeground(color);
        return titleLabel;
    }
    
    /**
     * Create a button panel
     */
    protected JPanel createButtonPanel(int flowAlignment) {
        JPanel buttonPanel = new JPanel(new FlowLayout(flowAlignment));
        return buttonPanel;
    }
    
    /**
     * Overloaded method: Create a button panel with background color
     */
    protected JPanel createButtonPanel(int flowAlignment, Color backgroundColor) {
        JPanel buttonPanel = createButtonPanel(flowAlignment);
        buttonPanel.setBackground(backgroundColor);
        return buttonPanel;
    }
    
    /**
     * Overloaded method: Create a button panel with custom margins
     */
    protected JPanel createButtonPanel(int flowAlignment, int hgap, int vgap) {
        JPanel buttonPanel = new JPanel(new FlowLayout(flowAlignment, hgap, vgap));
        return buttonPanel;
    }
}