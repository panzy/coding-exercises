package simplify_path_71;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Zhiyong Pan on 2021-02-05.
 */
class Solution {
    public String simplifyPath(String path) {
        // A toke of path always ends with '/'. The root is just a '/'.
        Deque<String> tokens = new ArrayDeque<>();

        // Ensure a tailing '/' as a sentinel.
        char[] A = path.endsWith("/") ? path.toCharArray() : (path + '/').toCharArray();

        int n = A.length;
        int wordBegin = 0; // The beginning of the current directory name.

        for (int i = 0; i < n;) {
            if (A[i] == '/') {
                if (wordBegin < i + 1) {
                    // Got a directory name.
                    String dirname = new String(Arrays.copyOfRange(A, wordBegin, i + 1));
                    if (dirname.equals("../")) {
                        // Pop the previous dir, but not the root.
                        if (tokens.size() > 1)
                            tokens.removeLast();
                    } else if (dirname.equals("./")) {
                        // Nothing to do.
                    } else {
                        tokens.addLast(dirname);
                    }
                }
                // move to the end of consequent '/'
                ++i;
                while (i < n && A[i] == '/')
                    ++i;
                wordBegin = i;
            } else {
                ++i;
            }
        }

        // Construct path string from the token queue.
        StringBuilder res = new StringBuilder(path.length());
        while (!tokens.isEmpty()) {
            res.append(tokens.removeFirst());
        }
        // Remove the tailing '/'. Be careful if the whole path is a '/'.
        if (res.length() > 2)
            res.deleteCharAt(res.length() - 1);

        return res.toString();
    }

    @Test
    void example1() {
        Assertions.assertEquals("/home", simplifyPath("/home/"));
    }

    @Test
    void example2() {
        Assertions.assertEquals("/", simplifyPath("/../"));
    }

    @Test
    void example3() {
        Assertions.assertEquals("/home/foo", simplifyPath("/home//foo/"));
    }

    @Test
    void example4() {
        Assertions.assertEquals("/c", simplifyPath("/a/./b/../../c/"));
    }
}
