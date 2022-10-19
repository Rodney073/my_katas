package loc;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LOC {


    public static void main(String[] args) {

        File file = openFileWithDialogWindow();
        System.out.println(countCodeLines(file));
    }

    private static File openFileWithDialogWindow() {

        FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);

        String fileName = dialog.getFile();

        if (fileName != null) {
            return new File(new File(dialog.getDirectory(), fileName).getAbsolutePath());
        } else {
            throw new NullPointerException("Filename can not be detected.");
        }
    }

    private static int countCodeLines(File file) {
        int numOfExecutableLines = 0;
        boolean commentStartFound = false;

        try {
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                String actualLine = myReader.nextLine().replaceAll("\\s+", "");

                if (actualLine.length() != 0) {
                    if (actualLine.length() >= 2) {

                        String lastTwoChars = actualLine.substring(Math.max(actualLine.length() - 2, 0));

                        if ((commentStartFound && !actualLine.contains("*/")) ||
                                actualLine.startsWith("//") ||
                                (actualLine.startsWith("/*") && lastTwoChars.equals("*/"))) {
                            continue;
                        }

                        if (actualLine.startsWith("/*") && !actualLine.contains("*/")) {
                            commentStartFound = true;
                            continue;
                        }

                        if (!actualLine.contains("/*") && lastTwoChars.equals("*/")) {
                            commentStartFound = false;
                            continue;
                        }
                    }
                    //1 char:
                    if (commentStartFound) {
                        continue;
                    }
                    numOfExecutableLines++;
                }
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return numOfExecutableLines;
    }

}
