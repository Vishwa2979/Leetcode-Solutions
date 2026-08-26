class Solution {
    public int subarraySum(int[] nums, int k) {
        int count=0;
        int currentSum=0;

        Map<Integer, Integer> prefixSumMap=new HashMap<>();
        prefixSumMap.put(0,1);

        for(int num: nums){
            currentSum+=num;
            if(prefixSumMap.containsKey(currentSum-k)){
                count+=prefixSumMap.get(currentSum-k);
            }
            prefixSumMap.put(currentSum,prefixSumMap.getOrDefault(currentSum,0)+1);
        }
        return count;
    }
}