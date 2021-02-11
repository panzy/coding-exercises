package crossword_puzzle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Crossword Puzzle
 * https://www.hackerrank.com/challenges/crossword-puzzle/problem
 *
 * Created by Zhiyong Pan on 2021-02-11.
 */
public class CrosswordPuzzle {
    final static int m = 10, n = 10;
    static String[] crosswordPuzzle(String[] crossword, String words) {

        char[][] grid = new char[crossword.length][];
        for (int i = 0; i < grid.length; ++i) {
            grid[i] = crossword[i].toCharArray();
        }

        solve(grid, words.split(";"));

        return Arrays.stream(grid).map(chars -> new String(chars))
                .collect(Collectors.toList()).toArray(new String[grid.length]);
    }

    static boolean solve(char[][] grid, String[] words) {
        // for each word that can fit here
        for (int wi = 0; wi < words.length; ++wi) {
            String w = words[wi];
            if (w != null) {
                // Find a place for it
                for (int r = 0; r < m; ++r) {
                    for (int c = 0; c < n; ++c) {
                        char[] backup = placeVert(grid, r, c, w);
                        if (backup != null) {
                            words[wi] = null;
                            if (solve(grid, words))
                                return true;

                            // Restore grid
                            words[wi] = w;
                            restoreVert(grid, r, c, backup);
                        }

                        backup = placeHori(grid, r, c, w);
                        if (backup != null) {
                            words[wi] = null;
                            if (solve(grid, words))
                                return true;

                            // Restore grid
                            words[wi] = w;
                            restoreHori(grid, r, c, backup);
                        }
                    }
                }
                return false;
            }
        }

        return true;
    }

    private static char[] placeVert(char[][] grid, int r, int c, String w) {
        if ((grid[r][c] == '-' || grid[r][c] == w.charAt(0)) && (r == 0 || grid[r - 1][c] == '+')) {
            int r2 = r + 1;
            while (r2 - r < w.length() && r2 < m &&
                    (grid[r2][c] == '-' || grid[r2][c] == w.charAt(r2 - r)))
                ++r2;
            if (r2 - r == w.length()) {
                char[] bakup = new char[r2 - r];
                for (int i = r; i < r2; ++i) {
                    bakup[i - r] = grid[i][c];
                    grid[i][c] = w.charAt(i - r);
                }
                return bakup;
            }
        }

        return null;
    }

    private static char[] placeHori(char[][] grid, int r, int c, String w) {
        if ((grid[r][c] == '-' || grid[r][c] == w.charAt(0)) && (c == 0 || grid[r][c - 1] == '+')) {
            int c2 = c + 1;
            while (c2 - c < w.length() && c2 < n &&
                    (grid[r][c2] == '-' || grid[r][c2] == w.charAt(c2 - c)))
                ++c2;
            if (c2 - c == w.length()) {
                char[] bakup = new char[c2 - c];
                for (int i = c; i < c2; ++i) {
                    bakup[i - c] = grid[r][i];
                    grid[r][i] = w.charAt(i - c);
                }
                return bakup;
            }
        }

        return null;
    }

    private static void restoreVert(char[][] grid, int r, int c, char[] backup) {
        for (int i = 0; i < backup.length; ++i)
            grid[r + i][c] = backup[i];
    }

    private static void restoreHori(char[][] grid, int r, int c, char[] backup) {
        for (int i = 0; i < backup.length; ++i)
            grid[r][c + i] = backup[i];
    }

    @Test
    void test1() {
        String[] grid = {
                "+-++++++++",
                "+-++++++++",
                "+-++++++++",
                "+-----++++",
                "+-+++-++++",
                "+-+++-++++",
                "+++++-++++",
                "++------++",
                "+++++-++++",
                "+++++-++++",
        };
        String words = "LONDON;DELHI;ICELAND;ANKARA";

        String[] ans = {
                "+L++++++++",
                "+O++++++++",
                "+N++++++++",
                "+DELHI++++",
                "+O+++C++++",
                "+N+++E++++",
                "+++++L++++",
                "++ANKARA++",
                "+++++N++++",
                "+++++D++++",
        };
        Assertions.assertArrayEquals(ans, crosswordPuzzle(grid, words));
    }

    @Test
    void test2() {
        String[] grid = {
                "++++++-+++",
                "++------++",
                "++++++-+++",
                "++++++-+++",
                "+++------+",
                "++++++-+-+",
                "++++++-+-+",
                "++++++++-+",
                "++++++++-+",
                "++++++++-+",
        };
        String words = "ICELAND;MEXICO;PANAMA;ALMATY";
        String[] ans = {
                "++++++I+++",
                "++MEXICO++",
                "++++++E+++",
                "++++++L+++",
                "+++PANAMA+",
                "++++++N+L+",
                "++++++D+M+",
                "++++++++A+",
                "++++++++T+",
                "++++++++Y+",
        };
        Assertions.assertArrayEquals(ans, crosswordPuzzle(grid, words));
    }
}
