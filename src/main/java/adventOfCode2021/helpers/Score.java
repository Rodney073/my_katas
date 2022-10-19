package adventOfCode2021.helpers;

public class Score {
    private final Integer number;
    private final Integer table;
    private final Integer row;
    private final Integer col;

    public Score(Integer number, Integer table, Integer row, Integer col) {
        this.number = number;
        this.table = table;
        this.row = row;
        this.col = col;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getTable() {
        return table;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getCol() {
        return col;
    }

    public String toString()
    {
        return number + " | " + table + " | " + row + " | "
                + col+"\n";
    }
}
