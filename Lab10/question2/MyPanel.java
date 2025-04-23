package question2;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.*;

public class MyPanel extends JPanel {
    private int x = -1, y = -1;
    public MyPanel() {
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    x = e.getX();
                    y = e.getY();
                    repaint();
                }
            }
        });
    }
    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawRect(x, y, 1, 1);
    }
}
