import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {


    public static void main(String[] args) {

        int count3 = 3;
        int count4 = 4;
        int count5 = 5;

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        for (String text : texts){
            new Thread (() -> {
            if (text.length() == 3){
                if (istPalindrom(text)) {
                    countA++;
                } else if (istPalindrom(text)) {
                    countA++;
                } else if (isSortedByAlphabet(text)) {
                    countA++;
                }
            }
            synchronized (countMap) {
                countMap.put(count3, countA);
            }
            }).start();

            new Thread (() -> {
                if (text.length() == 4){
                    if (istPalindrom(text)) {
                        countB++;
                    } else if (istPalindrom(text)) {
                        countB++;
                    } else if (isSortedByAlphabet(text)) {
                        countB++;
                    }
                }
                synchronized (countMap) {
                    countMap.put(count4, countB);
                }
            }).start();

            new Thread (() -> {
                if (text.length() == 5){
                    if (istPalindrom(text)) {
                        countC++;
                    } else if (istPalindrom(text)) {
                        countC++;
                    } else if (isSortedByAlphabet(text)) {
                        countC++;
                    }
                }
                synchronized (countMap) {
                    countMap.put(count5, countC);
                }
            }).start();
       }

       printMap(countMap);

    }

    public static final Map<Integer,Integer> countMap = new HashMap<>();
    public static volatile int countA = 0;
    public static volatile int countB = 0;
    public static volatile int countC = 0;

    public static boolean isSortedByAlphabet(String text){
        for(int i = 0;i<text.length()-1;i++)
            if(text.charAt(i) > text.charAt(i+1))
                return false;
        return true;
    }
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
    public static boolean istPalindrom(String text){
        char[] word = text.toCharArray();
        int i1 = 0;
        int i2 = word.length - 1;
        while (i2 > i1) {
            if (word[i1] != word[i2]) {
                return false;
            }
            ++i1;
            --i2;
        }
        return true;
    }
    public static boolean isSameCharacters(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        char c = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != c) {
                return false;
            }
        }
        return true;
    }
    public static void printMap(Map<Integer, Integer> map) {
        Map.Entry<Integer, Integer> maxEntry = map.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .orElse(null);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Красивых слов с длиной " + entry.getKey() + " - " + entry.getValue() + " шт.");
        }
    }
}
