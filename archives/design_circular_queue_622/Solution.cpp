/* 622. Design Circular Queue
 * https://leetcode.com/problems/design-circular-queue/
 * --
 * Zhiyong 2021-04-04
 */

class MyCircularQueue {
    vector<int> data;
    int size;
    
    // front := data[s]
    // back := data[e - 1]
    // is full := s == e
    // is empty := s == -1
    int s, e;
public:
    MyCircularQueue(int k) : size{k}, s{-1}, e{0} {
        data.resize(k);
    }
    
    bool enQueue(int value) {
        if (s == e) return false;
        data[e] = value;
        if (s == -1) s = e;
        e = (e + 1) % size;
        return true;
    }
    
    bool deQueue() {
        if (s == -1) return false;
        s = (s + 1) % size;
        
        if (s == e) {
            // must reset s to -1, otherwise can't tell empty from full
            s = -1;
            e = 0;
        }
        
        return true;
    }
    
    int Front() {
        return s == -1 ? -1 : data[s];
    }
    
    int Rear() {
        // data[e - 1] is the rear
        return s == -1 ? -1 : data[(e - 1 + size) % size];
    }
    
    bool isEmpty() {
        return s == -1;
    }
    
    bool isFull() {
        return s == e;
    }
};

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue* obj = new MyCircularQueue(k);
 * bool param_1 = obj->enQueue(value);
 * bool param_2 = obj->deQueue();
 * int param_3 = obj->Front();
 * int param_4 = obj->Rear();
 * bool param_5 = obj->isEmpty();
 * bool param_6 = obj->isFull();
 */
