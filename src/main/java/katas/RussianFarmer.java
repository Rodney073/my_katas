package katas;

import java.util.ArrayList;

public class RussianFarmer {
    public static void main(String[] args) {
        System.out.println(multiple(47, 42));
        System.out.println(multiple(46, 42));
    }

    private static int multiple(int a, int b) {

        ArrayList<Integer> listOfADivs = new ArrayList<>();
        listOfADivs.add(a);

        while (a != 1) {
            a = (int) Math.floor((double) a / 2);
            listOfADivs.add(a);
        }

        ArrayList<Integer> listOfBDivs = new ArrayList<>();
        if (listOfADivs.get(0) % 2 != 0) {
            listOfBDivs.add(b);
        }

        for (int i = 0; i < listOfADivs.size() - 1; i++) {
            b = b * 2;

            if (listOfADivs.get(i + 1) % 2 != 0) {
                listOfBDivs.add(b);
            }
        }

        return listOfBDivs.stream()
                .reduce(0, Integer::sum);

    }
}
