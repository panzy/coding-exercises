package maximum_binary_string_after_change;

import java.util.BitSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    public String maximumBinaryString(String binary) {
        final int n = binary.length();
        BitSet s = new BitSet(n);

        for (int i = 0; i < n; ++i) {
            if (binary.charAt(i) == '1') {
                s.set(i);
            }
        }

        // range of all zeros
        AtomicInteger endA = new AtomicInteger();

        // range of 011...110..0
        AtomicInteger endB = new AtomicInteger();
        AtomicInteger countZeros = new AtomicInteger();

        while (true) {
            int i = findAllZeros(s, n, endA);
            int j = findOnesBracedByZeros(s, n, endB, countZeros);
            if (i != -1 && (j == -1 || i < j)) {
                // change all zeros (at least two) to 11...10
                s.set(i, endA.get() - 1, true);
            } else if (j != -1) {
                // change 01..10..0 (at least one 1) to 1..101..1
                s.set(j, endB.get());
                s.clear(j + countZeros.get());
            } else if (i == -1 && j == -1) {
                // exit if neither of the above two happened
                break;
            }
        }

        return getBinaryString(n, s);
    }

    private static String getBinaryString(int n, BitSet s) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; ++i) {
            sb.append(s.get(i) ? "1" : "0");
        }
        return sb.toString();
    }

    int findAllZeros(BitSet s, int n, AtomicInteger end) {
        int found = -1;
        // find the starting "00"
        for (int i = 0; i + 1 < n; ++i) {
            if (!s.get(i) && !s.get(i + 1)) {
                int j = i + 2;
                // extend the end
                while (j < n && !s.get(j)) ++j;

                end.set(j);
                found = i;
                break;
            }
        }
        return found;
    }

    static int findOnesBracedByZeros(BitSet s, int n, AtomicInteger end, AtomicInteger countZeros) {
        int start = -1;
        // find the starting "01"
        for (int i = 0; i + 1 < n; ++i) {
            if (!s.get(i) && s.get(i + 1)) {

                // search first zero from the end
                int j = n;
                while (j > i + 2 && s.get(j - 1)) --j;

                if (!s.get(j - 1)) {
                    end.set(j);

                    // count the tailing zeros
                    int cnt = 0;
                    for (int k = j - 1; k > i; --k) {
                        if (!s.get(k))
                            ++cnt;
                    }
                    countZeros.set(cnt);

                    start = i;
                }
                break;
            }
        }
        return start;
    }
}
