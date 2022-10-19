package gameOfLife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class GridTest {

    public GridTest() {
    }

    @Test
    public void neighbourCounterShouldWork() {
        Grid grid = new Grid(5, 5);
        int gridRowSize = grid.getGrid().size();

        for (int row = 0; row < gridRowSize; row++) {
            int gridColSize = grid.getGrid().get(row).size();
            for (int col = 0; col < gridColSize; col++) {
                System.out.print(grid.getGrid().get(row).get(col).isAlive() + "  | ");
            }
            System.out.print("\n-----------------------------\n");
        }

        System.out.println("gridRowSize" + gridRowSize);
        for (int row = 0; row < gridRowSize; row++) {
            int gridColSize = grid.getGrid().get(row).size();
            System.out.println("gridColSize" + gridColSize);
            for (int col = 0; col < gridColSize; col++) {
                System.out.println("Index: " + row + ":" + col);

                System.out.println(grid.countNeighbours(row, col, gridRowSize, gridColSize));
            }
        }
        assertEquals("hello", "hello");
    }

    @Test
    public void testingCopyGridFunc() {

        Grid grid = new Grid(500, 500);

        ArrayList<ArrayList<Tile>> newGrid = grid.copyGrid(grid.getGrid());

        for (int i = 0; i < grid.getGrid().size(); i++) {
            for (int j = 0; j < grid.getGrid().size(); j++) {

                assertEquals(grid.getGrid().get(i).get(j).isAlive(), newGrid.get(i).get(j).isAlive());
            }
        }
    }
}
