package restore_ip_addresses_93;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhiyong Pan on 2021-01-23.
 */
public class Solution {
    ArrayList<String> ans = new ArrayList<>();

    // The current ip, in the form of 4 ints.
    int[] ip = new int[4];

    public List<String> restoreIpAddresses(String s) {
        ans.clear();
        dfs(s, 0, 4);
        return ans;
    }

    /**
     * Search ip numbers.
     * @param s
     * @param offset s.substr(offset) is our search scope.
     * @param count how many numbers do we need to obtain an IP address?
     */
    void dfs(String s, int offset, int count) {
        if (count == 0) {
            if (offset == s.length()) // got an ip address
                ans.add(fmt(ip));
        } else {
            if (offset < s.length()) {
                for (int end = offset + 1; end <= Math.min(offset + 3, s.length()); ++end) {
                    if (s.charAt(offset) == '0' && end - offset > 1)
                        break;
                    int num = Integer.parseInt(s.substring(offset, end));
                    if (num < 256) {
                        ip[4 - count] = num;
                        dfs(s, end, count - 1);
                    }
                }
            }
        }
    }

    /**
     * Format an IP address from 4 numbers.
     */
    private String fmt(int[] numbers) {
        return String.format("%d.%d.%d.%d",
                numbers[0], numbers[1], numbers[2], numbers[3]);
    }

    @Test
    void example1() {
        List<String> ips = restoreIpAddresses("25525511135");
        Assertions.assertEquals(2, ips.size());
        Assertions.assertEquals("255.255.11.135", ips.get(0));
        Assertions.assertEquals("255.255.111.35", ips.get(1));
    }

    @Test
    void example2() {
        List<String> ips = restoreIpAddresses("0000");
        Assertions.assertEquals(1, ips.size());
        Assertions.assertEquals("0.0.0.0", ips.get(0));
    }

    @Test
    void example3() {
        List<String> ips = restoreIpAddresses("1111");
        Assertions.assertEquals(1, ips.size());
        Assertions.assertEquals("1.1.1.1", ips.get(0));
    }

    @Test
    void example4() {
        List<String> ips = restoreIpAddresses("010010");
        Assertions.assertEquals(2, ips.size());
        Assertions.assertEquals("0.10.0.10", ips.get(0));
        Assertions.assertEquals("0.100.1.0", ips.get(1));
    }

    @Test
    void example5() {
        List<String> ips = restoreIpAddresses("101023");
        Assertions.assertEquals(5, ips.size());

        String[] expected = {
                "1.0.10.23", "1.0.102.3", "10.1.0.23", "10.10.2.3", "101.0.2.3"
        };

        for (String ip : expected)
            Assertions.assertTrue(ips.contains(ip));
    }

    @Test
    void example6() {
        List<String> ips = restoreIpAddresses("25525510035");
        Assertions.assertEquals(1, ips.size());
        Assertions.assertEquals("255.255.100.35", ips.get(0));
    }

    @Test
    void example7() {
        List<String> ips = restoreIpAddresses("25525510005");
        Assertions.assertEquals(0, ips.size());
    }
}
