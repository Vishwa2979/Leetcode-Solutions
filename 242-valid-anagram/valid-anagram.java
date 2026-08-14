class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> countMap = new HashMap<>();

        // Count frequencies in s and decrement for t
        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            countMap.put(charS, countMap.getOrDefault(charS, 0) + 1);
            countMap.put(charT, countMap.getOrDefault(charT, 0) - 1);
        }

        // If all counts are zero, t is an anagram of s
        for (int count : countMap.values()) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }
}