package swingTests;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class DrawPanel extends JPanel {

    Random gen = new Random();
    //position and dimension
    int x = 0, y = 0, width = 200, height = 200;
    Color drawColor = Color.BLACK;

    public DrawPanel() {
        repaint();
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = new Color(gen.nextInt(256), gen.nextInt(256), gen.nextInt(256));

                //Draw color disk
                drawColor = color;
                DrawPanel.this.repaint();
            }
        };

        Timer t = new Timer(500, action);
        t.setRepeats(true);
        t.setInitialDelay(0);
        t.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        //Overwriting of old picture
        super.paintComponent(g);

        //Draw boundary of circle
        g.setColor(drawColor);
        g.drawArc(x, y, width, height, 0, 360);
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame();
        frame.setSize(300, 300);
        final DrawPanel panel = new DrawPanel();
        panel.setOpaque(true);
        frame.getContentPane().add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}