import java.util.Scanner;

/**
 * @author vanchung
 */
public class Main {

    public static void main(String[] args) {
        int n = 6;
        int kk= 80;

        int i, a[];
        int c=0;
        a = new int[n+1];
        for(int j = 1; j<n;j++){
            a[j]=j;
        }
        do {
            c++;
            if (c == kk) {
                String s = "";
                for (int j = 1; j <= n; j++)
                    s += a[j] + "";
                System.out.println(s);
            }
            i = n - 1;
            while (i > 0 && a[i] > a[i + 1]) --i;
            if (i > 0) {
                int k = n;
                while (a[k] < a[i]) --k;
                int temp = a[k];
                a[k] = a[i];
                a[i] = temp;
                k = n;
                for (int j = i + 1; j < k; j++, k--) {
                    temp = a[j];
                    a[j] = a[k];
                    a[k] = temp;
                }
            }
        } while (i != 0);
    }
}