/* 478. Generate Random Point in a Circle
 * https://leetcode.com/problems/generate-random-point-in-a-circle/
 *
 * Note: the generated points have to be uniformly distributed.
 *
 * --
 * Zhiyong
 * 2021-03-17
 */

class Solution {
    double r, cx, cy;
public:
    Solution(double radius, double x_center, double y_center) : r(radius), cx(x_center), cy(y_center) {
        
    }
    
    vector<double> randPoint_not_well_distributed() {
        double d = r * (double)rand() / RAND_MAX; // dist to center. XXX this is the problem
        double a = (double)rand() / RAND_MAX * 2 * 3.14159; // angle in radian
        double dx = d * cos(a);
        double dy = d * sin(a);
        return { cx + dx, cy + dy };
    }
    
    // filter a square. easy and fast. accepted
    vector<double> randPoint_filter_a_square() {
        double r2 = r * r;
        while (true) {
            double x = r * 2 * ((double)rand() / RAND_MAX - 0.5);
            double y = r * 2 * ((double)rand() / RAND_MAX - 0.5);
            if (x * x + y * y < r2)
                return { x + cx, y + cy };           
        }
    }
    
    // elegant, slightly slower then filtering a square (possibly because sqrt(), cos() and sin() are expensive).
    vector<double> randPoint() {
        // Too see why the distance should not be uniformed but sqrt-ed:
        // https://leetcode.com/problems/generate-random-point-in-a-circle/discuss/1113679/Python-Polar-coordinates-explained-with-diagrams-and-math
        double d = r * sqrt((double)rand() / RAND_MAX); // dist to center
        double a = (double)rand() / RAND_MAX * 2 * 3.14159; // angle in radian
        double dx = d * cos(a);
        double dy = d * sin(a);
        return { cx + dx, cy + dy };
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(radius, x_center, y_center);
 * vector<double> param_1 = obj->randPoint();
 */
