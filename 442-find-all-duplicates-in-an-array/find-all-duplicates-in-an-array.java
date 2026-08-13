class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result=new ArrayList<>();
        Map<Integer,Integer> counts=new HashMap<>();
        
        for(int num:nums){
            if(counts.containsKey(num)){
                result.add(num);
            }else{
                counts.put(num,1);
            }
        }
        return result;
    }
}