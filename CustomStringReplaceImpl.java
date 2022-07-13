
public class CustomStringReplaceImpl {

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            boolean equalsThree = i % 3 == 0;
            boolean equalsFive = i % 5 == 0;
            if (equalsThree && equalsFive) {
                System.out.println("FizzBuzz");
            } else {
                if (equalsFive) {
                    System.out.println("Buzz");
                } else if (equalsThree) {
                    System.out.println("Fizz");
                } else {
                    System.out.println(i);
                }
            }
        }
    }

}
