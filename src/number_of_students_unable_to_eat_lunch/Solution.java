package number_of_students_unable_to_eat_lunch;

import java.util.LinkedList;

public class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        int j = 0; // sandwich stack top's position in the array

        LinkedList<Integer> stu = new LinkedList<>();
        for (int i : students) {
            stu.add(i);
        }

        while (j < sandwiches.length) {
            if (stu.peekFirst() == sandwiches[j]) {
                // eat
                stu.pop();
                ++j;
            } else {
                if (-1 == stu.indexOf(sandwiches[j])) {
                    break;
                }
                while (stu.peekFirst() != sandwiches[j]) {
                    // move the curr student to the end
                    stu.add(stu.pop());
                }
            }
        }

        return stu.size();
    }
}
