package data_structure.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A framework (or boilerplate) for graph BFS. The API is designed such that it does not assume how the graph is presented
 * or how each node is be processed. The only requirement is that graph nodes are identified by integer numbers.
 *
 * Notice that it does not keep track of the visiting state. Instead, it is up to the caller.
 *
 * To see an application of this API, check out the FindTheNearestClone class below, which performs multiple BFS
 * simultaneously from different nodes.
 *
 * Multiple source BFS is quicker to find the shortest path between two or more nodes than single source BFS.
 *
 * There is also an application doing BFS on a grid below, the class is named ConnectedCellInAGrid.
 *
 * Created by Zhiyong Pan on 2021-02-09.
 */
public class GraphBFS {
    public interface DataProvider {
        List<Integer> getNeighbours(int id);
    }

    public interface TraversalListener {
        /**
         * @param currNode the new found node's id.
         * @param prevNode from which node does the current node directly reached?
         * @param originNode the origin node from which this traversal was started.
         * @param currLayerIdx the origin node is at layer 0.
         * @return
         */
        boolean onNode(int currNode, int prevNode, int originNode, int currLayerIdx);
    }


    private int originNode;
    private DataProvider dataProvider;
    private TraversalListener traversalListener;
    private LinkedList<Integer> currLayer;
    private int currLayerIdx;

    public GraphBFS(DataProvider dataProvider, TraversalListener traversalListener, int originNode) {
        this.dataProvider = dataProvider;
        this.traversalListener = traversalListener;
        this.originNode = originNode;
        currLayer = new LinkedList<>();
        currLayer.add(originNode);
        currLayerIdx = 0;
    }

    public List<Integer> nextLayer() {
        LinkedList<Integer> nextLayer = new LinkedList<>();
        ++currLayerIdx;

        for (int a : currLayer) {
            List<Integer> neighbours = dataProvider.getNeighbours(a);
            if (neighbours != null) {
                for (int b : neighbours) {
                    if (originNode != b && traversalListener.onNode(b, a, this.originNode, currLayerIdx))
                        nextLayer.add(b);
                }
            }
        }

        currLayer = nextLayer;
        return currLayer;
    }

    public boolean ended() {
        return currLayer.isEmpty();
    }
}

