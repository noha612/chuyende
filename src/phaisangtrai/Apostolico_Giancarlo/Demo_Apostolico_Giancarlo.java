package phaisangtrai.Apostolico_Giancarlo;

public class Demo_Apostolico_Giancarlo {

    public static void main(String[] args) {
        char[] x = "ABAB".toCharArray();
        char[] y = "ABABDABACDABABCABAB".toCharArray();
        searchAG(x, y);
    }

    static void preBmBc(char[] x, int m, int bmBc[]) {
        int i;

        for (i = 0; i < 256; ++i)
            bmBc[i] = m;
        for (i = 0; i < m - 1; ++i)
            bmBc[x[i]] = m - i - 1;
    }

    static void preBmGs(char[] x, int m, int bmGs[], int[] suff) {
        int i, j;

        int f = 0, g, h;
        suff[m - 1] = m;
        g = m - 1;
        for (h = m - 2; h >= 0; --h) {
            if (h > g && suff[h + m - 1 - f] < h - g)
                suff[h] = suff[h + m - 1 - f];
            else {
                if (h < g)
                    g = h;
                f = h;
                while (g >= 0 && x[g] == x[g + m - 1 - f])
                    --g;
                suff[h] = f - g;
            }
        }


        for (i = 0; i < m; ++i)
            bmGs[i] = m;
        j = 0;
        for (i = m - 1; i >= 0; --i)
            if (suff[i] == i + 1)
                for (; j < m - 1 - i; ++j)
                    if (bmGs[j] == m)
                        bmGs[j] = m - 1 - i;
        for (i = 0; i <= m - 2; ++i)
            bmGs[m - 1 - suff[i]] = m - 1 - i;
    }

    static void searchAG(char[] x, char[] y) {
        int m = x.length;
        int n = y.length;
        int[] bmGs = new int[m];
        int[] bmBc = new int[256];
        int i, j, k, s, shift;
        int[] skip = new int[m];
        int[] suff = new int[m];

        preBmGs(x, m, bmGs, suff);
        preBmBc(x, m, bmBc);

        for (int temp : suff) System.out.print(temp + " ");

        j = 0;
        while (j <= n - m) {
            i = m - 1;
            while (i >= 0) {
                k = skip[i];
                s = suff[i];
                if (k > 0)
                    if (k > s) {
                        if (i + 1 == s)
                            i = (-1);
                        else
                            i -= s;
                        break;
                    } else {
                        i -= k;
                        if (k < s)
                            break;
                    }
                else {
                    if (x[i] == y[i + j])
                        --i;
                    else
                        break;
                }
            }
            if (i < 0) {
                System.out.println("index : " + j);
                skip[m - 1] = m;
                shift = bmGs[0];
            } else {
                skip[m - 1] = m - 1 - i;
                shift = Math.max(bmGs[i], bmBc[y[i + j]] - m + 1 + i);
            }
            j += shift;
            for (int t = 0; t < m - shift; t++) skip[t] += shift;
        }
    }
}
