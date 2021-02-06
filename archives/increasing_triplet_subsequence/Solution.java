package increasing_triplet_subsequence;

public class Solution {
    public boolean increasingTriplet(int[] nums) {
        // The first two numbers of the triplet we're searching.
        // There might be multiple increasing triplets, and we care about only the
        // first one.
        int a = 0, b = 0;
        // a flag marking whether a pair of |a| anb |b| have been found.
        boolean abFound = false;

        // search.
        // We will keep |a| and |b| minimized.
        for (int i = 1; i < nums.length; ++i) {
            if (abFound && nums[i] > b) {
                // found a triplet -- [i] is the 3rd of it.
                return true;
            }

            if (abFound && nums[i] < b && nums[i] > a) {
                // found a better |b|.
                b = nums[i];
            }

            if (abFound && nums[i] > nums[i - 1] && nums[i] < b) {
                // found a better pair
                a = nums[i - 1];
                b = nums[i];
            }

            if (!abFound && nums[i] > nums[i - 1]) {
                // found the first possible |a| and |b|
                a = nums[i - 1];
                b = nums[i];
                abFound = true;
            }
        }
        return false;
    }
}
