package abbreviation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * HackerRank problem: Abbreviation
 * https://www.hackerrank.com/challenges/abbr/problem
 *
 * The recursive approach is helpful for understanding the problem, while the iterative one is efficient.
 *
 * Created by Zhiyong Pan on 2021-02-09.
 */
public class Solution {
    static String abbreviation(String a, String b) {
        char[] A = a.toCharArray();
        char[] B = b.toCharArray();
        int m = A.length;
        int n = B.length;

        // dp[i][j] = whether substring a[0:i) and b[0:j) match.
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Initialize the first row and col of the db table.
        dp[0][0] = true;
        // dp[0][j] is false, where j > 0.
        // dp[i][0] is true only if A[i] and all its preceded letters are lowercase.
        for (int i = 1; i < m; ++i) {
            dp[i][0] = 'a' <= A[i - 1] && A[i - 1] <= 'z' && dp[i - 1][0];
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (A[i] == B[j])
                    dp[i + 1][j + 1] = dp[i][j];
                else if (A[i] >= 'A' && A[i] <= 'Z')
                    dp[i + 1][j + 1] = false;
                else if (A[i] - 'a' == B[j] - 'A') // capitalize or drop
                    dp[i + 1][j + 1] = dp[i][j] || dp[i][j + 1];
                else // drop
                    dp[i + 1][j + 1] = dp[i][j + 1];
            }
        }

        return dp[m][n] ? "YES" : "NO";
//        return check(A, B, 0, 0) ? "YES" : "NO";
    }

    /**
     * Recursive approach, correct but slow (would be improved by introducing memo).
     */
    private static boolean check(char[] A, char[] B, int i, int j) {
        if (i == A.length)
            return j == B.length;
        if (j == B.length) {
            // return true only if remaining A are all lowercase letters.
            while (i < A.length && 'a' <= A[i] && A[i] <= 'z') ++i;
            return i == A.length;
        }

        if (A[i] == B[j])
            return check(A, B, i + 1, j + 1);
        if ('A' <= A[i] && A[i] <= 'Z') // can't drop a capitalized letter
            return false;
        if (A[i] - 'a' == B[j] - 'A')
            return check(A, B, i + 1, j + 1) || check(A, B, i + 1, j);
        return check(A, B, i + 1, j);
    }

    @Test
    void examples() {
        Assertions.assertEquals("NO", abbreviation("XXVVnDEFYgYeMXzWINQYHAQKKOZEYgSRCzLZAmUYGUGILjMDET", "XXVVDEFYYMXWINQYHAQKKOZEYSRCLZAUYGUGILMDETQVWU"));
        Assertions.assertEquals("YES", abbreviation( "PVJSNVBDXABZYYGIGFYDICWTFUEJMDXADhqcbzva", "PVJSNVBDXABZYYGIGFYDICWTFUEJMDXAD"));
        Assertions.assertEquals("YES", abbreviation("QOTLYiFECLAGIEWRQMWPSMWIOQSEBEOAuhuvo", "QOTLYFECLAGIEWRQMWPSMWIOQSEBEOA"));
        Assertions.assertEquals("YES", abbreviation("DRFNLZZVHLPZWIupjwdmqafmgkg", "DRFNLZZVHLPZWI"));
        Assertions.assertEquals("NO", abbreviation("SLIHGCUOXOPQYUNEPSYVDaEZKNEYZJUHFXUIL", "SLIHCUOXOPQYNPSYVDEZKEZJUHFXUIHMGFP"));
        Assertions.assertEquals("YES", abbreviation("RYASPJNZEFHEORROXWZFOVDWQCFGRZLWWXJVMTLGGnscruaa", "RYASPJNZEFHEORROXWZFOVDWQCFGRZLWWXJVMTLGG"));
        Assertions.assertEquals("YES", abbreviation("AVECtLVOXKPHIViTZViLKZCZAXZUZRYZDSTIHuCKNykdduywb", "AVECLVOXKPHIVTZVLKZCZAXZUZRYZDSTIHCKN"));
        Assertions.assertEquals("YES", abbreviation("wZPRSZwGIMUAKONSVAUBUgSVPBWRSTJZECxMTQXXA", "ZPRSZGIMUAKONSVAUBUSVPBWRSTJZECMTQXXA"));
        Assertions.assertEquals("YES", abbreviation("SYIHDDSMREKXOKRFDQAOZJQXRIDWXPYINFZCEFYyxu", "SYIHDDSMREKXOKRFDQAOZJQXRIDWXPYINFZCEFY"));
        Assertions.assertEquals("YES", abbreviation("EIZGAWWDCSJBBZPBYVNKRDEWVZnSSWZIw", "EIZGAWWDCSJBBZPBYVNKRDEWVZSSWZI"));

        Assertions.assertEquals("NO", abbreviation("AbcDE", "AFDE"));
        Assertions.assertEquals("YES", abbreviation("aB", "B"));
        Assertions.assertEquals("YES", abbreviation("daBcd", "ABC"));
        Assertions.assertEquals("YES", abbreviation("AbcDE", "ABDE"));
        Assertions.assertEquals("YES", abbreviation("AbcBDE", "ABDE"));
        Assertions.assertEquals("NO", abbreviation("daBcd", "ABCDEF"));
    }
}
