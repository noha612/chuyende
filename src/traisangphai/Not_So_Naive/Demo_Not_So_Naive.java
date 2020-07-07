package traisangphai.Not_So_Naive;

public class Demo_Not_So_Naive {
    static String pattern = "ABABCABAB";
    static String text = "ABABDABACDABABCABAB";

    public static void main(String[] args) {
        int shiftIfEqual = 1;
        int shiftIfNotEqual = 2;
        if (pattern.charAt(0) != pattern.charAt(1)) {
            shiftIfEqual = 2;
            shiftIfNotEqual = 1;
        }

        int i = 0;
        while (i <= text.length() - pattern.length()) {
            if (pattern.charAt(1) == text.charAt(i + 1)) {
                if (text.substring(i, i + pattern.length()).equals(pattern)) {
                    System.out.println("index : " + i);
                }
                i += shiftIfEqual;
            } else {
                i += shiftIfNotEqual;
            }
        }
    }
}
