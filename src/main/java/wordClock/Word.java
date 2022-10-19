package wordClock;

public class Word {
    private final int row;
    private final int startIndex;
    private final int endIndex;

    public Word(int row, int startIndex, int endIndex) {
        this.row = row;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public int getRow() {
        return row;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}
