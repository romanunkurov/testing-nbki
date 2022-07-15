package src.main.java.ru.nbki.stringreplacer;

public class CustomStringReplacer {

    public static void main(String[] args) {
        System.out.println(replace("replaced string", 'e', 'i'));
    }

    public static String replace (String str, Character oldChar, Character newChar){
        char[] charArray = str.toCharArray();

        for(int i = 0; i < charArray.length; i++){
            if(charArray[i] == oldChar){
                charArray[i] = newChar;
            }
        }

        return new String(charArray);
    }
}
