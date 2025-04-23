package question3;

import javax.swing.JPanel;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.*;

public class MyPanel extends JPanel {
    private ArrayList<Point> points;
    public MyPanel() {
        points = new ArrayList<>();
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                points.add(e.getPoint());
                repaint();
            }
        });
    }
    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        for (Point p : points) {
            g.drawRect(p.x, p.y, 1, 1);
        }
    }
}
