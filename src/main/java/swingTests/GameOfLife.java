package swingTests;

import javax.swing.*;
import java.awt.*;

public class GameOfLife {
    JFrame frame = new JFrame(); // creates frame
    JButton[][] grid; // names the grid of buttons



    public GameOfLife(int width, int length) { // constructor
        frame.setLayout(new GridLayout(width, length)); // set layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);


        grid = new JButton[width][length]; // allocate the size of grid
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {
                grid[x][y] = new JButton(); // creates new button
                frame.add(grid[x][y]); // adds button to grid
                grid[x][y].setBackground(Color.white);

            }

        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); // sets appropriate size for frame
        frame.setVisible(true); // makes frame visible
    }

    public static void main(String[] args) {
        new GameOfLife(10, 10);// makes new ButtonGrid with 2 parameters
    }

}


