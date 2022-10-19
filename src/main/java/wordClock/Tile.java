package wordClock;

import java.awt.*;

public class Tile {

    private boolean isLightUp;

    private Color textColor;


    public void draw(Graphics graphics, int rowIndex, int columnIndex, int tileSize, Character character) {

        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(rowIndex+1, columnIndex+1, tileSize-1, tileSize-1);

        graphics.setColor(textColor);
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        graphics.drawString(character.toString(), rowIndex+15, columnIndex+tileSize-12);

    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public boolean isLightUp() {
        return isLightUp;
    }

    public void setLightUp(boolean lightUp) {
        isLightUp = lightUp;
    }
}
