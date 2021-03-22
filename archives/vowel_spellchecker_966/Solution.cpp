/* 996. Vowel Spellchecker
 * https://leetcode.com/problems/vowel-spellchecker/
 * --
 * Zhiyong
 * 2021-03-22
 */

/*
Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.

For a given query word, the spell checker handles two categories of spelling mistakes:

Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
In addition, the spell checker operates under the following precedence rules:

When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
When the query matches a word up to capitlization, you should return the first such match in the wordlist.
When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
If the query has no matches in the wordlist, you should return the empty string.
Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].

 

Example 1:

Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
 

Note:

1 <= wordlist.length <= 5000
1 <= queries.length <= 5000
1 <= wordlist[i].length <= 7
1 <= queries[i].length <= 7
All strings in wordlist and queries consist only of english letters.
*/

class Solution {
    // convert an upper case vowel to '*'.
    static char towild(char c) {
        switch (c) {
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
                return '*';
            default:
                return c;
        }
    }
public:
    vector<string> spellchecker(vector<string>& wordlist, vector<string>& queries) {
        unordered_set<string> orig; // original words
        unordered_map<string, vector<string>> cap; // capitalized words -> original words
        unordered_map<string, vector<string>> wild; // key = replaced vowels with '*'
        
        for (auto&& w : wordlist) {
            orig.insert(w);
            
            string W = w;
            for (auto & c: W) c = toupper(c);
            cap[W].push_back(w);            
            
            for (auto & c: W) c = towild(c);
            wild[W].push_back(w);
        }
        
        vector<string> res;
        res.reserve(queries.size());
        
        for (auto&& q : queries) {
            if (orig.count(q)) {
                res.push_back(q);
                continue;
            }
            
            for (auto & c: q) c = toupper(c);
            if (cap.count(q)) {
                res.push_back(cap[q][0]);
                continue;
            }
            
            for (auto & c: q) c = towild(c);
            if (wild.count(q)) {
                res.push_back(wild[q][0]);
                continue;
            }
            
            res.push_back("");
        }
        
        return res;
    }
};
