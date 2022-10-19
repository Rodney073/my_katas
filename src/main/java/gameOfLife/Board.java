package gameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Board extends JPanel {

    private final int gridSize = 100;
    private final int tileSize = 5;

    private final Grid grid = new Grid(gridSize, tileSize);

    public Board() {
        int boardSize = 600;
        this.setPreferredSize(new Dimension(boardSize, boardSize));
        setVisible(true);

        ActionListener action = e -> {
            repaint();
            grid.update();
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
        Timer timer = new Timer(100, action);
        timer.setRepeats(true);
        timer.setDelay(100);
        timer.start();
    }

    private static void createAndShowGui() throws IOException {
        System.out.println("createAndShowGui");

        JFrame frame = new JFrame();
        Board b = new Board();

        frame.add(b);
        frame.setTitle("Game of life");
        frame.setContentPane(b);
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
