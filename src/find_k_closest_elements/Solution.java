package find_k_closest_elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhiyong Pan on 2021-01-13.
 */
public class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // range [a, b) is the answer
        int a = 0, b = k;

        for (int i = k; i < arr.length; ++i) {
            if (arr[i] <= x) {
                ++a;
                ++b;
            } else if (arr[i] - x < x - arr[a]) {
                ++a;
                ++b;
            }

            if (arr[a] >= x)
                break;
        }

        List<Integer> ans = new ArrayList<>(k);
        for (int i = a; i < b; ++i) {
            ans.add(arr[i]);
        }
        return ans;
    }
}
