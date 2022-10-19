package loc;

public class testCodeForLOC {
    public static void main(String[] args) {
        fizzBuzz();
    }

    // 20 out of 37 is executable
    private static void fizzBuzz() {
        for (int number = 1; number <= 100; number++) {

            if ((number % 3 == 0) && (number % 5 == 0)) {

                System.out.println("main.katas.FizzBuzz");
                //Ez itt egy comment

            } else if (number % 5 == 0) {//10

                System.out.println("Buzz");/*This is another comment just for fun*/

            } else if (number % 3 == 0) {

                System.out.println("Fizz"); //comment13

            }//14
            //I don't need this block
            /*else if (number % 8 == 0) {

                System.out.println("Fizz");

            }
            */
            else {
                /*Just another trash comment*/ System.out.println(number);
            }
        }
    }
}