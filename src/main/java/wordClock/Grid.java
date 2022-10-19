package wordClock;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Grid<T> {
    //TODO: Fix known bug regarding to hours: X mins TO HOUR!!

    private final ArrayList<ArrayList<Tile>> grid;
    private final int tileSize;
    private int turn;
    private LocalDateTime currentTime = java.time.LocalDateTime.now();
    private DateTimeFormatter formatter;

    Map<Integer, Word> mapOfHrs = new HashMap<Integer, Word>() {{

        put(9, new Word(4, 7, 10));
        put(1, new Word(5, 0, 2));
        put(6, new Word(5, 3, 5));
        put(3, new Word(5, 6, 10));
        put(4, new Word(6, 0, 3));
        put(5, new Word(6, 4, 7));
        put(2, new Word(6, 8, 10));
        put(8, new Word(7, 0, 4));
        put(11, new Word(7, 5, 10));
        put(7, new Word(8, 0, 4));
        put(12, new Word(8, 5, 10));
        put(10, new Word(9, 0, 2));
    }};

    Map<Integer, Word> mapOfMinutes = new HashMap<Integer, Word>() {{

        put(15, new Word(1, 2, 8));
        put(20, new Word(2, 0, 5));
        put(5, new Word(2, 6, 9));
        put(25, new Word(2, 0, 9));
        put(30, new Word(3, 0, 3));
        put(10, new Word(3, 5, 7));
    }};

    Map<String, Word> mapOfWords = new HashMap<String, Word>() {{

        put("AM", new Word(0, 7, 8));
        put("PM", new Word(0, 9, 10));
        put("to", new Word(3, 9, 10));
        put("past", new Word(4, 0, 3));
    }};

    Map<String, Word> fixWords = new HashMap<String, Word>() {{
        put("it", new Word(0, 0, 1));
        put("is", new Word(0, 3, 4));
        put("oclock", new Word(9, 5, 10));
    }};

    Character[][] wordClock = {

            {'I', 'T', 'L', 'I', 'S', 'A', 'S', 'A', 'M', 'P', 'M'},
            {'A', 'C', 'Q', 'U', 'A', 'R', 'T', 'E', 'R', 'D', 'C'},
            {'T', 'W', 'E', 'N', 'T', 'Y', 'F', 'I', 'V', 'E', 'X'},
            {'H', 'A', 'L', 'F', 'S', 'T', 'E', 'N', 'F', 'T', 'O'},
            {'P', 'A', 'S', 'T', 'E', 'R', 'U', 'N', 'I', 'N', 'E'},
            {'O', 'N', 'E', 'S', 'I', 'X', 'T', 'H', 'R', 'E', 'E'},
            {'F', 'O', 'U', 'R', 'F', 'I', 'V', 'E', 'T', 'W', 'O'},
            {'E', 'I', 'G', 'H', 'T', 'E', 'L', 'E', 'V', 'E', 'N'},
            {'S', 'E', 'V', 'E', 'N', 'T', 'W', 'E', 'L', 'V', 'E'},
            {'T', 'E', 'N', 'S', 'E', 'O', 'C', 'L', 'O', 'C', 'K'}
    };

    public Grid() {

        this.grid = new ArrayList<>();

        int rowLength = wordClock.length;
        int colLength = wordClock[0].length;

        for (int i = 0; i < rowLength + 2; i++) {
            ArrayList<Tile> row = new ArrayList<>();
            for (int j = 0; j < colLength + 2; j++) {
                Tile tile = new Tile();
                row.add(tile);
            }
            this.grid.add(row);
        }

        this.tileSize = 50;
        this.turn = 0;
    }

    @SuppressWarnings("unchecked")
    public void draw(Graphics graphics) {

        System.out.println("Draw");

        for (int row = 1; row < this.grid.size() - 1; row++) {
            for (int col = 1; col < this.grid.get(row).size() - 1; col++) {

                this.grid.get(row).get(col).setTextColor(Color.GRAY);
                paintFixWords(col, row);
                this.grid.get(row).get(col).draw(graphics, col * tileSize, row * tileSize, tileSize, wordClock[row - 1][col - 1]);
            }
        }
        paintWord(graphics, (Map<T, Word>) mapOfWords, (T) getAMorPM());
        if (getMinKey() != 0) {
            paintWord(graphics, (Map<T, Word>) mapOfMinutes, (T) getMinKey());
        }
        if (!getKeyPastOrTo().equals("")) {
            paintWord(graphics, (Map<T, Word>) mapOfWords, (T) getKeyPastOrTo());
        }
        paintWord(graphics, (Map<T, Word>) mapOfHrs, (T) getHourIn12hFormat());
    }

    public Integer getHourIn12hFormat() {
        formatter = DateTimeFormatter.ofPattern("hh");
        return Integer.parseInt(currentTime.format(formatter));
    }

    public Integer findClosestMin() {
        formatter = DateTimeFormatter.ofPattern("mm");
        return Math.toIntExact(5 * (Math.round((double) Integer.parseInt(currentTime.format(formatter)) / 5)));
    }

    public String getAMorPM() {
        formatter = DateTimeFormatter.ofPattern("a", new Locale("en"));
        return currentTime.format(formatter);
    }

    public Integer getMinKey() {
        int min = findClosestMin();

        if (min > 30) {
            min = 60 - min;
        }
        return min;
    }

    public String getKeyPastOrTo() {
        int currentMin = findClosestMin();
        String key = "";

        if (currentMin > 0 && currentMin <= 30) {
            key = "past";
        }
        if (currentMin > 30 && currentMin < 60) {
            key = "to";
        }
        return key;
    }

    public void paintFixWords(int col, int row) {
        for (Map.Entry<String, Word> entry : fixWords.entrySet()) {

            int itRow = entry.getValue().getRow();
            int itColStart = entry.getValue().getStartIndex();
            int itColEnd = entry.getValue().getEndIndex();

            if (row - 1 == itRow && col - 1 >= itColStart && col - 1 <= itColEnd) {
                this.grid.get(row).get(col).setTextColor(Color.GREEN);
            }
        }
    }

    public void paintWord(Graphics graphics, Map<T, Word> map, T key) {
        int row = map.get(key).getRow();
        int startIndex = map.get(key).getStartIndex();
        int endIndex = map.get(key).getEndIndex();

        changeLetterColorToGreen(graphics, row, startIndex, endIndex);
    }

    public void changeLetterColorToGreen(Graphics graphics, int row, int startIndex, int endIndex) {

        for (int col = startIndex; col <= endIndex; col++) {
            this.grid.get(row).get(col).setTextColor(Color.GREEN);
            this.grid.get(row).get(col).draw(graphics, (col + 1) * tileSize, (row + 1) * tileSize, tileSize, wordClock[row][col]);
        }
    }

    protected void updateTime() {

        System.out.println("update, turn: " + this.turn);
        this.turn++;
        this.currentTime = java.time.LocalDateTime.now();
    }
}
