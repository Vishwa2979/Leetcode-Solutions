class Solution {
    public boolean checkDivisibility(int n) {
        int original=n;
        int product=1;
        int sum=0;
        while(n>0){
            int ldigit=n%10;
            product=product*ldigit;
            sum=sum+ldigit;
            n/=10;
        }
        return original%(sum+product)==0;
    }
}