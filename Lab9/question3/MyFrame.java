package question3;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class MyFrame extends JFrame {
    public MyFrame() {
        setTitle("My Frame");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JButton("Button 1"));
        add(new JButton("Button 2"));
        add(new JLabel("Enter your name: "));
        add(new JTextField("Type Text Here"));
        add(new JCheckBox("I agree"));
        add(new JRadioButton("Yes"));
        add(new JComboBox<String>(new String[]{"Red", "Green", "Blue"}));
        add(new JComboBox<Integer>(new Integer[]{2, 7, -3, 24}));
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEFT);
        layout.setHgap(20);
        layout.setVgap(40);
        setLayout(layout);
        setVisible(true);
    }
}
