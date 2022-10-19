package katas;

public class GreatUpheaval {

    public static void main(String[] args) {
        String text = "132465789123456798132456789123456789 night is blue, the stars are twinkling, 123456789132456798132465 snowflakes are sinking softly down.";
        int length = 14;
        wrapText(text, length);
    }

    private static void wrapText(String text, int maxLength) {
        int originalLength = maxLength;

        String[] words = text.split("\\s+");

        StringBuilder sb = new StringBuilder();

        for (int actualWordIndex = 0; actualWordIndex < words.length; actualWordIndex++) {

            if (words[actualWordIndex].length() > originalLength) {

                if (actualWordIndex > 0 && words[actualWordIndex].length() > maxLength) {
                    sb.append(System.getProperty("line.separator"));
                }

                int howMuchLonger = (int) Math.floor((double) words[actualWordIndex].length() / originalLength);

                for (int i = 0; i < howMuchLonger; i++) {

                    for (int j = 0; j < originalLength; j++) {
                        sb.append(words[actualWordIndex].charAt(j + i * originalLength));
                    }
                    sb.append(System.getProperty("line.separator"));

                }
                for (int j = 0; j < words[actualWordIndex].length() - (originalLength * howMuchLonger); j++) {
                    sb.append(words[actualWordIndex].charAt(j + (originalLength * howMuchLonger)));
                }

                maxLength = maxLength - (words[actualWordIndex].length() - (originalLength * howMuchLonger));

                if (maxLength > 0) {
                    sb.append(" ");
                    maxLength--;
                }

            } else if (words[actualWordIndex].length() <= maxLength) {
                sb.append(words[actualWordIndex]);
                maxLength = maxLength - words[actualWordIndex].length();
                if (maxLength > 0) {
                    sb.append(" ");
                    maxLength--;
                }
            } else {
                maxLength = originalLength;
                sb.append(System.getProperty("line.separator"));

                sb.append(words[actualWordIndex]);
                maxLength = maxLength - words[actualWordIndex].length();
                if (maxLength != 0) {
                    sb.append(" ");
                    maxLength--;
                }
            }

        }
        System.out.println(sb);

    }
}
