package Rabin_Karp;

//https://nhannguyen95.github.io/rabin-karp-algorithm/
public class Main {
    static final int BASE = 2;
    static final int PRIME = 101;
    static int valueCurrent;
    static int hashCurrent;
    static int hashOrigin;
    static String pattern = "ABABCABAB";
    static String text = "ABABDABACDABABCABAB";

    public static void main(String[] args) {

        hashOrigin = initHashP(pattern);
        hashCurrent = initHashT(text.substring(0, pattern.length()));

        System.out.println(pattern + " : " + hashOrigin);
        System.out.println(text.substring(0, pattern.length()) + " : " + hashCurrent);


        for (int i = 1; i < text.length(); i++) {
            hash(text.charAt(i), text.charAt(i + pattern.length()-1));
            System.out.println(text.substring(i, i + pattern.length()) + " : " + hashCurrent);
        }
    }

    private static int initHashP(String init) {
        char[] temp = init.toCharArray();
        int mul = 1;
        for (int i = temp.length - 1; i >= 0; i--) {
            valueCurrent += temp[i] * mul;
            mul *= BASE;
        }
        hashCurrent = valueCurrent % PRIME;
        return hashCurrent;
    }

    private static int initHashT(String init) {
        char[] temp = init.toCharArray();
        int mul = 1;
        for (int i = temp.length - 1; i >= 0; i--) {
            valueCurrent += temp[i] * mul;
            mul *= BASE;
        }
        hashCurrent = valueCurrent % PRIME;
        return hashCurrent;
    }

    private static void hash(char old, char next) {
        valueCurrent = (int) ((valueCurrent - old * Math.pow(BASE, pattern.length())) * BASE + next);

        hashCurrent = valueCurrent % PRIME;
    }
}
