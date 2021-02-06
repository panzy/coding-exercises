package frequency_queries;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * Created by Zhiyong Pan on 2021-02-05.
 */
public class FrequencyQueries {
    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        // key = number, value = frequency
        HashMap<Integer, Integer> freq = new HashMap<>();

        // key = freq, value = number count
        HashMap<Integer, Integer> nums = new HashMap<>();

        List<Integer> res = new LinkedList<>();

        for (List<Integer> q : queries) {
            int cmd = q.get(0);
            int x = q.get(1);
            if (cmd == 1) {
                // Add

                int oldFreq = freq.getOrDefault(x, 0);
                int newFreq = oldFreq + 1;
                if (oldFreq > 0) {
                    nums.put(oldFreq, nums.get(oldFreq) - 1);
                }
                nums.put(newFreq, nums.getOrDefault(newFreq, 0) + 1);

                freq.put(x, newFreq);
            } else if (cmd == 2) {
                // Delete

                int oldFreq = freq.getOrDefault(x, 0);
                if (oldFreq > 0) {
                    int newFreq = oldFreq - 1;
                    freq.put(x, newFreq);
                    if (newFreq > 0) {
                        nums.put(newFreq, nums.get(newFreq) + 1);
                    }
                    nums.put(oldFreq, nums.get(oldFreq) - 1);
                }
            } else if (cmd == 3) {
                res.add(nums.getOrDefault(x, 0) > 0 ? 1 : 0);
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int q = Integer.parseInt(bufferedReader.readLine().trim());

            List<List<Integer>> queries = new ArrayList<>();

            IntStream.range(0, q).forEach(i -> {
                try {
                    queries.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                    .map(Integer::parseInt)
                                    .collect(toList())
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            List<Integer> ans = freqQuery(queries);

            bufferedWriter.write(
                    ans.stream()
                            .map(Object::toString)
                            .collect(joining("\n"))
                            + "\n"
            );

            bufferedReader.close();
        }
        bufferedWriter.close();
    }

    @Test
    void test0() {
        Integer[][] input = {
                {1, 5},
                {1, 6},
                {3, 2},
                {1, 10},
                {1, 10},
                {1, 6},
                {2, 5},
                {3, 2},
        };
        List<List<Integer>> queries = Arrays.stream(input)
                .map(a -> Arrays.stream(a).collect(toList()))
                .collect(toList());
        List<Integer> res = freqQuery(queries);
        Assertions.assertEquals(0, res.get(0));
        Assertions.assertEquals(1, res.get(1));
    }
}
