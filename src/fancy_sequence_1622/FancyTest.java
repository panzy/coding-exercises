package fancy_sequence_1622;

import _lib.IntArrays;
import _lib.IntegerArrays;
import _lib.StringArrays;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Created by Zhiyong Pan on 2021-01-16.
 */
public class FancyTest {

    private Integer[] executeCmds(String[] cmds, int[][] args) {
        Integer[] output = new Integer[cmds.length];

        Fancy fancy = new Fancy();
        output[0] = null;

        for (int i = 1; i < cmds.length; ++i) {
            switch (cmds[i]) {
                case "append":
                    fancy.append(args[i][0]);
                    output[i] = null;
                    break;
                case "addAll":
                    fancy.addAll(args[i][0]);
                    output[i] = null;
                    break;
                case "multAll":
                    fancy.multAll(args[i][0]);
                    output[i] = null;
                    break;
                case "getIndex":
                    output[i] = fancy.getIndex(args[i][0]);
                    break;
            }
        }

        return output;
    }

    @Test
    void example1() {
        Fancy fancy = new Fancy();
        fancy.append(2);   // fancy sequence: [2]
        fancy.addAll(3);   // fancy sequence: [2+3] -> [5]
        fancy.append(7);   // fancy sequence: [5, 7]
        fancy.multAll(2);  // fancy sequence: [5*2, 7*2] -> [10, 14]
        Assertions.assertEquals(10, fancy.getIndex(0)); // return 10
        fancy.addAll(3);   // fancy sequence: [10+3, 14+3] -> [13, 17]
        fancy.append(10);  // fancy sequence: [13, 17, 10]
        fancy.multAll(2);  // fancy sequence: [13*2, 17*2, 10*2] -> [26, 34, 20]
        Assertions.assertEquals(26, fancy.getIndex(0)); // return 26
        Assertions.assertEquals(34, fancy.getIndex(1)); // return 34
        Assertions.assertEquals(20, fancy.getIndex(2)); // return 20
    }

    @Test
    void example2() {
        String[] cmds = {
                "Fancy", "append",
                "multAll", "multAll", "multAll", "multAll", "multAll", "multAll", "multAll", "multAll",
                "multAll", "multAll", "multAll", "multAll", "multAll", "multAll", "multAll", "multAll",
                "getIndex"
        };

        int[][] args = {
                {}, {1},
                {2}, {2}, {2}, {2}, {2}, {2}, {2}, {2},
                {2}, {2}, {2}, {2}, {2}, {2}, {2}, {2},
                {0},
        };

        Integer[] output = executeCmds(cmds, args);

        Assertions.assertEquals((int) Math.pow(2, 16), output[cmds.length - 1]);
    }

    @Test
    void test11() {
        String[] cmds = {"Fancy", "addAll", "getIndex"};

        int[][] args = {{}, {1}, {0}};

        Integer[] expected = {null, null, -1};

        Integer[] output = executeCmds(cmds, args);

        Assertions.assertArrayEquals(expected, output);
    }

    @Test
    void test64() {
        String[] cmds = {
                "Fancy", "append", "append", "getIndex", "append", "getIndex", "addAll", "append", "getIndex", "getIndex",
                "append", "append", "getIndex", "append", "getIndex", "append", "getIndex", "append", "getIndex", "multAll",
                "addAll", "getIndex", "append", "addAll", "getIndex", "multAll", "getIndex", "multAll", "addAll",
                "addAll", "append", "multAll", "append", "append", "append", "multAll", "getIndex", "multAll",
                "multAll", "multAll", "getIndex", "addAll", "append", "multAll", "addAll", "addAll", "multAll",
                "addAll", "addAll", "append", "append", "getIndex"
        };

        int[][] args = {
                {}, {12}, {8}, {1}, {12}, {0}, {12}, {8}, {2}, {2}, {4}, {13}, {4}, {12}, {6}, {11}, {1}, {10}, {2},
                {3}, {1}, {6}, {14}, {5}, {6}, {12}, {3}, {12}, {15}, {6}, {7}, {8}, {13}, {15}, {15}, {10}, {9}, {12},
                {12}, {9}, {9}, {9}, {9}, {4}, {8}, {11}, {15}, {9}, {1}, {4}, {10}, {9}
        };

        Integer[] expected = {
                null, null, null, 8, null, 12, null, null, 24, 24, null, null, 4, null, 12, null, 20, null, 24, null,
                null, 37, null, null, 42, null, 360, null, null, null, null, null, null, null, null, null, 220560, null,
                null, null, 285845760, null, null, null, null, null, null, null, null, null, null, 150746316
        };

        Integer[] output = executeCmds(cmds, args);

        Assertions.assertArrayEquals(expected, output);
    }

    @Test
    void test70() {
        String[] cmds = {
                "Fancy", "append", "getIndex", "multAll", "multAll", "getIndex", "addAll", "append", "append", "getIndex", "append", "append", "addAll", "getIndex", "multAll", "addAll", "append", "addAll", "getIndex", "getIndex", "multAll", "multAll", "multAll", "append", "addAll", "getIndex", "getIndex", "getIndex", "append", "getIndex", "addAll", "multAll", "append", "multAll", "addAll", "getIndex", "append", "append", "addAll", "getIndex", "multAll", "getIndex", "addAll", "getIndex", "multAll", "addAll", "getIndex", "addAll", "append", "append", "append", "multAll", "multAll", "append", "multAll", "addAll", "getIndex", "addAll", "multAll", "multAll", "multAll", "append", "multAll", "append", "multAll", "addAll", "append", "append", "getIndex", "getIndex", "getIndex", "addAll", "multAll", "multAll", "append", "append", "getIndex", "append", "append", "append", "getIndex", "getIndex"
        };

        int[][] args = {
                {}, {5}, {0}, {14}, {10}, {0}, {12}, {10}, {4}, {2}, {4}, {2}, {1}, {1}, {8}, {11}, {15}, {12}, {0}, {3}, {4}, {11}, {11}, {10}, {8}, {2}, {3}, {0}, {7}, {3}, {2}, {6}, {10}, {6}, {8}, {7}, {9}, {9}, {12}, {0}, {13}, {7}, {3}, {4}, {8}, {14}, {2}, {9}, {9}, {9}, {7}, {5}, {12}, {9}, {3}, {8}, {10}, {14}, {14}, {14}, {6}, {1}, {3}, {11}, {12}, {6}, {7}, {13}, {12}, {5}, {6}, {1}, {11}, {11}, {4}, {9}, {7}, {11}, {1}, {3}, {1}, {0}
        };

        Integer[] expected = {
                null, null, 5, null, null, 700, null, null, null, 4, null, null, null, 11, null, null, null, null, 5727, 63, null, null, null, null, null, 30500, 30500, 2771876, null, 30500, null, null, null, null, null, 332, null, null, null, 99787628, null, 4472, null, 10651007, null, null, 114201606, null, null, null, null, null, null, null, null, null, 401588, null, null, null, null, null, null, null, null, null, null, null, 69515718, 633655703, 831230656, null, null, null, null, null, 715527902, null, null, null, 728131107, 601045500
        };

        Integer[] output = executeCmds(cmds, args);

        Assertions.assertArrayEquals(expected, output);
    }

    @Test
    void test91() {
        String[] cmds = {
                "Fancy", "append", "multAll", "append", "getIndex", "addAll", "append", "append", "getIndex", "append",
                "append", "addAll", "addAll", "getIndex", "append", "getIndex", "getIndex", "addAll", "getIndex",
                "addAll", "getIndex", "getIndex", "getIndex", "append", "addAll", "append", "getIndex", "multAll",
                "append", "append", "addAll", "getIndex", "addAll", "getIndex"
        };

        int[][] args = {
                {}, {2}, {10}, {8}, {0}, {9}, {8}, {10}, {0}, {10},
                {3}, {6}, {3}, {3}, {5}, {4}, {0}, {8}, {3}, {6},
                {7}, {4}, {3}, {1}, {8}, {5}, {3}, {3}, {7}, {5},
                {2}, {3}, {2}, {9}
        };

        Integer[] expected = {
                null,null,null,null,20,null,null,null,29,null,null,null,null,19,null,19,38,null,27,null,-1,33,33,null,null,null,41,null,null,null,null,125,null,11
        };

        Integer[] output = executeCmds(cmds, args);

        Assertions.assertArrayEquals(expected, output);
    }

    @Test
    void test105() throws IOException, ParseException {
        String[] cmds = StringArrays.loadFromJsonFile("./src/fancy_sequence_1622/test-case-105-cmds.json");

        int[][] args = IntArrays.load2DFromJsonFile("./src/fancy_sequence_1622/test-case-105-args.json");

        Integer[] output = executeCmds(cmds, args);

//        Assertions.assertArrayEquals(expected, output);
    }

    @Test
    void test106() throws IOException, ParseException {
        String[] cmds = StringArrays.loadFromJsonFile("./src/fancy_sequence_1622/test-case-106-cmds.json");

        int[][] args = IntArrays.load2DFromJsonFile("./src/fancy_sequence_1622/test-case-106-args.json");

        Integer[] expected = IntegerArrays.loadFromJsonFile("./src/fancy_sequence_1622/test-case-106-expected.json");;

        Integer[] output = executeCmds(cmds, args);

        Assertions.assertArrayEquals(expected, output);
    }
}
