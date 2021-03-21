/* 1801. Number of Orders in the Backlog
 * https://leetcode.com/problems/number-of-orders-in-the-backlog/
 * --
 * Zhiyong
 * 2021-03-21
 */

/*
You are given a 2D integer array orders, where each orders[i] = [pricei, amounti, orderTypei] denotes that amounti orders have been placed of type orderTypei at the price pricei. The orderTypei is:

0 if it is a batch of buy orders, or
1 if it is a batch of sell orders.
Note that orders[i] represents a batch of amounti independent orders with the same price and order type. All orders represented by orders[i] will be placed before all orders represented by orders[i+1] for all valid i.

There is a backlog that consists of orders that have not been executed. The backlog is initially empty. When an order is placed, the following happens:

If the order is a buy order, you look at the sell order with the smallest price in the backlog. If that sell order's price is smaller than or equal to the current buy order's price, they will match and be executed, and that sell order will be removed from the backlog. Else, the buy order is added to the backlog.
Vice versa, if the order is a sell order, you look at the buy order with the largest price in the backlog. If that buy order's price is larger than or equal to the current sell order's price, they will match and be executed, and that buy order will be removed from the backlog. Else, the sell order is added to the backlog.
Return the total amount of orders in the backlog after placing all the orders from the input. Since this number can be large, return it modulo 109 + 7.

Constraints:

1 <= orders.length <= 105
orders[i].length == 3
1 <= pricei, amounti <= 109
orderTypei is either 0 or 1.
*/

class Solution {

public:
    int getNumberOfBacklogOrders(vector<vector<int>>& orders) {

        using Order = tuple<int, int, int>;

        // backlog for buy orders
        priority_queue<Order, vector<Order>> buyLog;

        // backlog for sell orders
        auto orderGreater = [](const Order& a, const Order& b) {
            return get<0>(a) > get<0>(b);
        };
        priority_queue<Order, vector<Order>, decltype(orderGreater)> sellLog(orderGreater);

        for (auto&& order : orders) {
            // orders[i] = [pricei, amounti, orderTypei]
            // 0 = buy
            // 1 = sell

            int price = order[0], amount = order[1], type = order[2];
            //cout << "process order: " << price << ' ' << amount << ' ' << type << endl;

            while (amount > 0) {
                if (type == 0) {
                    if (sellLog.empty())
                        break;

                    // order on the heap
                    int p, a, o;
                    tie(p, a, o) = sellLog.top();

                    if (p <= price) {
                        if (a <= amount) {
                            amount -= a;
                            a = 0;
                        } else {
                            //cout << "reduce backlog order (" << p << ' ' << a << ' ' << o << ") by " << amount << endl;
                            a -= amount;
                            amount = 0;
                            //cout << "sell log top amount: " << get<1>(sellLog.top()) << endl;
                        }

                        // pq top is constant; we can't modify the order amount in place, so pop and push.
                        sellLog.pop();
                        if (a > 0)
                            sellLog.push({p, a, o});
                    } else {
                        break;
                    }
                } else {
                    if (buyLog.empty())
                        break;

                    // order on the heap
                    int p, a, o;
                    tie(p, a, o) = buyLog.top();

                    if (p >= price) {
                        if (a <= amount) {
                            amount -= a;
                            a = 0;
                        } else {
                            //cout << "reduce backlog order (" << p << ' ' << a << ' ' << o << ") by " << amount << endl;
                            a -= amount;
                            amount = 0;
                        }

                        // pq top is constant; we can't modify the order amount in place, so pop and push.
                        buyLog.pop();
                        if (a > 0)
                            buyLog.push({p, a, o});
                    } else {
                        break;
                    }
                }
            }

            // put remained order amount to backlog
            if (amount > 0) {
                if (type == 0) {
                    buyLog.push({price, amount, type});
                } else {
                    sellLog.push({price, amount, type});
                }
            }
        }

        // sum order amounts

        unsigned long long ans = 0;
        int MOD = (int)(1e9 + 7);

        while (buyLog.size()) {
            ans = (ans + get<1>(buyLog.top())) % MOD;
            buyLog.pop();
        }

        while (sellLog.size()) {
            ans = (ans + get<1>(sellLog.top())) % MOD;
            sellLog.pop();
        }

        return ans;
    }
};
