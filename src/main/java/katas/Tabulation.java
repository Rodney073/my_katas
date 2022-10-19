package katas;

import java.util.*;

public class Tabulation {

    public static void doTabulation(Enumeration<String> csv) {

        List<String> csvLines = Collections.list(csv);
        ArrayList<Integer> longestColumnInRowList = new ArrayList<>();

        //Count the longest word in the actual column
        for (int actualLineNum = 0; actualLineNum < csvLines.size(); actualLineNum++) {

            String[] columns = csvLines.get(actualLineNum).split(";");

            if (actualLineNum == 0) {
                for (int colIndex = 0; colIndex < columns.length; colIndex++) {
                    longestColumnInRowList.add(0);
                }
            }

            for (int colIndex = 0; colIndex < columns.length; colIndex++) {

                if (columns[colIndex].length() > longestColumnInRowList.get(colIndex)) {
                    longestColumnInRowList.set(colIndex, columns[colIndex].length());
                }
            }
        }

        for (int actualLineNum = 0; actualLineNum < csvLines.size(); actualLineNum++) {

            String[] columns = csvLines.get(actualLineNum).split(";");
            printTabbedLine(longestColumnInRowList, columns);

            if (actualLineNum == 0) {
                printSeparator(longestColumnInRowList);
            }
        }

    }

    public static void printTabbedLine(ArrayList<Integer> longestColumnInRowList, String[] columns) {
        StringBuilder sb = new StringBuilder();

        for (int colIndex = 0; colIndex < columns.length; colIndex++) {
            sb.append(columns[colIndex]);
            for (int j = 0; j < longestColumnInRowList.get(colIndex) - columns[colIndex].length(); j++) {
                sb.append(" ");
            }
            sb.append("|");
        }
        sb.append("\n");
        System.out.print(sb);
    }

    public static void printSeparator(ArrayList<Integer> longestColumnInRowList) {
        StringBuilder sb = new StringBuilder();
        for (Integer integer : longestColumnInRowList) {
            for (int j = 0; j < integer; j++) {
                sb.append("-");
            }
            sb.append("+");
        }
        sb.append("\n");
        System.out.print(sb);
    }

    public static void main(String[] args) {

        Vector<String> lines = new Vector<>();

        lines.add("Name;Strasse;Ort;Alter");
        lines.add("Peter Pan;Am Hang 5;12345 Einsam;42");
        lines.add("Maria Schmitz;Kölner Straße 45;50123 Köln;43");
        lines.add("Paul Meier;Münchener Weg 1;87654 München;65");

        Enumeration<String> csv = lines.elements();

        doTabulation(csv);

    }
}
