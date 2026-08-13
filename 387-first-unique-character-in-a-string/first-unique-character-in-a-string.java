class Solution {
    public int firstUniqChar(String s) {
        Map<Character,Integer> counts=new LinkedHashMap<>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        for(Map.Entry<Character,Integer>entry:counts.entrySet()){
            if(entry.getValue()==1)
            return s.indexOf(entry.getKey());
        }
        return -1;
    }
}