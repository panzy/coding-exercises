package maximum_binary_string_after_change;

import java.util.BitSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution0 {
    public String maximumBinaryString(String binary) {
        final int n = binary.length();
        BitSet s = new BitSet(n);

        for (int i = 0; i < n; ++i) {
            if (binary.charAt(i) == '1') {
                s.set(i);
            }
        }

        // range of all zeros
        AtomicInteger startA = new AtomicInteger();
        AtomicInteger endA = new AtomicInteger();

        // range of 011...110..0
        AtomicInteger startB = new AtomicInteger();
        AtomicInteger endB = new AtomicInteger();
        AtomicInteger end2B = new AtomicInteger();

        while (true) {
            int i = findAllZeros(s, n, startA, endA);
            int j = findOnesBracedByZeros(s, n, startB, endB, end2B);
            if (i != -1 && (j == -1 || i < j)) {
                // change all zeros (at least two) to 11...10
                s.set(startA.get(), endA.get() - 1, true);
            } else if (j != -1) {
                // change 01..10..0 (at least one 1) to 1..101..1
                int tailingZeros = end2B.get() - endB.get() + 1;
                s.set(startB.get(), end2B.get());
                s.clear(startB.get() + tailingZeros);
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

    int findAllZeros(BitSet s, int n, AtomicInteger start, AtomicInteger end) {
        int found = -1;
        // find the starting "00"
        for (int i = 0; i + 1 < n; ++i) {
            if (!s.get(i) && !s.get(i + 1)) {
                start.set(i);

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

    int findOnesBracedByZeros(BitSet s, int n, AtomicInteger start, AtomicInteger end, AtomicInteger end2) {
        int found = -1;
        // find the starting "01"
        for (int i = 0; i + 1 < n; ++i) {
            if (!s.get(i) && s.get(i + 1)) {
                start.set(i);

                int j = i + 2;
                // extend the end
                while (j < n && s.get(j)) ++j;

                if (j < n) {
                    end.set(j + 1);

                    // find the end2
                    while (j < n && !s.get(j)) ++j;
                    end2.set(j);

                    found = i;
                }
                break;
            }
        }
        return found;
    }
}
