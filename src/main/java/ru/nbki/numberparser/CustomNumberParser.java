package src.main.java.ru.nbki.numberparser;

import java.util.HashMap;
import java.util.Map;

public class CustomNumberParser {

    static Map<Character, Integer> mapOfCodes = new HashMap<>();

    static {
        mapOfCodes.put('0', 0);
        mapOfCodes.put('1', 1);
        mapOfCodes.put('2', 2);
        mapOfCodes.put('3', 3);
        mapOfCodes.put('4', 4);
        mapOfCodes.put('5', 5);
        mapOfCodes.put('6', 6);
        mapOfCodes.put('7', 7);
        mapOfCodes.put('8', 8);
        mapOfCodes.put('9', 9);
    }

    public static void main(String[] args) {
        System.out.println(parseToInt("123432"));
        System.out.println(parseToDouble("-1.2323"));
    }

    private static Double parseToDouble(String str) {
        preValidateDouble(str);
        boolean negative = str.startsWith("-");
        String[] leftAndRight = str.split("\\.");
        Integer leftInt = parseToInt(leftAndRight[0]);
        Integer rightInt = parseToInt(leftAndRight[1]);

        Double divider = Math.pow(10, leftAndRight[1].length());

        double right = (double) rightInt / divider;

        if(negative){
            return  (double) leftInt - right;
        }else {
            return (double) leftInt + right;
        }
    }

    private static Integer parseToInt(String str) {
        preValidateInt(str);
        boolean negative = str.startsWith("-");
        str = str.replace("-", "");
        str = str.replace("+", "");

        char[] chars = str.toCharArray();
        int result = 0;

        for (int i = 0; i < chars.length; i++) {
            int multiplier = (int) Math.pow(10, chars.length - i);
            result += multiplier * mapOfCodes.get(chars[i]);
        }
        result = result / 10;

        if (negative) {
            return -result;
        } else {
            return result;
        }
    }

    private static void preValidateDouble(String str) {
        StringBaseValidator(str);

        StringValidatorNegativeNumber(str);

        if (str.indexOf('.') != -1) {
            if (str.indexOf('.') == 0) {
                throw new NumberFormatException(". находится в начале строки");
            }
        }

    }

    private static void preValidateInt(String str) {
        StringBaseValidator(str);

        if (str.length() >= 11) {
            if (!(str.startsWith("+") || str.startsWith("-"))) {
                throw new IndexOutOfBoundsException("Число больше Integer.MAX_VALUE или Integer.MIN_VALUE");
            }
        }

        StringValidatorNegativeNumber(str);
    }

    private static void StringValidatorNegativeNumber(String str) {
        if (str.indexOf('-') != -1) {
            if (str.indexOf('-') != 0) {
                throw new NumberFormatException("- не находится в начале строки");
            }
        }

        if (str.indexOf('+') != -1) {
            if (str.indexOf('+') != 0) {
                throw new NumberFormatException("+ не находится в начале строки");
            }
        }
    }

    private static void StringBaseValidator(String str) {
        if (str == null) {
            throw new NullPointerException("Переданная строка null");
        }
        String numbers = "0123456789-+.";

        for (char ch : str.toCharArray()) {
            if (numbers.indexOf(ch) == -1) {
                throw new NumberFormatException("Есть не численные символы");
            }
        }
    }
}
