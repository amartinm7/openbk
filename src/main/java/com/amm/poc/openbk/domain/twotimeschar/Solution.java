package com.amm.poc.openbk.domain.twotimeschar;

/**
 * given a string S, returns a single-character string: the single-character that occurs twice.
 *
 * examples:
 * 1. Given "aba", the function should return "a".
 * 2. Given "zz", the function should return "z".
 * 3. Given "codility", the function should return "i".
 */
class Solution {

  public static void main (String[] args) {
    new Solution().solution("codility");
  }

  public String solution(String S) {
    // reserves space for 26 chars in english
    // every space is for one char
    // the position 0 in the array is the 'a' and so on
    // the 'a' represent a position in the UNICODE code
    // 'b' - 'a' substract the code in UNICODE table, so 100 - 99 is 1, which is the position in the array
    // 'c' - 'a' substract the code in UNICODE table, so 101 - 99 is 2, which is the position in the array
    // everytime that we iterate over the array, we add a 1 to the position on the array.
    // and we check if the position has a stored value more than 1. In that we have finished. This is the result.
    int[] charCount = new int[26];
    for (char c : S.toCharArray()) {
      charCount[c - 'a']++;
      if (charCount[c - 'a'] > 1) {
        return String.valueOf(c);
      }
    }
    return "";
  }
}