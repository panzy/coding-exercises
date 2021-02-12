package reverse_shuffle_merge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Reverse Shuffle Merge
 * https://www.hackerrank.com/challenges/reverse-shuffle-merge/problem
 *
 * Created by Zhiyong Pan on 2021-02-07.
 */
public class Solution {
    static class CharState {
        int remains = 0;
        int needed = 0;
    }
    static String reverseShuffleMerge(String s) {
        char[] A = s.toCharArray();
        int n = A.length;
        int[] freq = new int[26];

        // Collect total frequency
        for (char c : A) {
            freq[c - 'a']++;
        }

        // Keep track of each char having been seen.
        CharState[] states = new CharState[26];
        for (char c = 'a'; c <= 'z'; ++c) {
            states[c - 'a'] = new CharState();
            states[c - 'a'].remains = freq[c - 'a'];
            states[c - 'a'].needed = freq[c - 'a'] / 2;
        }

        // Build the answer.
        char[] buf = new char[n / 2];
        int bufIdx = 0;
        int leftMostSelectedCharIndex = n;
        for (int i = n - 1; i >= 0 && bufIdx < n / 2; --i) {
            CharState st = states[A[i] - 'a'];

            // If we have selected enough of this char either final or pooled, ignore it.
            if (st.needed == 0)
                continue;

            if (st.remains <= st.needed) {
                // To ensure enough occurrences of this char in the answer,
                // at least one occurrence has to be selected either here or from the pool.
                // But before appending this char to the answer, all pending chars which are between
                // i and leftMostSelectedCharIndex and smaller than this char should be appended first.
                // Notice that:
                // (1) There may be more than one kind of chars;
                // (2) In each round, all occurrences of the smallest char are all appended;
                // (3) leftMostSelectedCharIndex is updated every time a char occurrence is appended, and will reduce
                //     the next char's chance to be selected. This variable is the key to ensure the answer is a valid
                //     subsequence of the input string.
                for (char c = 'a'; c < A[i] && leftMostSelectedCharIndex - 1 > i; ++c) {
                    for (int j = leftMostSelectedCharIndex - 1; j > i; --j) {
                        if (A[j] == c && (states[A[j] - 'a'].needed > 0)) {
                            buf[bufIdx++] = c;
                            leftMostSelectedCharIndex = j;
                            states[A[j] - 'a'].needed--;
                        }
                    }
                }

                // For the current char, only one occurrence is definitely required at this point.
                // And the selected occurrence should be the as left as possible.
                for (int j = leftMostSelectedCharIndex - 1; j >= i; --j) {
                    if (A[j] == A[i]) {
                        buf[bufIdx++] = A[j];
                        leftMostSelectedCharIndex = j;
                        states[A[j] - 'a'].needed--;
                        break;
                    }
                }
            }

            st.remains--;
        }

        return new String(buf);
    }

    @Test
    void examples() {
        Assertions.assertEquals("abc", reverseShuffleMerge("cabbac"));
        Assertions.assertEquals("egg", reverseShuffleMerge("eggegg"));
        Assertions.assertEquals("agfedcb", reverseShuffleMerge("abcdefgabcdefg"));
        Assertions.assertEquals("aeiou", reverseShuffleMerge("aeiouuoiea"));
    }

    @Test
    void test1() {
        String expected = "aaaaabccigicgjihidfiejfijgidgbhhehgfhjgiibggjddjjd";
        String output = reverseShuffleMerge("djjcddjggbiigjhfghehhbgdigjicafgjcehhfgifadihiajgciagicdahcbajjbhifjiaajigdgdfhdiijjgaiejgegbbiigida");

        char[] expectedChars = expected.toCharArray();
        char[] outputChars = output.toCharArray();
        Arrays.sort(expectedChars);
        Arrays.sort(outputChars);
        Assertions.assertArrayEquals(expectedChars, outputChars);

        Assertions.assertEquals(expected, output);
    }

    @Test
    void test3() {
        Assertions.assertEquals("aaaaaavvcembskuabxddlpbbsgiaskucosdlhndqzovpjlcyerauvrbcugnbluescevrnubgvtzrcwccfzrqgmfpbjnpdqybtsdvobhzsslojqsyfhoghrifhtclcoiputjhpklkrhsdyhlcuevikwyrqxpxhspxllimlowtghssqzivhrjtywweuvejxokgyrnqxzns",
                reverseShuffleMerge("sbcnzxqnrygkocahxjebvueaawwcythjdrhvizqsshgtwdolmillxpshxpxqrywkivceufclhydhshrklkphajbftuapiocjlcthfirhgaohfysqjolssbzhbovdstbyqdpnjbpfmgqrzfctcwcrztvgbegunarvecseoulabaonguckqbtrvuagreyclyjytpvozqdnhldlnsocenuzksawirgsbjobpldjdlrxbricrauuksbmecvvwagnnacaqghmjpzrlsvlpbbcuaddgvlhvuvkxexjcfhxrodmcwlrzyyiksuksshhonahsyzbbprwuitmoyoqurtqsaslevgvpfbzkkhgcnpjdpseuiylluvdetsqssbrxpiclxxvosuqipsqvvwsezhl"));
    }

    @Test
    void test4() {
        String expected = "aaaaaaaaaccddeikusbccfseagluimqvagocfqqcikvcjvhnuezxdcoxffortjyzjmsjvivcxujlsnxcfztwtegpvqqtgkwgijyzkohzhqmfhapweqbqmtfnumdzgolmmsmpgmeoggrtsdcebcvxectzjqeezjplvunofppmnyzzbgheghgklphkqcsjmsjmqabwkoeehktblvgnsbiltyvfrktrpcdkgjwnuckcijpvixosgrssokcmgltbhedgodkhvhtmrnryosnxfdyhbvdtykhddkjvsrmxyfltlztuwpmrftjrgjjlowzrnztizdgtrbkelrjkpeoqpmiwqzzhgoxbkrkfggptbozuyrfehrezenttskjqjyurfkkrjyggnusejtjjonzmkxfjldyltdwrxfervfixqhpyolzvvfnnnmlvmwuyyxzlxrlvlnpvlexixqkwpfmgvvyldysetmyrxqduulqojlfzjolffpgwrfwf";
        String output = reverseShuffleMerge("fwfcrwgpfflojzfiljoqluudqxrymtegsydlyvvgmfgpwkqxixelvpnlvlrxlzxyyuwmvlmnnnfvvzloypchaaqxinfvraefxrwdtlaydcljlfxkmaznojjtjaesunggyjrkkfruyjlnqjksttnegzaenrhbefrybuzobtpglngfkrckbxognhzzqwaimpqoepkcjralekbrtgdziltaznrazwoljjgrjtfrmpwutzltlfyxcmrsvjkddhnkytdvbhydfxnsoyrnrmtahvhkdfogdeghjbtlgmckossrgsaoxijvpjickcunwjgkldcnprtkrfvytlyibsngvlbtkheeokwbalqmjksmjscqkhplkghgehgbjzzynmrppfonuvlpjlzeeqjztcexvcbecdstrggoemgpmsmmlroyglzdmunrfjtmqbkrrfqewpafhfmqhzhokzyjigiwkgkrkrhtqqvpgetwtzflcmxnsljuxcvivjsmjzyjptrofjfxocidxzefznuxnhzvjcgxpnymvkicnqqfchotgavqvwmivulgniatmeoqzymvsfjcqhcondbxqsukpulvuisjvzozedtepydvkoumpyvylzkqjvozhzrhkjfgkggwxjzesxtectvfvpkmfxddhjstomgfjqdyooxezsinfxwrknvtczgwwukwagjvdwiuwmbjigxnbrdyzepgeqfaezzekqvqdkkyiwpdfrjvxzxbltjrmulfceffmuzpzftodalvjejycdyzvgtggbaeedfvsstqomwmmdfsbxocfgqhkxjkqmradotmneufmbhgaklsrxkdkjlysgivqegsqtrrzpwrptelykgeatacslhqotootuhxloutefrksqwuiyvclfcmjkocjxgtqjshiyryccgesfgpjtxjohevathpvdmtppcbsnmsfezkbulprtphgcmqywrlefmbrebemjzfzhiqcolvhdduukstgrlo");

        char[] expectedChars = expected.toCharArray();
        char[] outputChars = output.toCharArray();
        Arrays.sort(expectedChars);
        Arrays.sort(outputChars);
        Assertions.assertArrayEquals(expectedChars, outputChars);

        Assertions.assertEquals(expected, output);
    }
}
