package check_if_two_string_arrays_are_equivalent;

/**
 * Compare char by char.
 *
 * Created by Zhiyong Pan on 2021-01-08.
 */
public class Solution {
    static class Cursor {
        String[] arr;
        int totalChars = 0;
        int remainChars = 0;
        int strIdx = 0;
        int charIdx = 0;

        Cursor(String[] arr) {
            this.arr = arr;

            for (String s : arr)
                totalChars += s.length();

            remainChars = totalChars;
        }

        boolean hasNext() {
            return remainChars > 0;
        }

        char next() {
            --remainChars;
            if (charIdx == arr[strIdx].length()) {
                ++strIdx;
                charIdx = 0;
            }
            return arr[strIdx].charAt(charIdx++);
        }
    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        Cursor c1 = new Cursor(word1);
        Cursor c2 = new Cursor(word2);

        if (c1.totalChars != c2.totalChars)
            return false;

        while (c1.hasNext()) {
            if (!c2.hasNext())
                return false;

            if (c1.next() != c2.next())
                return false;
        }

        if (c2.hasNext())
            return false;

        return true;
    }
}
