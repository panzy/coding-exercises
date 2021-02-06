package number_of_students_unable_to_eat_lunch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    void countStudents_clear() {
        int[] students = new int[]{1, 1, 0, 0};
        int[] sandwiches = new int[]{0, 1, 0, 1};
        int expected = 0;
        Assertions.assertEquals(expected, solution.countStudents(students, sandwiches));
    }

    @Test
    void countStudents_left3() {
        int[] students = new int[]{1, 1, 1, 0, 0, 1};
        int[] sandwiches = new int[]{1, 0, 0, 0, 1, 1};
        int expected = 3;
        Assertions.assertEquals(expected, solution.countStudents(students, sandwiches));
    }

    @Test
    void countStudents_leftAll() {
        int[] students = new int[]{0, 0, 0};
        int[] sandwiches = new int[]{1, 1, 1};
        int expected = 3;
        Assertions.assertEquals(expected, solution.countStudents(students, sandwiches));
    }
}
