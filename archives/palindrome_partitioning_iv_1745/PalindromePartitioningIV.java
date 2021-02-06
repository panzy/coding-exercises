package palindrome_partitioning_iv_1745;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-31.
 */
public class PalindromePartitioningIV {

    public boolean checkPartitioning(String s) {
        return new Solution().checkPartitioning(s);
    }

    @Test
    void testPalindromePartitioning() {
        Assertions.assertTrue(checkPartitioning("aaa"));
        Assertions.assertTrue(checkPartitioning("aaaaa" + "ababa" + "bbbb"));
        Assertions.assertTrue(checkPartitioning("abcbdd"));
        Assertions.assertFalse(checkPartitioning("bcbddxy"));
        Assertions.assertTrue(checkPartitioning(
                "gxexv"));
        Assertions.assertTrue(checkPartitioning(
                "gbofdldvwelqiizbievfolrujxnwjmjwsjrjeqecwssgtlteltslfzkblzihcgwjnqaiqbxohcnxulxozzkanaofgoddogfoanakzzoxluxnchoxbqiaqnjwgchizlbkzflstletltgsswceqejrjswjmjwnxjurlofveibziiqlewvdldfobgxebrcrbexv"));
        Assertions.assertTrue(checkPartitioning(
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                        "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                        "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                        "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                        "tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttxxvvvv" +
                        "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv" +
                        "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv" +
                        "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv" +
                        "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv" +
                        "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
        ));
        Assertions.assertFalse(checkPartitioning(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaxaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaxaaaaaaaaaaaaaaaaayaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        ));
        Assertions.assertFalse(checkPartitioning(
                "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis" +
                        "givenastringsreturntrueifitispossibletosplitthestringsintothreenonemptypalindromicsubstringsotherwis"
        ));
    }
}
