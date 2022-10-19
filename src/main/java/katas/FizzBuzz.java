package katas;

public class FizzBuzz {

    public static void main(String[] args) {
        fizzBuzz();
    }

    private static void fizzBuzz() {
        for (int number = 1; number <= 100; number++) {

            if ((number % 3 == 0) && (number % 5 == 0)) {

                System.out.println("main.katas.FizzBuzz");

            } else if (number % 5 == 0) {

                System.out.println("Buzz");

            } else if (number % 3 == 0) {

                System.out.println("Fizz");

            } else {
                System.out.println(number);
            }
        }
    }
}
