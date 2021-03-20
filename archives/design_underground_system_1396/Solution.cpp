/* 1396. Design Underground System
https://leetcode.com/problems/design-underground-system/

Implement the UndergroundSystem class:

void checkIn(int id, string stationName, int t)
A customer with a card id equal to id, gets in the station stationName at time t.
A customer can only be checked into one place at a time.
void checkOut(int id, string stationName, int t)
A customer with a card id equal to id, gets out from the station stationName at time t.
double getAverageTime(string startStation, string endStation)
Returns the average time to travel between the startStation and the endStation.
The average time is computed from all the previous traveling from startStation to endStation that happened directly.
Call to getAverageTime is always valid.
You can assume all calls to checkIn and checkOut methods are consistent. If a customer gets in at time t1 at some station, they get out at time t2 with t2 > t1. All events happen in chronological order.

--
Zhiyong
2021-03-20
*/

class UndergroundSystem {
public:
    UndergroundSystem() {
        
    }
    
    void checkIn(int id, string stationName, int t) {
        checkins[id] = { stationName, t };
    }
    
    void checkOut(int id, string stationName, int t) {
        auto [startStation, t1] = checkins[id];
        string key = min(startStation, stationName) + '-' + max(startStation, stationName);
        times[key].push_back(t - t1);
    }
    
    double getAverageTime(string startStation, string endStation) {
        string key = min(startStation, endStation) + '-' + max(startStation, endStation);
        auto& arr = times[key];
        return (double)reduce(arr.begin(), arr.end()) / arr.size();
    }
    
private:
    // checkins[id] = {stationName, t}
    unordered_map<int, pair<string, int>> checkins;
    
    // times[startStation + '-' + endStation] = travel times
    unordered_map<string, vector<int>> times;
};

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem* obj = new UndergroundSystem();
 * obj->checkIn(id,stationName,t);
 * obj->checkOut(id,stationName,t);
 * double param_3 = obj->getAverageTime(startStation,endStation);
 */
