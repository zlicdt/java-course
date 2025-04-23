package question6;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    public MyFrame() {
        setTitle("My Frame");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        MyPanel myPanel = new MyPanel();
        JButton lb = new JButton("Reset");
        lb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myPanel.clearAllPoints();
                repaint();
            }
        });
        JButton rb = new JButton("Undo");
        rb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myPanel.undoPoint();
                repaint();
            }
        });
        panel.add(lb);
        panel.add(rb);
        add(panel, BorderLayout.NORTH);
        add(myPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}