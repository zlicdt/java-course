package question2;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class MyFrame extends JFrame {
    public MyFrame() {
        setTitle("My Frame");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JButton("Button 1"));
        add(new JButton("Button 2"));
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEFT);
        layout.setHgap(20);
        layout.setVgap(40);
        setLayout(layout);
        setVisible(true);
    }
}
