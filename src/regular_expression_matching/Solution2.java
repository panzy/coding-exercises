package regular_expression_matching;

import java.util.ArrayList;
import java.util.Stack;

/**
 * A failed attempting to get rid of recursion from the previous solution.
 *
 * Created by Zhiyong Pan on 2021-01-08.
 */
public class Solution2 {
    public boolean isMatch(String s, String p) {
        Token[] tokens = parsePattern(p);

        int i = 0; // token index
        int j = 0; // char index

        while (i < tokens.length && j < s.length()) {
            Token t = tokens[i];
            char c = s.charAt(j);

            if (t.match(c)) {
                t.stack.push(c);
                if (!t.anyTimes) {
                    ++i;
                }
                ++j;
            } else if (t.anyTimes) {
                // move to next token and retry
                ++i;
            } else if (borrow(tokens, i, i - 1)) {
                ++i;
            } else {
                break;
            }
        }

        // there shouldn't be any remaining string
        if (j < s.length())
            return false;

        // for a remaining pattern token:
        // (1) if it's optional, ignore it; otherwise,
        // (2) try borrowing a char from its previous token.
        // XXX this approach does not work, here is a counter case:
        // Assertions.assertTrue(solution.isMatch("aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s"));
        while (i < tokens.length) {
            if (tokens[i].stack.size() < tokens[i].minTimes) {
                if (borrow(tokens, i, i - 1))
                    ++i;
                else
                    break;
            } else {
                ++i;
            }
        }

        return i == tokens.length;
    }

    static boolean borrow(Token[] tokens, int i, int j) {

        Token curr = tokens[i];

        if (i == 0)
            return false;

        Token prev = tokens[j];

        // recursively borrow for prev
        if (prev.stack.isEmpty()) {
            if (prev.anyTimes) {
                return j > 0 ? borrow(tokens, i, j - 1) : false;
            } else {
                if (j == 0 || !borrow(tokens, j, j - 1))
                    return false;
            }
        }

        // borrow from prev
        if (curr.match(prev.stack.peek())) {
            curr.stack.push(prev.stack.pop());

            if (prev.stack.size() < prev.minTimes) {
                return borrow(tokens, j, j - 1);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    static Token[] parsePattern(String p) {
        ArrayList<Token> tokens = new ArrayList<>();
        for (int i = 0; i < p.length(); ) {
            Token t = new Token(p.charAt(i), i + 1 < p.length() && p.charAt(i + 1) == '*');
            t.position = i;
            i += t.anyTimes ? 2 : 1;
            tokens.add(t);
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
         * Captured chars.
         */
        Stack<Character> stack;

        /**
         * At least how many chars must this token capture?
         */
        int minTimes;

        /**
         * Position in the regexp. This is for debug purpose.
         */
        int position;

        public Token(char target, boolean anyTimes) {
            this.target = target;
            this.anyTimes = anyTimes;
            minTimes = anyTimes ? 0 : 1;
            stack = new Stack<>();
        }

        /**
         * Test whether the given char matches this token or not.
         */
        public boolean match(char c) {
            if (this.target == c) {
                return true;
            }

            if (this.target == '.') {
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
