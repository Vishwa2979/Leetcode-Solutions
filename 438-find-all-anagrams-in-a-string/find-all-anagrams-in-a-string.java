class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        
        int sLen = s.length();
        int pLen = p.length();
        
        if (sLen < pLen) {
            return result;
        }
        
        Map<Character, Integer> pMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();
        
        for (int i = 0; i < pLen; i++) {
            pMap.put(p.charAt(i), pMap.getOrDefault(p.charAt(i), 0) + 1);
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        
        if (pMap.equals(sMap)) {
            result.add(0);
        }
        
        for (int i = pLen; i < sLen; i++) {
            char inChar = s.charAt(i);
            sMap.put(inChar, sMap.getOrDefault(inChar, 0) + 1);
            
            char outChar = s.charAt(i - pLen);
            if (sMap.get(outChar) == 1) {
                sMap.remove(outChar);
            } else {
                sMap.put(outChar, sMap.get(outChar) - 1);
            }
            
            if (pMap.equals(sMap)) {
                result.add(i - pLen + 1);
            }
        }
        
        return result;
    }
}