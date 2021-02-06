package increasing_triplet_subsequence;

import org.junit.jupiter.api.Test;

public class SolutionTest {
    @Test
    void ordered() {
        assert(new Solution().increasingTriplet(new int[]{1,2,3,4,5}));
    }

    @Test
    void noTripletExists() {
        assert(!new Solution().increasingTriplet(new int[]{5,4,3,2,1}));
    }

    // the triplet is in the middle
    @Test
    void middle() {
        assert(new Solution().increasingTriplet(new int[]{2,1,5,0,4,6,3,2,1}));
    }

    // the triplet is at the tail
    @Test
    void tail() {
        assert(new Solution().increasingTriplet(new int[]{2,1,5,0,4,6}));
    }

    // the triplet is the sequence itself
    @Test
    void len3() {
        assert(new Solution().increasingTriplet(new int[]{1,2,3}));
    }

    @Test
    void len0() {
        assert(!new Solution().increasingTriplet(new int[]{}));
    }

    @Test
    void len1() {
        assert(!new Solution().increasingTriplet(new int[]{1}));
    }

    @Test
    void len2() {
        assert(!new Solution().increasingTriplet(new int[]{1,2}));
    }

    @Test
    void notContinuous_235() {
        assert(new Solution().increasingTriplet(new int[]{20,100,10,12,5,13}));
    }

    @Test
    void notContinuous_023() {
        assert(new Solution().increasingTriplet(new int[]{1,6,2,5,1}));
    }

    // in order to find the triplet, the earlier candidates must be dropped
    @Test
    void abandonEarlierPair() {
        // in this case, we must drop [8,9] and restart from [1,2].
        assert(new Solution().increasingTriplet(new int[]{8, 9, 1, 2, 3}));
    }

    // in order to find the triplet, the earlier candidates must be dropped
    @Test
    void abandonEarlierPair2() {
        // in this case, we must drop [4,6] and restart from [3,4].
        assert(new Solution().increasingTriplet(new int[]{4,6,3,4,5}));
    }

    // one of the element is -2^31
    @Test
    void minimumElement() {
        assert(new Solution().increasingTriplet(new int[]{20, (int) -Math.pow(2, 31),10,12,5,13}));
    }
}
