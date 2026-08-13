class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashMap<Integer,Boolean> map = new HashMap<>();
        List<Integer> resultList=new ArrayList<>();
        for(int num:nums1){
            map.put(num,false);
        }
        for(int num:nums2){
            if(map.containsKey(num) && !map.get(num)){
                resultList.add(num);
                map.put(num,true);
            }
        }
        int[] result =new int[resultList.size()];
        for(int i=0;i<resultList.size();i++){
            result[i]=resultList.get(i);
        }
        return result;
    }
}