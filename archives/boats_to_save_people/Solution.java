package boats_to_save_people;

import java.util.Arrays;

/**
 * Greedy.
 *
 * Created by Zhiyong Pan on 2021-01-13.
 */
public class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        int ans = 0;
        int room = limit; // remained room on the current boat

        for (int i = 0, j = people.length - 1; i <= j;) {
            if (room == limit) { // a new boat, guaranteed to be able to carry the most weighted person
                room -= people[j--];
            } else if (room >= people[j]) {
                --j; // carry the 2nd person
                ++ans; // next boat
                room = limit; // reset room
            } else if (room >= people[i]) {
                ++i; // carry the 2nd person
                ++ans; // next boat
                room = limit; // reset room
            } else { // no room for the 2nd person
                ++ans; // next boat
                room = limit; // reset room
            }
        }

        if (room < limit) // the last boat
            ++ans;

        return ans;
    }
}
