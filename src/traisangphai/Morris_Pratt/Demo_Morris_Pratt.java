package traisangphai.Morris_Pratt;

public class Demo_Morris_Pratt {

    public static void main(String[] args) {
//        String pattern = "ABCABAB";
        String pattern = "ABABCABAB";
        String text = "ABABDABACDABABCABAB";
//        String text = "GCATCGCAGAGAGTATACAGTACG";
        searchMP(pattern, text);
    }

    static void searchMP(String pattern, String text) {
        int[] mpNext = preMP(pattern);
        for (int i : mpNext) System.out.print(i + " ");
        int i = 0;
        int j = 0;
        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            if (j == pattern.length()) {
                System.out.println("index : " + (i - j));
                j = mpNext[j - 1];
            } else if (i < text.length() && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) j = mpNext[j - 1];
                else {
                    i++;
                }
            }
        }
    }

    static int[] preMP(String pattern) {
        int[] mpNext = new int[pattern.length()];
        int i = 0;
        int len = i;
        mpNext[i] = len;
        i++;
        char[] X = pattern.toCharArray();
        while (i < mpNext.length) {
            if (X[i] == X[len]) {
                len++;
                mpNext[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = mpNext[len - 1];
                } else {
                    mpNext[len] = 0;
                    i++;
                }
            }
        }
        return mpNext;
    }
}
