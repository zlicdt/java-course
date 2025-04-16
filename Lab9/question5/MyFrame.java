package question5;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
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
        JPanel panelT = new JPanel();
        panelT.setLayout(new BorderLayout());
        panelT.setBackground(Color.BLUE);
        panelT.add(new JButton("B1"), BorderLayout.LINE_START);
        panelT.add(new JButton("B2"), BorderLayout.LINE_END);
        add(panelT, BorderLayout.NORTH);
        JPanel panelC = new JPanel();
        panelC.setLayout(new FlowLayout());
        panelC.setBackground(Color.GREEN);
        panelC.add(new JLabel("Enter your name: "), BorderLayout.LINE_START);
        panelC.add(new JTextField("Type Text Here"), BorderLayout.LINE_END);
        add(panelC, BorderLayout.CENTER);
        JPanel panelB = new JPanel();
        panelB.setLayout(new GridLayout(2, 2));
        panelB.setBackground(Color.WHITE);
        panelB.add(new JCheckBox("I agree"));
        panelB.add(new JRadioButton("Yes"));
        panelB.add(new JComboBox<String>(new String[]{"Red", "Green", "Blue"}));
        panelB.add(new JComboBox<Integer>(new Integer[]{2, 7, -3, 24}));
        add(panelB, BorderLayout.SOUTH);
        // FlowLayout layout = new FlowLayout();
        // layout.setAlignment(FlowLayout.LEFT);
        // layout.setHgap(20);
        // layout.setVgap(40);
        // GridLayout layout = new GridLayout(0, 5);
        setVisible(true);
    }
}
