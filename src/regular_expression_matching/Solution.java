package regular_expression_matching;

/**
 * Created by Zhiyong Pan on 2021-01-08.
 */
public class Solution {
    public boolean isMatch(String s, String p) {
        Pattern pat = new Pattern(p);

        if (!pat.hasNext())
            return s.isEmpty();

        Token token = pat.next(), prevToken = null;
        int i = 0;

        while (i < s.length() && token != null) {
            char c = s.charAt(i);
            if (token.match(c)) {
                ++i;
                if (!token.anyTimes) {
                    prevToken = token;
                    token = pat.hasNext() ? pat.next() : null;
                }
            } else if (token.anyTimes) {
                prevToken = token;
                do {
                    token = pat.hasNext() ? pat.next() : null;

                    // let the prev token eat this one
                    if (token != null && token.target != '.'
                            && token.target == prevToken.target && prevToken.anyTimes)
                        continue;
                    else
                        break;
                } while (true);
            } else if (prevToken != null && prevToken.target == c && prevToken.anyTimes) {
                // drop this token (and don't touch the previous token)
                token = pat.hasNext() ? pat.next() : null;
            } else {
                return false;
            }
        }

        if (i < s.length())
            return false;


        boolean patExhausted = token == null || token.anyTimes;
        prevToken = token;
        while (pat.hasNext()) {
            Token t = pat.next();
            if (t.anyTimes || (prevToken != null && t.target == prevToken.target))
                continue;
            prevToken = t;
        }

        return i == s.length() && patExhausted;
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

        Character lockedTo;

        public Token(char target, boolean anyTimes) {
            this.target = target;
            this.anyTimes = anyTimes;
            lockedTo = null;
        }

        /**
         * Test whether the given char matches this token or not.
         *
         * Notice that this method has side effect: if this token wraps a '.', after the first time
         * it is test against a char, it only matches that char in the future. Of course this rule only
         * matters when this token can match any times.
         */
        public boolean match(char c) {
            if (this.target == c)
                return true;

            if (this.target == '.' && (lockedTo == null || lockedTo == c)) {
                lockedTo = c;
                return true;
            }

            return false;
        }

        @Override
        public String toString() {
            return target + (anyTimes ? "*" : "") + (lockedTo != null ? "->" + lockedTo : "");
        }
    }

    /**
     * A Pattern is a collection of Tokens.
     */
    static class Pattern {
        String p;
        int i;

        public Pattern(String p) {
            this.p = p;//preprocessPattern(p);
            i = 0;
        }

        public boolean hasNext() {
            return i < p.length();
        }

        public Token next() {
            Token t = new Token(p.charAt(i), i + 1 < p.length() && p.charAt(i + 1) == '*');
            i += t.anyTimes ? 2 : 1;
            return t;
        }

        static String preprocessPattern(String p) {
            // preprocess the pattern, replacing "x*x" with "x*".
            char[] chars = new char[p.length()];
            int idx = 0;
            for (int i = 0; i < p.length();) {
                if (p.charAt(i) != '.'
                        && i + 2 < p.length()
                        && p.charAt(i + 1) == '*'
                        && p.charAt(i) == p.charAt(i + 2)) {
                    // ignore [i+2]
                    chars[idx++] = p.charAt(i++);
                    chars[idx++] = p.charAt(i++);
                    ++i;
                } else {
                    chars[idx++] = p.charAt(i++);
                }
            }
            return new String(chars, 0, idx);
        }
    }
}
