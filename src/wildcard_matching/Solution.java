package wildcard_matching;

import java.util.ArrayList;

/**
 * Adapted from {@link regular_expression_matching.Solution}.
 *
 * TODO clean the code.
 *
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

        if (tokens[0].isPlain()) {
            int i = tokens[0].match(s, 0);
            if (i == -1)
                return false;
            else
                return isMatch(s.substring(i), copyTokensWithoutState(tokens, 1, tokens.length));
        }

        if (tokens[tokens.length - 1].isPlain()) {
            int tlen = tokens[tokens.length - 1].target.length();
            int i = tokens[tokens.length - 1].match(s, s.length() - tlen);
            if (i == -1)
                return false;
            else
                return isMatch(s.substring(0, s.length() - tlen),
                        copyTokensWithoutState(tokens, 0, tokens.length - 1));
        }

        int ti = -1;

        // find the longest plain text token
        for (int i = 0; i < tokens.length; ++i) {
            if (tokens[i].isPlain()) {
                if (ti == -1 ||
                        (tokens[ti].target.length() < tokens[i].target.length())) {
                    ti = i;
                }
            }
        }

        // if there is a plain text token, use it to divide and conquer.
        // notice that there might be multiple dividing point in the string, in that case,
        // we try them all until a successful match is found.
        if (ti != -1) {
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
            int j = token.match(s, i);

            if (j != -1) {
                token.capturedChars += j - i;
                i = j;
                ++ti;
            } else {
                break;
            }
        }

        if (i < s.length())
            return false;

        while (ti < tokens.length) {
            if (tokens[ti].anyTimes || borrow(tokens, ti, ti - 1))
                ++ti;
            else
                break;
        }

        return ti == tokens.length;
    }

    static boolean borrow(Token[] tokens, int i, int j) {

        Token curr = tokens[i];

        // Only "?" need to borrow:
        // "*" does not need to borrow;
        // plain text should have been used to divide the string.
        assert curr.target.equals("?");

        if (i == 0)
            return false;

        Token prev = tokens[j];

        // recursively borrow for prev
        if (prev.capturedChars == 0) {
            if (prev.anyTimes) {
                return j > 0 ? borrow(tokens, i, j - 1) : false;
            } else {
                if (j == 0 || !borrow(tokens, j, j - 1))
                    return false;
            }
        }

        // borrow from prev
        ++curr.capturedChars;
        --prev.capturedChars;

        if (prev.capturedChars < prev.minTimes) {
            return borrow(tokens, j, j - 1);
        } else {
            return true;
        }
    }

    private Token[] copyTokensWithoutState(Token[] tokens, int begin, int end) {
        Token[] a = new Token[end - begin];
        for (int i = begin; i < end; ++i) {
            a[i - begin] = new Token(tokens[i].target);
        }
        return a;
    }

    private boolean splitAndMatch(String s, Token[] tokens, int ti, int i) {
        if (!isMatch(s.substring(0, i), copyTokensWithoutState(tokens, 0, ti)))
            return false;

        if (!isMatch(s.substring(i + tokens[ti].target.length()),
                copyTokensWithoutState(tokens, ti + 1, tokens.length)))
            return false;

        return true;
    }

    static Token[] parsePattern(String p) {
        ArrayList<Token> tokens = new ArrayList<>();
        for (int i = 0; i < p.length(); ++i) {
            if (p.charAt(i) == '?') {
                tokens.add(new Token(p.charAt(i)));
            } else if (p.charAt(i) == '*') {
                int j = i + 1;
                while (j < p.length() && p.charAt(j) == '*')
                    ++j;
                tokens.add(new Token("*"));
                i = j - 1;
            } else {
                int j = i + 1;
                while (j < p.length() && p.charAt(j) != '?' && p.charAt(j) != '*')
                    ++j;
                tokens.add(new Token(p.substring(i, j)));
                i = j - 1;
            }
        }
        return tokens.toArray(new Token[tokens.size()]);
    }

    /**
     * A token of a pattern. It can match a char various times.
     */
    static class Token {
        /**
         * The char to match. '?' means any char.
         */
        String target;
        /**
         * Does this token match any (including zero) times or exactly one time?
         */
        boolean anyTimes;

        /**
         * Record how many chars were captured by this token.
         */
        int capturedChars;

        int minTimes;

        public Token(String target) {
            this.target = target;
            this.anyTimes = target.equals("*");
            capturedChars = 0;
            minTimes = anyTimes ? 0 : 1;
        }

        public Token(char c) {
            this("" + c);
        }

        public int match(String s, int begin) {
            if (target.equals("?")) {
                if (begin < s.length())
                    return begin + 1;
                else
                    return -1;
            } else if (target.equals("*")) {
                return s.length();
            } else if (s.startsWith(target, begin)) {
                return begin + target.length();
            }
            return -1;
        }

        public boolean isPlain() {
            return !target.equals("*") && !target.equals("?");
        }

        @Override
        public String toString() {
            return "" + target;
        }
    }
}
