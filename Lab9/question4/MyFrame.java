package question4;

import javax.swing.JFrame;
import javax.swing.JButton;
// import java.awt.FlowLayout;
// import java.awt.GridLayout;
import java.awt.BorderLayout;

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
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        add(new JButton("Button 1"), BorderLayout.PAGE_START);
        add(new JButton("Button 2"), BorderLayout.PAGE_START);
        add(new JLabel("Enter your name: "), BorderLayout.LINE_START);
        add(new JTextField("Type Text Here"), BorderLayout.LINE_END);
        add(new JCheckBox("I agree"), BorderLayout.LINE_START);
        add(new JRadioButton("Yes"), BorderLayout.LINE_END);
        add(new JComboBox<String>(new String[]{"Red", "Green", "Blue"}), BorderLayout.PAGE_END);
        add(new JComboBox<Integer>(new Integer[]{2, 7, -3, 24}), BorderLayout.PAGE_END);
        // FlowLayout layout = new FlowLayout();
        // layout.setAlignment(FlowLayout.LEFT);
        // layout.setHgap(20);
        // layout.setVgap(40);
        // GridLayout layout = new GridLayout(0, 5);
        setVisible(true);
    }
}
