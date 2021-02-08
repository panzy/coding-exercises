package peeking_iterator_284;

import java.util.Iterator;

/**
 * 284. Peeking Iterator
 * https://leetcode.com/problems/peeking-iterator/
 *
 * Created by Zhiyong Pan on 2021-02-08.
 */
class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> itr;
    Integer peekedVal;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        itr = iterator;
        peekedVal = null;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (peekedVal == null) {
            peekedVal = itr.next();
        }
        return peekedVal;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (peekedVal != null) {
            Integer res = peekedVal;
            peekedVal = null;
            return res;
        } else {
            return itr.next();
        }
    }

    @Override
    public boolean hasNext() {
        if (peekedVal != null) {
            return true;
        } else {
            return itr.hasNext();
        }
    }
}
