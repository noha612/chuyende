package cuthe.Colussi;

public class Demo_Colussi {

    public static void main(String[] args) {
        char[] x = "ABABCABAB".toCharArray();
        char[] y = "ABABDABACDABABCABAB".toCharArray();
        searchCOLUSSI(x, y);
    }

    static int preColussi(char []x, int m, int[] h, int[] next,
                   int[] shift) {
        int i, k, nd, q, r = 0, s;
        int[] hmax = new int[m+1];
        int[] kmin = new int[m+1];
        int[] nhd0 = new int[m+1];
        int[] rmin = new int[m+1];

        /* Computation of hmax */
        i = k = 1;
        do {
            while (i<m&&x[i] == x[i - k])
                i++;
            hmax[k] = i;
            q = k + 1;
            while (hmax[q - k] + k < i) {
                hmax[q] = hmax[q - k] + k;
                q++;
            }
            k = q;
            if (k == i + 1)
                i = k;
        } while (k <= m);

        /* Computation of kmin */
        for (i = m; i >= 1; --i)
            if (hmax[i] < m)
                kmin[hmax[i]] = i;

        /* Computation of rmin */
        for (i = m - 1; i >= 0; --i) {
            if (hmax[i + 1] == m)
                r = i + 1;
            if (kmin[i] == 0)
                rmin[i] = r;
            else
                rmin[i] = 0;
        }

        /* Computation of h */
        s = -1;
        r = m;
        for (i = 0; i < m; ++i)
            if (kmin[i] == 0)
                h[--r] = i;
            else
                h[++s] = i;
        nd = s;

        /* Computation of shift */
        for (i = 0; i <= nd; ++i)
            shift[i] = kmin[h[i]];
        for (i = nd + 1; i < m; ++i)
            shift[i] = rmin[h[i]];
        shift[m] = rmin[0];

        /* Computation of nhd0 */
        s = 0;
        for (i = 0; i < m; ++i) {
            nhd0[i] = s;
            if (kmin[i] > 0)
                ++s;
        }


        /* Computation of next */
        for (i = 0; i <= nd; ++i)
            next[i] = nhd0[h[i] - kmin[h[i]]];
        for (i = nd + 1; i < m; ++i)
            next[i] = nhd0[m - rmin[h[i]]];
        next[m] = nhd0[m - rmin[h[m - 1]]];

        return(nd);
    }

    static void searchCOLUSSI(char[] x, char[] y) {
        int m = x.length;
        int n = y.length;
        int[] h = new int[m+1];
        int[] next = new int[m+1];
        int[] shift = new int[m+1];
        int i, j, last, nd;

        nd = preColussi(x, m, h, next, shift);

        i = j = 0;
        last = -1;
        while (j <= n - m) {
            while (i < m && last < j + h[i] &&
                    x[h[i]] == y[j + h[i]])
                i++;
            if (i >= m || last >= j + h[i]) {
                System.out.println("index : " + j);
                i = m;
            }
            if (i > nd)
                last = j + m - 1;
            j += shift[i];
            i = next[i];
        }
    }
}
