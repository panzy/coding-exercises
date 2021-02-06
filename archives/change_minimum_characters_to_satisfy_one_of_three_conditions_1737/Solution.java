package change_minimum_characters_to_satisfy_one_of_three_conditions_1737;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-24.
 */
public class Solution {
    public int minCharacters(String a, String b) {
        // a's frequency array.
        int[] fa = new int[26];

        // b's frequency array.
        int[] fb = new int[26];

        for (int i = 0, n = a.length(); i < n; ++i) {
            ++fa[a.charAt(i) - 'a'];
        }

        for (int i = 0, n = b.length(); i < n; ++i) {
            ++fb[b.charAt(i) - 'a'];
        }

        int cost1 = separate(fa, fb);
        int cost2 = separate(fb, fa);
        int cost3 = unify(fa) + unify(fb);
        return Math.min(Math.min(cost1, cost2), cost3);
    }

    // Returns the cost of making a string consist of only one distinct letter.
    private int unify(int[] freq) {
        int max = freq[0];
        int sum = 0;
        for (int f : freq) {
            max = Math.max(max, f);
            sum += f;
        }
        return sum - max;
    }

    // Returns the cost of making every letter in string a is strictly less than every letter in string b.
    private int separate(int[] fa, int[] fb) {
        // The minimal letter in string b could be 'b', 'c', ... 'y', 'z'.
        // They're where we put the separator.
        // For each separator position, compute the cost, and pick the minimal one in the end.

        int cost = Integer.MAX_VALUE;
        for (char sep = 'b'; sep <= 'z'; ++sep) {
            int c = 0;
            for (char a = 'a'; a < sep; ++a)
                c += fb[a - 'a'];
            for (char a = sep; a <= 'z'; ++a)
                c += fa[a - 'a'];
            cost = Math.min(cost, c);
        }

        return cost;
    }

    @Test
    void example1() {
        Assertions.assertEquals(2, minCharacters("aba", "caa"));
    }

    @Test
    void example2() {
        Assertions.assertEquals(3, minCharacters("dabadd", "cda"));
    }

    @Test
    void test65() {
        Assertions.assertEquals(2, minCharacters("azzzz", "bzzzz"));
    }
}
