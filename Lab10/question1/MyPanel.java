package question1;

import javax.swing.JPanel;
import java.awt.event.*;

public class MyPanel extends JPanel {
    // In the constructor of MyPanel, add a mouse listener to the panel using an anonymous class that extends MouseAdapter.
    public MyPanel() {
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getButton() == MouseEvent.BUTTON1);
                System.out.println(e.getX() + ", " + e.getY());
            }
        });
    }
}
