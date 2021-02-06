package perfect_rectangle;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Group vertical edges by x position. Scan the groups from left to right. In the middle, the entering amount
 * should match the exiting amount.
 *
 * Created by Zhiyong Pan on 2021-01-03.
 */
public class Solution {
    /**
     * Describe a vertical edge.
     */
    private static class Edge {
        int x;
        int bottom;
        int top;

        public Edge(int x, int bottom, int top) {
            this.x = x;
            this.bottom = bottom;
            this.top = top;
        }

        int length() {
            return top - bottom;
        }
    }

    /**
     * Describe info of a group of vertical edges.
     */
    private static class EdgeGroupSummary {
        int x;
        int bottom;
        int top;
        /** accumulated length of all edges in this group */
        int length;

        void init(Edge e) {
            x = e.x;
            bottom = e.bottom;
            top = e.top;
            length = e.length();
        }

        public boolean match(EdgeGroupSummary s) {
            return x == s.x && bottom == s.bottom && top == s.top && length == s.length;
        }
    }

    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles.length < 1)
            return false;

        // group vertical edges by category (entering or exiting) and x position,
        // and sort them by bottom position.
        LinkedList<LinkedList<Edge>> leftEdges = collectLeftEdges(rectangles);
        LinkedList<LinkedList<Edge>> rightEdges = collectRightEdges(rectangles);

        // list iterators
        Iterator<LinkedList<Edge>> enterGroupItr = leftEdges.iterator();
        Iterator<LinkedList<Edge>> exitGroupItr = rightEdges.iterator();

        // consume the first entering group
        EdgeGroupSummary firstEnterSum = new EdgeGroupSummary();
        if (!inspectGroup(enterGroupItr.next(), firstEnterSum))
            return false;
        if (firstEnterSum.top - firstEnterSum.bottom > firstEnterSum.length)
            return false;

        //
        // in each iteration, consume one exiting group and one entering group (if any).
        //
        // rule: the amount of entering should be the same as of exiting, unless there is no entering.
        //

        EdgeGroupSummary enterSum = new EdgeGroupSummary(),
                exitSum = new EdgeGroupSummary();

        int remainedLen = firstEnterSum.length;

        while (exitGroupItr.hasNext()) {

            // consume the next exiting group
            if (!inspectGroup(exitGroupItr.next(), exitSum)
                    || exitSum.bottom < firstEnterSum.bottom
                    || exitSum.top > firstEnterSum.top)
                return false;
            remainedLen -= exitSum.length;
            assert remainedLen >= 0;

            // consume the next entering group, if any
            if (enterGroupItr.hasNext()) {
                if (!inspectGroup(enterGroupItr.next(), enterSum)
                        || enterSum.bottom < firstEnterSum.bottom
                        || enterSum.top > firstEnterSum.top
                        || !enterSum.match(exitSum))
                    return false;
                remainedLen += enterSum.length;
                assert remainedLen <= firstEnterSum.length;

                // each time there are new rectangles entering,
                // they should precisely take over from exited ones.
                if (remainedLen != firstEnterSum.length)
                    break;
            } else if (remainedLen != 0) {
                // if there are no more rectangles entering,
                // the exiting must be clean.
                break;
            }
        }

        return !enterGroupItr.hasNext() && !exitGroupItr.hasNext() && remainedLen == 0;
    }

    /**
     * Inspect a group of vertical edges, resulting a summary.
     * @return false if overlaps detected.
     */
    private boolean inspectGroup(LinkedList<Edge> edges, EdgeGroupSummary output) {
        Iterator<Edge> itr = edges.iterator();
        Edge e = itr.next();
        output.init(e);

        while (itr.hasNext()) {
            e = itr.next();
            assert e.x == output.x; // all input edges must be at the same x position!
            assert e.bottom >= output.bottom; // all input edges must have been sorted by bottom!
            if (e.bottom < output.top) { // overlap detected!
                return false;
            }
            output.top = e.top;
            output.length += e.length();
        }

        return true;
    }

    /**
     * Group all left edges by x position. Each group is sorted by bottom position.
     * @param rectangles
     * @return
     */
    private LinkedList<LinkedList<Edge>> collectLeftEdges(int[][] rectangles) {
        LinkedList<LinkedList<Edge>> edges;
        edges = new LinkedList<>();
        // sort rectangles by their left and bottom
        Arrays.sort(rectangles, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        LinkedList<Edge> currGroup = new LinkedList<>();
        int currGroupX = rectangles[0][0];

        for (int i = 0; i < rectangles.length; ++i) {
            int[] a = rectangles[i];
            if (currGroupX != a[0]) {
                // start a new group
                currGroupX = a[0];
                edges.add(currGroup);
                currGroup = new LinkedList<>();
            }
            currGroup.add(new Edge(a[0], a[1], a[3]));
        }
        edges.add(currGroup);
        return edges;
    }

    /**
     * Group all right edges by x position. Each group is sorted by bottom position.
     * @param rectangles
     * @return
     */
    private LinkedList<LinkedList<Edge>> collectRightEdges(int[][] rectangles) {
        LinkedList<LinkedList<Edge>> edges;
        edges = new LinkedList<>();
        // sort rectangles by their right and bottom
        Arrays.sort(rectangles, (a, b) -> a[2] != b[2] ? a[2] - b[2] : a[1] - b[1]);

        LinkedList<Edge> currGroup = new LinkedList<>();
        int currGroupX = rectangles[0][2];

        for (int i = 0; i < rectangles.length; ++i) {
            int[] a = rectangles[i];
            if (currGroupX != a[2]) {
                // start a new group
                currGroupX = a[2];
                edges.add(currGroup);
                currGroup = new LinkedList<>();
            }
            currGroup.add(new Edge(a[2], a[1], a[3]));
        }
        edges.add(currGroup);
        return edges;
    }
}