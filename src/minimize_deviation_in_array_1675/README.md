# 1675. Minimize Deviation in Array

Hard

https://leetcode.com/problems/minimize-deviation-in-array/

## Notes

I couldn't come up with a solution until peeking a hint.

- Brute force won't do because `2 <= n <= 10^5`.
- In fact, any `O(N^2)` solution won't do.
- The minimized value range `[L, U]` can be very hard to find.
    Because updating `L` by `x2` might invalidate `U` and
    updating `U` by `/2` might invalidate `L`. If each of the
    two bounds can change in each of two directions, how do you
    search the answer in such a 2-D space?
- Fortunately, tricks exist to eliminate one of the changing
    direction, making a linear search possible.

(TODO: revisit)