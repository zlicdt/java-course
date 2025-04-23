package question6;

import javax.swing.JPanel;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.*;

public class MyPanel extends JPanel {
    private ArrayList<Point> points;
    public void clearAllPoints() {
        points.clear();
        repaint();
    }
    public void undoPoint() {
        if (!points.isEmpty()) {
            points.remove(points.size() - 1);
            repaint();
        }
    }
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
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            g.drawRect(p.x, p.y, 1, 1);
            if (i > 0) {
                Point p1 = points.get(i - 1);
                g.drawLine(p.x, p.y, p1.x, p1.y);
            }
        }
    }
}
