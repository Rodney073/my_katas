package gameOfLife;

import java.awt.*;

public class Tile {
    private boolean isAlive;

    public Tile() {
        this.isAlive = Math.random() < 0.08;
    }

    public void draw (Graphics graphics, int rowIndex, int columnIndex, int tileSize) {

        graphics.fillRect(rowIndex, columnIndex, tileSize, tileSize);

    }

    public void setColor (Graphics graphics, Color color) {
        graphics.setColor(color);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
