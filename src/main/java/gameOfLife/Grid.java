package gameOfLife;

import java.awt.*;
import java.util.ArrayList;

class Grid {

    private ArrayList<ArrayList<Tile>> grid;
    private final int tileSize;
    private int turn;

    public Grid(int gridSize, int tileSize) {
        this.grid = new ArrayList<>();

        for (int i = 0; i < gridSize; i++) {
            ArrayList<Tile> row = new ArrayList<>();
            for (int j = 0; j < gridSize; j++) {
                Tile tile = new Tile();
                row.add(tile);
            }
            this.grid.add(row);
        }
        this.tileSize = tileSize;
        this.turn = 0;
    }

    public ArrayList<ArrayList<Tile>> getGrid() {
        return grid;
    }

    public void draw(Graphics graphics) {

        System.out.println("Draw");

        for (int i = 0; i < this.grid.size(); i++) {
            for (int j = 0; j < this.grid.get(i).size(); j++) {

                if (this.grid.get(i).get(j).isAlive()) {
                    this.grid.get(i).get(j).setColor(graphics, Color.BLACK);
                } else {
                    this.grid.get(i).get(j).setColor(graphics, Color.WHITE);
                }
                this.grid.get(i).get(j).draw(graphics, i * tileSize, j * tileSize, tileSize);
            }
        }
    }


    protected void update() {
        ArrayList<ArrayList<Tile>> newGrid = copyGrid(this.grid);

        System.out.println("update, turn: " + this.turn);
        this.turn++;

        int gridRowSize = this.grid.size();
        for (int row = 0; row < gridRowSize; row++) {
            int gridColSize = this.grid.get(row).size();
            for (int col = 0; col < gridColSize; col++) {

                int numOfAliveNeighbours = countNeighbours(row, col, gridRowSize, gridColSize);
                boolean isAlive = this.grid.get(row).get(col).isAlive();

                if (isAlive && (numOfAliveNeighbours >= 4 ||  numOfAliveNeighbours <= 1)) {
                    newGrid.get(row).get(col).setAlive(false);
                }

                if (!isAlive && numOfAliveNeighbours == 3) {
                    newGrid.get(row).get(col).setAlive(true);
                }
            }
        }
        this.grid = copyGrid(newGrid);
    }

    public ArrayList<ArrayList<Tile>> copyGrid(ArrayList<ArrayList<Tile>> grid) {

        ArrayList<ArrayList<Tile>> newGrid = new ArrayList<>();

        for( ArrayList<Tile> sublist : grid) {
            newGrid.add(new ArrayList<>(sublist));
        }

        return newGrid;
    }
    public int countNeighbours(int row, int col, int gridRowSize, int gridColSize) {

        int rowStart = Math.max(row - 1, 0);
        int rowFinish = Math.min(row + 1, gridRowSize - 1);
        int colStart = Math.max(col - 1, 0);
        int colFinish = Math.min(col + 1, gridColSize - 1);

        int numOfAliveNeighbours = 0;

        for (int curRow = rowStart; curRow <= rowFinish; curRow++) {
            for (int curCol = colStart; curCol <= colFinish; curCol++) {
                if ((curRow == row) && (curCol == col)) {
                    continue;
                }
                if (this.grid.get(curRow).get(curCol).isAlive()) {
                    numOfAliveNeighbours++;
                }
            }
        }

        return numOfAliveNeighbours;
    }
}
