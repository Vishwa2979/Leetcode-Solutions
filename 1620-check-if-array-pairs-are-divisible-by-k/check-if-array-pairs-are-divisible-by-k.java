class Solution {
    public boolean canArrange(int[] arr,int k) {
        int[] rem=new int[k];

        for(int num:arr){
            int r=num%k;
            if(r<0)r+=k;
            rem[r]++;
        }

        if(rem[0]%2!=0)return false;

        for(int i=1;i<k;i++){
            if(rem[i]!=rem[k-i])return false;
        }

        return true;
    }
}