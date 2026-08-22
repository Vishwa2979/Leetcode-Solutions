class Solution {
    public boolean canArrange(int[] arr,int k) {
        HashMap<Integer,Integer> map=new HashMap<>();

        for(int num:arr){
            int rem=((num%k)+k)%k;
            map.put(rem,map.getOrDefault(rem,0)+1);
        }

        for(int rem:map.keySet()){
            if(rem==0){
                if(map.get(rem)%2!=0) return false;
            }else if(rem*2==k){
                if(map.get(rem)%2!=0) return false;
            }else{
                if(!map.get(rem).equals(map.getOrDefault(k-rem,0)))
                    return false;
            }
        }

        return true;
    }
}