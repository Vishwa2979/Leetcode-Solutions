class Solution {
    public int largestInteger(int[] nums, int k) {
        int n=nums.length;
        if(k==1){
            int ans=-1;
            for(int i=0;i<n;i++){
                int count=0;
                for(int j=0;j<n;j++){
                    if(nums[i]==nums[j]) count++;
                }
                if(count==1) ans=Math.max(ans,nums[i]);
            }
            return ans;
        }
        if(k==n){
            int ans=nums[0];
            for(int i=1;i<n;i++){
                ans=Math.max(ans,nums[i]);
            }
            return ans;
        }
        int ans=-1;
        int cFirst=0;
        int cLast=0;

        for(int i=0;i<n;i++){
            if(nums[i]==nums[0]) cFirst++;
            if(nums[i]==nums[n-1]) cLast++;
        }
        if(cFirst==1) ans=nums[0];
        if(cLast==1) ans=Math.max(ans,nums[n-1]);
        return ans;
    }
}