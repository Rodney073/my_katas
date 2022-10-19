package swingTests;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BulletExample extends JPanel {
    public static final String IMG_PATH = "http://www.i2clipart.com/cliparts/f/0/5/8/clipart-blue-circle-f058.png";
    private static final int PREF_W = 700;
    private static final int PREF_H = PREF_W;
    private static final int TIMER_DELAY = 20;
    private BufferedImage bullet;
    private int bulletX;
    private int bulletY;

    public BulletExample() throws IOException {
        URL imgUrl = new URL(IMG_PATH);
        bullet = ImageIO.read(imgUrl);
        new Timer(TIMER_DELAY, new BulletListener()).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Paint bullet");
        if (bullet != null) {
            g.drawImage(bullet, bulletX, bulletY, this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (isPreferredSizeSet()) {
            return super.getPreferredSize();
        }
        return new Dimension(PREF_W, PREF_H);
    }

    private class BulletListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            bulletX++;
            bulletY++;
            repaint();
        }
    }

    private static void createAndShowGui() throws IOException {

        // create the drawing JPanel
        BulletExample mainPanel = new BulletExample();

        JFrame frame = new JFrame("BulletExample");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // add it to the JFrame
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGui();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}