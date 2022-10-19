package katas;

public class XMasTree {

    public static void main(String[] args) {
        draw(10, true);
        draw(8, false);
    }

    private static void draw(int height, boolean star) {

        StringBuilder sb = new StringBuilder();

        if (star) {
            for (int j = 0; j < height - 1; j++) {
                sb.append(" ");
            }
            sb.append("*\n");
        }

        int width = 1;
        for (int i = 1; i <= height; i++) {
            for (int j = 0; j < height - i; j++) {
                sb.append(" ");
            }

            for (int j = 0; j < width; j++) {
                sb.append("X");
            }
            sb.append("\n");
            width += 2;
        }

        for (int j = 0; j < height - 1; j++) {
            sb.append(" ");
        }
        sb.append("I");

        System.out.println(sb);
    }


}
