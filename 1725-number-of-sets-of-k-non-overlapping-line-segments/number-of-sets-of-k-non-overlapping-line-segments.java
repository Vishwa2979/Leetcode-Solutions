class Solution {
    public int numberOfSets(int n,int k) {
        long mod=1000000007;
        long ans=1;

        for(int i=1;i<=2*k;i++){
            ans=ans*(n+k-i)%mod;
            ans=ans*pow(i,mod-2,mod)%mod;
        }

        return (int)ans;
    }

    long pow(long a,long b,long mod){
        long ans=1;
        while(b>0){
            if(b%2==1) ans=ans*a%mod;
            a=a*a%mod;
            b/=2;
        }
        return ans;
    }
}