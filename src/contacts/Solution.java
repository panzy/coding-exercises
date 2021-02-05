package contacts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
        import java.math.*;
        import java.text.*;
        import java.util.*;
        import java.util.regex.*;

/**
 * Created by Zhiyong Pan on 2021-02-04.
 */
public class Solution {
    static class Node {
        char val;
        int cnt = 0;
        HashMap<Character, Node> children = new HashMap<>();

        Node(char val) {
            this.val = val;
        }

        public Node getOrCreate(char c) {
            Node node = children.get(c);
            if (node == null) {
                node = new Node(c);
                children.put(c, node);
            }
            return node;
        }

        public Node get(char c) {
            return children.get(c);
        }
    }

    /*
     * Complete the contacts function below.
     */
    static int[] contacts(String[][] queries) {
        /*
         * Write your code here.
         */

        Node root = new Node('/');
        ArrayList<Integer> res = new ArrayList<>();

        for (String[] q : queries) {
            if (q[0].equals("add")) {
                add(root, q[1]);
            } else if (q[0].equals("find")) {
                res.add(find(root, q[1]));
            }
        }

        return res.stream().mapToInt(i -> i).toArray();
    }

    private static int find(Node root, String w) {
        Node p = root;
        for (int i = 0, n = w.length(); i < n && p != null; ++i) {
            char c = w.charAt(i);
            p = p.get(c);
        }
        return p == null ? 0 : p.cnt;
    }

    private static void add(Node root, String w) {
        Node p = root;
        for (int i = 0, n = w.length(); i < n; ++i) {
            char c = w.charAt(i);
            p = p.getOrCreate(c);
            ++p.cnt;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int queriesRows = Integer.parseInt(scanner.nextLine().trim());

        String[][] queries = new String[queriesRows][2];

        for (int queriesRowItr = 0; queriesRowItr < queriesRows; queriesRowItr++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");

            for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
                String queriesItem = queriesRowItems[queriesColumnItr];
                queries[queriesRowItr][queriesColumnItr] = queriesItem;
            }
        }

        int[] result = contacts(queries);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bufferedWriter.write(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }

    @Test
    void test() {
        String[][] queries = {
                {"add", "hack"},
                {"add", "hackerrank"},
                {"find", "hac"},
                {"find", "hak"},
        };
        int[] expected = {2, 0};
        Assertions.assertArrayEquals(expected, contacts(queries));
    }
}

