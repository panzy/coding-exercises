/* 1797. Design Authentication Manager
 * https://leetcode.com/contest/biweekly-contest-48/problems/design-authentication-manager/
 * --
 * Zhiyong
 * 2021-03-20
 */

/*
There is an authentication system that works with authentication tokens. For each session, the user will receive a new authentication token that will expire timeToLive seconds after the currentTime. If the token is renewed, the expiry time will be extended to expire timeToLive seconds after the (potentially different) currentTime.

Implement the AuthenticationManager class:

AuthenticationManager(int timeToLive) constructs the AuthenticationManager and sets the timeToLive.
generate(string tokenId, int currentTime) generates a new token with the given tokenId at the given currentTime in seconds.
renew(string tokenId, int currentTime) renews the unexpired token with the given tokenId at the given currentTime in seconds. If there are no unexpired tokens with the given tokenId, the request is ignored, and nothing happens.
countUnexpiredTokens(int currentTime) returns the number of unexpired tokens at the given currentTime.
Note that if a token expires at time t, and another action happens on time t (renew or countUnexpiredTokens), the expiration takes place before the other actions.
*/

class AuthenticationManager {
public:
    AuthenticationManager(int timeToLive) : ttl(timeToLive) {

    }

    void generate(string tokenId, int currentTime) {
        tokens[tokenId] = currentTime + ttl;
    }

    void renew(string tokenId, int currentTime) {
        if (tokens[tokenId] > currentTime)
            tokens[tokenId] = currentTime + ttl;
    }

    int countUnexpiredTokens(int currentTime) {
        int ans = 0;
        for (auto&& i : tokens) {
            if (i.second > currentTime)
                ++ans;
        }
        return ans;
    }

private:
    int ttl;
    // tokens[tokenId] = expiration time
    unordered_map<string, int> tokens;
};

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * AuthenticationManager* obj = new AuthenticationManager(timeToLive);
 * obj->generate(tokenId,currentTime);
 * obj->renew(tokenId,currentTime);
 * int param_3 = obj->countUnexpiredTokens(currentTime);
 */
