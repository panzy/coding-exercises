package strong_password_checker_420;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-31.
 */
public class PasswordChecker {

    public int strongPasswordChecker(String password) {
        return new Solution().strongPasswordChecker(password);
    }

    @Test
    void testPassword() {
        Assertions.assertEquals(23, strongPasswordChecker("FFFFFFFFFFFFFFF11111111111111111111AAA"));
        // aaaabbbbccccddeeddeeddeedd n=26
        // aa?abb?bcc?cddeeddeeddeedd
        //       ?bcc?cddeeddeeddeedd +6
        //       Bbcc0cddeeddeeddeedd +2
        Assertions.assertEquals(8, strongPasswordChecker("aaaabbbbccccddeeddeeddeedd"));
        Assertions.assertEquals(2, strongPasswordChecker("aaaBBB"));
        Assertions.assertEquals(5, strongPasswordChecker("a"));
        Assertions.assertEquals(3, strongPasswordChecker("aA1"));
        Assertions.assertEquals(0, strongPasswordChecker("1337C0d3"));
        Assertions.assertEquals(3, strongPasswordChecker("aaa"));
        Assertions.assertEquals(3, strongPasswordChecker("..."));
        Assertions.assertEquals(3, strongPasswordChecker("..!"));
        Assertions.assertEquals(3, strongPasswordChecker("!!!"));
        Assertions.assertEquals(3, strongPasswordChecker("aBc"));
        Assertions.assertEquals(3, strongPasswordChecker("aB5"));
        Assertions.assertEquals(1, strongPasswordChecker("aaa123"));
        Assertions.assertEquals(2, strongPasswordChecker("aaabbb"));
        Assertions.assertEquals(3, strongPasswordChecker("1111111111"));
        Assertions.assertEquals(1, strongPasswordChecker("000aA"));
        // bbaaaaaaaaaaaaaaacccccc
        // bbaaAaa0aa?aa?aa?cc?cc? +2
        // bbaaAaa0aa?aa?aacc?cc?  +1
        // bbaaAaa0aa^aa^aacc^cc   +4
        // bbaaAaa0aa^aa^aacc^c    +1
        Assertions.assertEquals(8, strongPasswordChecker("bbaaaaaaaaaaaaaaacccccc"));
        Assertions.assertEquals(10, strongPasswordChecker("1337C0d3XY01234567890123456789"));

        // aaabbb01234567890123456789
        // aa?bb?01234567890123456789
        // aaAbb?01234567890123456789 +1
        // aaAbb567890123456789       +6
        Assertions.assertEquals(7, strongPasswordChecker("aaabbb01234567890123456789"));

        // aaaaaaCCC01234567890123456789
        Assertions.assertEquals(9, strongPasswordChecker("aaaaaaCCC01234567890123456789"));

        Assertions.assertEquals(7, strongPasswordChecker("aaaaCCC01234567890123456789"));

        Assertions.assertEquals(6, strongPasswordChecker("aaaBBB01234567890123456789"));
    }
}
