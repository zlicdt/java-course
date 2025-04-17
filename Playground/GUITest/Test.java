import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
public class Test extends JFrame {

    public Test() {
        setTitle("Test");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JButton button = new JButton("Click Me");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked!" + e.getActionCommand());
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Events Test"));
        panel.add(button);
        add(panel);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse clicked at " + e.getPoint());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Mouse pressed at " + e.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("Mouse released at " + e.getPoint());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Mouse entered at " + e.getPoint());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Mouse exited at " + e.getPoint());
            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println("Mouse dragged at " + e.getPoint());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println("Mouse moved at " + e.getPoint());
            }
        });
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                System.out.println("Mouse wheel moved at " + e.getPoint() + " with rotation " + e.getWheelRotation());
            }
        });
        Timer t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Timer event");
            }
        });
        t.start();
        setVisible(true);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Test();
            }
        });
    }
}