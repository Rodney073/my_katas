package wordClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Clock extends JPanel {
    private final Grid grid = new Grid();

    public Clock() {
        this.setPreferredSize(new Dimension(650, 600));

        ActionListener action = e -> {
            this.grid.updateTime();
            repaint();
            System.out.println("actionPerformed");
        };

        refreshScreen(action);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.grid.draw(g);
        System.out.println("repaint");

    }

    public void refreshScreen(ActionListener action) {
        System.out.println("refreshScreen");
        Timer timer = new Timer(60000, action);
        timer.setRepeats(true);
        timer.setDelay(60000);
        timer.start();
    }

    private static void createAndShowGui() throws IOException {
        System.out.println("createAndShowGui");

        JFrame frame = new JFrame();

        frame.setTitle("Word clock");
        frame.setContentPane(new Clock());
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGui();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
