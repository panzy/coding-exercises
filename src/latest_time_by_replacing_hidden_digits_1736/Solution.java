package latest_time_by_replacing_hidden_digits_1736;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Did it in a contest.
 *
 * Created by Zhiyong Pan on 2021-01-23.
 */
public class Solution {
    public String maximumTime(String time) {
        int h = 0, m = 0;

        if (time.charAt(0) == '?') {
            h = 20;
        } else {
            h = 10 * (time.charAt(0) - '0');
        }

        if (time.charAt(1) == '?') {
            if (h == 20)
                h = 23;
            else if (h == 10)
                h = 19;
            else {
                assert h == 0;
                h = 9;
            }
        } else {
            h += (time.charAt(1) - '0');
            if (h > 23)
                h -= 10;
        }

        if (time.charAt(3) == '?') {
            m = 50;
        } else {
            m = 10 * (time.charAt(3) - '0');
        }

        if (time.charAt(4) == '?') {
            m += 9;
        } else {
            m += (time.charAt(4) - '0');
        }

        return String.format("%02d:%02d", h, m);
    }

    @Test
    void test() {
        Assertions.assertEquals("23:50", maximumTime("2?:?0"));
        Assertions.assertEquals("09:39", maximumTime("0?:3?"));
        Assertions.assertEquals("19:22", maximumTime("1?:22"));
        Assertions.assertEquals("23:59", maximumTime("??:??"));
        Assertions.assertEquals("15:09", maximumTime("?5:0?"));
    }
}
