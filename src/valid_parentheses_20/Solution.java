package valid_parentheses_20;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * Created by Zhiyong Pan on 2021-01-20.
 */
public class Solution {
    public boolean isValid(String s) {
        Stack<Character> unpairedParentheses = new Stack<>();
        for (int i = 0, n = s.length(); i < n; ++i) {
            char c = s.charAt(i);
            switch (c) {
                case ')':
                    if (unpairedParentheses.isEmpty() || unpairedParentheses.pop() != '(')
                        return false;
                    else
                        break;
                case '}':
                    if (unpairedParentheses.isEmpty() || unpairedParentheses.pop() != '{')
                        return false;
                    else
                        break;
                case ']':
                    if (unpairedParentheses.isEmpty() || unpairedParentheses.pop() != '[')
                        return false;
                    else
                        break;
                default:
                    unpairedParentheses.push(c);
                    break;
            }
        }

        return unpairedParentheses.isEmpty();
    }

    @Test
    void example1() {
        Assertions.assertTrue(isValid("()"));
        Assertions.assertTrue(isValid("()[]{}"));
        Assertions.assertTrue(isValid("()[]{[()]}"));
        Assertions.assertFalse(isValid("(}"));
        Assertions.assertFalse(isValid("(("));
        Assertions.assertFalse(isValid(")"));
    }
}
