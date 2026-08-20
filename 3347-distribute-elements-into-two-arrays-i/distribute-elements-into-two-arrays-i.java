class Solution {
    public int[] resultArray(int[] nums) {
        Stack<Integer> st1 = new Stack<>();
        Stack<Integer> st2 = new Stack<>();

        st1.push(nums[0]);
        st2.push(nums[1]);

        for(int i=2;i<nums.length;i++){
            if(st1.peek()>st2.peek()){
                st1.push(nums[i]);
            }else{
                st2.push(nums[i]);
            }
        }
            int[] ans = new int[nums.length];
            int k=0;
            
            for(int x:st1){
                ans[k]=x;
                k++;
            }
            for(int x:st2){
                ans[k]=x;
                k++;
            }
            return ans;
    }
}