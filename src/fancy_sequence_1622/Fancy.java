package fancy_sequence_1622;

import java.util.HashMap;

/**
 * A barely accepted solution (almost Time Limit Exceeded).
 *
 * A number is stored as its "current value" and how many computations have been applied to it.
 *
 * Created by Zhiyong Pan on 2021-01-16.
 */
public class Fancy {
    // Since 1 <= val, inc, m <= 100, we can add 128 to m to distinguish it from inc.
    final int BITFLAG_MUL = 128;

    // Record all addAll and multAll commands.
    // For an element e:
    // if (e & BITFLAG_MUL) != 0 then it's a multAll command;
    // otherwise, it's an addAll command;
    int[] cmds = new int[1 + 100_000]; // 10^5 calls at most

    // Count of all commands.
    int cmdCnt = 0;

    // key = number index
    HashMap<Integer, Num> nums = new HashMap<>(100_000);

    // Count of all numbers, i.e., appending times.
    int numCnt = 0;

    public Fancy() {
    }

    public void append(int val) {
        nums.put(numCnt++, new Num(val, cmdCnt));
    }

    public void addAll(int inc) {
        cmds[cmdCnt++] = inc;
    }

    public void multAll(int m) {
        cmds[cmdCnt++] = m | BITFLAG_MUL;
    }

    public int getIndex(int idx) {
        if (idx >= numCnt)
            return -1;

        Num ans = nums.get(idx);

        // apply math operations
        for (int i = ans.appliedCmdCnt; i < cmdCnt; ++i) {
            if (cmds[i] <= 100)
                ans.val += cmds[i];
            else
                ans.val = (ans.val * (cmds[i] ^ BITFLAG_MUL)) % 1_000_000_007;
        }

        ans.appliedCmdCnt = cmdCnt;
        return (int) ans.val;
    }
}

/**
 * Represent a number in the sequence.
 */
class Num {
    /** Current value. */
    long val;
    /** How many add/mul commands have been applied to it? */
    int appliedCmdCnt;

    public Num(int val, int cmdCnt) {
        this.val = val;
        this.appliedCmdCnt = cmdCnt;
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */
