package gui;

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
     * The data object to display
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
     * Create a button panel
     */
    protected JPanel createButtonPanel(int flowAlignment) {
        JPanel buttonPanel = new JPanel(new FlowLayout(flowAlignment));
        return buttonPanel;
    }

}