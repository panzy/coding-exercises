package regular_expression_matching;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Zhiyong Pan on 2021-01-08.
 */
public class Solution {
    public boolean isMatch(String s, String p) {
        Token[] tokens = parsePattern(p);
        return isMatch(s, tokens);
    }

    public boolean isMatch(String s, Token[] tokens) {
        if (tokens.length == 0)
            return s.isEmpty();

        int ti = 0;

        // find the first plain text token
        while (ti < tokens.length && (tokens[ti].anyTimes || tokens[ti].target == '.'))
            ++ti;

        // if there is a plain text token, use it to divide and conquer.
        // notice that there might be multiple dividing point in the string, in that case,
        // we try them all until a successful match is found.
        if (ti < tokens.length) {
            int i = s.indexOf(tokens[ti].target);
            if (i == -1) {
                return false;
            } else {
                boolean found = false;
                do {
                    if (splitAndMatch(s, tokens, ti, i)) {
                        found = true;
                        break;
                    }

                    i = s.indexOf(tokens[ti].target, i + 1);
                } while (i != -1);

                return found;
            }
        }

        ti = 0;
        int i = 0;
        while (i < s.length() && ti < tokens.length) {
            Token token = tokens[ti];
            char c = s.charAt(i);

            if (token.match(c)) {
                ++i;
                if (!token.anyTimes) {
                    ++ti;
                }
            } else if (token.anyTimes) {
                do {
                    ++ti;

                    // let the prev token eat this one
                    if (ti < tokens.length && tokens[ti].target != '.'
                            && tokens[ti].target == tokens[ti - 1].target && tokens[ti - 1].anyTimes)
                        continue;
                    else
                        break;
                } while (true);
            } else if (ti > 0 && tokens[ti - 1].target == c && tokens[ti - 1].anyTimes) {
                // drop this token (and don't touch the previous token)
                ++ti;
            } else {
                return false;
            }
        }

        if (i < s.length())
            return false;

        while (ti < tokens.length) {
            Token t = tokens[ti];
            if (t.actualTimes < t.minTimes)
                return false;
            ++ti;
        }

        return true;
    }

    private boolean splitAndMatch(String s, Token[] tokens, int ti, int i) {
        if (!isMatch(s.substring(0, i), Arrays.copyOfRange(tokens, 0, ti)))
            return false;

        if (!isMatch(s.substring(i + 1), Arrays.copyOfRange(tokens, ti + 1, tokens.length)))
            return false;

        return true;
    }

    static Token[] parsePattern(String p) {
        ArrayList<Token> tokens = new ArrayList<>();
        Token prev = null;
        for (int i = 0; i < p.length(); ) {
            Token t = new Token(p.charAt(i), i + 1 < p.length() && p.charAt(i + 1) == '*');
            t.position = i;
            i += t.anyTimes ? 2 : 1;
            if (prev != null && t.target == prev.target
                    && (t.anyTimes || prev.anyTimes)) {
                // merge with the previous one
                prev.anyTimes = true;

                if (!t.anyTimes)
                    ++prev.minTimes;
            } else {
                tokens.add(t);
                prev = t;
            }
        }
        return tokens.toArray(new Token[tokens.size()]);
    }

    /**
     * A token of a pattern. It can match a char various times.
     */
    static class Token {
        /**
         * The char to match. '.' means any char.
         */
        char target;
        /**
         * Does this token match any (including zero) times or exactly one time?
         */
        boolean anyTimes;

        /**
         * Actual matched times.
         */
        int actualTimes;

        int minTimes;

        /**
         * Position in the regexp. This is for debug purpose.
         */
        int position;

        public Token(char target, boolean anyTimes) {
            this.target = target;
            this.anyTimes = anyTimes;
            actualTimes = 0;
            minTimes = anyTimes ? 0 : 1;
        }

        /**
         * Test whether the given char matches this token or not.
         */
        public boolean match(char c) {
            if (this.target == c) {
                ++actualTimes;
                return true;
            }

            if (this.target == '.') {
                ++actualTimes;
                return true;
            }

            return false;
        }

        @Override
        public String toString() {
            return target + (anyTimes ? "*" : "");
        }
    }
}
